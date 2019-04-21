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

    /**
     * 调用方法名
     */

    /**
     * 调用参数
     */


    public SenderParams () {

    }
    protected SenderParams(Parcel in) {
        message = in.readString();
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
//        parcel.writeArray(result);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
