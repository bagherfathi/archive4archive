package com.renhenet.web;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.renhenet.fw.Config;
import com.renhenet.modules.member.DictionarySortService;
import com.renhenet.modules.member.FileService;
import com.renhenet.modules.member.MemberService;
import com.renhenet.modules.member.ResourcesService;
import com.renhenet.po.DictionarySort;
import com.renhenet.po.Member;
import com.renhenet.po.Resources;
import com.renhenet.util.DateUtil;
import com.renhenet.util.FileUtil;
import com.renhenet.util.HaoImageScale;
import com.renhenet.util.HighlighterUtil;
import com.renhenet.util.NumberUtil;
import com.renhenet.util.SecurityUtil;
import com.renhenet.util.StringUtil;

public class VMUtils {
	private ResourcesService resourcesService;

	private MemberService memberService;

	private DictionarySortService dictionarySortService;

	private FileService fileService;

	public List<DictionarySort> getDictionarySortByParentId(int parentId) {
		return dictionarySortService.getDictionarySortByParentId(new Integer(
				parentId));
	}

	public String getResourceValue(int id) {
		return resourcesService.getResourceValue(id);
	}

	public Member getMember(int userId) {
		return memberService.getMemberById(userId);
	}

	public String getResourceValue(String id) {
		return resourcesService.getResourceValue(id);
	}

	public int stringToInt(String strNum) {
		int num = -1;
		if (StringUtils.isEmpty(strNum)) {
			num = new Integer(strNum);
		}
		return num;
	}

	public List<Resources> getResourceByType(String type) {
		return resourcesService.getResourceByType(type);
	}

	public String dateFormate(Date date, String formate) {
		return DateUtil.dateFormate(date, formate);
	}

	public String dateFormateSimple(Date date) {
		return dateFormate(date, "yyyy年M月d日");
	}

	public String dateTimeFormateSimple(Date date) {
		return dateFormate(date, "yyyy年M月d日 H:m");
	}

	public String dateTimeFormateSimple(int seconds) {
		Date date = DateUtil.mysqlDate2Date(seconds);
		return dateTimeFormateSimple(date);
	}

	public String dateFormateSimple(int seconds) {
		Date date = DateUtil.mysqlDate2Date(seconds);
		return dateFormate(date, "yyyy年M月d日");
	}

	public String dateFormate(int seconds, String formate) {
		return DateUtil.dateFormate(seconds, formate);
	}

	public String dateFormateHuman(int seconds) {
		return DateUtil.dateFormateHuman(seconds);
	}

	public String dateFormateHuman(Date date) {
		return DateUtil.dateFormateHuman(date);
	}

	public static String getTimeBetweenHuman(int seconds) {
		return DateUtil.getTimeBetweenHuman(seconds);
	}

	public static String getTimeBetweenHuman(Date date) {
		return DateUtil.getTimeBetweenHuman(date);
	}

	public Date getDate(int mysqlDate) {
		return DateUtil.mysqlDate2Date(mysqlDate);
	}

	public String getstring(String source) {
		return (StringUtil.is_Empty(source) ? "" : source);
	}

	public String substring(String source, int start, int num) {
		if (StringUtils.isEmpty(source)) {
			return "";
		} else {
			return StringUtils.substring(source, 0, num);
		}
	}

	public String urlencoder(String str) {
		return StringUtil.urlencoder(str);
	}

	public String dateFormate(long seconds, String formate) {
		return dateFormate((int) seconds, formate);
	}

	public String getShowRelation(String memberName, String fromName,
			String companyName, String format) {
		return String.format(format, memberName, fromName, companyName);
	}

	public static String encrypt(int istr) {
		return SecurityUtil.encrypt(istr);
	}

	public static String encrypt(String str) {
		return SecurityUtil.encrypt(str);
	}

	public int add(int s, int v) {
		return s + v;
	}

	public int sub(int s, int v) {
		return s - v;
	}

	public String showShort(String content, int length) {
		return StringUtil.showShort(content, length);
	}

	public String toScriptStr(String str) {
		return StringUtil.toScriptStr(str);
	}

	public String toSafeStr(String str) {
		return StringUtil.toSafeStr(str);
	}

	public boolean isHttpUrl(String url) {
		return StringUtil.isHttpUrl(url);
	}

	public String toHtmlBR(String content) {
		return StringUtil.dtoHtml(content);
	}

	public String showHtml(String content) {
		return StringUtil.delHtml(content);
	}

	public static String decode(String source) {
		return WebHelper.decode(source);

	}

