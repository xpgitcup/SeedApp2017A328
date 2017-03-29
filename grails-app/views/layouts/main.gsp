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

    <!--引入bootstrap的相关内容-->
    <aseet:stylesheet src="bootstrap.css"/>

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
    <asset:stylesheet src="cn/edu/cup/cup.css"/>

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

<div class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar">??</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!-- 这是左上角图标，来自于白色的圣杯的大图的缩小版  -->
            <a class="navbar-brand" href="/#">
                <i class="fa grails-icon">
                    <!--asset:image src="grails-cupsonly-logo-white.svg"/-->
                    <asset:image src="cn/edu/cup/${cn.edu.cup.system.SystemTitle.last()?.applicationLogo}"/>
                </i> <!--Grails-->
            </a>
            <!--这是程序的标题-->
            <label class="applicationTitle">
                ${cn.edu.cup.system.SystemTitle.last()?.applicationTitle}
            </label>
        </div>
        <!-- 这里插入导航按钮 -->
        <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
            <ul class="nav navbar-nav navbar-right">
            <!-- 插入共同的菜单 -->
            <!--这里显示保存在会话中的各个菜单-->
                <g:each in="${session.systemMenuList}" status="i" var="menuItem">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">${menuItem.menuContext}<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <g:each in="${session.subMenuItems[i]}" status="j" var="subMenuItem">
                                <li>
                                    <a href="${createLink(uri: '/' + subMenuItem.menuAction)}">
                                        ${subMenuItem.menuContext}
                                    </a>
                                </li>
                            </g:each>
                        </ul>
                    </li>
                </g:each>
            <!-- 显示当前用户 -->
                <g:if test="${session.systemUser}">
                    <li><a href="${createLink(uri: '/home/logout')}">退出</a></li>
                </g:if>
                <g:else>
                    <li><a href="${createLink(uri: '/home/loginUI')}">去登录</li>
                </g:else>
        </div>
        <!-- 下面插入各个具体页面各自的菜单 -->
        <g:pageProperty name="page.nav"/>
    </ul>

    </div>
</div>

<!-- 这里插入显示主体 -->
<g:layoutBody/>

<!-- 这里是页脚 -->
<div id="footer" role="contentinfo">
    <ul class="nav navbar-right">
        <li>
            当前用户：${session?.systemUser?.userName}[${session?.systemUser?.roleAttribute}]
        </li>
        <li>
            在线:${session?.onlineCount}人，
        </li>
        <li>
            ${session?.systemUserList}
        </li>
    </ul>
</div>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<!--asset:javascript src="application.js"/-->

</body>
</html>
