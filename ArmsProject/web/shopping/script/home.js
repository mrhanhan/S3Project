function showPlane(ele) {
    var parent = ele.parentElement.parentElement.parentElement;

    var goods_form = parent.querySelector(".goods_form");
    var form = goods_form.querySelector("form")

    if ($(goods_form).css("height") != "80px") {
        $.get("cart/get?gnum="+form['goods_num'].value,function (obj) {
            if(!obj.cart){
                Toast("网络出错".toString(),3000,"top")
                return;
            }
            if(obj.cart.login){
                form["size"] = obj.cart.buy.size
                $(form).attr("price",obj.cart.buy.price)
                goods_form.querySelector(".money").innerHTML="价格:"+(obj.cart.buy.price*obj.cart.buy.size);
                $(".goods_form").css({"height": "0px","border":"1px solid #00000000"})
                $(goods_form).css({"height": "80px","border":"1px solid #000000"})
            }else{
                Toast(obj.cart.msg.toString(),3000,"top")
            }
        })

    }else{
        $(goods_form).css({"height": "0px","border":"1px solid #00000000"})
    }
}

/**
 * 添加商品
 * @param from
 * @returns {boolean}
 */
function addGoods(from) {
    var goodsId = from['goods_num'].value;
    var size = from['size'].value;
    $.post("cart/add",{
        "goodsNum":goodsId,"buyNum":size
    },function (obj) {
        Toast(obj.cart.toString(),3000,"top")
       if(obj.cart){
           Toast(obj.cart.msg,3000,"top")
       }else{
           Toast("添加失败:网络出错",3000,"top")
       }
    })

    return false;
}

function changeMoney(btn) {
    var parent = btn.parentElement;
    var price = parseFloat($(parent).attr("price"))
    parent.querySelector(".money").innerHTML = "价格:"+(parseInt(btn.value)*price)
}