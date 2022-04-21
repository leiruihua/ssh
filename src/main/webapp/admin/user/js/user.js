/**
 * author 雷瑞铧
 * */
$(function () {
    /**管道级别*/
    var pipingLevel = [{"value": "男", "text": "男"}, {"value": "女", "text": "女"}];//json格式 ,这样命名是因为用到修改方法表单塞值
    var datagrid = null;//定义全局变量datagrid
    var editRow = undefined; //定义全局变量：当前编辑的行的索引
    //渲染datagrid
    datagrid = $('#tt').datagrid({
        loadMsg: '数据加载中....',
        url: $.path + '/userAction!list.action',
        title: '',
        iconCls: '',
        singleSelect: false,//true表示只能单选某行，false表示可以选择多行
        singSelect: false,
        pagination: true,//显示datagrid自带的底部分页列
        rownumbers: true,//行号
        pageSize: 10,
        pageList: [10, 15, 25],
        fit: true,//datagrid里的表格宽高随着center的大小改变做自适应改变
        fitColumns: false,//表示无论怎么调整datagrid大小都不会出现滚动条，哪怕数据都挤在一起，数据列少的情况使用true
        //改为false的时候则可以固定宽高，数据列太多显示不下的时候出现滚动条
        nowarp: false,//默认为true，就是某列的内容太多，它也给你一行显示，如果改为false，它会多行显示
                      //不过文本内容必须为汉字或者英文单词组成，他才能智能的给你把内容换行，否则它不知道怎么换
        border: false,//作用是去掉导航标题与datagrid表格直接的连接横线
        //idField:'userNo',//做跨页选中多行数据删除时会用到，但是会影响之前的多选
        sortName: 'userNo',//第一次默认为userNo传到后台，后来选择点击（添加sortTable:true属性的一列），就把那一列的field的属相传递
        order: 'asc',//定义升序排列,
        //sortable:true让其变为可点击的排序列,editable: false表示不可被编辑 ,panelHeight:45表示下拉框的高度 ，formatter鼠标移上去在指针的右下角生成提示
        columns: [[
            {field: 'checkid', checkbox: true},//在每列前面加个多选框
            {title: '编号', field: 'userNo', width: '50', align: 'center', sortable: true, hidden: true},
            {
                title: '姓名',
                field: 'userName',
                width: '100',
                align: 'center',
                editor: {type: 'validatebox', options: {required: true, validType: ['CHS', 'length[2,4]']}},
                formatter: function (value, rowIndex, rowData) {
                    return '<span title="' + value + '">' + value + '</span>';
                }
            },
            {
                title: '性别',
                field: 'userSex',
                width: '100',
                align: 'center',
                sortable: true,
                editor: {
                    type: 'combobox',
                    options: {
                        required: true,
                        data: pipingLevel,
                        valueField: "value",
                        textField: "text",
                        editable: false,
                        panelHeight: 45,
                    }
                },
                formatter: function (value, rowIndex, rowData) {
                    return '<span title="' + value + '">' + value + '</span>';
                }
            },
            {
                title: '年龄',
                field: 'userAge',
                width: '100',
                align: 'center',
                sortable: true,
                editor: {type: 'validatebox', options: {required: true, validType: ['Age']}},
                formatter: function (value, rowIndex, rowData) {
                    return '<span title="' + value + '">' + value + '</span>';
                }
            },
            {
                title: '手机号码',
                field: 'userPhone',
                width: '100',
                align: 'center',
                editor: {type: 'validatebox', options: {required: true, validType: ['Mobile']}},
                formatter: function (value, rowIndex, rowData) {
                    return '<span title="' + value + '">' + value + '</span>';
                }
            }
        ]],
        //数据加载之前
        onBeforeLoad: function () {
            var flag = true;
            if (flag) {
                return true;//发送请求查询数据
            } else {
                return false;//不发送请求
            }
        },
        // 数据加载成功时触发
        onLoadSuccess: function (data) {
            editRow = undefined;//重新把行编辑的状态置为undefined
            $('#tt').datagrid('unselectAll');//取消选择所有当前页中所有的行
            $('#btnAdd').linkbutton('enable');
            $('#btnDelete').linkbutton('enable');
            $('#btnEdit').linkbutton('enable');
            $('#btnQuery').linkbutton('enable');
            if (data.total == 0) {
                msgInfoTip("暂无相关数据。");
            }

        },
        toolbar: '#user_tb',//引入一个按钮组
        //双击开启编辑行
        onDblClickRow: function (rowIndex, rowData) {
            if (editRow != undefined) {//如果editRow有值得话表示
                msgInfoTip("您有一行数据处在编辑状态<br>请选择保存或取消编辑！");
            }
            if (editRow == undefined) {
                datagrid.datagrid("beginEdit", rowIndex);
                editRow = rowIndex;
                /**禁用部分按钮*/
                $('#btnAdd').linkbutton('disable');
                $('#btnDelete').linkbutton('disable');
                $('#btnEdit').linkbutton('disable');
                $('#btnQuery').linkbutton('disable');
            }
        },
        //结束编辑状态调用的函数，如果编辑状态中有的列的值不符合条件，则该函数不会执行
        onAfterEdit: function (rowIndex, rowData, changes) {
            console.info(rowData);//获取编辑状态中的那一列中的所有值
            console.info(changes);//获取某列中的改变字段的值，没发生改变的字段值就不获取
            setFormValue(rowData);//先往隐藏的修改表单塞值
            var a = $("#userEditForm").serialize();//将表单的值序列化
            $.post($.path + "/userAction!editAngleUser.action", a, function (data) {
                $('#dlgUserEdit').dialog('close');
                $('#tt').datagrid('reload');//重新刷新表格，并且显示当前页
                msgInfoTip("修改成功");
            }, "json");
        },
        //右键弹出菜单事件
        onRowContextMenu: function (e, rowIndex, rowData) {
            e.preventDefault();//阻止浏览器自带的右键点击出现菜单事件
            $(this).datagrid('unselectAll');
            $(this).datagrid('selectRow', rowIndex);
            $('#menu').menu('show', {
                left: e.pageX,//捕捉鼠标点击右键位置的x轴距离
                top: e.pageY//捕捉鼠标点击右键位置的Y轴距离
            });
        }
    });


    //增加点击事件
    $('#btnAdd').click(function () {
        if ($(this).linkbutton('options').disabled != true) {
            $('#userAddForm input[type="text"]').val('');//点击增加之前先清空表单
            $('#dlgUserAdd').dialog('open');
        }
    });
    //增加的表单按回车提交
    $('#userAddForm').find('input').on('keyup', function (event) {
        if (event.keyCode == '13') {
            userAddSubmitForm();
        }
    });
    //删除点击事件
    $('#btnDelete').click(function () {
        if ($(this).linkbutton('options').disabled != true) {
            deleteCheckedUser();
        }
    });
    //点击修改按钮时
    $('#btnEdit').click(function () {
        if ($(this).linkbutton('options').disabled != true) {
            var rows = $('#tt').datagrid('getSelections');//获取表格中多选选中的数据
            if (rows.length > 1) {
                msgInfoTip("您不能同时选中多行数据进行修改");
            }
            if (rows.length == 1) {
                var row = $('#tt').datagrid('getSelected');//获取表格中选中的数据
                $('#dlgUserEdit').dialog('open');
                setFormValue(row);
            }
            if (rows.length == 0) {
                msgInfoTip("您未选中任何数据");
            }
        }
    });
    //修改的表单按回车提交
    $('#userEditForm').find('input').on('keyup', function (event) {
        if (event.keyCode == '13') {
            userEditSubmitForm();
        }
    });
    //保存点击事件
    $('#btnSave').click(function () {
        datagrid.datagrid("endEdit", editRow);
    });
    //撤销事件
    $('#btnUndo').click(function () {
        datagrid.datagrid('rejectChanges');//回滚事件
        editRow = undefined;//重新把行编辑的状态置为undefined
        $('#tt').datagrid('unselectAll');//取消选择所有当前页中所有的行
        $('#btnAdd').linkbutton('enable');
        $('#btnDelete').linkbutton('enable');
        $('#btnEdit').linkbutton('enable');
        $('#btnQuery').linkbutton('enable');
    });
    //模糊查询点击事件
    $('#btnQuery').click(function () {
        if ($(this).linkbutton('options').disabled != true) {
            if ($('#vagueQuery').form('validate')) {
                var searchForm = $("#vagueQuery").form();
                //console.info(serializeObject(searchForm));
                /*load后面参数一般传递 方式
                {
                    userName:$("#userName").val(),
                    userAgeStart:$("#userAgeStart").val(),
                    userAgeStop:$("#userAgeStop").val(),
                    userSex:$("#userSex").val(),
                    userPhone:$("#userPhone").val()
                }*/
                $('#tt').datagrid('load', serializeObject(searchForm));//load会默认从第一页加载，后面是经过特殊序列化的参数的参数
            }
        }
    });
});
/*
 * 
 * 一些与后台交互的方法
 * 
 * */

