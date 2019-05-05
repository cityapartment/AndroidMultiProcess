package com.mini.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.mini.aidl.CallBackResult;
import com.mini.aidl.IRemoteServiceCallback;
import com.mini.aidl.IRemoteServiceSender;
import com.mini.aidl.SenderParams;

import java.util.ArrayList;

/**
 * @author leroy
 * @description:
 * @data: 19/4/20
 */
public class AIDLClient {

    private String TAG = AIDLClient.class.getSimpleName();

    private IRemoteServiceSender remoteServiceSender;
    private IRemoteServiceCallback remoteServiceCallback;

    ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteServiceSender = IRemoteServiceSender.Stub.asInterface(service);
            //TODO:
            registerCallback();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
//            remoteService = null;
        }
    };

    public void sendClientMessage(String message) {
        if(remoteServiceSender != null) {
            try {
//                String result = remoteService.sendMessage(message);

                SenderParams params = new SenderParams();
                params.setMessage("sendParams in pid = " + android.os.Process.myPid());
                remoteServiceSender.requestAidlSync(params);

//                CallBackResult result = new CallBackResult();
//                remoteServiceSender.requestAidlASync(params, result);
//                Log.i(TAG, "client result ======= " + result.getMessage());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "client remoteService is null ");
        }
    }

    //TODO:
    public void requestServerMethod() {
        if(remoteServiceSender != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("aaaa");
            arrayList.add("bbb");
            arrayList.add("ccc");
            arrayList.add("dddd");

            Object[] obj = new Object[1];
            obj[0] = arrayList;
            SenderParams params = new SenderParams();
            params.setClassName("com.mini.server.impl.ServerManagerImpl");
            params.setMethodName("getUserData");
            params.setMethodParams(obj);
            try {
                getRemoteService().requestAidlSync(params);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "client remoteService is null ");
        }
    }

    private IRemoteServiceSender getRemoteService() {
        return remoteServiceSender;
    }

    public void startRemoteService(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        //
//        registerCallback();
    }


    public void registerCallback() {
        //获取当前进程id
        int pid = 0;
        try {
            remoteServiceCallback = new RemoteServiceCallback();
            getRemoteService().registerRemoteCallback(pid, remoteServiceCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unRegisterCallback() {
        int pid = 0;
        try {
            getRemoteService().unregisterRemoteCallback(pid);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    //listening data from server service
    class RemoteServiceCallback extends IRemoteServiceCallback.Stub {

        @Override
        public String callbackTransport(SenderParams msg) throws RemoteException {
            handlerCallbackReceiver(msg);
            return null;
        }
    }

    private void handlerCallbackReceiver(SenderParams msg) {
        Log.i(TAG, "server send message ======= " + msg.getMessage());
    }
}
