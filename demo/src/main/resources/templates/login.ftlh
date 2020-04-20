<#import "macroses/commonMacroses.ftlh" as common>
<#import "macroses/loginMacroses.ftlh" as loginMacros>

<@common.page>
    Login page
    <@loginMacros.login "/login" />
    <a href="/registration">Add new user</a>
</@common.page>