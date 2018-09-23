/*工具js，需要jQuery*/

$(function () {
    console.log($(".dialog-btn"));
    btnList = $(".dialog-btn");
    for (var i = 0; i < btnList.length; i++) {
        var item = btnList[i];
        console.log(item);
        $(item).click(function () {
            /*获取按钮绑定的对话狂*/
            var bind = $(this).attr("bind");
            var onshow = $(this).attr("onshow");
            var display = $(this).css("display")
            if (bind) {

                if (onshow && display == "none") {
                    eval(onshow)
                }
                $(bind.toString()).fadeToggle();

            }
        })
        // item.onclick = function () {
        //     /*取到绑定的值*/
        //     var dialog = item.getAttribute("bind");
        //
        //     console.log(item);
        //     console.log(dialog);
        //     $(dialog).fadeToggle();
        // }
    }
    __setDialog();
});

/**
 * 显示对话狂
 * @param select
 */
function showDialog(select) {
    var display = $(select).css("display");
    var onshow = $(select).attr("onshow");
    if (display == "none") {
        if (onshow)
            eval(onshow)
        $(select).fadeToggle();
    }
}

/**
 * 文件上传预览
 * @param inputFileSelect 文件选择器
 * @param ImgSelect 图片标签选择器
 */
function reViewInputFile(inputFileSelect, ImgSelect) {
    var reads = new FileReader();
    var f = $(inputFileSelect)[0].files[0];
    //f=document.getElementById('file').files[0];
    reads.readAsDataURL(f);
    reads.onload = function (e) {
        $(document.body).css({"background-image": "url('" + this.result + "')"})

        $(ImgSelect).attr({"src": this.result});
    };
}


/**
 * 关闭对话坤哥
 * @param select
 */
function hiedDialog(select) {
    var display = $(select).css("display");
    var onshow = $(select).attr("onshow");
    if (display == "block") {
        if (onshow)
            eval(onshow)
        $(select).fadeToggle();
    }
}

/**
 * 私有函数！设置对话框
 * <div class="dialog" width="[数字/数字%]" height="数字" top="数字" left="数字/center剧中/left/right"></div>
 * 打开对话框按钮
 * <div class="dialog-btn" bind="对话框标识[#id/.classname]"></div>
 * @private
 */
function __setDialog() {
    /*遍历对话框*/
    $.each($(".dialog"), function (e) {
        var dg = $($(".dialog")[e]);
        var width = dg.attr("width");
        var height = dg.attr("height");
        var parentWidth = screen.availWidth;//获取屏幕宽度
        var parentHeight = screen.availHeight;

        /*如果围城设置，则给默认值*/
        if (!width) {
            width = 100;
        }
        if (!height) {
            height = 200;
        }
        /*判断是否是百分比的大小*/
        if (width.toString().match(/\%/)) {
            width = parseInt(__convertSize(width, parentWidth));
        }
        if (height.toString().match(/\%/)) {
            height = parseInt(__convertSize(height, parentHeight));
        }
        /*设置对话框样式*/
        dg.css({

            "border": "0px solid black",
            "width": width + "px",
            "min-height": height + "px",
            "display": "none",
            "margin": "10px auto",
            "z-index": "9999999",
            "position": "fixed",
            "top": function () {
                var top = dg.attr("top");
                if (!top) {
                    top = 10;
                }
                if (top.toString().match(/\%/)) {
                    top = parseInt(__convertSize(top, parentHeight))
                }

                return top + "px";
            },
            "left": function () {

                var left = dg.attr("left")
                /*如果不存在则剧中*/
                if (!left) {
                    left = "center";
                }
                console.log(left);
                /*判断*/
                switch (left) {
                    case "center":
                        return (parentWidth / 2 - width / 2) + "px";
                    case "left":
                        return 10 + "px";
                    case "right":

                        return parseInt(parentWidth - 10 - width) + "px";
                    default:
                        /*判断是否包含百分比*/
                        if (left.toString().match(/\%/)) {
                            left = parseInt(__convertSize(left, parentWidth));
                        }
                        return parseInt(left) + "px";
                }

            },
        })

        /**/

    });
}

