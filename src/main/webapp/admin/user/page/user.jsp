<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/jsp/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户测试-easyui</title>
    <!-- 让浏览器不去请求网站根目录favicon.ico图标，IE8及以下浏览器不支持 -->
    <link rel="icon" href="data:image/ico;base64,aWNv">
    <link rel="stylesheet" type="text/css" href="${path}/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${path}/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${path}/admin/user/css/user.css">
    <!-- IE只能识别defer,不能识别async -->
    <script type="text/javascript"
            defer
            async="true"
            src="${path}/common/js/require.js"
            data-main="${path}/common/js/main.js"
            data-lib="${path}/admin/user/js/user.js">
    </script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div id="conArea" data-options="region:'north',border:false" style="height:70px;">
        <div class="easyui-layout" data-options="fit:true">

            <div id="panel0" data-options="region:'west',border:false" style="width:4px;"></div>

            <div id="panel1" data-options="region:'center',border:false"
                 style="visibility: hidden;overflow: hidden; padding: 0px;background-color:#f6f6f6">
                <form id="vagueQuery" method="post">
                    <table>
                        <tr>
                            <td>
                                姓名:<input id="userName" class="easyui-validatebox" type="text" name="userName" size="4"
                                          data-options="validType:['CHS','length[2,4]']"></input>
                                年龄:<input id="userAgeStart" class="easyui-validatebox" type="text" name="userAgeStart"
                                          size="2" data-options="validType:'Age'"></input> --
                                <input id="userAgeStop" class="easyui-validatebox" type="text" name="userAgeStop"
                                       size="2" data-options="validType:'Age'"></input>
                                性别:<select id="userSex" name="userSex">
                                <option value="" selected="selected">--请选择--</option>
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select>
                                手机号码:<input id="userPhone" class="easyui-validatebox" type="text" name="userPhone"
                                            size="8" data-options="validType:'Mobile'"></input>
                            </td>
                            <td>
                                <!-- plain:true表示按钮变成方形的 -->
                                <a href="javascript:void(0)" class="easyui-linkbutton"
                                   data-options="iconCls:'icon-search',plain:true" id="btnQuery">查询</a>
                                <a href="javascript:void(0)" class="easyui-linkbutton"
                                   data-options="iconCls:'icon-redo',plain:true" onclick="clearForm();">清空</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>

            <div id="panel2" data-options="region:'east',border:false" style="width:4px;"></div>

            <div id="panel3" data-options="region:'north',border:false"
                 style="visibility:hidden;height:32px; overflow: hidden;">
                <div class="easyui-tabs" fit="true" border="false">
                    <div title="用户管理"></div>
                </div>
            </div>

            <div id="panel4" data-options="region:'south',border:false" style="height:2px; overflow: hidden;"></div>
        </div>
    </div>

    <!----------------------------------- 数据显示区域 ----------------------------------->

    <div data-options="region:'center',border:false">
        <div class="easyui-layout" data-options="fit:true">
            <div id="panel0Data" data-options="region:'east',border:false" style="width:2px;"></div>
            <div id="panel1Data" data-options="region:'west',border:false" style="width:2px;"></div>
            <div data-options="region:'center',border:false">
                <!-- 用户的datagrid -->
                <table id="tt"></table>
            </div>
            <div id="panel2Data" data-options="region:'south',border:false"
                 style="height: 0px; overflow: hidden;"></div>
        </div>
    </div>
</div>
<!-- 按钮组 -->
<div id="user_tb" style="height:30px;display:none">
    <table>
        <tr>
            <td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
                   id="btnAdd">新增</a></td>
            <td>
                <div class="datagrid-btn-separator"></div>
            </td>
            <td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"
                   id="btnDelete">删除</a></td>
            <td>
                <div class="datagrid-btn-separator"></div>
            </td>
            <td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
                   id="btnEdit">修改</a></td>
            <td>
                <div class="datagrid-btn-separator"></div>
            </td>
            <td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true"
                   id="btnSave">保存</a></td>
            <td>
                <div class="datagrid-btn-separator"></div>
            </td>
            <td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true"
                   id="btnUndo">取消编辑</a></td>
        </tr>
    </table>
