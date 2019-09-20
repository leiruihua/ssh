/*定义一个js名称数组*/
var main_js = [
    "domRead",
    "jquery",
    "jquery_easyui_min",
    "easyui-lang-zh_CN",
    "validatebox",
    "user"
];
require.config({
    baseUrl: getRootPath(), //获取根路径，不定义的话默认路径是跟require.js文件同级的所在路径
    paths: {//声明为如下6个模块
        "domRead": "common/js/domRead",
        "jquery": "js/jquery-1.7.1",//jquery  
        "jquery_easyui_min": "easyui/jquery_easyui_min",//easyui所需  
        "easyui-lang-zh_CN": "easyui/locale/easyui-lang-zh_CN",//easyui中文
        "validatebox": "admin/user/js/validatebox",//validatebox验证
        "user": "admin/user/js/user",
    },
    shim: {//对于不支持AMD库的js文件，例如bootstrap、自定义的不规范的js文件，可以用如下配置

        'jquery_easyui_min': {
            deps: ['jquery']
        },
        'easyui-lang-zh_CN': {
            deps: ['jquery_easyui_min']
        },
        'validatebox': {
            deps: ['jquery_easyui_min', 'easyui-lang-zh_CN']
        },
        'user': {
            deps: ['jquery_easyui_min', 'easyui-lang-zh_CN'],
            exports: 'User'
        },//user依赖于easyui的js
    }
});
require(main_js, function (domRead, $, j1, j2, j3, j4) {
    /**
     * easyui不支持异步加载，它的渲染代码在前，各种定义在后，导致还没定义就先渲染，所以不会有效果，
     * 因为渲染部分是在easyui中的解析器$.parser.parse()中完成的,以为保证打开页面后组件全部被
     * 正常渲染，应该在$(document).ready()后加上$.parser.parse()
     *
     * */
    $(document).ready(function () {
        $.parser.parse();
    });
    //window.location.reload();
    $('#panel1').css('visibility', 'visible');//让查询表单显示
    $('#panel3').css('visibility', 'visible');//让查询表单显示
    $('#user_tb').css('display', 'block');//让按钮组显现
    $('#userAddBox').css('display', 'block');//让增加用户的表单显示
    $('#userEditBox').css('display', 'block');//让修改用户的表单显示
    $('#userAddBottomButton').css('display', 'block');//让增加用户的表单按钮显示
    $('#userEditBottomButton').css('display', 'block');//让修改用户的表单按钮显示

});


function getRootPath() {
    //获取当前网址，如： http://localhost:8080/a/meun.jsp    
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： a/meun.jsp    
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080   
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/a   
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}    