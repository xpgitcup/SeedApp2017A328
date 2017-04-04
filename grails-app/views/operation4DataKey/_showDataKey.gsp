<div id="show-dataKey" class="content scaffold-show" role="main">
    <h1>${entityName}</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:display bean="dataKey"/>
<!--g:form resource="${this.dataKey}" method="DELETE"-->
    <g:form id="${this.dataKey.id}" method="DELETE" action="delete" controller="operation4DataKey">
        <fieldset class="buttons">
            <!--g:link class="edit" action="edit" resource="${this.dataKey}"-->
            <!--/g:link-->
            <a href="javascript: editDataKey(${this.dataKey.id})">
                <g:message code="default.button.edit.label" default="Edit"/>
            </a>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
