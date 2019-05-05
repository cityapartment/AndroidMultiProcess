package com.mini.library.api;

import java.util.ArrayList;

public interface IServerManager {

    void getUserData(String userId);

    void getUserData(ArrayList userList);

    /**
     * 保存SharePreferences数据
     * @param key
     * @param userId
     * @param password
     */
    void saveSharePreferences(String key, String userId, String password);

    /**
     * 访问SharePreferences数据
     * @param key
     * @return
     */
    String getSharePreferences(String key);
}
