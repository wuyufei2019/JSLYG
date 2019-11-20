package com.cczu.util.common;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String lowerFirst(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        } else {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
    }

    public static String upperFirst(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * 缩略字符串（不区分中英文字符）
     *
     * @param str    目标字符串
     * @param length 截取长度
     * @return
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 缩略字符串（替换html）
     *
     * @param str    目标字符串
     * @param length 截取长度
     * @return
     */
    public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
    }


    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    /**
     * 将实体对象的属性转化成符合导出word条件的字符串
     * 反转html字符并替换“<>&”字符串
     */
    public static void parseBeanForWord(Object obj) throws Exception {
        Map<String, Object> map = BeanUtils.describe(obj);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object entryvalue = entry.getValue();
            if (entryvalue != null && StringUtils.isNoneBlank(entryvalue.toString())
                    && !"null".equals(entryvalue.toString()) && !org.apache.commons.lang3.StringUtils.isNumeric(entryvalue.toString())) {
                String tmp = parseStringForWord(entryvalue.toString());
                entry.setValue(tmp);
            }
        }
        BeanUtils.populate(obj, map);
    }

    /**
     * 将字符串转化成符合导出word条件的字符串
     * 反转html字符并替换“<>&”字符串
     */
    public static String parseStringForWord(String str) throws Exception {
        return StringEscapeUtils.unescapeHtml4(str).replace("&", "&amp;")
                .replace("<", "&lt;").replace(">", "&gt;");
    }

    //根据监管类型返回 查询语句
    public static String qyQueryJGLX(Map<String, Object> mapData, String alias) {
        String content = "";
        if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
            content = content + " AND " + alias + ".M1 like'%" + mapData.get("qyname") + "%'";
        }
        if (mapData.get("qyid") != null && mapData.get("qyid") != "") {
            content = content + " AND " + alias + ".id =" + mapData.get("qyid") ;
        }
        if (mapData.get("equalqynm") != null && mapData.get("equalqynm") != "") {
            content = content + " AND " + alias + ".M1 ='" + mapData.get("equalqynm") + "'";
        }
        if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
            if (mapData.get("xj") != null && mapData.get("xj").equals("1")) {
                content = content + " AND " + alias + ".ID2 ='" + mapData.get("xzqy") + "' ";
            } else {
                content = content + " AND " + alias + ".ID2 like'" + mapData.get("xzqy") + "%'";
            }
        }
        if (mapData.get("jglx") != null && mapData.get("jglx") != "") {
            if (com.cczu.sys.comm.utils.StringUtils.toInteger(mapData.get("jglx")) == 10) {
                content = content + " AND " + alias + ".m36= '1' OR " + alias + ".m36 = '2'";
            } else if (com.cczu.sys.comm.utils.StringUtils.toInteger(mapData.get("jglx")) == 11) {
                content = content + " AND " + alias + ".m36= '1' OR " + alias + ".m36 = '3'";
            } else if (com.cczu.sys.comm.utils.StringUtils.toInteger(mapData.get("jglx")) == 12) {
                content = content + " AND " + alias + ".m36= '1' OR " + alias + ".m36 = '9'";
            } else if (com.cczu.sys.comm.utils.StringUtils.toInteger(mapData.get("jglx")) == 13) {
                content = content + " AND " + alias + ".m36= '2' OR " + alias + ".m36 = '3'";
            } else if (com.cczu.sys.comm.utils.StringUtils.toInteger(mapData.get("jglx")) == 14) {
                content = content + " AND " + alias + ".m36= '2' OR " + alias + ".m36 = '9'";
            } else if (com.cczu.sys.comm.utils.StringUtils.toInteger(mapData.get("jglx")) == 15) {
                content = content + " AND " + alias + ".m36= '3' OR " + alias + ".m36 = '9'";
            } else if (com.cczu.sys.comm.utils.StringUtils.toInteger(mapData.get("jglx")) == 16) {
                content = content + " AND " + alias + ".m36= '1' OR " + alias + ".m36 = '2' OR " + alias + ".m36 = " +
                        "'3' ";
            } else if (com.cczu.sys.comm.utils.StringUtils.toInteger(mapData.get("jglx")) == 17) {
                content = content + " AND " + alias + ".m36= '1' OR " + alias + ".m36 = '3' OR " + alias + ".m36 = " +
                        "'9' ";
            } else if (com.cczu.sys.comm.utils.StringUtils.toInteger(mapData.get("jglx")) == 18) {
                content = content + " AND " + alias + ".m36= '9' OR " + alias + ".m36 = '2' OR " + alias + ".m36 = " +
                        "'3' ";
            } else {
                content = content + " AND " + alias + ".m36='" + mapData.get("jglx") + "' ";
            }

        }
        return content;
    }


}
