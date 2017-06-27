package com.taohuh.breathingtraining.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pimpakarn.w on 6/27/2017.
 */

public class History implements Parcelable {
    //PK
    private long mId;

    private float bpmAVG;
    private float bpmMAX;
    private float bpmMIN;

    private String date;

    //FK
    private long mUser;
    private int mPractice;

    public History(){

    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public float getBpmAVG() {
        return bpmAVG;
    }

    public void setBpmAVG(float bpmAVG) {
        this.bpmAVG = bpmAVG;
    }

    public float getBpmMAX() {
        return bpmMAX;
    }

    public void setBpmMAX(float bpmMAX) {
        this.bpmMAX = bpmMAX;
    }

    public float getBpmMIN() {
        return bpmMIN;
    }

    public void setBpmMIN(float bpmMIN) {
        this.bpmMIN = bpmMIN;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getmUser() {
        return mUser;
    }

    public void setmUser(long mUser) {
        this.mUser = mUser;
    }

    public int getmPractice() {
        return mPractice;
    }

    public void setmPractice(int mPractice) {
        this.mPractice = mPractice;
    }

    protected History(Parcel in) {
        mId = in.readLong();
        bpmAVG = in.readFloat();
        bpmMAX = in.readFloat();
        bpmMIN = in.readFloat();
        date = in.readString();
        mUser = in.readLong();
        mPractice = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeFloat(bpmAVG);
        dest.writeFloat(bpmMAX);
        dest.writeFloat(bpmMIN);
        dest.writeString(date);
        dest.writeLong(mUser);
        dest.writeInt(mPractice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel in) {
            return new History(in);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };
}
