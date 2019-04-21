package com.mini.aidl;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * @author leroy
 * @description:
 * @data: 19/4/20
 */
public class CallBackResult implements Parcelable {

    private String message;

    private int code;

    private Object result[];

    public CallBackResult() {

    }
    protected CallBackResult(Parcel in) {
        message = in.readString();
//        in.readStringArray(result);
    }

    public static final Creator<CallBackResult> CREATOR = new Creator<CallBackResult>() {
        @Override
        public CallBackResult createFromParcel(Parcel in) {
            return new CallBackResult(in);
        }

        @Override
        public CallBackResult[] newArray(int size) {
            return new CallBackResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(message);
//        parcel.writeArray(result);
    }

    public void readFromParcel(Parcel in) {
        message = in.readString();
//        in.readStringArray(result);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object[] getResult() {
        return result;
    }

    public void setResult(Object[] result) {
        this.result = result;
    }
}
