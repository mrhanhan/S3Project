<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/8/31 0031
  Time: 22:45
  To change this template use File | Settings | File Templates.
  弹跳框
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="dialog" id="useradd-1" width="50%" left="center" top="13%">
    <div class="dialog-body">
        <div class="title">添加员工</div>
        <form ><!--添加用户-->
            <table>
                <tr>
                    <td>
                        编号:
                    </td>
                    <td>
                        <input class="input" type="text" name="n_u_num" id="id_u_num" readonly value=""/>
                    </td>
                    <td>
                        <span class="error"></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        名称:
                    </td>
                    <td>
                        <input class="input" pattern=".{2,6}" type="text" name="n_u_name" id="id_u_name"/>
                    </td>
                    <td>
                        <span class="error">2-6给个字符</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        账号:
                    </td>
                    <td>
                        <input class="input" pattern="\d{11}" type="text" name="n_u_account" id="id_u_account"
                               value=""/>
                    </td>
                    <td>
                        <span class="error">11为数字</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        密码:
                    </td>
                    <td>
                        <input class="input" pattern="\w{6,16}" type="password" name="n_u_pwd" id="id_u_pwd"/>
                    </td>
                    <td>
                        <span class="error">6-16个字符（字母，数字）</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        确认密码:
                    </td>
                    <td>
                        <input class="input" type="password" name="n_u_repwd" id="id_u_repwd"/>
                    </td>
                    <td>
                        <span class="error">*</span>
                    </td>
                </tr>

                <tr>
                    <td>
                        用户协议:
                    </td>
                    <td colspan="2">
                        <textarea class="textarea" readonly style="width: 100%" rows="5"><%@include file="../staticfile/userProtocl.jsp"  %></textarea>
                        <input type="checkbox" checked id="id_addUser_isok">我同意此协议
                    </td>
                </tr>
            </table>
            <div class="btn-group" style="border-top:1px solid white;padding: 5px 10px; text-align: right;">
                <button class="btn-submit" type="submit">提交</button>
                <button class="btn" type="reset">重置</button>
                <button class="btn-close dialog-btn" bind="#useradd-1" type="button">关闭</button>
            </div>
        </form>
    </div>
</div>

<div class="dialog" id="useradd-2" width="50%" left="center" top="13%">
    <div class="dialog-body">
        <div class="title">添加经理</div>
        <form >
            <table>
                <tr>
                    <td>
                        编号:
                    </td>
                    <td>
                        <input class="input" type="text" name="n_u_num" id="id_u_num2" readonly value=""/>
                    </td>
                    <td>
                        <span class="error"></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        名称:
                    </td>
                    <td>
                        <input class="input" pattern=".{2,6}" type="text" name="n_u_name" id="id_u_name2"/>
                    </td>
                    <td>
                        <span class="error">2-6给个字符</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        账号:
                    </td>
                    <td>
                        <input class="input" pattern="\d{11}" type="text" name="n_u_account" id="id_u_account2"
                               value=""/>
                    </td>
                    <td>
                        <span class="error">11为数字</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        密码:
                    </td>
                    <td>
                        <input class="input" pattern="\w{6,16}" type="password" name="n_u_pwd" id="id_u_pwd2"/>
                    </td>
                    <td>
                        <span class="error">6-16个字符（字母，数字）</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        确认密码:
                    </td>
                    <td>
                        <input class="input" type="password" name="n_u_repwd" id="id_u_repwd2"/>
                    </td>
                    <td>
                        <span class="error">*</span>
                    </td>
                </tr>

                <tr>
                    <td>
                        用户协议:
                    </td>
                    <td colspan="2">
                        <textarea class="textarea" readonly style="width: 100%" rows="5"><%@include file="../staticfile/userProtocl.jsp"%></textarea>
                        <input type="checkbox" checked id="id_addUser2_isok">我同意此协议
                    </td>
                </tr>
            </table>
            <div class="btn-group" style="border-top:1px solid white;padding: 5px 10px; text-align: right;">
                <button class="btn-submit" type="submit">提交</button>
                <button class="btn" type="reset">重置</button>
                <button class="btn-close dialog-btn" bind="#useradd-2" type="button">关闭</button>
            </div>
        </form>
    </div>
</div>
<script src="./admin/script/dialog.js"></script>
