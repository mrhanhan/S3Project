$(function () {
    var list = $(".userrow");
    $.each(list,function (i) {
       var item = $(list[i]);
       item.click(function () {
           $(".userrow").removeClass("selected")
           var a = item[0].className
           item[0].className="";
           $(item).addClass(" selected "+a)


       })
    })
})