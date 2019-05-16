<#import "parts/common.ftl" as p>
<#import "parts/login.ftl" as f>

<@p.page "Login">
${message?ifExists}
<h1>Login Page</h1>
<@f.login "/login" "Sign In" true/>
</@p.page>