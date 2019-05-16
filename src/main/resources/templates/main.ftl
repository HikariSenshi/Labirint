<#import "parts/common.ftl" as p>

<@p.page "Messages">
<header class="form-row">
    <div class="form-group col-md-6">
        <form class="form-inline" method="get" action="/main">
            <input type="text" name="filter" value="${filter?ifExists}" placeholder="Введите тэг для поиска"/>
            <button type="submit" class="btn btn-primary ml-2">Найти</button>
        </form>
    </div>
</header>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseSender" role="button"
    aria-expanded="false">Добавить новое сообщение
</a>

<section class="collapse" id="collapseSender">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input class="form-control" type="text" name="text" placeholder="Пиши давай">
            </div>
            <div class="form-group">
                <input class="form-control" type="text" name="tag" placeholder="Тэг">
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <label class="custom-file-label" for="file_sender">Выберите файл</label>
                    <input type="file" name="file" id="file_sender">
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button class="btn btn-primary" type="submit">Отправить</button>
            </div>
        </form>
    </div>
</section>


<section class="messages card-columns">
    <#list messages as message>
        <div class="card my-3" style="width: 18rem;">
            <#if message.filename??>
            <img class="card-img-top" src="/img/${message.filename}">
            </#if>
            <div class="m-2">
                <span>${message.text}</span>
                <i>${message.tag}</i>
            </div>
            <div class="card-footer text-muted">
                ${message.author.username?ifExists}
            </div>
        </div>
        <#sep> </br>
        <#else>
        No message
    </#list>
</section>
</@p.page>

