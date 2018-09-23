//注册用户的方法！post提交
function addUser1() {
    var numEl = $("#id_u_num");
    var u_num = numEl.value;//获取编号

    var accEl = $("#id_u_account");
    var u_acc = $("#id_u_account").val();//获取账号
    console.log(u_acc)
    var pwdEl  = $("#id_u_pwd");
    var u_pwd = $("#id_u_pwd").val();//获取密码

    var repwdEl =  $("#id_u_repwd")
    var u_repwd =$("#id_u_repwd").val();//获取确认密码

    var nameEl = $("#id_u_name");
    var u_name= $("#id_u_name").val();//获取姓名

    if(!u_name){
        nameEl.title="请输入名称"
        nameEl.focus()
        $(nameEl).addClass("error-input")
        Toast("请输入名称",2000,"top");
        return false;
    }else{
        $(nameEl).removeClass("error-input")
    }

    if(!u_acc){
        accEl.title="请输入账号"
        accEl.focus()
        $(accEl).addClass("error-input")
        Toast("请输入账号",2000,"top");
        return false;
    }else{
        $(accEl).removeClass("error-input")
    }
    if(!u_pwd){
        pwdEl.title="请输入密码"
        pwdEl.focus()
        $(pwdEl).addClass("error-input")
        Toast("请输入密码",2000,"top");
        return false;
    }else{
        $(pwdEl).removeClass("error-input")
    }

    if(u_pwd!=u_repwd){
        repwdEl.title="请输入正确密码"
        repwdEl.focus()
        $(repwdEl).addClass("error-input")
        Toast("两次密码不相同",2000,"top");
        return false;
    }else{
        $(repwdEl).removeClass("error-input")
    }

    if(!$("id_addUser_isok")[0].checked){
        Toast("请同意用户协议",2000,"top");
        return false;
    }

    showDialog(".wait-dialog");

    return false;
}

//注册用户的方法！post提交
function addUser2() {
    var numEl = $("#id_u_num2");
    var u_num = numEl.value;//获取编号

    var accEl = $("#id_u_account2");
    var u_acc = $("#id_u_account2").val();//获取账号
    console.log(u_acc)
    var pwdEl  = $("#id_u_pwd2");
    var u_pwd = $("#id_u_pwd2").val();//获取密码

    var repwdEl =  $("#id_u_repwd2")
    var u_repwd =$("#id_u_repwd2").val();//获取确认密码

    var nameEl = $("#id_u_name2");
    var u_name= $("#id_u_name2").val();//获取姓名

    if(!u_name){
        nameEl.title="请输入名称"
        nameEl.focus()
        $(nameEl).addClass("error-input")
        Toast("请输入名称",2000,"top");
        return false;
    }else{
        $(nameEl).removeClass("error-input")
    }

    if(!u_acc){
        accEl.title="请输入账号"
        accEl.focus()
        $(accEl).addClass("error-input")
        Toast("请输入账号",2000,"top");
        return false;
    }else{
        $(accEl).removeClass("error-input")
    }
    if(!u_pwd){
        pwdEl.title="请输入密码"
        pwdEl.focus()
        $(pwdEl).addClass("error-input")
        Toast("请输入密码",2000,"top");
        return false;
    }else{
        $(pwdEl).removeClass("error-input")
    }

    if(u_pwd!=u_repwd){
        repwdEl.title="请输入正确密码"
        repwdEl.focus()
        $(repwdEl).addClass("error-input")
        Toast("两次密码不相同",2000,"top");
        return false;
    }else{
        $(repwdEl).removeClass("error-input")
    }

    if(!$("id_addUser_isok")[0].checked){
        Toast("请同意用户协议",2000,"top");
        return false;
    }

    showDialog(".wait-dialog");

    return false;
}
