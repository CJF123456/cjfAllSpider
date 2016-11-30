package com.unbank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UtilTest {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		String dstr1="2016-02-16";  
		String dstr2="1993-10-10";
		Date date11=sdf.parse(dstr1);  	
		Date date22=sdf.parse(dstr2);  	
		boolean ss=DateTool.isSameDate(date11, date22);
		System.out.println(ss);
		if (false==ss) {
			System.out.println("不一样");
			
		}else {
			System.out.println("yiyang");
			
		}
		
		
		int num=(int) DateTool.betweenDate(date11, date22);
		System.out.println(num);
		
	}

}
