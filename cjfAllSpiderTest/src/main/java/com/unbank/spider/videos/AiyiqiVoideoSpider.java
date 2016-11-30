package com.unbank.spider.videos;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.fetch.HtmlUnitFetcher;
import com.unbank.fetchs.JsoupNetFetcher;
import com.unbank.filter.URLBaseFilter;
import com.unbank.filter.URLFilter;
import com.unbank.store.RateValueStore;
import com.unbank.util.MD5;

public class AiyiqiVoideoSpider {

	private static String detailUrl;

	/**
	 * 爱奇艺下所有网络剧采集
	 * 
	 * @param args
	 */
	//
	// span.curPage
	public static void main(String[] args) {
		for (int i = 1; i <= 4; i++) {
			String url = "http://list.iqiyi.com/www/2/-24065------------11-"
					+ i + "-1-iqiyi--.html";
			getURL(url);
		}
	}

	private static void getURL(String url) {
		Document document = new JsoupNetFetcher().fetchText(url);
		 //System.out.println(document);
		Elements elements = document.select("div.wrapper-piclist");
		for (Element element : elements) {
			String aString = element.select("ul>li>div.site-piclist_pic>a")
					.attr("href");
			System.out.println(aString +"dasfdasf");
			
			//getdoucmentByurl(aString);
		}
	}

//	private static void getdoucmentByurl(String aString) {
//		String tableName = "aiqiyi_wangju";
//		System.out.println(aString);
//		MD5 md = new MD5();
//		Document document = new JsoupNetFetcher().fetchText(aString);
//		
//		Elements elements = document.select("div.result_detail-minH");
//		for (Element element : elements) {
//			String name = element.select("h1.main_title>a").first().text();
//			String nameNum = element.select("h1.main_title>span.sub_title")
//					.first().text();
//			String zhuyanName = element.select("div>div.role-album").text(); // left_col
//			String director = element.select("div>div.left_col").text();
//			String address = element.select("div>div.right_col").text();//
//			String type = element.select("div:nth-child(5)>div.left_col")
//					.text();
//			String language = element.select("div:nth-child(5)>div.right_col")
//					.text();
//			String num = element.select("div:nth-child(6)>div.right_col")
//					.text(); // 集数playcountWrapper
//			String playcountWrapper = element.select(
//					"div:nth-child(7)>div.playcountWrapper").text();// data-moreorless="moreinfo"
//			String detail = element.select(
//					"div:nth-child(8)>span[data-moreorless='moreinfo']").text();
//
////			//String detailUrl1 = name + nameNum + zhuyanName + zhuyanName
////					+ director + address + type + language + num
////					+ playcountWrapper;
//
//			//detailUrl = md.GetMD5Code(detailUrl1);
//			
//				try {
//					Map<String, Object> colums = new HashMap<String, Object>();
//
//					colums.put("name", name);
//					colums.put("nameNum", nameNum);
//					colums.put("zhuyanName", zhuyanName);
//					colums.put("director", director);
//					colums.put("address", address);
//					colums.put("language", language);
//					colums.put("num", num);
//					colums.put("playcountWrapper", playcountWrapper);
//					colums.put("detail", detail);
//				//	colums.put("detailUrl", detailUrl);
//					new RateValueStore().saveValues(tableName, colums);
//					colums.clear();
//				} catch (Exception e) {
//					e.printStackTrace();
//					continue;
//
//				}
//		}
//	}
}
