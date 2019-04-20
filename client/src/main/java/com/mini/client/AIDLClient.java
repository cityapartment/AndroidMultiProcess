package com.mini.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.mini.aidl.CallBackResult;
import com.mini.aidl.IRemoteService;
import com.mini.aidl.SenderParams;

/**
 * @author leroy
 * @description:
 * @data: 19/4/20
 */
public class AIDLClient {

    private String TAG = AIDLClient.class.getSimpleName();

    private IRemoteService remoteService;

    ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteService = IRemoteService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
//            remoteService = null;
        }
    };

    public void sendClientMessage(String message) {
        if(remoteService != null) {
            try {
//                String result = remoteService.sendMessage(message);

                SenderParams params = new SenderParams();
                params.setMessage("sendParams");

                CallBackResult result = new CallBackResult();

                remoteService.requestAidlASync(params, result);
                Log.i(TAG, "client result ======= " + result.getMessage());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "client remoteService is null ");
        }
    }


    public void startRemoteService(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }


    public void registerCallback() {

    }

    public void unRegisterCallback() {

    }

}
