package com.unbank.offeredRate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.unbank.fetch.Fetcher;
import com.unbank.filter.URLBaseFilter;
import com.unbank.filter.URLFilter;
import com.unbank.store.RateValueStore;
import com.unbank.util.MD5;

/**
 * 正常的页面采集
 * 银行间同业拆借行情(IBO)
 * 
 * @author Administrator
 * 
 */
@Component
public class BankchaijietaiRateSipder {

	
public static void main(String[] args) {
	new BankchaijietaiRateSipder().spiderBankchaijietai();
}
	public void spiderBankchaijietai() {
		 String tableName = "bankchaijie_bill";
		String oldUrl = "http://www.chinamoney.com.cn/fe/static/html/column/marketdata/dailyexpress/daily/creditlend/creditLendDaily.html";
		MD5 md = new MD5();
		URLFilter urlFilter = new URLBaseFilter();
		String dateString = null;
		String detailUrl = null;
		String num = null;
		String money = null;
		String zengjian = null;
		String zengjiannum = null;
		String jiaquanavg = null;
		String updown = null;
		Fetcher fetcher = Fetcher.getInstance();
		String html2 = fetcher.getHtmlWithCookie(oldUrl);
		Document cloumElements = Jsoup.parse(html2, "utf-8");
		dateString = cloumElements.select(".datereport-title").text()
				.replaceAll("[^0-9-]", "").trim();
		Elements elements = cloumElements
				.select("table.market-new-text:nth-child(1) > tbody:nth-child(1) > tr:nth-child(4)");

		for (Element element : elements) {
			num = element.select("td:nth-child(1)").text();
			money = element.select("td:nth-child(2)").text();
			zengjian = element.select("td:nth-child(3)").text();
			zengjiannum = element.select("td:nth-child(4)").text();
			jiaquanavg = element.select("td:nth-child(5)").text();
			updown = element.select("td:nth-child(6)").text();
		}
		Elements dElements = cloumElements.select(".market-new-text").get(1)
				.select("tbody > tr");
		for (int i = 2; i < dElements.size() - 1; i++) {
			Element element = dElements.get(i);
			String type = element.select("td:nth-child(1)").text();
			String openrange = element.select("td:nth-child(2)").text();
			String closerange = element.select("td:nth-child(3)").text();
			String maxrange = element.select("td:nth-child(4)").text();
			String minrange = element.select("td:nth-child(5)").text();
			String jiaquanrange = element.select("td:nth-child(6)").text();
			String updownge = element.select("td:nth-child(7)").text();
			String numge = element.select("td:nth-child(8)").text();
			String moneyge = element.select("td:nth-child(9)").text();
			String zengjiange = element.select("td:nth-child(10)").text();

			String detailUrl1 = type + num + money + zengjian + zengjiannum
					+ jiaquanavg + updown + openrange + closerange + maxrange
					+ minrange + jiaquanrange + updownge + updownge + numge
					+ moneyge + zengjiange + dateString;
			detailUrl = md.GetMD5Code(detailUrl1);
			if (urlFilter.checkNewsURL(detailUrl)) {
				try {
					Map<String, Object> colums = new HashMap<String, Object>();

					colums.put("num", num);
					colums.put("money", zengjian);
					colums.put("zengjian", money);
					colums.put("zengjiannum", zengjiannum);
					colums.put("jiaquanavg", jiaquanavg);
					colums.put("updown", updown);
					colums.put("type", type);
					colums.put("openrange", openrange);
					colums.put("closerange", closerange);
					colums.put("maxrange", maxrange);
					colums.put("minrange", minrange);
					colums.put("jiaquanrange", jiaquanrange);
					colums.put("updownge", updownge);
					colums.put("numge", numge);
					colums.put("moneyge", moneyge);
					colums.put("zengjiange", zengjiange);
					colums.put("date", dateString);
					colums.put("crawlerTime", new Date());
					colums.put("detailUrl", detailUrl);
					new RateValueStore().saveValues(tableName, colums);
					colums.clear();
				} catch (Exception e) {
					e.printStackTrace();
					continue;
					
				}
			}

		}
	}
}