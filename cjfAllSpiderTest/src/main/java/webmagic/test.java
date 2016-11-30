package webmagic;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

public class test {

	public static void main(String[] args) {
		Spider.create(new MoocSpider())
				.addUrl("http://www.imooc.com/user/newlogin/from_url/1005/")
				.addPipeline(new ConsolePipeline()).thread(5).run();
	}
}
