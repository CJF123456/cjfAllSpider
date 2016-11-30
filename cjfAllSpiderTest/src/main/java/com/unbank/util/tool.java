package com.unbank.util;

public class tool {

	public static void main(String[] args) {
		useTime();
		newRandTime();
		  new  tool().getCurrentPath();
	}

	private static void newRandTime() {
		// 生成一个4位数的随机数
		int sleepNum = (int) (Math.random() * 9000) + 1000;
		System.out.println(sleepNum);
	}

	/**
	 * 查看一段代码的运行的消耗时间
	 */
	private static void useTime() {
		long a = System.currentTimeMillis();
		long b = System.currentTimeMillis();
		System.out.println("采集一个url耗时" + (b - a));

	}

	public String getCurrentPath() {
		// 取得根目录路径
		String rootPath = getClass().getResource("/").getFile().toString();
		// 当前目录路径
		String currentPath1 = getClass().getResource(".").getFile().toString();
		String currentPath2 = getClass().getResource("").getFile().toString();
		// 当前目录的上级目录路径
		String parentPath = getClass().getResource("../").getFile().toString();
		System.out.println(rootPath);
		System.out.println(currentPath1);
		System.out.println(currentPath2);
		return rootPath;
		

	}

}
