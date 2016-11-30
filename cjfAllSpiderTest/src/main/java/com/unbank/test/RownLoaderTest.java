package com.unbank.test;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class RownLoaderTest {

	public static void main(String[] args) {

		String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E7%9B%B8%E4%BC%BC%E7%8E%87%20%E8%8B%B1%E6%96%87&oq=%E7%9B%B8%E4%BC%BC%E7%8E%87&rsv_pq=8e41e3690010de23&rsv_t=e318XkunWPdPXlTp661pGkh0sq2Qy6aiYDrH4g2RYhLecDNz8LChx7Dp6lk&rsv_enter=1&inputT=7184&rsv_sug3=38&rsv_sug1=29&rsv_sug7=100&bs=%E7%9B%B8%E4%BC%BC%E7%8E%87";
		String path = "D:/TestParser/" + "376" + ".txt";

		String html = downLoader(url);
		System.out.println(html);
		
		FileOperate.saveStringToFile(html, path);
	}

	private static String downLoader(String url) {
		Connection conn = Jsoup.connect(url);
		Document doc = null;

		try {
			doc = conn.post();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (doc == null) {
			return null;

		}

		return doc.toString();
	}

}
