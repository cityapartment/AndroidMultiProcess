package com.mini.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.mini.aidl.CallBackResult;
import com.mini.aidl.IRemoteService;
import com.mini.aidl.SenderParams;


/**
 * @author leroy
 * @description:
 * @data: 19/4/20
 */
public class RemoteService extends Service {

    private IBinder remoteBinder;

    @Override
    public void onCreate() {
        super.onCreate();
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
        return new RemoteServiceImpl();
    }


    public class RemoteServiceImpl extends IRemoteService.Stub {

        @Override
        public String sendMessage(String callBackResult) throws RemoteException {
            return "RemoteService receiver message: " + callBackResult;
        }

        @Override
        public CallBackResult requestAidlSync(SenderParams callBackResult) throws RemoteException {
            return null;
        }

        @Override
        public void requestAidlASync(SenderParams sendParams, CallBackResult callbackResult) throws RemoteException {
            callbackResult.setMessage(sendParams.getMessage());
        }
    }


    /**
     * 执行方法
     * @param obj 执行方法的对象
     * @param methodName 方法名称
     * @param classes 执行方法的参数类型
     * @param args 执行方法的参数
     */
//    public void invokeMethod(Object obj, String methodName, Class[] classes, Object... args){
//        try {
//            Method method;
//            if (classes != null && classes.length > 0){
//                method = obj.getClass().getDeclaredMethod(methodName,classes);
//            }else {
//                method = obj.getClass().getDeclaredMethod(methodName);
//            }
//            method.setAccessible(true);
//            method.invoke(obj, args);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
}
