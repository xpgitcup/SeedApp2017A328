/**
 * Created by LiXiaoping on 2017/4/4.
 */

/**
 * Created by LiXiaoping on 2017/2/26.
 */

var displayTreeDataKeyDiv;
var paginationDataKeyDiv;

$(function(){
    console.info($("title").text() + "加载成功...");

    //获取当前页面的div
    displayTreeDataKeyDiv = $("#displayTreeDataKeyDiv");
    paginationDataKeyDiv = $("#paginationDataKeyDiv");

    //获取当前页
    var currentPgaeDataKey = readCookie("currentPgaeDataKey", 1);
    var pageSizeDataKey = readCookie("pageSizeDataKey", pageSize);
    var totalDataKey = countDataKey();
    console.info("记录总数：" + totalDataKey);

    //加载数据
    displayTreeDataKeyDiv.tree({
        url: "getTreeDataKey" + getParams(currentPgaeDataKey, pageSizeDataKey),
        onSelect: function (node) {
            showDataKey(node);
            $("#createDataKey").attr('href', 'javascript: createDataKey(' + node.attributes[0] + ')');
            console.info(node);
            console.info("当前节点：" + node.target.id);
            $.cookie("currentDataKey", node.target.id);
        },
        onLoadSuccess: function () {
            displayTreeDataKeyDiv.tree("collapseAll");
            var nodeid = $.cookie("currentDataKey");
            console.info("当初扩展到" + nodeid);
            var cNode = $("#" + nodeid);
            displayTreeDataKeyDiv.tree("expandTo", cNode);
            displayTreeDataKeyDiv.tree("select", cNode);
        }
    });
    //分页
    paginationDataKeyDiv.pagination({
        pageSize: pageSizeDataKey,
        total: totalDataKey,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage:function(pageNumber, pageSize){
            displayTreeDataKeyDiv.tree({
                url: "getTreeDataKey" + getParams(pageNumber, pageSize)
            })
        }
    });
});

/*
 * 新建
 * */
function createDataKey(id) {
    console.info("创建DataKey. 上级节点：" + id);
    ajaxRun("createDataKey", id, "showDataKeyDiv");
}

/*
 * 编辑
 * */
function editDataKey(id) {
    console.info("编辑DataKey." + id);
    ajaxRun("editDataKey", id, "showDataKeyDiv");
}

/*
 * 统计记录总数
 * */
function countDataKey() {
    console.info("开始统计...")
    var total = ajaxCalculate("countDataKey");
    console.info("统计结果：" + total);
    return total;
}

/*
 * 显示当前属性
 * */
function showDataKey(node) {
    console.info("显示当前系统属性" + node);
    if (node) {
        var id = node.attributes[0];
        ajaxRun("getDataKey", id, "showDataKeyDiv");
    }
}
