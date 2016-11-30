package com.unbank.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import net.sf.json.JSONObject;

import com.unbank.fetch.Fetcher;

public class MultithreadingSipider {
	
	public static Fetcher fetcher = Fetcher.getInstance();
	public LinkedBlockingQueue<Object> datas = new LinkedBlockingQueue<Object>();
	public LinkedBlockingQueue<Object> trees = new LinkedBlockingQueue<Object>();
	public static void main(String[] args) {
		new MultithreadingSipider().getQuotasTree();
	}
	public void getQuotasTree() {
		
		
		for (int i = 0; i < 1; i++) {
			
			new Thread(new DataThread()).start();
		}
		
	}
	class DataFsThread implements Runnable{

		private LinkedBlockingQueue<Object> datas;
		private String tablePre;
		private boolean update;
		public  DataFsThread(LinkedBlockingQueue<Object> datas){
			super();
			this.datas=datas;
		}
		
		public void run() {
			while (true) {
				if (datas.size()>0) {
					try {
						Map<String, Object> map = (Map<String, Object>) datas.take();
						String id = (String) map.get("id");
						String[] regWd = new String[] { "110000", "120000",
								"130000", "140000", "150000", "210000",
								"220000", "230000", "310000", "320000",
								"330000", "340000", "350000", "360000",
								"370000", "410000", "420000", "430000",
								"440000", "450000", "460000", "500000",
								"510000", "520000", "530000", "540000",
								"610000", "620000", "630000", "640000",
								"650000" };
						for (String reg : regWd) {
						/*	System.out.println(reg);*/
							Map<String, String> resultparams = new HashMap<String, String>();
							resultparams.put("m", "QueryData");
							resultparams.put("dbcode", tablePre);
							resultparams.put("rowcode", "zb");
							resultparams.put("colcode", "sj");
							resultparams.put("wds",
									"[{\"wdcode\":\"reg\",\"valuecode\":\""
											+ reg + "\"}]");
							resultparams.put("k1", new Date().getTime() + "");
							resultparams.put("dfwds",
									"[{\"wdcode\":\"zb\",\"valuecode\":\"" + id
											+ "\"}]");
							//
							if (update) {
								spiderNode(resultparams);
								resultparams
										.put("dfwds",
												"[{\"wdcode\":\"sj\",\"valuecode\":\"LAST36\"}]");
							}
							JSONObject jsonObject = getJsonObject(resultparams);
							getResult(jsonObject, map);
							resultparams.clear();
							resultparams = null;
						}
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}

		private void getResult(JSONObject jsonObject, Map<String, Object> map) {
			// TODO Auto-generated method stub
			
		}

		private JSONObject getJsonObject(Map<String, String> resultparams) {
			// TODO Auto-generated method stub
			return null;
		}

		private void spiderNode(Map<String, String> resultparams) {
			// TODO Auto-generated method stub
			
		}
	
	

}
