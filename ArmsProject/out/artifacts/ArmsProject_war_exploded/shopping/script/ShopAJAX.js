/**
 * get请求,a标签实现
 * @param url 请求的连接
 * @param target 制定跳转位置 a标签的target 默认_blank
 * @param attr 携带其他属性
 * @constructor
 */
function GetRequest(url, target, attr) {
    var a = document.createElement("a");
    a.href = url;
    if (attr)
        $(a).attr(attr);
    if (!target)
        target = "_blank"
    a.target = target;
    a.click();
}

/**
 * Post请求,表单方式实现 form
 * @param action 请求动作
 * @param params 参数集合 参数书写方式=>[{name:"",value:""}]
 * @param target 类似a标签的target
 * @param attr 书信
 * @constructor
 */
function PostRequest(action, params, target, attr) {
    var from = document.createElement("form")
    from.action = action;
    if (!target)
        target = "_blank"
    from.target = target;
    if (params) {
        var obj = null;
        var input = null;
        for (var i = 0; i < params; i++) {
            obj = params[i];
            if (obj) {
                input = document.createElement("input")
                input.name = obj.name;
                input.value = obj.value;
                from.appendChild(input)
            }
        }
    }
    if (attr) {
        $(from).attr(attr)
    }
    $(from).css({"display": "hidden"})
    document.body.appendChild(from);
    from.submit();
    from.remove();
}

/**
 * AJAX Get请求
 * @param url 请求地址
 * @param fun 回调方法
 * @param params 参数数组{"key":"value",}
 */
function Get(url, fun, params) {
    if (!params) {
        $.get(url, function (d,e) {
            fun(d,e)
        });
    } else {
        $.get(url, params, function (d,e) {
            fun(d,e)
        });
    }


}

/**
 * AJAX Post请求
 * @param url 请求地址
 * @param fun 回调方法
 * @param params 参数数组{"key":"value",}
 */
function Post(url, fun, params) {
    if (params) {
        $.post(url, params, function (d,e) {
            fun(d,e)
        });
    } else {
        $.post(url, function (d,e) {
            fun(d,e)
        });
    }
}

/**
 * 添加购物车的方法
 * @param gnum
 * @param size
 * @constructor
 */
function AddCart(url1,gnum,size) {
    if(!gnum)
        return;
    $.post(url1+"cart/add",{"goodsNum":gnum,"buyNum":size},function (data){
        var cart = data.cart;
        if(cart.login){
           flushCartView(url1);//刷新页面
            Toast("添加成功",2000,"top")
        }else{
            alert("请先登录")
           // alert("url1+\"customer/login\"")
            GetRequest(url1+"useroperation/login","_blank")
        }
    });


}



/*刷新购物车视图*/
/**
 *
 * @param url跟路基
 */
function flushCartView(url) {
    var GoodsNum = $(".cartGoodsNum");//购物车商品的数量
    var Cart = $("#cart")//购物车区域
    var order = $("#order");//账单区域
    var orderList = $("#orderlist");//订单列表
    var btn = $("#buyBtn")//买单按钮
    var title = $("#order #title")
    $.get(url+"cart/get",function (data) {
        var cart = data.cart;//获取json的购物车对象
        if(cart.login){//判断登陆状态
            title[0].innerHTML="账单"
            orderList[0].innerHTML="";
            $(orderList).append("<li><span>原价:</span> <span>$"+parseFloat(cart.money1).toFixed(3)+"</span></li>")
            $(orderList).append("<li><span>数量:</span> <span>"+cart.count+"</span></li>")
            $(orderList).append("<li><span>折扣:</span> <span>$"+(parseFloat(cart.money1-cart.money).toFixed(3))+"</span></li>")
            $(orderList).append(" <li><span>现价:</span> <span>$"+parseFloat(cart.money).toFixed(3)+"</span></li>")
            GoodsNum.text(cart.count)
            Cart.html("")
            var buys = cart.buys;
            var buy = null;

            for(var i=0;i<buys.length;i++){
                buy = buys[i].buy;
            //    console.log(buy)
                var remark = buy.good.gremark;
                if(remark.length>30)
                    remark = remark.substr(0,30);
                var price = buy.good.gprice;
                if("是" == buy.good.gisdiscount)//是否折扣
                    price = parseFloat(price*buy.good.gdiscount/100).toFixed(3)//计算泽口价格

               Cart.append("<div class=\"single-cart-item goodsImg\" onclick='GetRequest(\"../../../shopgoods/queryGoods?gnum="+buy.good.gnum+"\",\"_top\")' style=\"width: 192px;height: 256px;background-image:url('../../../images/goodspic/"+buy.good.gimg+"')\">\n" +
                    "<div class=\"product-image\">\n" +
                    "<div class=\"cart-item-desc\" style=\"width: 192px;height: 256px;\">\n" +
                    "<span class=\"product-remove\" onclick='if(confirm(\"是否移除？\"))AddCart(\""+url+"\",\""+buy.good.gnum+"\",-"+buy.count+")'><i class=\"fa fa-close\" aria-hidden=\"true\"></i></span>\n" +
                    "<span class=\"badge\">"+buy.type.tname+"</span>\n" +
                    "<h6>"+buy.good.gname+"</h6>\n" +
                    "<p class=\"size\">数量:"+buy.count+"</p>\n" +
                    "<p class=\"color\">"+remark+"</p>\n" +
                    "<p class=\"price\">$"+parseFloat(price).toFixed(3)+" x "+buy.count+"</p>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</div>");
            }
        }else{
            title.html(cart.msg);
            orderList.html("")
            Cart.html("");//隐藏
            btn.hide();
        }
    })
}