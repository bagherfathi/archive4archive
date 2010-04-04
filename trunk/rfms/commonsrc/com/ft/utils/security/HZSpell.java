/**
 * <p>Title: HZSpell.java</p>
 * <p>Description:
 *  ºº×Ö´Ê×é×ª»¯ÎªÆ´Òô×ÖÄ¸µÄËã·¨
 * </p>
 */

package com.ft.utils.security;



public class HZSpell{

    private static byte[] getByteString(String msg) {
      byte[] b1 = new byte[64];
      System.arraycopy(msg.getBytes(), 0, b1, 0, msg.getBytes().length);
      b1[msg.getBytes().length]=0x0;
      return b1;
    }


    public  static String transition(String hz)
    {
        StringBuffer sb=new StringBuffer();

        for(int i=0;i<hz.length();i++)
        {
            //System.out.println(getPYChar(hz.substring(i,i+1)));
            sb.append(getPYIndexChar(getPYChar(hz.substring(i,i+1))));
        }

        return sb.toString();
    }

    private static String getPYIndexChar(String hexChar)
    {
        String result="";
        if(hexChar.length()>0){
	        int decChar=Integer.valueOf(hexChar, 16).intValue();
	        if(decChar>0xB0A1&&decChar<0xB0C4)
	            result="A";
	        else if(decChar>0xB0C5&&decChar<0xB2C0)
	            result="B";
	        else if(decChar>0xB2C1&&decChar<0xB4ED)
	            result="C";
	        else if(decChar>0xB4EE&&decChar<0xB6E9)
	            result="D";
	        else if(decChar>0xB6EA&&decChar<0xB7A1)
	            result="E";
	        else if(decChar>0xB7A2&&decChar<0xB8C0)
	            result="F";
	        else if(decChar>0xB8C1&&decChar<0xB9FD)
	            result="G";
	        else if(decChar>0xB9FE&&decChar<0xBBF6)
	            result="H";
	        else if(decChar>0xBBF7&&decChar<0xBFA5)
	            result="J";
	        else if(decChar>0xBFA6&&decChar<0xC0AB)
	            result="K";
	        else if(decChar>0xc0AC&&decChar<0xC2E7)
	            result="L";
	        else if(decChar>0xC2E8&&decChar<0xC4C2)
	            result="M";
	        else if(decChar>0xC4C3&&decChar<0xC5B5)
	            result="N";
	        else if(decChar>0xC5B6&&decChar<0xC5BD)
	            result="O";
	        else if(decChar>0xC5BE&&decChar<0xC6D9)
	            result="P";
	        else if(decChar>0xC6DA&&decChar<0xC8BA)
	            result="Q";
	        else if(decChar>0xC8BB&&decChar<0xC8F5)
	            result="R";
	        else if(decChar>0xC8F6&&decChar<0xCBF9)
	            result="S";
	        else if(decChar>0xCBFA&&decChar<0xCDD9)
	            result="T";
	        else if(decChar>0xCDDA&&decChar<0xCEF3)
	            result="W";
	        else if(decChar>0xCEF4&&decChar<0xD188)
	            result="X";
	        else if(decChar>0xD1B9&&decChar<0xD4D0)
	            result="Y";
	        else if(decChar>0xD4D1&&decChar<0xD7F9)
	            result="Z";
	        else
	            result="";
        }
        return result;
    }

    private static String getPYChar(String charStr)
    {
        String result="";
    	byte[] charByte=HZSpell.getByteString(charStr);
        if(charByte[0]<0){
        	result=Integer.toHexString(charByte[0]).substring(6,8)+Integer.toHexString(charByte[1]).substring(6,8);
        }
        //System.out.println(result);
        return result;
    }

    public static void main(String[] argv){
    	//System.out.println(HZSpell.transition("²âÊÔ1asdqe132.,./"));
    	System.out.println(new String("²âÊÔ2qweqw2342352345").getBytes().length);
    }
}