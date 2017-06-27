package com.taohuh.breathingtraining.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.taohuh.breathingtraining.model.Setting;
import com.taohuh.breathingtraining.model.User;

/**
 * Created by pimpakarn.w on 6/27/2017.
 */

public class SettingDAO {
    public static final String TAG = "SettingDAO";

    private Context mContext;

    //Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = {
            DBHelper.COLUMN_SETTING_ID,
            DBHelper.COLUMN_LEVEL_MAP,
            DBHelper.COLUMN_SETTING_MAP,
            DBHelper.COLUMN_SETTING_USER_ID
    };

    public SettingDAO(Context context){
        mDbHelper = new DBHelper(context);
        this.mContext = context;
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on opening database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Setting createdSetting(int level_map, int setting_map, long userId) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_LEVEL_MAP, level_map);
        values.put(DBHelper.COLUMN_SETTING_MAP, setting_map);
        values.put(DBHelper.COLUMN_TEST_USER_ID, userId);

        long insertId = mDatabase
                .insert(DBHelper.TABLE_SETTING, null, values);
        System.out.println("ID Setting : " + insertId);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_SETTING, mAllColumns,
                DBHelper.COLUMN_SETTING_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Setting newSetting = cursorToSetting(cursor);
        cursor.close();
        return newSetting;
    }

    public int updateSetting(long idUser, int level_map, int setting_map){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_LEVEL_MAP, level_map);
        values.put(DBHelper.COLUMN_SETTING_MAP, setting_map);
        System.out.println("Update Setting: " + idUser
                + " level_map: " + level_map + " setting map: " + setting_map);
        return mDatabase.update(DBHelper.TABLE_SETTING,values,DBHelper.COLUMN_LEVEL_MAP + " = " + level_map
                        + " AND " + DBHelper.COLUMN_SETTING_USER_ID + " = " + idUser
                ,null);
    }

    public Setting SearchByUserIdAndLevel (long idUser, int level_map){
        Setting setting = new Setting();
        String query = String.format("SELECT %s, %s, %s FROM %s WHERE %s = %s AND %s = %s",
                DBHelper.COLUMN_SETTING_USER_ID,
                DBHelper.COLUMN_LEVEL_MAP,
                DBHelper.COLUMN_SETTING_MAP,
                DBHelper.TABLE_SETTING,
                DBHelper.COLUMN_LEVEL_MAP,
                level_map,
                DBHelper.COLUMN_SETTING_USER_ID,
                idUser);

        Cursor cursor = mDatabase.rawQuery(query, null);

        //fetch each record
        //เลื่อน cursor ไปตำแหน่งแรกสุด
        cursor.moveToFirst();
        //ทำไปเรื่อยๆ ถ้า cursor ยังไม่ใช่ตัวสุดท้าย
        while (!cursor.isAfterLast()) {
            //Setting setting = new Setting();
            int setting_map = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_SETTING_MAP));
            long userId = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_SETTING_USER_ID));
            int lv_map = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_LEVEL_MAP));
            System.out.println("Setting: " + setting_map
                    + " lv: " + lv_map
                    + " userId: " + userId);

            setting.setSettingMap(setting_map);
            setting.setLevelMap(lv_map);
            setting.setmUser(userId);

            //เลื่อน cursor ไปตัวถัดไป
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return setting;
    }

    private Setting cursorToSetting(Cursor cursor) {
        Log.d("Count Setting",String.valueOf(cursor.getCount()));

        Setting setting = new Setting();
        setting.setmId(cursor.getLong(0));
        setting.setLevelMap(cursor.getInt(1));
        setting.setSettingMap(cursor.getInt(2));


        // get The user by id
        long userId = cursor.getLong(3);

        UserDAO dao = new UserDAO(mContext);
        User user = dao.getUserById(userId);
        if (user != null)
            setting.setmUser(userId);

        return setting;
    }
}
