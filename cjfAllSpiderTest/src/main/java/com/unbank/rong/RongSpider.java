package com.unbank.rong;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.unbank.fetch.PostFetcher;

/**
 * @name 巨潮咨询
 * @author Administrator
 * @date 2016-11-08
 * @remark 分页采用点击瀑布流，请求后返回Json串
 */
public class RongSpider {

	private static String url = "http://www.cninfo.com.cn/cninfo-new/announcement/query";
	private static Map<String, String> params;
	private static String charset = "utf-8";
	private static PostFetcher fetcher = PostFetcher.getInstance();

	public static void main(String[] args) {
		RongSpider rongSpider = new RongSpider();
		rongSpider.getSpider();

	}

	private String num;
	private String path="D://";
	private String name="bong";
	String con=null;
	private void getSpider() {
		for (int i = 2; i <= 5; i++) {
			num = "" + i;
			getData(num);
			String html = fetcher.post(url, params, null, charset);
			JSONObject body = JSONObject.fromObject(html);
			JSONArray resultBody = body.getJSONArray("announcements");
			for (Object object : resultBody) {
				JSONObject jsonObject = JSONObject.fromObject(object);
				String title = (String) jsonObject.get("announcementTitle");
				String code = (String) jsonObject.get("secCode");
				Long announcementTime = (Long) jsonObject.get("storageTime");
				con=code + title + announcementTime;
				saveToFile(path,name,con);
			}
			params.clear();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void getData(String num) {
		params = new HashMap<String, String>();
		params.put("stock", "");
		params.put("searchkey", "");
		params.put("plate", "");
		params.put("category", "");
		params.put("trade", "");
		params.put("columnTitle", "历史公告查询");
		params.put("column", "szse");
		params.put("pageNum", "" + num);
		params.put("pageSize", "30");
		params.put("tabName", "fulltext");
		params.put("sortName", "");
		params.put("sortType", "");
		params.put("limit", "");
		params.put("showTitle", "");
		params.put("seDate", "2015-11-08 ~ 2016-11-08");
	}

	public void saveToFile(String path, String name, String con) {
		if (!name.endsWith(".txt"))
			name = name + ".txt";
		File file = new File(path + File.separator + name);
		FileOutputStream fos = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fos = new FileOutputStream(file,true);
			fos.write(con.getBytes());
			fos.write('\r');
		} catch (Exception e) {
			System.out.println("文件出错");
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
			}
		}
	}

}
