package webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;


	/**
	 * 
	 */
	public class MoocSpider implements PageProcessor{
		private Site site =new Site().setRetryTimes(3).setSleepTime(100)
				//添加cookie之前一定要先设置主机地址，否则cookie信息不生效
				.setDomain("www.imooc.com")
				//添加抓包获取的cookie信息
				.addCookie("Hm_lvt_f0cfcccd7b1393990c78efdeebff3968", "1476943377")
				.addCookie("Hm_lpvt_f0cfcccd7b1393990c78efdeebff3968", "1476949361")
				.addCookie("PHPSESSID", "ju2ku734jg8jkjddqtclv9km20")
				.addCookie("apsid",
						"dhNGFkNmFhYjgyYmNmMWE0M2IwODhhMGFlMjQ1MTgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANDI0NTg1NgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADRhYWIwMWJiOGY5ZWJhYzg3ZDM4NjA3ZmJlZTg3MzU3i3UIWIt1CFg%3DZj")
				.addCookie("cvde", "58085e1045b72-20").addCookie("imooc_isnew", "1")
				.addCookie("imooc_isnew_ct", "1476943376")
				.addCookie("imooc_uuid", "8a0656ab-1729-4e09-a55e-1b140c19f65f")
				.addCookie("loginstate", "1")
				//添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的
				.addHeader("User-Agent",
						"ozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.516.400 QQBrowser/9.4.8188.400")
				.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.addHeader("Accept-Encoding", "gzip, deflate, sdch").addHeader("Accept-Language", "zh-CN,zh;q=0.8")
				.addHeader("Connection", "keep-alive").addHeader("Referer", "http://www.imooc.com/");
		
		public void process(Page page) {
			System.out.println(page.getHtml());
			page.putField("aboutme", page.getHtml().xpath("//textarea[@id='aboutme']/text()").toString());
			
		}

		public Site getSite() {
			// TODO Auto-generated method stub
			return site;
		}
		
}
