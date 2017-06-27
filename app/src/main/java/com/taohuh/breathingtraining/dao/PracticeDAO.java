package com.taohuh.breathingtraining.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.taohuh.breathingtraining.model.History;
import com.taohuh.breathingtraining.model.Practice;
import com.taohuh.breathingtraining.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by pimpakarn.w on 6/27/2017.
 */

public class PracticeDAO {
    public static final String TAG = "PracticeDAO";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = {
            DBHelper.COLUMN_PRACTICE_ID,
            DBHelper.COLUMN_LEVEL_GAME,
            DBHelper.COLUMN_BPM_RESULT,
            DBHelper.COLUMN_DATE,
            DBHelper.COLUMN_PRACTICE_USER_ID};

    public PracticeDAO(Context context) {
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

    public Practice createPractice(int level_game,int bpm_result,String date,long userId) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_LEVEL_GAME, level_game);
        values.put(DBHelper.COLUMN_BPM_RESULT, bpm_result);
        values.put(DBHelper.COLUMN_DATE, getDateTime());
        values.put(DBHelper.COLUMN_PRACTICE_USER_ID, userId);

        long insertId = mDatabase
                .insert(DBHelper.TABLE_PRACTICE, null, values);
        System.out.println("ID Practice : " + insertId);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_PRACTICE, mAllColumns,
                DBHelper.COLUMN_PRACTICE_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Practice newPractice = cursorToPractice(cursor);
        cursor.close();
        return newPractice;
    }

    public void deletePractice(Practice practice) {
        long id = practice.getId();
        System.out.println("the deleted practice has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_PRACTICE, DBHelper.COLUMN_PRACTICE_ID
                + " = " + id, null);
    }

    public List<Practice> getAllPractices() {
        List<Practice> listPractices = new ArrayList<Practice>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_PRACTICE, mAllColumns,
                null, null, null, null, null);
        System.out.println("count cursor : " + cursor.getCount());
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Practice practice = cursorToPractice(cursor);
            listPractices.add(practice);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listPractices;
    }

    public List<Practice> getPracticeOfUser(long userId) {
        List<Practice> listPractices = new ArrayList<Practice>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_PRACTICE, mAllColumns,
                DBHelper.COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(userId)}, null, null, null);
        //fetch each record

        try{
            if(cursor.moveToFirst()){
                do{
                    Practice practice = cursorToPractice(cursor);
                    listPractices.add(practice);
                    cursor.moveToNext();
                }while(!cursor.isAfterLast());
            }

        }catch(Exception e){
            Log.d(TAG,"Error while trying to getPracticeOfUser from database");
        }finally{
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }

        /*
        if(cursor.moveToFirst()){
            do{
                Practice practice = cursorToPractice(cursor);
                listPractices.add(practice);

                //cursor.moveToNext();
            }while(cursor.moveToNext());
        }
        */
        // make sure to close the cursor
        //cursor.close();
        return listPractices;
    }

    public List<History> getPracticeHistoryOfUser(long userId, String firstDayOfWeek, String lastDayOfWeek) {
        //หา Max Mix Avg ของ bpm result
        System.out.println("getPracticeHistoryOfUser");
        System.out.println(firstDayOfWeek +"\n"+ lastDayOfWeek);
        List<History> listPracticesHistory = new ArrayList<History>();

        String query = String.format("SELECT %s ,%s, %s, avg(%s) AS %s, min(%s) AS %s, max(%s) AS %s FROM %s WHERE %s = %s AND %s BETWEEN '%s' AND '%s' GROUP BY %s, %s, %s ORDER BY %s, %s DESC" ,
                DBHelper.COLUMN_PRACTICE_USER_ID,
                DBHelper.COLUMN_LEVEL_GAME,
                DBHelper.COLUMN_DATE,
                DBHelper.COLUMN_BPM_RESULT,
                DBHelper.COLUMN_BPM_AVG,
                DBHelper.COLUMN_BPM_RESULT,
                DBHelper.COLUMN_BPM_MIN,
                DBHelper.COLUMN_BPM_RESULT,
                DBHelper.COLUMN_BPM_MAX,
                DBHelper.TABLE_PRACTICE,
                DBHelper.COLUMN_PRACTICE_USER_ID,
                userId,
                DBHelper.COLUMN_DATE,
                firstDayOfWeek,
                lastDayOfWeek,
                DBHelper.COLUMN_PRACTICE_USER_ID,
                DBHelper.COLUMN_LEVEL_GAME,
                DBHelper.COLUMN_DATE,
                DBHelper.COLUMN_DATE,
                DBHelper.COLUMN_LEVEL_GAME);

        Cursor cursor = mDatabase.rawQuery(query,null);

        System.out.println("count cursor" + cursor.getCount());
        //fetch each record
        //เลื่อน cursor ไปตำแหน่งแรกสุด
        cursor.moveToFirst();
        //ทำไปเรื่อยๆ ถ้า cursor ยังไม่ใช่ตัวสุดท้าย
        while (!cursor.isAfterLast()) {
            History history = new History();
            userId = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_PRACTICE_USER_ID));
            float avg_result = cursor.getFloat(cursor.getColumnIndex(DBHelper.COLUMN_BPM_AVG));
            float min_result = cursor.getFloat(cursor.getColumnIndex(DBHelper.COLUMN_BPM_MIN));
            float max_result = cursor.getFloat(cursor.getColumnIndex(DBHelper.COLUMN_BPM_MAX));
            int columnLevel = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_LEVEL_GAME));
            String date = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DATE));
            System.out.println("Bpm avg: " + avg_result
                    + " Bpm min: " + min_result
                    + " Bpm max: " + max_result
                    + " Level: " + columnLevel
                    + " Date: " + date);

            history.setmPractice(columnLevel);
            history.setDate(date);
            history.setmUser(userId);
            history.setBpmMAX(max_result);
            history.setBpmMIN(min_result);
            history.setBpmAVG(avg_result);
            listPracticesHistory.add(history);

            //เลื่อน cursor ไปตัวถัดไป
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listPracticesHistory;
    }

    private Practice cursorToPractice(Cursor cursor) {
        Log.d("Count Practice",String.valueOf(cursor.getCount()));
        Practice practice = new Practice();
        practice.setId(cursor.getLong(0));
        practice.setLevelGame(cursor.getInt(1));
        practice.setBpmResult(cursor.getInt(2));
        practice.setDate(cursor.getString(3));

        // get The user by id
        long userId = cursor.getLong(4);

        UserDAO dao = new UserDAO(mContext);
        User user = dao.getUserById(userId);
        if (user != null)
            practice.setmUser(user);

        return practice;
    }

    private Practice cursorToHistoryPractice(Cursor cursor){
        System.out.println("Data Count: " + String.valueOf(cursor.getCount()));
        Practice historyPractice = new Practice();
        historyPractice.setLevelGame(cursor.getInt(1));
        historyPractice.setBpmResult(cursor.getInt(2));
        historyPractice.setDate(cursor.getString(3));

        // get The user by id
        long userId = cursor.getLong(4);

        UserDAO dao = new UserDAO(mContext);
        User user = dao.getUserById(userId);
        if (user != null)
            historyPractice.setmUser(user);

        return historyPractice;
    }

    /*
    * get datetime
    */
    private String getDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String strDate = dateFormat.format(date);
        System.out.println("Date in EndGamePage: " + dateFormat.format(date));
        return strDate;
    }
}
