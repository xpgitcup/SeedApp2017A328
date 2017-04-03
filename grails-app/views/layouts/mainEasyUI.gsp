<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <!--引入easyui的相关内容-->
    <asset:stylesheet src="easyui/themes/default/easyui.css"/>
    <asset:stylesheet src="easyui/themes/icon.css"/>
    <asset:stylesheet src="easyui/themes/color.css"/>
    <!--asset:stylesheet src="easyui/themes/bootstrap/easyui.css"/-->
    <!--引入树形结构显示组件-->
    <asset:stylesheet src="bootstrap-treeview/bootstrap-treeview.min.css"/>
    <!--引入jqpagination的样式-->
    <!--asset:stylesheet src="jqpagination/jqpagination.css"/-->

    <!--引入CUP的相关内容-->
    <asset:stylesheet src="cn/edu/cup/cupEasyUi.css"/>

    <!--JS加载-->
    <asset:javascript src="jquery-2.2.0.min.js"/>

    <asset:javascript src="easyui/jquery.easyui.min.js"/>

    <asset:javascript src="bootstrap.js"/>
    <asset:javascript src="bootstrap-treeview/bootstrap-treeview.min.js"/>
    <asset:javascript src="jquery/jquery.cookie.js"/>
    <!--引入jqpagination-->
    <!--asset:javascript src="jqpagination/jquery.jqpagination.min.js"/-->
    <!--用户自定义的-->
    <asset:javascript src="cn/edu/cup/common/common.js"/>

    <g:layoutHead/>
</head>

<body>

<!-- 定义主框架 -->
<div id="mainFrame" class="easyui-layout" fit="true">
    <!-- 标题部分 -->
    <div data-options="region:'north'" style="height: 78px">
        <div class="header-EasyUI">
            <!-- 这是左上角图标，来自于白色的圣杯的大图的缩小版  -->
            <asset:image src="cn/edu/cup/${cn.edu.cup.system.SystemTitle.last()?.applicationLogo}"/>
        </div>

        <div class="applicationTitle">
            ${cn.edu.cup.system.SystemTitle.last()?.applicationTitle}
        </div>

        <div class="applicationHeaderStatus">
            <ul>
            <!-- 显示当前用户 -->
                <g:if test="${session.systemUser}">
                    <li><a href="${createLink(uri: '/home/logout')}">退出</a></li>
                </g:if>
                <g:else>
                    <li><a href="${createLink(uri: '/home/loginUI')}">去登录 < /li>
                </g:else>
            </ul>
        </div>
    </div>
    <!-- 左边的菜单-->
    <div data-options="region:'west', split: true" style="width: 20%">
        <div class="easyui-accordion" style="width: auto">
            <g:each in="${session.systemMenuList}" var="menuItem" status="i">
                <div title="${menuItem.menuContext}" data-options="iconCls:'icon-ok'">
                    <ul>
                        <g:each in="${session.subMenuItems[i]}" status="j" var="subMenuItem">
                            <li>
                                <a href="${createLink(uri: '/' + subMenuItem.menuAction)}">
                                    ${subMenuItem.menuContext}
                                </a>
                            </li>
                        </g:each>
                    </ul>
                </div>
            </g:each>

        </div>
    </div>
    <!-- 主显示区 -->
    <div data-options="region:'center'" class="mainContent">
        <!-- 这里插入显示主体 -->
        <g:layoutBody/>
    </div>
    <!-- 页脚 -->
    <div data-options="region:'south'" style="width: 100%">
        <div class="applicationFooterLeft">
            <span>中国石油大学（北京），CopyRight 2017，Ver 1.0</span>
        </div>
        <ul class="applicationFooter">
            <li>
                ${session?.systemUserList}
            </li>
            <li>
                在线:${session?.onlineCount}人:
            </li>
            <li>
                当前用户：${session?.systemUser?.userName}[${session?.systemUser?.roleAttribute}]
            </li>
        </ul>
    </div>
</div>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<!--asset:javascript src="application.js"/-->

</body>
</html>
