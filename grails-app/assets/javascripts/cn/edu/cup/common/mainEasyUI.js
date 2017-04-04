/**
 * Created by LiXiaoping on 2017/4/3.
 */

var mainSystemMenuDiv;
var currentAccordion;
var currentPanel;
var mainPanel;

$(function () {
    console.info("这是整体布局...");

    mainPanel = $("#mainPanel");
    mainSystemMenuDiv = $("#mainSystemMenuDiv");

    //读取当前的Panel
    //currentAccordion = readCookie("currentAccordion", "底层管理");
    currentPanel = readCookie("mainPanel", "底层管理");
    console.info("上一次停留在：" + currentPanel);
    mainPanel.panel('setTitle', "主功能区=>" + currentPanel);  //没有状态---没有状态

    mainSystemMenuDiv.accordion({
        onSelect: function (title, index) {
            console.info("选择：" + title + "---" + index);
            if (index>-1) {
                mainPanel.panel('setTitle', "主功能区=>" + title);
                $.cookie('mainPanel', title, {path: '/'});
            }
        }
    });
    //mainSystemMenuDiv.accordion('select', currentAccordion);
    currentAccordion = mainSystemMenuDiv.accordion('getSelected');
    console.info(currentAccordion.attr('title'));
    if (currentAccordion !== currentPanel) {
        mainSystemMenuDiv.accordion('select', currentPanel);
    }

});

