<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name="layout" content="main">
    <!-- 设置当前域类的名字，以后的所有命名规则都基于这个名字 -->
    <g:set var="entityName" value="Home"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 设置标题，如果没有特殊情况，标题叫这样了。 -->
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/os/${entityName}.js"/>
</head>

<body>
<h1>这是首页，显示首页信息</h1>

<h2>按照新的理念，将SystemAttribute的维护重写了一遍</h2>

<h2>关键是注意了代码的规范化，请大家批评斧正；</h2>

<h2>无论是界面方面，还是代码规约——源代码在Gitlab上</h2>
</body>
</html>
