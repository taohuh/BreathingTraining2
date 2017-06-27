package com.taohuh.breathingtraining.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pimpakarn.w on 6/27/2017.
 */

public class Practice implements Parcelable {
    private long id;
    private int levelGame;
    private int bpmResult;
    private String date;
    private User mUser;

    public Practice() {

    }

    protected Practice(Parcel in) {
        id = in.readLong();
        levelGame = in.readInt();
        bpmResult = in.readInt();
        date = in.readString();
        mUser = in.readParcelable(User.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(levelGame);
        dest.writeInt(bpmResult);
        dest.writeString(date);
        dest.writeParcelable(mUser, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Practice> CREATOR = new Creator<Practice>() {
        @Override
        public Practice createFromParcel(Parcel in) {
            return new Practice(in);
        }

        @Override
        public Practice[] newArray(int size) {
            return new Practice[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevelGame() {
        return levelGame;
    }

    public void setLevelGame(int levelGame) {
        this.levelGame = levelGame;
    }

    public int getBpmResult() {
        return bpmResult;
    }

    public void setBpmResult(int bpmResult) {
        this.bpmResult = bpmResult;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }
}
