package com.unbank.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReaderHtml {
	
	
	
	public static void main(String[] args) {
		String filePath = "C://Users/Administrator/Desktop/article5677/html5/index.html";
		// "res/";
		readTxtFile(filePath);

	}

	public static void readTxtFile(String filePath) {
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), "utf-8");// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String html = lineTxt;
					Document document = Jsoup.parse(html);
					Element element = document.body().append(html);
					//System.out.println(element);
					Elements allElements = element.children();
					for (Element element2 : allElements) {
						if (element2.tagName().equals("br")) {
							// 如果是br 
							element2.remove();
						}
						System.out.println(element2.text().replaceAll(" ", "").trim());
					}
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}

}
