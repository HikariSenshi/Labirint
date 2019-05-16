<#import "parts/common.ftl" as p>
<#import "parts/login.ftl" as f>

<@p.page "Registration">
<h1> Registration </h1>
<div class="mb-1">Register a new user</div>
${message?ifExists}
<@f.login "/signup" "Sign UP" false/>

</@p.page>
