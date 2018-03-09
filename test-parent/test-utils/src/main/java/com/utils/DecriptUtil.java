package com.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DecriptUtil {

	/**
	 * SHA加密
	 * @param decript     要加密的字符串
	 * @param type        加密的类型。SHA1/SHA256等
	 * @return
	 */
    public static String SHA(String decript,String type) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance(type);
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

	/**
	 * 解码
	 * @param str
	 * @return string
	 * @throws UnsupportedEncodingException 
	 */
	public static String decode(String str) throws UnsupportedEncodingException{
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer( str );
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new String(bt,"utf-8");
	}

	/**
	 * 二进制数据编码为BASE64字符串
	 *
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static String encode(final byte[] bytes) {
		return new String(Base64.encodeBase64(bytes));
	}

    public static void main(String[] args) throws UnsupportedEncodingException {
        String sha1 = decode("MjIyMg==");
        System.out.println(sha1);
    }
}
