package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.models.entities.User;
import com.example.demo.models.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MailService mailService;

    @Value("${base.url}")
    private String baseUrl;

    public UserService(UserRepository userRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }

    public boolean addUser(User user){
        User userFromDB = userRepository.findUserByUsername(user.getUsername());

        if(userFromDB != null){
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepository.save(user);

        sendMessage(user);

        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findUserByActivationCode(code);

        if(user == null){
            return false;
        }

        user.setActivationCode(null);

        userRepository.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                                 (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged){
            user.setEmail(email);

            if (!StringUtils.isEmpty(email)){
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)){
            user.setPassword(password);
        }

        userRepository.save(user);

        if (isEmailChanged){
            sendMessage(user);
        }
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Sweater. Please, visit next link: " + baseUrl + "/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailService.send(user.getEmail(), "Activation code", message);
        }
    }
}