	public static String encode(String source) {
		return WebHelper.encode(source);
	}

	public static String picEncode(String picUrl) {
		String picUrl1 = encode(picUrl);
		String picEncode = picUrl1.replace("%2F", "/");
		return picEncode;
	}

	public static String numberFormat(double f) {
		NumberFormat nf1 = NumberFormat.getInstance(Locale.CHINA);
		return nf1.format(f);
	}

	public static String getPercent(double d1, double d2) {
		return getPercent(d1, d2, 2);
	}

	public static String getPercent(double d1, double d2, int fractionDigits) {
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMaximumFractionDigits(fractionDigits);
		return nf.format(d1 / d2);
	}

	public String joinString(String str1, String str2) {
		String[] ss = new String[] { str1, str2 };
		return StringUtils.join(ss);
	}

	public boolean findArr(String index, String arr, String type) {
		return StringUtil.findArr(index, arr, type);
	}

	public Boolean getAndOperate(int value1, int value2) {
		return NumberUtil.andOperate(value1, value2);
	}

	public static void main(String[] args) throws Exception {
		NumberFormat nf1 = NumberFormat.getInstance(Locale.CHINA);
		System.out.println(nf1.format(214324220002304.12));

		System.out.println("1111==" + nf1.parse("214,324,220,002,304.12"));
		System.out.println("1111==" + nf1.parse("123.12"));
		System.out.println("2222==" + nf1.parse("214324220002304.12"));
	}

	public String getBaseFile(String file) {
		return FileUtil.getFileName(file);
	}

	public String getFileUrl(String file) {
		return FileUtil.getFileUrl(file);
	}

	public String getMinUserFace(String faceImg) {
		if (StringUtil.is_Empty(faceImg))
			return "/images/t-photo.gif";
		String dir = Config.getString("sys.userface");
		String filename = "";
		if (!FileUtil.exists(dir + "/" + faceImg)) {
			return "/images/t-photo.gif";
		}
		if (faceImg.indexOf("/") >= 0) {
			filename = FileUtil.getFileName(faceImg);
			String path = faceImg.substring(0, faceImg.indexOf(filename));
			String name = FileUtil.getName(filename);
			filename = path + name + "_1.jpg";
			if (!FileUtil.exists(dir + "/" + filename)) {
				HaoImageScale img = new HaoImageScale();
				// 转换缩略图
				HaoImageScale.ImgToFile(img.fileImgZoom2Square(dir + "/"
						+ faceImg, 36), dir + "/" + filename);
			}
		} else {
			filename = faceImg;
			String name = FileUtil.getName(filename);
			filename = name + "_1.jpg";
		}
		return "/userface/" + filename;
	}

	public String hignlight(String value, String keyWords) {
		return HighlighterUtil.hignlight(value, keyWords);
	}

	public int getInt(float num) {
		return (int) Math.ceil(num);
	}

	/**
	 * str 要新加上的字符 context 字段
	 * 
	 * @param len
	 * @param str
	 * @return
	 */
	public String subPicUrl(String context, String str) {
		String str1 = context.substring(0, context.length() - 4);
		String str2 = context.substring(context.length() - 4, context.length());
		String str3 = str1 + str + str2;

		return str3;
	}

	public String getGroupTopicText(String content, int count) {
		String temp = this.showShort(content, count);
		temp = temp.replace("\n", "\n<br>\n");
		int i = temp.indexOf("[img");
		if (i >= 0) {
			temp = temp.substring(0, i);
		}
		i = temp.indexOf("[mp3");
		if (i >= 0) {
			temp = temp.substring(0, i);
		}
		return temp;
	}

	/**
	 * 得到当前的小时,24小时制
	 * 
	 * @return
	 */
	public int getHourOfDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	public int getTotalPage(int count, int size) {
		return (count - 1) / size + 1;
	}

	public double delResidue(double value, int digit) {
		return ((int) (value * digit)) / digit;
	}

	public String showWelcome() {
		int h = this.getHourOfDay();
		if (h >= 3 && h < 11) {
			return "早上好";
		} else if (h >= 11 && h < 15) {
			return "中午好";
		} else if (h >= 15 && h < 18) {
			return "下午好";
		} else {
			return "晚上好";
		}
	}

	public ResourcesService getResourcesService() {
		return resourcesService;
	}

	public void setResourcesService(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public DictionarySortService getDictionarySortService() {
		return dictionarySortService;
	}

	public void setDictionarySortService(
			DictionarySortService dictionarySortService) {
		this.dictionarySortService = dictionarySortService;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

}