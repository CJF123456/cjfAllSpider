package webmagic;


import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.Selectors;

public class YidianzixunProcessor implements PageProcessor{
	private Site site=Site.me();//.?等特殊符号需要加//引用符
	private static final String ARTICLE_URL="http://www//.yidianzixun//.com/home//?page=article&id=//w+";
	
	
	public void process(Page page){

	if(page.getUrl().regex(ARTICLE_URL).match()){
	String content=page.getHtml().xpath("//div[@class='content']").toString();

	//1）文章头部:1、标题、2、发表时间、3、文章来源
	String content_hd=Selectors.xpath("//div[@class='content-hd']").select(content);
	String title=Selectors.xpath("//h2/text()").select(content_hd);
	String date=Selectors.xpath("//div[@class='meta']/span[@class='date']/text()").select(content_hd);
	String source=Selectors.xpath("//a[@id='source-name']/text()/text()").select(content_hd);
	//2）文章主题
	String body=Selectors.xpath("//div[@class='content-bd']").select(content);
	System.out.println(body);

	}else{

	List<String>  results=new JsonPathSelector("$.result[*]").selectList(page.getRawText());

	for(String result:results){
	///home?page=article&id={{docid}}&up={{up}}#comment
	///home?page=article&id={{docid}}也是可以支持
	String docid=new JsonPathSelector("docid").select(result);
	String targetUrl="http://www.yidianzixun.com/home?page=article&id="+docid;

	page.addTargetRequest(targetUrl);

	}
	}
	}
	public static void main(String[]args){
	//url的特殊符号‘|’:%7C
	//通过ajax判断请求的参数
	String targetUrl="http://www.yidianzixun.com/api/q/?path=channel%7Cnews-list-for-keyword&display=%E8%8A%B1%E8%8C%B6&word_type=token&fields=docid&fields=category&fields=date&fields=image&fields=image_urls&fields=like&fields=source&fields=title&fields=url&fields=comment_count&fields=summary&fields=up&cstart=10&cend=20&version=999999&infinite=true";
	Spider.create(new YidianzixunProcessor()).addUrl(targetUrl).thread(5).run();
	}
	
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
}