//增加的表单提交
var flag = false;

function userAddSubmitForm() {
    if ($('#userAddForm').form('validate')) {//校验各项输入框是否合法，各项都正确就执行
        if (flag) {
            return;
        }
        flag = true;
        var a = $("#userAddForm").serialize();
        $.post($.path + "/userAction!addUser.action", a, function (data) {
            //console.log(data);
            //alert(JSON.stringify(data));//把json格式的数据以字符串形式弹出
            $('#dlgUserAdd').dialog('close');
            $('#tt').datagrid('reload');//重新刷新表格，并且显示当前页
            msgInfoTip("添加成功");
            flag = false;
        }, "json");
        $('#tt').datagrid('unselectAll');//取消选择所有当前页中所有的行

    }
}

//右击选择修改
function rightClickUpdateUser() {
    var row = $('#tt').datagrid('getSelected');//获取表格中单选选中的数据
    if (row) {
        $('#dlgUserEdit').dialog('open');
        setFormValue(row);
    } else {
        $.messager.progress("close");
        msgInfoTip("您未选中任何数据");
    }
}

//修改完成点击提交
function userEditSubmitForm() {
    this.o = "";//让方法链接到这
    if ($("#userEditForm").form('validate')) {
        var currentFormValue = $("#userEditForm").serialize();
        console.info(currentFormValue);
        if (currentFormValue != initialFormValue) {
            $.post($.path + "/userAction!editAngleUser.action", currentFormValue, function (data) {
                $('#dlgUserEdit').dialog('close');
                $('#tt').datagrid('reload');//重新刷新表格，并且显示当前页
                msgInfoTip("修改成功");
                initialFormValue = "";
            }, "json");
            $('#tt').datagrid('unselectAll');//取消选择所有当前页中所有的行
        } else {
            msgInfoTip("您未修改任何数据");
        }

    }
}

