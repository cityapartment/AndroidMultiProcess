package com.mini.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author leroy
 * @description:
 * @data: 19/4/20
 */
public class SenderParams implements Parcelable {

    private String message;
//    private String result[];

    /**
     * 调用类名
     */
    private String className;

    /**
     * 调用方法名
     */
    private String methodName;

    /**
     * 调用参数
     */
    private Object[] methodParams;

    public SenderParams () {

    }
    protected SenderParams(Parcel in) {
        message = in.readString();
        className = in.readString();
        methodName = in.readString();
        methodParams = in.readArray(Object.class.getClassLoader());
//        in.readStringArray(result);
    }

    public static final Creator<SenderParams> CREATOR = new Creator<SenderParams>() {
        @Override
        public SenderParams createFromParcel(Parcel in) {
            return new SenderParams(in);
        }

        @Override
        public SenderParams[] newArray(int size) {
            return new SenderParams[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(message);
        parcel.writeString(className);
        parcel.writeString(methodName);
        parcel.writeArray(methodParams);
//        parcel.writeArray(result);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(Object[] methodParams) {
        this.methodParams = methodParams;
    }
}
