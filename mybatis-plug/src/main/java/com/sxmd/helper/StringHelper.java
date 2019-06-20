package com.sxmd.helper;

/**
 * Description: 字符串处理
 *
 * @author cy
 * @date 2019年06月20日 14:27
 * Version 1.0
 */
public class StringHelper {

    /**
     * Description:   驼峰式转化为下划线字符
     * @author cy
     * @param camelCaseName:
     * @return java.lang.String
     * @date  2019/6/20 14:27
     */
    public static String underscoreName(String camelCaseName) {
        StringBuilder result = new StringBuilder();
        if (camelCaseName != null && camelCaseName.length() > 0) {
            result.append(camelCaseName.substring(0, 1).toLowerCase());
            for (int i = 1; i < camelCaseName.length(); i++) {
                char ch = camelCaseName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }


    /**
     * Description:   转化为驼峰式
     * @author cy
     * @param underscoreName:
     * @return java.lang.String
     * @date  2019/6/20 14:30
     */
    public static String camelCaseName(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

}