</div>


<!-- 新增用户 -->
<div id="dlgUserAdd" class="easyui-dialog" title="新增用户"
     data-options="iconCls:'icon-save',closed:true,modal:true,buttons:'#userAddBottomButton'"
     style="width:400px;height:200px;padding:10px">
    <div id="userAddBox" style="display:none;">
        <form id="userAddForm" method="post">
            <table class="TableStyle">
                <tr>
                    <td>姓&nbsp;&nbsp;名:</td>
                    <td><input class="easyui-validatebox" type="text" name="userOne.userName"
                               data-options="required:true,validType:['CHS','length[2,4]']"></input></td>
                </tr>
                <tr>
                    <td>性&nbsp;&nbsp;别:</td>
                    <td>
                        <input class="easyui-validatebox" type="radio" name="userOne.userSex" data-options="required:true"
                               value="1" checked="checked"/>男
                        <input class="easyui-validatebox" type="radio" name="userOne.userSex" data-options="required:true"
                               value="2"/>女
                    </td>
                </tr>
                <tr>
                    <td>年&nbsp;&nbsp;龄:</td>
                    <td><input class="easyui-validatebox" type="text" name="userOne.userAge"
                               data-options="required:true,validType:'Age'"></input></td>
                </tr>
                <tr>
                    <td>手机号码:</td>
                    <td><input class="easyui-validatebox" type="text" name="userOne.userPhone"
                               data-options="required:true,validType:'Mobile'"></input></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div id="userAddBottomButton" class='bottomButton' style="display:none;">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="userAddSubmitForm();">提交</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo'"
       onclick="$('#ff').find(':text').val('');">重置</a>
</div>

<!-- 修改用户-->
<div id="dlgUserEdit" class="easyui-dialog" title="修改用户"
     data-options="iconCls:'icon-save',closed:true,modal:true,buttons:'#userEditBottomButton'"
     style="width:400px;height:200px;padding:10px">
    <div id="userEditBox" style="display:none;">
        <form id="userEditForm" method="post">
            <input id="userNo2" name="userTwo.userNo" value="1" type="hidden"/>
            <table class="TableStyle">
                <tr>
                    <td align="right">姓&nbsp;&nbsp;名:</td>
                    <td align="left"><input id="userName2" name="userTwo.userName" class="easyui-validatebox" type="text"
                                            data-options="required:true,validType:['CHS','length[2,4]']"></input></td>
                </tr>
                <tr>
                    <td>性&nbsp;&nbsp;别:</td>
                    <td>
                        <input id="userSex21" name="userTwo.userSex" class="easyui-validatebox" type="radio"
                               data-options="required:true" value="1"/><label>男</label>
                        <input id="userSex22" name="userTwo.userSex" class="easyui-validatebox" type="radio"
                               data-options="required:true" value="2"/><label>女</label>
                    </td>
                </tr>
                <tr>
                    <td>年&nbsp;&nbsp;龄:</td>
                    <td><input id="userAge2" name="userTwo.userAge" class="easyui-validatebox" type="text"
                               data-options="required:true,validType:'Age'"></input></td>
                </tr>
                <tr>
                    <td>手机号码:</td>
                    <td><input id="userPhone2" name="userTwo.userPhone" class="easyui-validatebox" type="text"
                               data-options="required:true,validType:'Mobile'"></input></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div id="userEditBottomButton" class="bottomButton" style="display:none;">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
       onclick="userEditSubmitForm();">确认</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
       onclick="$('#dlgUserEdit').dialog('close')">取消</a>
</div>

<!--点击鼠标右键弹出的菜单  -->
<div id="menu" class="easyui-menu" style="width:50px;display:none">
    <div onclick="$('#dlgUserAdd').dialog('open')" iconCls="icon-add">新增</div>
    <div onclick="deleteCheckedUser()" iconCls="icon-remove">删除</div>
    <div onclick="rightClickUpdateUser()" iconCls="icon-edit">修改</div>
</div>

</body>
</html>