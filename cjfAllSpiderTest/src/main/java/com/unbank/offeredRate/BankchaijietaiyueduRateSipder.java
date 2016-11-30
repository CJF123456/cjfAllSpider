package com.unbank.offeredRate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import com.unbank.fetch.PostFetcher;
import com.unbank.filter.URLBaseFilter;
import com.unbank.filter.URLFilter;
import com.unbank.store.RateValueStore;
import com.unbank.util.MD5;
import com.unbank.util.StringToDate;

/**
 * @采集方式：页面是post提交的请求，通过post请求方式采集数据
 * 
 * @注意点 每个月1号手动执行main方法启动本类，将数据保存在数据库里面 银行间同业拆借月度的模块
 * @date private static String strDate = "2015-08";修改时间;如2015-09
 * @author Administrator
 * @url http://www.chinamoney.com.cn/fe/Channel/21478
 */
@Component
public class BankchaijietaiyueduRateSipder {
	

	public static void main(String[] args) {
		new BankchaijietaiyueduRateSipder().spider();
	}
	public void spider() {
		String tableName = "bankchaijieyuedu_bill";
		
		String strdate1="2014-11";
		String strDate = strdate1.replaceAll("[^0-9]", "").trim();
		System.out.println(strDate);
		String detailUrl;
		MD5 md = new MD5();
		URLFilter urlFilter = new URLBaseFilter();
		PostFetcher postFetcher = PostFetcher.getInstance();
		String url = "http://www.chinamoney.com.cn/fe-c/CreditlendSearchAction.do";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		headers.put("Connection", "keep-alive");
		headers.put("Cookie",
				"JSESSIONID=YJzmVlZd2pV0VDyt0bYzmtH7DJsLzT1XkQL4ZRpBxRNFTWfThKjN!-2078670573");
		headers.put("Host", "www.chinamoney.com.cn");
		headers.put(
				"Referer",
				"http://www.chinamoney.com.cn/fe/static/html/column/marketdata/monthlystatistics/tranoverview/monthlyshow/creditlend/creditLendMonthly.html");
		headers.put("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
		Map<String, String> params = new HashMap<String, String>();
		StringToDate strt = new StringToDate();
		Date date = strt.getStringToDate(strdate1);
		params.put("searchDate", strDate);
		String charset = "utf-8";
		String html = postFetcher.post(url, params, headers, charset);
		Document document = Jsoup.parse(html);
		Elements elements = document
				.select("body > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1)>tbody>tr");
		for (int i = 1; i < elements.size(); i++) {
			Element element = elements.get(i);
			String type = element.select("td:nth-child(1)").text();
			String jiaquanrate = element.select("td:nth-child(2)").text();
			String num = element.select("td:nth-child(3)").text();
			String money = element.select("td:nth-child(4)").text();
			detailUrl = type + jiaquanrate + money + date;
			detailUrl = md.GetMD5Code(detailUrl);
			if (urlFilter.checkNewsURL(detailUrl)) {
				try {
					Map<String, Object> colums = new HashMap<String, Object>();
					colums.put("type", type);
					colums.put("jiaquanrate", jiaquanrate);
					colums.put("money", money);
					colums.put("date", date);
					colums.put("num", num);
					colums.put("detailUrl", detailUrl);
					colums.put("crawlerTime", new Date());
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