package com.taohuh.breathingtraining.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pimpakarn.w on 6/27/2017.
 */

public class TestPractice implements Parcelable {
    private long mId;
    private int levelTest;
    private int testResult;
    private User mUser;

    public TestPractice() {
    }

    protected TestPractice(Parcel in) {
        mId = in.readLong();
        levelTest = in.readInt();
        testResult = in.readInt();
        mUser = in.readParcelable(User.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeInt(levelTest);
        dest.writeInt(testResult);
        dest.writeParcelable(mUser, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TestPractice> CREATOR = new Creator<TestPractice>() {
        @Override
        public TestPractice createFromParcel(Parcel in) {
            return new TestPractice(in);
        }

        @Override
        public TestPractice[] newArray(int size) {
            return new TestPractice[size];
        }
    };

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public int getLevelTest() {
        return levelTest;
    }

    public void setLevelTest(int levelTest) {
        this.levelTest = levelTest;
    }

    public int getTestResult() {
        return testResult;
    }

    public void setTestResult(int testResult) {
        this.testResult = testResult;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }
}
