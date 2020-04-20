<#import "macroses/commonMacroses.ftlh" as common>
<#import "macroses/loginMacroses.ftlh" as loginMacros>

<@common.page>
    Add new user
    ${message!""}
    <@loginMacros.login "/registration"/>
</@common.page>