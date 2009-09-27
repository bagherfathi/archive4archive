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
		// String str ="<property name=\"a2\"
		// type=\"java.lang.String\">\n<column
		// name=\"A2\" length=\"120\" />\n</property>";
		String str = "file.setA1(oo[6] + \"\");";
		// String str1 = "";

		for (int i = 1; i <= 100; i++) {
			String str1 = "";
			str1 = str.replaceAll("A1", "A" + i);
			str1 = str1.replace("[6]", "[" + (i + 5) + "]");
			// str1 = str1.replaceAll("A2", "A" + i);
			System.out.println(str1);
		}

	}
}
