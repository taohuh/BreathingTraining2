package com.taohuh.breathingtraining.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pimpakarn.w on 6/27/2017.
 */

public class Setting implements Parcelable {
    private long mId;
    private int levelMap;
    private int settingMap;
    private long mUser;

    public Setting() {
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public int getLevelMap() {
        return levelMap;
    }

    public void setLevelMap(int levelMap) {
        this.levelMap = levelMap;
    }

    public int getSettingMap() {
        return settingMap;
    }

    public void setSettingMap(int settingMap) {
        this.settingMap = settingMap;
    }

    public long getmUser() {
        return mUser;
    }

    public void setmUser(long mUser) {
        this.mUser = mUser;
    }

    protected Setting(Parcel in) {
        mId = in.readLong();
        levelMap = in.readInt();
        settingMap = in.readInt();
        mUser = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeInt(levelMap);
        dest.writeInt(settingMap);
        dest.writeLong(mUser);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Setting> CREATOR = new Creator<Setting>() {
        @Override
        public Setting createFromParcel(Parcel in) {
            return new Setting(in);
        }

        @Override
        public Setting[] newArray(int size) {
            return new Setting[size];
        }
    };
}
