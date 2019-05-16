<#macro login path value isLog>

<form action="${path}" method="post">

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name: </label>
        <div class="col-sm-5">
            <input type="text" name="username" placeholder="Логин"/>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password: </label>
        <div class="col-sm-5">
            <input type="password" name="password" placeholder="Пароль"/>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <#if isLog><a href="/signup">Sign Up</a></#if>
    <#if !isLog>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email: </label>
        <div class="col-sm-5">
            <input type="email" name="email" placeholder="Электронная почта"/>
        </div>
    </div>
    </#if>
    <input class="btn btn-primary" type="submit" value="${value}"/>
</form>
</#macro>

<#macro logout>

<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input class="btn btn-primary" type="submit" value="Log Out"/>
</form>

</#macro>