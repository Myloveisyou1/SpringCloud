package com.utils.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Descript:
 * @Author: liuyingjie
 * @Date: create in 2018/3/9 0009 14:38
 */
public class SecurityUtils {

    private static Log logger = LogFactory.getLog(SecurityUtils.class);
    private static char[] password = null;

    static {
        String s = "tool";
        s = MD5util.getMD5(s);
        password = s.toCharArray();
    }

    /**
     * @Description(功能描述)		: 加密
     * @author(作者)				: wangliyou
     * @date (开发日期)			: 2015年3月10日 上午9:35:10
     * @param str               : 所需加密的字符串
     * @return  String          : 返回加密后的字符串
     */
    public static String encrypt(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        str = encode(str);
        str = Base64.encode(str.getBytes());
        str = str.replace("+", "(");
        str = str.replace("/", ")");
        str = str.replace("=", "@");
        str = str.replace("\n", "");
        return str;
    }

    /**
     * @Description(功能描述)		: 解密
     * @author(作者)				: wangliyou
     * @date (开发日期)			: 2015年3月10日 上午9:35:48
     * @param str               : 所需解密的字符串
     * @return  String          : 返回解密后的字符串
     */
    public static String decrypt(String str) {
        if (str == null || "".equals(str.trim())) {
            return "";
        }
        str = str.replace("(", "+");
        str = str.replace(")", "/");
        str = str.replace("@", "=");
        try {
            logger.debug("str:" + str);
            byte[] re = Base64.decode(str);
            str = new String(re);
            str = encode(str);
            return str;
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * @Description(功能描述)		: 编码
     * @author(作者)				: wangliyou
     * @date (开发日期)			: 2015年3月10日 上午9:36:33
     * @param str               : 所需编码的字符串
     * @return  String          : 返回编码后的字符串
     */
    public static String encode(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        int len = str.length();
        StringBuilder re = new StringBuilder();
        char[] cs = str.toCharArray();
        for (int i = 0, j = 0; j < len; i++, j++) {
            char c = (char) (cs[j] ^ password[i]);
            re.append(c);
            if (i == 31) {
                i = 0;
            }
        }
        return re.toString();
    }
}
