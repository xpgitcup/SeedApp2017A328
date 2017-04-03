<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page import="cn.edu.cup.system.SystemMenu" contentType="text/html;charset=UTF-8" %>

<html>
<head>
<!-- 实现可定制的布局 -->
    <g:if test="${layout}">
        <meta name="layout" content="${layout}"/>
    </g:if>
    <g:else>
        <g:if test="${session.layout}">
            <meta name="layout" content="${session.layout}"/>
        </g:if>
        <g:else>
            <meta name="layout" content="main"/>
        </g:else>
    </g:else>
<!-- end 实现可定制的布局 -->
    <g:set var="entityName" value="SystemStatus"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/system/${entityName}.js"/>
</head>

<body>
<h1>系统状态：</h1>
<ul>
    <li><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
    <li><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
    <li><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
    <li><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
    <li><a href="#">系统菜单: ${cn.edu.cup.system.SystemMenu.count()}</a></li>
    <li><a href="#">缺少动作的菜单: ${noAction}</a></li>
    <hr>
    <ul>
        <g:each in="${toDoList}" var="item" status="i">
            <li>
                ${item}
            </li>
        </g:each>
    </ul>
</ul>
</body>
</html>
