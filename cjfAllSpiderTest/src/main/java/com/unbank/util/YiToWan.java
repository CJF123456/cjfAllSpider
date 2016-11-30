package com.unbank.util;

import java.text.DecimalFormat;

/**
 * @author Administrator
 * @effect tool 单位转换
 *
 */
public class YiToWan {
	/**
	 * 亿转万
	 * 
	 * @return wan
	 */
	public String getYiToWan(String StringValue) {
		double yi = Double.parseDouble(StringValue);
		double a = yi * 10000.0d;
		DecimalFormat df1 = new DecimalFormat("###");
		String wan = df1.format(a);
		return wan;
	}

}
