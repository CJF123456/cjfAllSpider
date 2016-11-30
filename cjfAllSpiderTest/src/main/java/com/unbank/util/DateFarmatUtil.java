package com.unbank.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author Administrator
 * 
 */
public class DateFarmatUtil {

	/**
	 * 获取当前时间
	 * 
	 * @return nowTime
	 */
	public String getNowDay() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 可以方便地修改日期格式
		String nowTime = dateFormat.format(now);
		return nowTime;
	}

	public static void main(String[] args) {
		String date = new DateFarmatUtil().getNowDay();
		System.out.println(date);
	}
}
