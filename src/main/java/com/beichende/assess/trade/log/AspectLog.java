/**
 * @author 雷瑞铧
 * @version 1.0.0
 * @filename AspectLog.java
 * @time 2017年9月12日 下午7:42:17
 * @copyright(C) 2017 深圳市北辰德科技股份有限公司
 */
package com.beichende.assess.trade.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


/**
 *<简述功能>
 *<功能详细描述>
 * @see
 * @since
 */
//@Aspect
public class AspectLog {
	private  static Logger logger = Logger.getLogger(AspectLog.class.getName()); 
	
	//获取当前时间
	public String getCurrentTime() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long currentTime = System.currentTimeMillis();
		String currentTimeFormat = dateformat.format(currentTime);
		return currentTimeFormat;
	}
	
	
	//@Pointcut("execution(* com.beichende.assess.trade.service.impl..*.*(..))")
	public void myMethod(){
		
	}
	//@Before(value="myMethod()")
	public void recordMethodExecutionTimeStart(){
	
		logger.info("执行方法开始："+ getCurrentTime());
	}
	//@After(value="myMethod()")
	public void recordMethodExecutionTimeStop(){
		
		logger.info("执行方法结束：" + getCurrentTime());
	}
	//@AfterReturning(value="myMethod()")
	public void recordMethodExecutionTimeReturn(){
		
		logger.info("执行方法彻底结束之后：" + getCurrentTime());
	}
}
