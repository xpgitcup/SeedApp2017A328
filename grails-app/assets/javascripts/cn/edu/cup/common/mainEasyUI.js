/**
 * Created by LiXiaoping on 2017/4/3.
 */

var mainSystemMenuDiv;
var currentAccordion;

$(function () {
    console.info("这是整体布局...");

    //读取当前的Panel
    currentAccordion = readCookie("currentAccordion", 0);
    console.info("上一次停留在：" + currentAccordion);
    mainSystemMenuDiv = $("#mainSystemMenuDiv");

    mainSystemMenuDiv.accordion({
        onSelect: function (title, index) {
            console.info("选择：" + title + "---" + index);
            $.cookie("currentAccordion", index, {path: '/'});
        }
    });
    mainSystemMenuDiv.accordion('select', currentAccordion);

});

//写在这里的好像没有用了。
function selectAccordion(title, index) {
    console.info("选择：" + title + "---" + index);
    $.cookie("currentAccordion", title);
}

