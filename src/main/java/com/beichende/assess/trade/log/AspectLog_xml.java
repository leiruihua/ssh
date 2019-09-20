/**
 * @author 雷瑞铧
 * @version 1.0.0
 * @filename AspectLog.java
 * @time 2017年9月12日 下午7:42:17
 * @copyright(C) 2017 深圳市北辰德科技股份有限公司
 */
package com.beichende.assess.trade.log;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;


/**
 * <简述功能>日志切面
 * <功能详细描述>
 *
 * @see
 */

public class AspectLog_xml {
    private static Logger logger = Logger.getLogger(AspectLog_xml.class.getName());

    //获取当前时间
    public String getCurrentTime() {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = System.currentTimeMillis();
        String currentTimeFormat = dateformat.format(currentTime);
        return currentTimeFormat;
    }

    public void before() {
        logger.info("执行方法开始：" + getCurrentTime());
    }

    public void after() {
        logger.info("执行方法结束：" + getCurrentTime());
    }

    public void afterReturning() {
        logger.info("执行方法彻底结束之后：" + getCurrentTime());
    }
}
