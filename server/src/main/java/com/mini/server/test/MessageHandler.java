package com.mini.server.test;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.mini.aidl.SenderParams;
import com.mini.library.utils.UserInfoSP;
import com.mini.server.RemoteService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @author leroy
 * @description: test code
 * @data: 19/4/21
 */
public class MessageHandler {

    private static String TAG = MessageHandler.class.getSimpleName();

    public static void receiverMessageAndShow(Context context, SenderParams senderParams) {
        int pid = android.os.Process.myPid();
        String msg = senderParams.getMessage() + ", show message in pid = " + pid;
        showServiceToast(context, msg);

        callMethodByInvoke(senderParams);
//        testSendMessageByTimer(context, msg);
    }

//    /**
//     * 执行方法
//     * @param obj 执行方法的对象
//     * @param methodName 方法名称
//     * @param classes 执行方法的参数类型
//     * @param args 执行方法的参数
//     */
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

    private static void callMethodByInvoke(SenderParams paramsObj) {
        String className = paramsObj.getClassName();
        String methodName = paramsObj.getMethodName();
        Method calledMethod;
        Object[] paramArray = paramsObj.getMethodParams();

        Class[] param = new Class[paramArray.length];
        for(int i = 0; i < paramArray.length; i++) {
            param[i] = paramArray[i].getClass();
        }
        try {
            Class cls = Class.forName(className);
            calledMethod = cls.getMethod(methodName, param);
            calledMethod.invoke(cls.newInstance(), paramArray);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }

    private static void showServiceToast(final Context context, final String message) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void testSendMessageByTimer(final Context context, final String msg) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SenderParams params = new SenderParams();
                params.setMessage(msg);
                ((RemoteService)context).handlerRemoteCallback(params);
            }
        }, 1000, 1000);
    }
}
