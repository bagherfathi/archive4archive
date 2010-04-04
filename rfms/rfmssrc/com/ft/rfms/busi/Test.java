package com.ft.rfms.busi;

import java.io.File;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String x=new File("E:\\Fortune Talent\\RFMS\\web\\reportFiles\\settlementReport.jasper").getName();
		System.out.println(x.substring(0,x.lastIndexOf(".")));
	}

}
