<#import "macroses/commonMacroses.ftlh" as common>
<#import "macroses/loginMacroses.ftlh" as loginMacros>

<@common.page>
    <div class="mb-1">
        Add new user
    </div>
    ${message?ifExists}
    <@loginMacros.login "/registration" true/>
</@common.page>