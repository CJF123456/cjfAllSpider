package com.unbank.email;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.unbank.zhishicx.PostFetcher;

public class EmailSpiderBy163 {

	public static void main(String[] args) {
		new EmailSpiderBy163().loginByNameAndPass();
	}

	public void loginByNameAndPass() {
		String loginurl = "https://mail.163.com/entry/cgi/ntesdoor?df=mail163_letter&from=web&funcid=loginone&iframe=1&language=-1&passtype=1&product=mail163&net=c&style=-1&race=71_39_205_gz";
		PostFetcher postFetcher = PostFetcher.getInstance();
		Map<String, String> params = new HashMap<String, String>();
		String username = "unbankcjf";
		String password="unbank";
		params.put("username", username);
		params.put("password", password);
		params.put("savelogin", "0");
		params.put("url2", "http://mail.163.com/errorpage/error163.htm");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(" Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8");
		headers.put("Cache-Control", "no-cache");
		headers.put("Connection", "keep-alive");
		headers.put("Host", "mail.163.com");
		headers.put(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
		String html = postFetcher.post(loginurl, params, headers, "utf-8");
		String locationhref=html.split("top.location.href =")[1].split(";</script>")[0].replace("\"","").trim();
		String sid = locationhref.split("sid=")[1].replace("=mail163_letter", "").trim();
		long starttime = new Date().getTime();
		String cookie="logType=;starttime="+starttime+"; nts_mail_user="+username+":-1:1; df=mail163_letter; "+postFetcher.cookeString;
		Map<String , String> urheaders = new HashMap<String, String>();
		urheaders.put("Host", "mail.163.com");
		urheaders.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
		urheaders.put("cookie",cookie );
		String reurl =locationhref;
		String html1 = postFetcher.get(reurl, urheaders, "utf-8");
		Map<String , String> deheaders = new HashMap<String, String>();
		deheaders.put("Host", "mail.163.com");
		deheaders.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
		deheaders.put("cookie",cookie+"Coremail.sid="+sid );
		deheaders.put("Referer:", locationhref);
		String detailurl="http://mail.163.com/contacts/call.do?uid="+username+"@163.com&sid="+sid+"&from=webmail&cmd=newapi.getContacts&vcardver=3.0&ctype=markedcontacts";
		System.out.println(cookie+"Coremail.sid="+sid);
		String htmldetail = postFetcher.get(detailurl, deheaders, "utf-8");
		System.out.println(htmldetail);

	}
}
