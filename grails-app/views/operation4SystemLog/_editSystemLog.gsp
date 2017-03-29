<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'systemLog.label', default: 'systemLog')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
<a href="#edit-systemLog" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="edit-systemLog" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.systemLog}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.systemLog}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
<!--g:form resource="${this.systemLog}" method="PUT"-->
    <g:form action="updateSystemLog" controller="operation4SystemLog">
        <!--这是增加的一行-->
        <g:hiddenField name="id" value="${this.systemLog?.id}" />
        <g:hiddenField name="version" value="${this.systemLog?.version}" />
        <fieldset class="form">
            <f:all bean="systemLog"/>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
