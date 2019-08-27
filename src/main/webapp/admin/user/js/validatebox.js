$.extend($.fn.validatebox.defaults.rules, {  
    //验证汉字  
    CHS: {  
        validator: function (value) {  
        	var reg =/^[\u0391-\uFFE5]+$/;
            return reg.test(value);  
        },  
        message: '只能输入中文字符'  
    },  
    //移动手机号码验证  
    Mobile: {//value值为文本框中的值  
        validator: function (value) {  
            var reg = /^1[3|4|5|7|8]\d{9}$/;  
            return reg.test(value);  
        },  
        message: '请输入正确的手机号码.'  
    },  
    //国内邮编验证  
    ZipCode: {  
        validator: function (value) {  
            var reg = /^[0-9]\d{5}$/;  
            return reg.test(value);  
        },  
        message: '邮政编码必须为6位数，0开始.'  
    }, 
  //数字  
    Number: {  
        validator: function (value) {  
            var reg =/^[0-9]*$/;  
            return reg.test(value);  
        },  
        message: '请输入数字.'  
    }, 
    //年龄
    Age: {  
        validator: function (value) {  
            var reg =/^(?:[1-9]?\d|150)$/;  
            return reg.test(value);  
        },  
        message: '请输入合法年龄.'  
    }, 
});  
