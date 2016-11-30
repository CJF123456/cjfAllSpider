package com.unbank.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StringSimilarity {

	
	
	/**
	 * 两个字符串的相同率
	 */
	public float RSAC(String str1, String str2) {
		List<String> list = new ArrayList<String>();
		for (int k = 0; k < str1.length(); k++) {
			for (int j = 0; j < str2.length(); j++) {
				if (str1.charAt(k) == str2.charAt(j)
						&& !isExist(list, str1.substring(k, k + 1))) {
					list.add(str1.substring(k, k + 1));
					break;
				}
			}
		}
		float size = (float) (list.size() * 2)
				/ (str1.length() + str2.length());
		DecimalFormat df = new DecimalFormat("0.00");// 格式化小数，不足的补0
		String ratiostr = df.format(size);// 返回的是String类型的
		float ratio = Float.parseFloat(ratiostr);
		return ratio;
	}

	
	private boolean isExist(List<String> list, String substring) {
		// TODO Auto-generated method stub
		return false;
	}


	public static void main(String[] args) {
		//String str1='wo shi  chenjian锋';
		String str2="wo xi huan chengjian锋";
		//int ss=new String().RSAC(str1, str2);
		//System.out.println(ss);
	}
}
