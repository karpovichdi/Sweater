<#import "macroses/commonMacroses.ftlh" as common>
<#import "macroses/loginMacroses.ftlh" as loginMacros>

<@common.page>
    ${message?ifExists}
    <@loginMacros.login "/login" false/>
</@common.page>