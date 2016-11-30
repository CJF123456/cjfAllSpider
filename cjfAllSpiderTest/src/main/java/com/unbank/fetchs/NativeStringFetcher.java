package com.unbank.fetchs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class NativeStringFetcher implements NativeFetcher
{

	public Document fetchText(String htmlString)
	{
		return Jsoup.parse(htmlString);
	}

	public String fetchImage(String imageSrc, ImageFilter imageFilter)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
