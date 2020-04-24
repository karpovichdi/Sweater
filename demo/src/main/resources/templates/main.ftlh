<#import "macroses/commonMacroses.ftlh" as commonMacroses>

<@commonMacroses.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form class="form-inline" method="get" action="/main">
                <input class="form-control" type="text" name="filter" value="${filter?ifExists}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <#include "macroses/messageEdit.ftlh"/>
    <#include "macroses/messageList.ftlh"/>

</@commonMacroses.page>