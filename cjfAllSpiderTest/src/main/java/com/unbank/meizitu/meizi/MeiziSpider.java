/**
 * 
 */
package com.unbank.meizitu.meizi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName:  MeiziSpider
 * @Description: TODO
 * @author:  Administrator
 * @version: V1.0
 * @date:  2016-12-5 上午9:34:31
 */
public class MeiziSpider {
	
	static LinkedBlockingQueue<String> listQueue = new LinkedBlockingQueue<String>();
	 
	static LinkedBlockingQueue<String> imgesQueue =new LinkedBlockingQueue<String>();
	 
	 public static void main(String[] args) {
		for (int i = 30; i <90; i++) {
			String url = "http://www.meizitu.com/a/list_1_" + i + ".html";
			try {
				listQueue.put(url);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		new Thread(new MeiziListThread(listQueue,imgesQueue)).start();
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executorService.execute(new MeiziImgsThread(imgesQueue));
		}
		executorService.shutdown();
	}

}
