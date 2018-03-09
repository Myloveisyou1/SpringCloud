package com.utils.security;

import com.utils.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MD5util {

	public static String getMD5(String pw) {
		try {

			// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 输入的字符串转换成字节数组
			byte[] inputByteArray = pw.getBytes();
			// inputByteArray是输入字符串转换得到的字节数组
			messageDigest.update(inputByteArray);
			// 转换并返回结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();
			// 字符数组转换成字符串返回
			return byteArrayToHex(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String byteArrayToHex(byte[] byteArray) {

		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}
	
	public static String getMD5String(String a, String b, String c){
		
		String t =a+b+c;
		
		return getMD5(t);
	}
	/**
     * 根据指定的签名密钥和算法签名Map<String,String>
     * @see 方法内部首先会过滤Map<String,String>参数中的部分键值对
     * @see 过滤规则:移除键名为"cert","hmac","signMsg"或者键值为null或者键值长度为零的键值对
     * @see 过滤结果:过滤完Map<String,String>后会产生一个字符串,其格式为[key11=value11|key22=value22|key33=value33]
     * @see And the calls {@link TradePortalUtil#getHexSign(String,String,String,boolean)}进行签名
     * @param param     待签名的Map<String,String>
     * @param charset   签名时转码用到的字符集
     * @param algorithm 签名时所使用的算法,从业务上看,目前其可传入两个值:MD5,SHA-1
     * @param signKey   签名用到的密钥
     * @return String algorithm digest as a lowerCase hex string
     */
    public static String getHexSign(Map<String, String> param, String charset, String algorithm, String signKey){
        StringBuilder sb = new StringBuilder();
        List<String> keys = new ArrayList<String>(param.keySet());
        Collections.sort(keys);
        for(int i=0; i<keys.size(); i++){
            String key = keys.get(i);
            String value = param.get(key);
            if(key.equalsIgnoreCase("cert") || key.equalsIgnoreCase("hmac") || key.equalsIgnoreCase("signMsg") || value==null || value.length()==0){
                continue;
            }
            sb.append(key).append("=").append(value).append("|");
        }
        sb.append("key=").append(signKey);
        return getHexSign(sb.toString(), charset, algorithm, true);
    }
    
    
    /**
     * 通过指定算法签名字符串
     * @see Calculates the algorithm digest and returns the value as a hex string
     * @see If system dosen't support this <code>algorithm</code>, return "" not null
     * @see It will Calls {@link TradePortalUtil#getBytes(String str, String charset)}
     * @see 若系统不支持<code>charset</code>字符集,则按照系统默认字符集进行转换
     * @see 若系统不支持<code>algorithm</code>算法,则直接返回""空字符串
     * @see 另外,commons-codec.jar提供的DigestUtils.md5Hex(String data)与本方法getHexSign(data, "UTF-8", "MD5", false)效果相同
     * @param data        Data to digest
     * @param charset     字符串转码为byte[]时使用的字符集
     * @param algorithm   目前其有效值为<code>MD5,SHA,SHA1,SHA-1,SHA-256,SHA-384,SHA-512</code>
     * @param toLowerCase 指定是否返回小写形式的十六进制字符串
     * @return String algorithm digest as a lowerCase hex string
     */
    public static String getHexSign(String data, String charset, String algorithm, boolean toLowerCase){
        char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        //Used to build output as Hex
        char[] DIGITS = toLowerCase ? DIGITS_LOWER : DIGITS_UPPER;
        //get byte[] from {@link TradePortalUtil#getBytes(String, String)}
        byte[] dataBytes = StringUtils.getBytes(data, charset);
        byte[] algorithmData = null;
        try {
            //get an algorithm digest instance
            algorithmData = MessageDigest.getInstance(algorithm).digest(dataBytes);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("签名字符串[" + data + "]时发生异常:System doesn't support this algorithm[" + algorithm + "]");
            return "";
        }
        char[] respData = new char[algorithmData.length << 1];
        //two characters form the hex value
        for(int i=0,j=0; i<algorithmData.length; i++){
            respData[j++] = DIGITS[(0xF0 & algorithmData[i]) >>> 4];
            respData[j++] = DIGITS[0x0F & algorithmData[i]];
        }
        return new String(respData);
    }

	public static void main(String[] args) {
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("name", "liuyingjie");
//		map.put("password", "liuyingjie");
//		map.put("age", "11");
//		map.put("address", "china");
//		String str =getMD5("594hsr@Mj");
//		System.out.println(getHexSign(map, "utf-8", "md5", "true"));
		String str2 =getMD5("changeme");
		System.out.println(str2);

	}

}
