
package com.df.smallwater.smallwater.api;

public class ApiConstants {


    public static final String BASEURL="http://hrbcg.cn/smallwater/";

    public static final String BASEURL_RESOURCE="http://hrbcg.cn/smallwater/word/";

    public static final String APK_URL="http://hrbcg.cn/smallwater/smallwater.apk";

    public static final boolean LOG_DEBUG = true;


//    /**
//     * 新闻id获取类型
//     *
//     * @param id 新闻id
//     * @return 新闻类型
//     */
//    public static String getType(String id) {
//        switch (id) {
//            case HEADLINE_ID:
//                return HEADLINE_TYPE;
//            case HOUSE_ID:
//                return HOUSE_TYPE;
//            default:
//                break;
//        }
//        return OTHER_TYPE;
//    }

    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.HOME_NEW_LIST:
                host = "";
                break;
            case HostType.PICTURE_NEW_LIST:
                host = "";
                break;

            default:
                host = "";
                break;
        }
        return host;
    }
}
