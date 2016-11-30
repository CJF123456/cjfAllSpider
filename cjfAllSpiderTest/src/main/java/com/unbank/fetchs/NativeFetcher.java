package com.unbank.fetchs;

import org.jsoup.nodes.Document;

public interface NativeFetcher extends Fetcher
{

	Document fetchText(String htmlString);
	
}
