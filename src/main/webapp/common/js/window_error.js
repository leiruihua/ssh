/**
 * @param {String}  msg     错误信息 
 * @param {String}  url     出错的文件 
 * @param {Long}    line    出错代码的行号 

 * */
window.onerror = function(msg, url, line) { 
	console.log('错误信息:'+msg);
	console.log('URL:'+url);
	console.log('行数:'+line);
	//return true;
};
