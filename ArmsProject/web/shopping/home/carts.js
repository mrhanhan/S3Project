//======================================移除商品========================================

/**
 * 添加购物车的方法
 * @param gnum
 * @param size
 * @constructor
 */
function addGoods(url1, gnum, size) {
    if (!gnum)
        return;
    $.post(url1 + "cart/add", {"goodsNum": gnum, "buyNum": size}, function (data) {
        var cart = data.cart;
        if (cart.login) {
            flushCartView(url1);//刷新页面
            flushCartPage(url1);//刷新页面
        } else {
            alert("请先登录")
            // alert("url1+\"customer/login\"")
            GetRequest(url1 + "customer/login", "_blank")
        }
    });

}


/*移除商品*/
function remove(btn, gnum) {
    if (confirm("是否取消购买此商品？")) {
        addGoods("../../../", gnum, 0 - parseInt($(btn).attr("count")))
        Toast("已移除", 2000, "top")
    }
}



function clearCart() {
    if (confirm("是否取清空购物车里的商品？")) {
        $.get("../../../cart/clear", function () {
            flushCartPage("../../../")
            flushCartView("../../../")
            Toast("已清空", 2000, "top")
        })
    }
}


/**
 * 提交购物车
 */
function conmitCart() {
    $.post("../../../cart/submit",function (data) {
        var cart = data.cart;

        if(cart.login){
            if(cart.login.caddress){


            Toast(cart.msg,3000,"top")
            flushCartView("../../../")
            flushCartPage("../../../")
            }else{
                Toast("请前往填写地址：",5000,"top",null,null,null,function () {
                    GetRequest("../../../useroperation/customer","_blank")
                })
            }
        }
    })
}

/*刷新购物车视图*/
/**
 *
 * @param url跟路基
 */
function flushCartPage(url) {
    var group = $(".order_content")[0]
    $.get(url + "cart/get", function (data) {
        var cart = data.cart;//获取json的购物车对象
        if (cart.login) {//判断登陆状态
            $(".total_text").text(" $" + parseFloat(cart.money).toFixed(3))//修改价格
            var buys = cart.buys;
            var buy = null;
            //时间，表示跟新的时间
            var time = new Date().toLocaleString();
            $(group).attr("time", time)//设置更新时间
            for (var i = 0; i < buys.length; i++) {
                buy = buys[i].buy;
                var good = buy.good;
                var type = buy.type;
                //    console.log(buy)
                var remark = buy.good.gremark;
                if (remark.length > 30)
                    remark = remark.substr(0, 30);
                var price = good.gprice;
                if ("是" == good.gisdiscount)//是否折扣
                    price = parseFloat(price * good.gdiscount / 100).toFixed(3)//计算泽口价格
                var OrderInfo = $("#G" + good.gnum)[0];


                if (!OrderInfo) {//判断是否不存在此商品信息
                    $(group).append(" <ul class=\"order_lists\" time='" + time + "' style=\"padding-left: 50px;\" id=\"G" + good.gnum + "\">\n" +
                        "\n" +
                        "                <li class=\"list_con\">\n" +
                        "                    <div class=\"list_img\">\n" +
                        "                        <a href=\"../../../queryGoods?goodnum=" + good.gnum + "\">\n" +
                        "                            <div class=\"goodsImg\"\n" +
                        "                                 style=\"    height: 100px;border: none;margin-left: 5px; background-image: url('../../../images/goodspic/" + good.gimg + "')\"\n" +
                        "                                 alt=\"\"></div>\n" +
                        "                        </a>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"list_info\"\n" +
                        "                         style=\"margin-top: 30px;line-height: 18px;  width: 200px;text-align: center\">\n" +
                        "<a href='../../../shop/query?ShowName=" + type.tname + "&typeName=" + type.tname + "'>" +
                        "                        <p style=\"color: black;font-weight: bold;\">" + good.gname + "</p>\n" +
                        "                        <p>" + type.tname + "</p> </a>\n" +
                        "                    </div>\n" +
                        "                </li>\n" +
                        "                <li class=\"list_price\">\n" +
                        "                    <p class=\"price\" style=\"line-height: 90px;\">" + parseFloat(good.gprice).toFixed(3) + "</p>\n" +
                        "                </li>\n" +
                        "                <li class=\"list_price\">\n" +
                        "                    <p class=\"price\"  style=\"line-height: 90px;\">" + (("是" == good.gisdiscount) ? parseFloat(price).toFixed(3) : "无折扣") + "</p>\n" +
                        "                </li>\n" +
                        "                <li class=\"list_amount\">\n" +
                        "                    <div class=\"amount_box\" style=\"margin-top: 50px;\">\n" +
                        "                        <a href=\"javascript:;\" class=\"reduce reSty\"  onclick='addGoods(\"../../../\",\"" + good.gnum + "\",-1)' >-</a>\n" +
                        "                        <input type=\"text\" readonly value=\"" + buy.count + "\" class=\"sum\"  >\n" +
                        "                        <a  href=\"javascript:;\" onclick='addGoods(\"../../../\",\"" + good.gnum + "\",1)' class=\"plus\">+</a>\n" +
                        "                    </div>\n" +
                        "                </li>\n" +
                        "                <li class=\"list_sum\">\n" +
                        "                    <p class=\"sum_price\" style=\"line-height: 90px;\"  > " + parseFloat(buy.price).toFixed(3) + "</p>\n" +
                        "                </li>\n" +
                        "                <li class=\"list_op\">\n" +
                        "                    <p class=\"del\">\n" +
                        "                        <a href=\"javascript:;\" class='remove' count='" + buy.count + "' onclick='remove(this,\"" + good.gnum + "\")' class=\"delBtn\" style=\"line-height: 90px;\" >移除商品</a>\n" +
                        "                    </p>\n" +
                        "                </li>\n" +
                        "            </ul>");
                } else {
                    $(OrderInfo.querySelector(".sum_price")).text(parseFloat(buy.price).toFixed(3))
                    $(OrderInfo).attr("time", time)//设置跟新时间
                    $(OrderInfo.querySelector("input")).val(buy.count)
                    $(OrderInfo.querySelector(".remove")).attr("count", buy.count)
                }
            }

            var childList = $(".order_lists");//成员列表
            for (var i = 0; i < childList.length; i++) {
                var t1 = $(group).attr("time")
                var t2 = $(childList[i]).attr("time")
                if (t1 != t2) {
                    childList[i].remove();
                }
            }

            if (buys.length < 1 || cart.count < 0) {
                var a = $(".cart-nomsg")

                if (a.length) a[0].remove();

                $(group).append("    <div class='cart-nomsg' style=\"\n" +
                    "    text-align:  center;\n" +
                    "    padding: 30px;\n" +
                    "    font-size: 20pt;\n" +
                    "\">当前未购买商品！<a href=\"../../../shop/query\" style=\"\n" +
                    "    padding:  10px;\n" +
                    "    color: blue;\n" +
                    "    text-decoration:  underline;\n" +
                    "\">请购买</a></div>")
                $(".js").removeClass("btn_sty")
            } else {
                $(".js").removeClass("btn_sty")
                $(".js").addClass("btn_sty")
            }
        } else {

        }
    })
}