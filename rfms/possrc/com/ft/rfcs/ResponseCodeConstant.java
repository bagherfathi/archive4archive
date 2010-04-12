package com.ft.rfcs;
/**
 * 
 * @author lch
 *
 */
public class ResponseCodeConstant {

	public static String SUCCESS = "00000"; //成功
	public static String EXCEPTION = "99999"; //系统异常

	public static String SYSTEM_MAC_ERROR = "02001"; //MAC鉴权失败
	public static String SYSTEM_NOTSIGNIN_ERROR = "02002"; //POS未签到
	public static String SYSTEM_MERCHANTPOS_ERROR = "03003"; //商户号或终端号不正确

	//优惠券相关
	
	public static String COUPON_CODE_ERROR = "04001";  //优惠券不存在
	public static String COUPON_USED_ERROR = "04002";  //优惠券已使用
	public static String COUPON_PASSWD_ERROR = "04003";  //优惠券验证错误
	public static String COUPON_UNABLED_ERROR = "04004";  //该优惠券不能在此商户内消费
	public static String COUPON_EXPIRED_ERROR = "04005";  //优惠券已过有效期
	public static String COUPON_APPLY_ERROR = "04006";  //优惠券申请失败


}
