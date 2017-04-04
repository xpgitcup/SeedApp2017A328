<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<!--meta name="layout" content="main"/-->
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
    <g:set var="entityName" value="DataKey"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/base/${entityName}.js"/>
</head>

<body>
<div class="container">
    <div class="row-fluid">
        <div class="col-md-4 column">
            <div class="panel panel-default">
                <div class="nav">
                    <ul>
                        <li>
                            <a class="list">
                                系统菜单维护——(重新登录后，更新)
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="easyui-panel">
                    <div id="displayTreeDataKeyDiv" class="easyui-tree"></div>
                    <div id="paginationDataKeyDiv" class="easyui-pagination"></div>
                </div>
            </div>
        </div>

        <div class="col-md-8 column">
            <div class="panel panel-default">
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="create" href="javascript: createDataKey(0)">新建根节点</a></li>
                        <li><a id="createDataKey" class="create" href="#">新建子节点</a></li>
                    </ul>
                </div>

                <div class="easyui-panel">
                    <div id="showDataKeyDiv"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
