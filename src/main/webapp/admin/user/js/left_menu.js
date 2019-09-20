$(function () {
    /*$("#div_leftmenu").accordion({
        animate:true,//折叠时是否显示动画效果
        fit: true,//宽高自适应
        border:false,//不显示panel的边框
        //multiple:false//可同时展开多个面板
    });
    $("#div_leftmenu").accordion("add",{

            title: "用户信息",
           content: "用户管理",
           selected:false
       });
    $("#div_leftmenu").accordion("add",{

            title: "远程审核",
           content: "管理员审核",
           selected:false//初始化不选中
       });
    $("#div_leftmenu").accordion("add",{

            title: "实时预警",
           content: "学分预警",
           selected:false
       });
   */
    //异步tree
    $("#div_leftmenu").tree({
        url: 'http://localhost:8080/assess_ssh/menuAction!queryMenu.action',
        lines: true
    });


});
