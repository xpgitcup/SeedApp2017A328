<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

    <title>
        <g:layoutTitle default="Wellcome"/>
    </title>

    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <!--引入easyui的相关内容-->
    <asset:stylesheet src="easyui/themes/default/easyui.css"/>
    <asset:stylesheet src="easyui/themes/icon.css"/>

    <asset:stylesheet src="cn/edu/cup/cup.css"/>

    <g:layoutHead/>

</head>

<body>

<g:layoutBody/>

<div>&nbsp;</div>

<div class="panel-footer">
    <div class="panel-title">日志</div>

    <div class="panel-body">
        <div id="systemLogDiv">
            <table>
                <thead>
                <th>IP</th>
                <th>用户</th>
                <th>行为</th>
                <th>时间</th>
                </thead>
                <tbody>
                <g:each in="${cn.edu.cup.system.SystemLog.findAll([offset: 0, max: 5])}" var="item" status="i">
                    <tr>
                        <td>${item.hostIP}</td>
                        <td>${item.userName}</td>
                        <td>${item.doing}</td>
                        <td>${item.actionDate}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>

        </div>
    </div>
</div>

<!--asset:javascript src="application.js"/-->
</body>
</html>
