package com.utils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 操作字符串相关
 * @author Administrator
 *
 */
public class StringUtils {
	/**
	 * 判断字符串是否为空
	 * @param input
	 * @return boolean     true：空；false:非空
	 */
	public static boolean isEmpty(String input) {
        return null==input || 0==input.length() || 0==input.replaceAll("\\s", "").length();
    }
	
	/**
     * 判断输入的字节数组是否为空
     * @return boolean 空则返回true,非空则flase
     */
    public static boolean isEmpty(byte[] bytes){
        return null==bytes || 0==bytes.length;
    }
    
    /**
     * 获取Map中的属性
     * @see 由于Map.toString()打印出来的参数值对,是横着一排的...参数多的时候,不便于查看各参数值
     * @see 故此仿照commons-lang.jar中的ReflectionToStringBuilder.toString()编写了本方法
     * @return String key11=value11 \n key22=value22 \n key33=value33 \n......
     */
    public static String getStringFromMap(Map<String, String> map){
        StringBuilder sb = new StringBuilder();
        sb.append(map.getClass().getName()).append("@").append(map.hashCode()).append("[");
        for(Map.Entry<String,String> entry : map.entrySet()){
            sb.append("\n").append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sb.append("\n]").toString();
    }
    
    /**
     * 获取Map中的属性
     * @see 该方法的参数适用于打印Map<String, byte[]>的情况
     * @return String key11=value11 \n key22=value22 \n key33=value33 \n......
     */
    public static String getStringFromMapForByte(Map<String, byte[]> map){
        StringBuilder sb = new StringBuilder();
        sb.append(map.getClass().getName()).append("@").append(map.hashCode()).append("[");
        for(Map.Entry<String,byte[]> entry : map.entrySet()){
            sb.append("\n").append(entry.getKey()).append("=").append(new String(entry.getValue()));
        }
        return sb.append("\n]").toString();
    }
    
    /**
     * 获取Map中的属性
     * @see 该方法的参数适用于打印Map<String, Object>的情况
     * @return String key11=value11 \n key22=value22 \n key33=value33 \n......
     */
    public static String getStringFromMapForObject(Map<String, Object> map){
        StringBuilder sb = new StringBuilder();
        sb.append(map.getClass().getName()).append("@").append(map.hashCode()).append("[");
        for(Map.Entry<String,Object> entry : map.entrySet()){
            sb.append("\n").append(entry.getKey()).append("=").append(entry.getValue().toString());
        }
        return sb.append("\n]").toString();
    }
    
    /**
     * 隐藏某个字符串的部分
     * @param data   传入的字符串
     * @param a      前面显示的个数
     * @param b      后面显示的个数
     * @return string  返回的字符串      
     */
    public static String getStringSimple(String data,int a,int b){
    	String str = "*";
    	for(int i=0;i<data.length()-a-b-1;i++){
    		str += "*";
    	}
        return data.substring(0,a) + str + data.substring(data.length()-b);
    }
    /**
     * 字符串转为字节数组
     * @see 该方法默认以ISO-8859-1转码
     * @see 若想自己指定字符集,可以使用<code>getBytes(String str, String charset)</code>方法
     */
    public static byte[] getBytes(String data){
        return getBytes(data, "ISO-8859-1");
    }
    /**
     * 字符串转为字节数组
     * @see 如果系统不支持所传入的<code>charset</code>字符集,则按照系统默认字符集进行转换
     */
    public static byte[] getBytes(String data, String charset){
        data = (data==null ? "" : data);
        if(isEmpty(charset)){
            return data.getBytes();
        }
        try {
            return data.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            System.out.println("将字符串[" + data + "]转为byte[]时发生异常:系统不支持该字符集[" + charset + "]");
            return data.getBytes();
        }
    }
    /**
     * 测试
     * @param args
     */
	public static void main(String[] args){
		String str = "15215215962";
		System.out.println(getStringSimple(str,3,4));
		
	}
}
