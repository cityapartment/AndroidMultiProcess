package com.mini.server.test;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.mini.aidl.SenderParams;
import com.mini.server.RemoteService;

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
        testSendMessageByTimer(context);
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


    private static void testSendMessageByTimer(final Context context) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SenderParams params = new SenderParams();
                params.setMessage("message by time");
                ((RemoteService)context).handlerRemoteCallback(params);
            }
        }, 1000, 1000);
    }
}
