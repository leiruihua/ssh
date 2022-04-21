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

    private static final Logger logger = Logger.getLogger(AspectLog_xml.class.getSimpleName());

    //获取当前时间
    public String getCurrentTime() {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = System.currentTimeMillis();
        return dateformat.format(currentTime);
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
