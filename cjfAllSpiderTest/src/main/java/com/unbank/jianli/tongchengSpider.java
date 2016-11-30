package com.unbank.jianli;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.unbank.fetch.Fetcher;

public class tongchengSpider {
	
	static String url ="http://www.seedchina.com.cn/defaultInfoList.aspx?Id=2";
	static Fetcher fetcher = Fetcher.getInstance();
	public static void main(String[] args) {
		
		String  html= fetcher.getHtmlWithCookie(url);
		System.out.println(html);
	    Document document=Jsoup.parse(html);
	    System.out.println(document);
	    Elements elements=document.select("#GridView1 > tbody > tr> td>a");
	    System.out.println(elements);
		
	}

}
