/**
 * 
 */
package com.unbank.meizitu.meizi;

import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.meizitu.fetcher.HttpClientFetcher;


/**
 * @ClassName:  MeiziListThread
 * @Description: TODO
 * @author:  Administrator
 * @version: V1.0
 * @date:  2016-12-5 上午9:38:36
 */
public class MeiziListThread implements Runnable {

	public LinkedBlockingQueue<String> listQueue;
	public LinkedBlockingQueue<String> imgsQueue;
	
	/**
	 * 构造方法
	 * @param listQueue
	 * @param imgsQueue
	 */
	public MeiziListThread(LinkedBlockingQueue<String> listQueue,
			LinkedBlockingQueue<String> imgsQueue) {
		super();
		this.listQueue = listQueue;
		this.imgsQueue = imgsQueue;
	}

	




	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (listQueue.size()>0) {
				try {
					String url =listQueue.take();
					parseList(url);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}



	/** 
	 * @description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @param url    
	 * @return:       void    
	 * @throws 
	 */
	private void parseList(String url) {
		// TODO Auto-generated method stub
		String html=new HttpClientFetcher().getHtml(url, "gb2312");
		Document document=Jsoup.parse(html);
		Elements elements=document.select(".wp-item");
		for (Element element : elements) {
			Element aElement = element.select(".tit > a").first();
			String title = aElement.text().trim();
			String href = aElement.absUrl("href");
			downDetailPage(title, href);
		}
	}		


	/** 
	 * @description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @param title
	 * @param:        @param href    
	 * @return:       void    
	 * @throws 
	 */
	private void downDetailPage(String title, String url) {
		String html = new HttpClientFetcher().getHtml(url, "gb2312");
		Document document = Jsoup.parse(html, url);
		Elements imgs = document.select("#picture > p > img");
		for (int i = 0; i < imgs.size(); i++) {
			Element element = imgs.get(i);
			String imgurl=element.attr("src");
			System.out.println(" 获取到  img  链接" + imgurl);
			try {
				imgsQueue.put(imgurl);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
