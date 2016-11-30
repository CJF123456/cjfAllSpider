package com.unbank.util;

public class Values {
	// 图片服务器地址
	public String IMAGEHOST;
	public static Values v;
	// 数据源编号，在这个编号以后的采集用数据库里的规则
	public Integer WEBSITEID;

	public void init() {
		v = this;
		v.IMAGEHOST = this.IMAGEHOST;
		v.WEBSITEID = this.WEBSITEID;

	}

	public Integer getWEBSITEID() {
		return WEBSITEID;
	}

	public void setWEBSITEID(Integer wEBSITEID) {
		WEBSITEID = wEBSITEID;
	}

	public String getIMAGEHOST() {
		return IMAGEHOST;
	}

	public void setIMAGEHOST(String iMAGEHOST) {
		IMAGEHOST = iMAGEHOST;
	}

}
