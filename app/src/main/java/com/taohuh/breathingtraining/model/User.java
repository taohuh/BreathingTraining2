package com.taohuh.breathingtraining.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pimpakarn.w on 6/27/2017.
 */

public class User implements Parcelable {

    public User(){};

    private long id;
    private String name;
    private int age;
    private double bpmBeforeTest;
    private double bpmAfterTest;
    private String goalBpm;
    private int character;
    private int testCount;
    private int isNoisy;
    private int testAble;

    protected User(Parcel in) {
        id = in.readLong();
        name = in.readString();
        age = in.readInt();
        bpmBeforeTest = in.readDouble();
        bpmAfterTest = in.readDouble();
        goalBpm = in.readString();
        character = in.readInt();
        testCount = in.readInt();
        isNoisy = in.readInt();
        testAble = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBpmBeforeTest() {
        return bpmBeforeTest;
    }

    public void setBpmBeforeTest(double bpmBeforeTest) {
        this.bpmBeforeTest = bpmBeforeTest;
    }

    public double getBpmAfterTest() {
        return bpmAfterTest;
    }

    public void setBpmAfterTest(double bpmAfterTest) {
        this.bpmAfterTest = bpmAfterTest;
    }

    public String getGoalBpm() {
        return goalBpm;
    }

    public void setGoalBpm(String goalBpm) {
        this.goalBpm = goalBpm;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public int getTestCount() {
        return testCount;
    }

    public void setTestCount(int testCount) {
        this.testCount = testCount;
    }

    public int getIsNoisy() {
        return isNoisy;
    }

    public void setIsNoisy(int isNoisy) {
        this.isNoisy = isNoisy;
    }

    public int getTestAble() {
        return testAble;
    }

    public void setTestAble(int testAble) {
        this.testAble = testAble;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeDouble(bpmBeforeTest);
        dest.writeDouble(bpmAfterTest);
        dest.writeString(goalBpm);
        dest.writeInt(character);
        dest.writeInt(testCount);
        dest.writeInt(isNoisy);
        dest.writeInt(testAble);
    }
}
