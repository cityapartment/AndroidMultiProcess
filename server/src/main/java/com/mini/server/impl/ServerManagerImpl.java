package com.mini.server.impl;

import com.mini.library.api.IServerManager;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * @author leroy
 * @description:
 * @data: 24
 */
public class ServerManagerImpl implements IServerManager {

    @Override
    public void getUserData(String userId) {
        Logger.i("ServerManagerImpl getUserData is called ============== userId");
    }

    @Override
    public void getUserData(ArrayList userList) {
        Logger.i("ServerManagerImpl getUserData is called ================ userList");
    }

    @Override
    public void saveSharePreferences(String key, String userId, String password) {
        Logger.i("ServerManagerImpl saveSharePreferences is called ================");
    }

    @Override
    public String getSharePreferences(String key) {
        Logger.i("ServerManagerImpl getSharePreferences is called ================");
        return null;
    }
}
