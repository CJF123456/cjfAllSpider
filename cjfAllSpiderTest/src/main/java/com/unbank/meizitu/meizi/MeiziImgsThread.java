/**
 * 
 */
package com.unbank.meizitu.meizi;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import com.unbank.meizitu.fetcher.HttpClientImageFetcher;


import sun.net.www.http.HttpClient;

/**
 * @ClassName: MeiziImgsThread
 * @Description: TODO
 * @author: Administrator
 * @version: V1.0
 * @date: 2016-12-5 上午10:02:40
 */
public class MeiziImgsThread implements Runnable {

	AtomicInteger atomicInteger = new AtomicInteger();

	public LinkedBlockingQueue<String> imgsQueue;

	/**
	 * @param imgsQueue
	 */
	public MeiziImgsThread(LinkedBlockingQueue<String> imgsQueue) {
		super();
		this.imgsQueue = imgsQueue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (imgsQueue.size() > 0) {
				String url;
				try {
					url = imgsQueue.take();
					System.out.println("正在下图片" + url);
					String name = "meizitu";
					int a = atomicInteger.incrementAndGet();
					String filename = a + ".jpg";
					new HttpClientImageFetcher().downImage(url, name, filename);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}

}
