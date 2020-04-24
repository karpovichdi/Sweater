<#import "macroses/commonMacroses.ftlh" as commonMacroses>

<@commonMacroses.page>

    <#if isCurrentUser>
        <#include "macroses/messageEdit.ftlh"/>
    </#if>

    <#include "macroses/messageList.ftlh"/>

</@commonMacroses.page>