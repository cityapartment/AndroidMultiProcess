// IRemoteService.aidl
package com.mini.aidl;
/**
 * @author leroy
 * @description:
 * @data: 19/4/20
 */
// Declare any non-default types here with import statements
import com.mini.aidl.CallBackResult;
import com.mini.aidl.SenderParams;

interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    String sendMessage(String callBackResult);

//in: 表示这个传入的对象A a只能由客户端传入服务端，其本身作为方法的参数，不会有任何改变！
//out:
//oneway:

    CallBackResult requestAidlSync(in SenderParams sendParams);


    void requestAidlASync(in SenderParams sendParams, out CallBackResult callbackResult);
    //注册CallBack

    //取消CallBack

}
