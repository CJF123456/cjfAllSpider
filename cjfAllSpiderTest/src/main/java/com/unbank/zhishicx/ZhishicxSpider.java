package com.unbank.zhishicx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.unbank.fetchs.JsoupNetFetcher;

public class ZhishicxSpider {

	public static void main(String[] args) {
		new ZhishicxLogin().loginByNameAndPass();
		new ZhishicxSpider().getAllDataByUrl();
	}

	public void getAllDataByUrl() {
		String url = "http://www.zhishicx.com/webword/user/myfavorite.do";
		PostFetcher postFetcher = PostFetcher.getInstance();
		String html = postFetcher.post(url, null, null, "utf-8");
		Document document = Jsoup.parse(html, "utf-8");

		// Document document=new JsoupNetFetcher().fetchText(url);
		System.out.println(document);
		// Elements elements = document.select("ul.news_list");
		// for (Element element : elements) {
		// String title = element.select("h3>a").text();
		// System.out.println(title);
		// }
	}

}
