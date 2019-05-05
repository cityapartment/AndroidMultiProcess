package com.mini.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.mini.aidl.CallBackResult;
import com.mini.aidl.IRemoteServiceCallback;
import com.mini.aidl.IRemoteServiceSender;
import com.mini.aidl.SenderParams;
import com.mini.server.test.MessageHandler;


/**
 * @author leroy
 * @description:
 * @data: 19/4/20
 */
public class RemoteService extends Service {

    private IBinder serverBinder;

    private RemoteCallbackList<IRemoteServiceCallback> callbackList = new RemoteCallbackList();

    @Override
    public void onCreate() {
        super.onCreate();
        //TODO:
        serverBinder = new RemoteServiceImpl();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return serverBinder;
    }


    public class RemoteServiceImpl extends IRemoteServiceSender.Stub {

        @Override
        public String sendMessage(String callBackResult) throws RemoteException {
            return "RemoteService receiver message: " + callBackResult;
        }

        @Override
        public CallBackResult requestAidlSync(SenderParams senderParams) throws RemoteException {
            handlerRemoteMessage(senderParams);
            return null;
        }

        @Override
        public void requestAidlASync(SenderParams sendParams, CallBackResult callbackResult) throws RemoteException {
            callbackResult.setMessage(sendParams.getMessage());
        }

        @Override
        public boolean registerRemoteCallback(int pid, IRemoteServiceCallback callback) throws RemoteException {
            callbackList.register(callback);
            return false;
        }

        @Override
        public boolean unregisterRemoteCallback(int pid) throws RemoteException {
//            callbackList.unregister()
            return false;
        }
    }


    private void handlerRemoteMessage(SenderParams params) {
        MessageHandler.receiverMessageAndShow(this, params);
    }

    //send message to client from server
    public void handlerRemoteCallback(SenderParams params) {
        int N = callbackList.beginBroadcast();
        for(int i = 0; i < N; i++) {
            try {
                IRemoteServiceCallback callback = callbackList.getBroadcastItem(i);
                callback.callbackTransport(params);
          } catch (RemoteException e) {
              // The RemoteCallbackList will take care of removing
              // the dead object for us.
          }
        }
        callbackList.finishBroadcast();
    }




    //发送给客户端



}
