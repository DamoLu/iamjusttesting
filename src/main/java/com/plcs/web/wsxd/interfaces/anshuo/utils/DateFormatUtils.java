package com.plcs.web.wsxd.interfaces.anshuo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luqihua
 * @title: DateFormatUtils
 * @projectName plcs-web
 * @description: TODO
 * @date 2019/7/18
 */
public class DateFormatUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateFormatUtils.class);
    public static Date parseToDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            logger.error("日期解析错误");
        }
        return null;
    }
}
