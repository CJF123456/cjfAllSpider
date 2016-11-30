package com.unbank.zhishicx;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ZhishicxLogin {

	
	public void loginByNameAndPass() {
		String loginurl = "http://www.zhishicx.com/webword/view/login.do";
		PostFetcher postFetcher = PostFetcher.getInstance();
		Map<String, String> params = new HashMap<String, String>();
		params.put("account", "18310068076");
		params.put("pass", "123456");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Host", "www.zhishicx.com");
		headers.put("Origin", "http://www.chinacp.com.cn");
		headers.put("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		headers.put("Referer",
				"http://www.zhishicx.com/webword/news/view/index.do");
		headers.put("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
		headers.put("X-Requested-With", "XMLHttpRequest");
		String html = postFetcher.post(loginurl, params, headers, "utf-8");

	}
}
