package com.unbank.offeredRate;

import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.unbank.fetch.Fetcher;
import com.unbank.filter.URLBaseFilter;
import com.unbank.filter.URLFilter;
import com.unbank.util.MD5;

/***
 * 内容是一个Json串 格式化json串后获取到数据存库
 * 
 * @name 失信人员和失信企业名单采集
 * @author Administrator
 * 
 */
@Component
public class ShixinAllSpider {
	public static void main(String[] args) {
		new ShixinAllSpider().shixinGetbybaidu();
	}

	public void shixinGetbybaidu() {
		URLFilter urlFilter = new URLBaseFilter();
		MD5 md5 = new MD5();
		String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_id=6899&query=%E5%A4%B1%E4%BF%A1%E8%A2%AB%E6%89%A7%E8%A1%8C%E4%BA%BA%E5%90%8D%E5%8D%95%E6%9F%A5%E8%AF%A2&pn=10&rn=10&ie=utf-8&oe=utf-8&format=json&t=1448005865006&cb=jQuery110203433753933001139_1448005842455&_=1448005842458";

		Fetcher fetcher = Fetcher.getInstance();
		String html = fetcher.getHtmlWithCookie(url);
		String dataString = html.split("data")[1].substring(3).replace(")", "")
				+ "}";
		System.out.println(dataString);
		JSONObject jsonObject = JSONObject.fromObject(dataString);
		JSONArray jsonArray = jsonObject.getJSONArray("result");

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject dataJsonObject = jsonArray.getJSONObject(i);
			String detailUrl = dataJsonObject.get("loc").toString();
			String businessEntity = dataJsonObject.get("businessEntity")
					.toString();
			String age1 = dataJsonObject.get("age").toString(); // 年龄
			int age = Integer.parseInt(age1);
			String sexy = dataJsonObject.get("sexy").toString();// 性别
			String iname = dataJsonObject.get("iname").toString(); // 名称
			String cardNum = dataJsonObject.get("cardNum").toString();// 身份证号
			String caseCode = dataJsonObject.get("caseCode").toString();// 案号
			String areaName = dataJsonObject.get("areaName").toString(); // 省份
			String courtName = dataJsonObject.get("courtName").toString();// 法院
			String duty = dataJsonObject.get("duty").toString();// 生效法律文书确定的义务
			String performance = dataJsonObject.get("performance").toString();// 被执行人的履行情况
			String disruptTypeName = dataJsonObject.get("disruptTypeName")
					.toString();// 失信被执行人行为具体情形
			String publishDate = dataJsonObject.get("publishDate").toString();
			String mdDetailUrl = md5.GetMD5Code(detailUrl);
			if (urlFilter.checkNewsURL(mdDetailUrl)) {
				try {
					if (businessEntity != null && businessEntity.length() != 0) {
						// ShixinUnit shixinUnit = new ShixinUnit();
						// shixinUnit.setIname(iname);
						// shixinUnit.setCardnum(cardNum);
						// shixinUnit.setCasecode(caseCode);
						// shixinUnit.setAreaname(areaName);
						// shixinUnit.setCourtname(courtName);
						// shixinUnit.setDuty(duty);
						// shixinUnit.setPerformance(performance);
						// shixinUnit.setDisrupttypename(disruptTypeName);
						// shixinUnit.setPublishdate(publishDate);
						// shixinUnit.setSouceid(mdDetailUrl);
						// shixinUnit.setBusinessentity(businessEntity);
						// shixinUnit.setClawertime(new Date());
						// new ShixinUnitStore().saveShixinUnit(shixinUnit);
					} else {
						// ShixinPro shixinPro = new ShixinPro();
						// shixinPro.setIname(iname);
						// shixinPro.setCardnum(cardNum);
						// shixinPro.setCasecode(caseCode);
						// shixinPro.setAreaname(areaName);
						// shixinPro.setCourtname(courtName);
						// shixinPro.setDuty(duty);
						// shixinPro.setPerformance(performance);
						// shixinPro.setDisrupttypename(disruptTypeName);
						// shixinPro.setPublishdate(publishDate);
						// shixinPro.setSouceid(mdDetailUrl);
						// shixinPro.setAge(age);
						// shixinPro.setSexy(sexy);
						// shixinPro.setClawertime(new Date());
						// new ShixinProStore().saveShixinPro(shixinPro);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

}
