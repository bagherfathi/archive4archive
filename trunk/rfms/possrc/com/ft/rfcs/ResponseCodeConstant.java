package com.ft.rfcs;
/**
 * 
 * @author lch
 *
 */
public class ResponseCodeConstant {

	public static String SUCCESS = "00000"; //�ɹ�
	public static String EXCEPTION = "99999"; //ϵͳ�쳣

	public static String SYSTEM_MAC_ERROR = "02001"; //MAC��Ȩʧ��
	public static String SYSTEM_NOTSIGNIN_ERROR = "02002"; //POSδǩ��
	public static String SYSTEM_MERCHANTPOS_ERROR = "03003"; //�̻��Ż��ն˺Ų���ȷ

	//�Ż�ȯ���
	
	public static String COUPON_CODE_ERROR = "04001";  //�Ż�ȯ������
	public static String COUPON_USED_ERROR = "04002";  //�Ż�ȯ��ʹ��
	public static String COUPON_PASSWD_ERROR = "04003";  //�Ż�ȯ��֤����
	public static String COUPON_UNABLED_ERROR = "04004";  //���Ż�ȯ�����ڴ��̻�������
	public static String COUPON_EXPIRED_ERROR = "04005";  //�Ż�ȯ�ѹ���Ч��
	public static String COUPON_APPLY_ERROR = "04006";  //�Ż�ȯ����ʧ��


}
