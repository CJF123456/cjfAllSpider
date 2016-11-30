package com.unbank.baidu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PostFetcher {
	public static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(30000).setConnectTimeout(30000).build();
	public static BasicCookieStore cookieStore = new BasicCookieStore();
	public static CloseableHttpClient httpClient;
	public final String _DEFLAUT_CHARSET = "utf-8";
	public static PostFetcher fetcher = PostFetcher.getInstance();
	public static Log logger = LogFactory.getLog(PostFetcher.class);
	public static String cookeString = "";

	public synchronized static PostFetcher getInstance() {
		if (fetcher == null) {
			fetcher = new PostFetcher();
			PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
			HttpClientBuilder httpClientBuilder = new HttpClientBuilder(false,
					poolingHttpClientConnectionManager, cookieStore);
			httpClient = httpClientBuilder.getHttpClient();
		}
		return fetcher;
	}

	public String inputStream2String(InputStream is, String charset) {
		String temp = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			int i = -1;
			while (true) {
				try {
					i = is.read();
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
				if (i <= -1) {
					break;
				}
				baos.write(i);

			}
			temp = baos.toString(charset);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return temp;

	}

	public String post(String url, Map<String, String> params,
			Map<String, String> headers, String charset) {
		String result = null;
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		HttpPost httpPost = new HttpPost(url);
		try {
			if (headers != null) {
				for (String key : headers.keySet()) {
					httpPost.setHeader(key, headers.get(key));
				}
			}
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (params != null) {
				for (String key : params.keySet()) {
					nvps.add(new BasicNameValuePair(key, params.get(key)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			}
			httpPost.setConfig(requestConfig);
			CloseableHttpResponse response = httpClient.execute(httpPost,
					context);
			Header[] header = response.getAllHeaders();
			for (Header header2 : header) {
				String header1 = header2.toString();
				 System.out.println(header1);
			}

			try {
				InputStream inputStream = response.getEntity().getContent();
				result = inputStream2String(inputStream, charset);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			List<Cookie> cookies = cookieStore.getCookies();
			StringBuffer sb = new StringBuffer();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					sb.append(cookie.getName() + "=" + cookie.getValue() + ";");
					cookeString = sb.toString();
				}
				
			}
			httpPost.abort();
		}

		return result;
	}

	public String get(String url, Map<String, String> headers, String charset) {
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		String useCharset = charset;
		if (charset == null) {
			useCharset = _DEFLAUT_CHARSET;
		}
		HttpGet httpGet = new HttpGet(url);
		if (headers != null) {
			for (String key : headers.keySet()) {
				httpGet.setHeader(key, headers.get(key));
			}
		}
		httpGet.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet,
					context);
			try {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity, useCharset);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Document getDoument(String url, Map<String, String> params,
			Map<String, String> headers, String charset) {
		String html = post(url, params, headers, charset);
		if (html == null) {
			return null;
		}
		Document document = Jsoup.parse(html, url);
		return document;
	}

}
