package com.unbank.baidu;

import org.jsoup.nodes.Document;

public class BaiduDataSpider {

	public static void main(String[] args) {
		String url = "https://www.baidu.com/";

		PostFetcher postFetcher = new PostFetcher();
		Document html = postFetcher.getDoument(url, null, null, "utf-8");
		System.out.println(html);

		String cookie = postFetcher.cookeString;
		System.out.println(cookie);
	}
}
