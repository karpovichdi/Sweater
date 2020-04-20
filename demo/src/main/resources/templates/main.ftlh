<#import "macroses/commonMacroses.ftlh" as common>
<#import "macroses/loginMacroses.ftlh" as loginMacros>

<@common.page>
    <div>
        <@loginMacros.logout/>
        <span><a href="/user"> User list</a></span>
    </div>
    <div>
        <form method="post" action="add">
            <input type="text" name="text" placeholder="Введите сообщение" />
            <input type="text" name="tag" placeholder="Тэг">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список сообщений</div>
    <form method="post" action="filter">
        <input type="text" name="filter">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Найти</button>
    </form>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
        </div>
        <#else>
        No messages
    </#list>
</@common.page>