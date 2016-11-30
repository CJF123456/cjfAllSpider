package com.unbank.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.fetch.HtmlUnitFetcher;

public class test {
	
	public static void main(String[] args) {
//		String url = "http://news.hexun.com/economy/";
//		
//		HtmlUnitFetcher htmlUnitFetcher = HtmlUnitFetcher.getinstence();
//		String html = htmlUnitFetcher.get(url);
//		System.out.println(html);
//		Document document = Jsoup.parse(html);
//		Elements elements = document.select("div.site-piclist_pic");
//		for (Element element : elements) {
//			System.out.println(element);
//		}
		int sleeptime = (int) (Math.random() * 50000);
		System.out.println(sleeptime);
	}

}