/**
 * 大小转换
 * @param zb 百分比 10
 * @param s2 比例值 s2*zb
 * @private
 */
function __convertSize(zb, s2) {
    if (zb.toString().match(/\%/)) {
        zb = parseFloat(zb.toString().replace(/\%/, "")) / 100;
    }
    return zb * s2;
}

/**
 * 设置关闭按钮时间
 */
$(function () {
    $(".close").click(function () {
        if (confirm("是否关闭？")) {
            window.close()
        }
    })
})
/**
 * 下拉列表框
 * <div class="downlist"></div>
 * 显示下拉框按钮，
 * <div class="downlist-btn" bind="绑定标识" show="click/显示事件"></div>
 */
$(function () {
    /*设置*/
    $.each($(".downlist-btn"), function (item) {
        item = $($(".downlist-btn")[item]);

        item.click(function () {
            var bind = item.attr("bind");
            var show = item.attr("show");
            var strs = show.split(",");
            for (var i = 0; i < strs.length; i++) {
                item.on(strs[i], function (e) {
                    console.log(e)
                })
            }
        })
    })
})

/**
 * 设置下拉列表框
 * @private
 */
function __setDownList(list) {

}

/**
 * 弹出消息框
 * @param msg 显示信息i
 * @param showTime 显示的时间
 * @param showPoint 显示位置：top bottom，left,right
 * @param width 宽度
 * @param height 高度
 * @param attr 属性{}
 *
 */
function Toast(msg, showTime, showPoint, width, height, attr, fun) {
    if (!width) {
        width = msg.length * 16;
        width = width < 300 ? 300 : width;
    }
    if (!height)
        height = 40;
    if (!showTime)
        showTime = 2000;
    if (!showPoint)
        showPoint = "TOP"

    var totas = document.createElement("div");
    var sw = screen.availWidth;
    var sh = screen.availHeight;
    var sx, sy;//起始位置
    var dx, dy;//显示位置
    /*计算坐标*/
    switch (showPoint.toLocaleString()) {
        case"bottom":
            sx = sw / 2 - width / 2;
            dx = sx;
            sy = sh;
            dy = sh - height - 55;

            break
        case"right":
            sy = sh / 2 - height / 2
            dy = sy;
            sx = sw;
            dx = sw - width;
            break
        case"left":
            sy = sh / 2 - height / 2
            dy = sy;
            sx = 0 - width;
            dx = 0;
            break
        default:
            sx = sw / 2.0 - width / 2;
            dx = sx;
            sy = 0 - height;
            dy = 0;
    }
    totas.innerHTML = msg;
    $(totas).attr({"class": "totas"});
    if (attr) {
        $(totas).attr(attr);
    }
    $(totas).css({
        "width": width + "px",
        "height": height + "px",
        "position": "fixed",
        "top": sy + "px",
        "left": sx + "px",
        "transition": "top 200ms ease-out, left 200ms ease-out",
        "z-index": "9999999999999999", "line-height": height + "px"
    });
    $("body").append(totas)

    setTimeout(function () {
        $(totas).css({"top": dy + "px", "left": dx + "px"})
    }, 10)
    setTimeout(function () {
        $(totas).css({"top": sy + "px", "left": sx + "px"})
    }, showTime - 200)
    setTimeout(function () {
        totas.remove();
        if (fun) {
            if (typeof (fun) == 'function')
                fun();
        }
    }, showTime)
}

/**
 * 相应制定内容信息
 * @param select 容器选择器
 * @param url 访问链接
 * @param attr 参数
 * @constructor
 */
function ShowPageTo(select, url, attr, method) {

    if (method == "POST") {
        $.post(url, attr, function (data) {

            var selects = $(select);
            $.each(selects, function (i) {
                selects[i].innerHTML = data;
            })

        });
    } else {
        $.get(url, attr, function (data) {
            var selects = $(select);
            $.each(selects, function (i) {
                selects[i].innerHTML = data;
            })
        });
    }
}

function Get(url) {
    $.get(url, function (data) {

        document.body.innerHTML = data

    })
}