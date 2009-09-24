package com.renhenet.web.structure;

public class Test {
	public static void main(String[] args) {
		// #elseif($strIndex=='a2')
		// <td
		// class="tablelistbg">&nbsp;#if($!file.a2!='null')$!file.a2#end</td>
		// String str = "#elseif($strIndex=='a2')\n<td
		// class='tablelistbg'>&nbsp;#if($!file.a2!='null')$!file.a2#end</td>";
		// String str = "#elseif($strIndex=='a2')\n#set($values=$!bizObj.a2)";
		// String str = "#elseif($strIndex=='a2')\n<td
		// class='tablelistbg'>&nbsp;#if($!file.a2!='null')$!file.a2#end</td>";
//		String str ="<property name=\"a2\" type=\"java.lang.String\">\n<column name=\"A2\" length=\"120\" />\n</property>";
		String str ="private String a2;";
//		String str1 = "";
		
		

		for (int i = 2; i <= 100; i++) {
			String str1 = "";
			str1 = str.replaceAll("a2", "a" + i);
//			str1 = str1.replaceAll("A2", "A" + i);
			System.out.println(str1);
		}

	}
}
