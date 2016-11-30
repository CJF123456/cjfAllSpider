package com.unbank.xmlfile;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//dom4j
public class Constants {
	
	
	// 传输设置
	public static Integer CLENTFILEINDEX;
	public static Integer CLENTTASK;
	public static Integer CLENTLIMITNUM;
	// 去重设置
	public static Integer DUPLICATEFILEINDEX;
	public static Integer DUPLICATETASK;
	public static Integer DUPLICATELIMITNUM;
	// 去重白名单
	public static String WHITELISTSTR;
	public static List<String> WHITELIST;
	// 智能编辑平台获取id的IP
	public static String IDIP;
	// 提取关键词IP
	public static String KEYWORDIP;
	// 图数据IP
	public static String TUSHUJUIP;
	// es 搜索ip 本地，远程
	public static String SEACHERIP;
	public static String SEACHERIP_REMOTE;
	// 去重服务IP
	public static String DUPLICATEIP;

	// 规则映射IP
	public static String RULEMAPPINGIP;
	// 指定的分类
	public static List<String> CLASSNAMES;
	// 是否发送重复
	public static Integer ISENDDUPLICATE;

	// 是否发送给本地Es
	public static Integer ISSENDTOES;
	public static Integer ISSENDDUPLICATETOES;
	// 是否发送给远程ES
	public static Integer ISSENDTOREMOTEES;
	public static Integer ISSENDDUPLICATETOREMOTEES;
	// 是否发送到图数据库
	public static Integer ISSENDTUSHUJU;
	public static Integer ISSENDDUPLICATETOTUSHUJU;
	// 是否发送给远程推送系统
	public static Integer ISSENDTORULEMAPPING;
	public static Integer ISSENDDUPLICATETORULEMAPPING;
	
	// 是否发送失败的信息
	public static Integer ISSENDFAILURE;
	
	// 是否自增获取智能编辑平台Id
	public static Integer ISBYZIZENG;
	
	public static void init() {
		try {
			String path = Constants.class.getClassLoader().getResource("")
					.toURI().getPath();
			parseExpandConfigData(path + "expandConfig.xml");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	private static void parseExpandConfigData(String xmlFilePath) {
		Document doc = parse2Document(xmlFilePath);
		Element root = doc.getRootElement();
		// 去重
		Element duplicateElement = root.element("duplicate");
	}

	private static Document parse2Document(String xmlFilePath) {
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(new File(xmlFilePath));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}
}