//批量删除方法
function deleteCheckedUser() {
    var rows = $('#tt').datagrid('getSelections');//获取表格中单选选中的数据
    console.info(rows);
    var ids = [];
    if (rows.length > 0) {
        $.messager.confirm('提示框', '你确定要删除吗?', function (f) {
            if (flag) {
                return;
            }
            flag = true;
            if (f) {
                for (var i = 0; i < rows.length; i++) {
                    ids.push(rows[i].userNo);//将选中行的userNo塞入数组中
                }
                console.info(ids);
                $.post($.path + "/userAction!deleteUser.action", "ids=" + ids.join(','), function (data) {//ids.join(',')表示将数组分隔成字符串，用逗号隔开
                    $('#tt').datagrid('reload');//重新刷新表格，并且显示当前页
                    msgInfoTip('删除成功');
                    flag = false;
                }, "json");
            }
        });
    } else {
        //从中间弹出提示
        //$.messager.alert("提示","您未选中任何数据！");
        //从右下角弹出提示
        msgInfoTip("您未选中任何数据");
    }
}

//清除模糊查询表单
function clearForm() {
    $('#vagueQuery input').val('');
    $('#userSex').val('');
}

/*****************调用的公共方法********************/
//往表单塞值并记录值
var initialFormValue = "";

function setFormValue(row) {
    $('#userEditForm').form('load', {//往表单塞值
        'userTwo.userNo': row.userNo,
        'userTwo.userName': row.userName,
        'userTwo.userAge': row.userAge,
        'userTwo.userPhone': row.userPhone,
    });
    if ($('#userSex21').next().text() == row.userSex) {
        $('#userSex21').attr("checked", true);
    } else {
        $('#userSex22').attr("checked", true);
    }
    initialFormValue = $("#userEditForm").serialize();
    console.info(initialFormValue);
}

//将表单序列化
function serializeObject(form) {
    var o = {};
    $.each(form.serializeArray(), function (index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
            o[this['name']] = this['value'];
        }
    });
    return o;
}

//消息提示
function msgInfoTip(msg) {
    $.messager.show({
        title: '消息提示',
        msg: msg,
        timeout: 3000,
        height: 150,
        width: 300,
        showType: 'slide'
    });
}