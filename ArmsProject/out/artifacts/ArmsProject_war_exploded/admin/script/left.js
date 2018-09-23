$(function () {
    var toolGroups = $(".tool-group");
    $.each(toolGroups,function (i) {
        var group = toolGroups[i];
        if($(group).attr("show")=="true"){
            __showGroup(group,toolGroups)
        }
        /*点击事件*/
        $(group.children[0]).click(function () {
            var show = $(group).attr("show");//获取显示状态
            if(show=="true"){
                __hideGroup(group)//隐藏
            }else{
                __showGroup(group,toolGroups)//显示
            }
        })
    })
});

/**
 * 显示功能框
 * @private
 */
function __showGroup(group,groups){
    $.each(groups,function (i) {
        __hideGroup(groups[i])
    })
    $(group).attr("show","true")
    var childs =group.children;
    $(group.querySelector(".title")).addClass("title-hover");//给标题添加样式
    console.log(childs)
    for(var i=1;i<childs.length;i++){
        var child =childs[i];
        $(child).css({
            "height":"30px",
            "line-height":"30px","padding-left":"40px","border":"1px solid  #3a3a3a"
        });

    }

}
/**
 * 隐藏功能框
 * @private
 */
function __hideGroup(group){
    $(group).attr("show","false")
    var childs =group.children;

    for(var i=1;i<childs.length;i++){
        $(childs[i]).css({
            "height":"0px",
            "padding-left":"0px","border":"0px solid #3a3a3a"
        });
    }
    $(group.querySelector(".title")).removeClass("title-hover");//给标题添加样式
}
$(function () {
    ShowPageTo("#SHOWBODY","../home_back.html");
})