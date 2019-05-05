package com.mini.library.utils;

import android.content.Context;

/**
 * @author leroy
 * @description:
 * @data: 24
 */
public class UserInfoSP {
    /**
     * 通用全局配置文件名字
     */
    public static final String SP_COMMON_CONFIG = "common_config";

    public static final String SP_CUSTOMER_INFO = "sp_customer_info";

    public static void saveCustomerInfo(Context context, String userName) {
        SharePreferencesUtils.savaToShared(context, SP_COMMON_CONFIG, SP_CUSTOMER_INFO, userName);
    }

    public static String getCustomerInfo(Context context) {
        return (String) SharePreferencesUtils.getShareObject(context, SP_COMMON_CONFIG, SP_CUSTOMER_INFO);
    }
}
