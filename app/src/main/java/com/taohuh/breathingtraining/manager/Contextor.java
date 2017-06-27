package com.taohuh.breathingtraining.manager;

import android.content.Context;

/**
 * Created by Administrator on 10/6/2560.
 */

public class Contextor {
    private static Contextor instance;

    public static Contextor getInstance(){
        if(instance == null)
            instance = new Contextor();
        return instance;
    }

    private Context mContext;

    public Contextor(){}

    public void init(Context context){
        mContext = context;
    }

    public Context getContext(){
        return mContext;
    }
}
