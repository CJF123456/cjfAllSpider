package com.unbank.page;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.unbank.fetch.PostFetcher;

public class PageSpider {

	private static String url;
	private static Map<String, String> headers=new HashMap<String, String>();
	private static Map<String, String> params=new HashMap<String, String>();

	public static void main(String[] args) {
		PostFetcher fetcher = new PostFetcher();
		url = "http://www.seedchina.com.cn/defaultInfoList.aspx?Id=2";
		String html = fetcher.post(url, params, headers, "utf-8");
		
		Document document = Jsoup.parse(html, "utf-8");
		// System.out.println(document);
		String VIEWSTATE = document.select("input#__VIEWSTATE").attr("value");
		String EVENTVALIDATION = document.select("input#__EVENTVALIDATION")
				.attr("value");
		/*params.put("Id", "2");*/
		params.put("__VIEWSTATE", VIEWSTATE);
		params.put("_EVENTVALIDATION", EVENTVALIDATION);
		params.put("__EVENTTARGET", "GridView1$ctl31$LinkButtonNextPage");
		System.out.println(VIEWSTATE);
		System.out.println("----------------------------------------");
		System.out.println(EVENTVALIDATION);
		//GridView1$ctl31$LinkButtonNextPage
		//GridView1$ctl31$LinkButtonNextPage
		
	}

}
