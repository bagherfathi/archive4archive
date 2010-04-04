package com.ft.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ft.utils.RandomNum;

public class JavaTest {
	
	public static void main(String[] args){
		RandomNum random=new RandomNum();
		random.setRange(0,32000);
		int rand=random.getRandom().intValue();
		String a=new SimpleDateFormat("yyMMdd").format(new Date());
		System.out.println(">>>>"+a);
	}
}
