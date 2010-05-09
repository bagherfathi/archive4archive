package com.ft.rfms.busi;

import java.io.File;
import java.util.Random;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
		Random rd = new Random(); // 创建随机对象
		int x = rd.nextInt(999999);

		if (x > 100000) {
			System.out.println(x);
		}
		}
	}

}
