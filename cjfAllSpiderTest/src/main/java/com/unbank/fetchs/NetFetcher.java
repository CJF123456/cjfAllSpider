package com.unbank.fetchs;

import org.jsoup.nodes.Document;

public interface NetFetcher extends Fetcher
{
	
	 public boolean verifyURL(String url);
	 
	 public String encodeURL(String url);

	Document fetchText(String url);
}
