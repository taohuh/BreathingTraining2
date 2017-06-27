package com.taohuh.breathingtraining.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.taohuh.breathingtraining.model.Practice;
import com.taohuh.breathingtraining.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pimpakarn.w on 6/27/2017.
 */

public class UserDAO {
    public static final String TAG = "BT: UserDAO";

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = {
            DBHelper.COLUMN_USER_ID,
            DBHelper.COLUMN_USER_NAME,
            DBHelper.COLUMN_USER_AGE,
            DBHelper.COLUMN_USER_BPM_BEFORE_TEST,
            DBHelper.COLUMN_USER_BPM_AFTER_TEST,
            DBHelper.COLUMN_USER_GOAL_BPM,
            DBHelper.COLUMN_USER_TESTABLE,
            DBHelper.COLUMN_USER_TEST_COUNT,
            DBHelper.COLUMN_USER_IS_NOISY,
            DBHelper.COLUMN_USER_CHARACTER};

    public UserDAO(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public User createUser(String name, int age, double bpm_before_test,double bpm_after_test, String goal_bpm,
                           int testable, int test_count, int is_noisy, int character) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_USER_NAME, name);
        values.put(DBHelper.COLUMN_USER_AGE, age);
        values.put(DBHelper.COLUMN_USER_BPM_BEFORE_TEST, bpm_before_test);
        values.put(DBHelper.COLUMN_USER_BPM_AFTER_TEST, bpm_after_test);
        values.put(DBHelper.COLUMN_USER_GOAL_BPM,goal_bpm);
        values.put(DBHelper.COLUMN_USER_TESTABLE, testable);
        values.put(DBHelper.COLUMN_USER_TEST_COUNT, test_count);
        values.put(DBHelper.COLUMN_USER_IS_NOISY, is_noisy);
        values.put(DBHelper.COLUMN_USER_CHARACTER, character);

        // id ของ User
        long insertId = mDatabase
                .insert(DBHelper.TABLE_USER, null, values);
        Log.d(TAG, "createUser() insertId : " + insertId);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_USER, mAllColumns,
                DBHelper.COLUMN_USER_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    public void deleteUser(User user) {
        long id = user.getId();

        // delete all practices of this user
        PracticeDAO practiceDao = new PracticeDAO(mContext);
        List<Practice> listPractices = practiceDao.getPracticeOfUser(id);
        if (listPractices != null && !listPractices.isEmpty()) {
            for (Practice e : listPractices) {
                practiceDao.deletePractice(e);
            }
        }

        System.out.println("the deleted user has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_USER, DBHelper.COLUMN_USER_ID
                + " = " + id, null);
    }

    public int updateUser(long idUser, double bpm_after_test, int testable){
        System.out.println("Update user: " + idUser);
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_USER_BPM_AFTER_TEST, bpm_after_test);
        values.put(DBHelper.COLUMN_USER_TESTABLE, testable);

        return mDatabase.update(DBHelper.TABLE_USER,values,DBHelper.COLUMN_USER_ID + " = " + idUser
                ,null);
    }

    public int updateUserTestCount(long idUser, int test_count){
        System.out.println("Update User Test Count");
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_USER_TEST_COUNT, test_count);

        return mDatabase.update(DBHelper.TABLE_USER,values,DBHelper.COLUMN_USER_ID + " = " + idUser
                ,null);
    }

    public int updateUserIsNoisy(long idUser, int is_noisy){
        System.out.println("Update User Is Noisy");
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_USER_IS_NOISY, is_noisy);

        return mDatabase.update(DBHelper.TABLE_USER,values,DBHelper.COLUMN_USER_ID + " = " + idUser
                ,null);
    }

    public int updateUserCharacter(long idUser, int character){
        System.out.println("Update User Character");
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_USER_CHARACTER, character);

        return mDatabase.update(DBHelper.TABLE_USER,values,DBHelper.COLUMN_USER_ID + " = " + idUser
                ,null);
    }

    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<User>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_USER, mAllColumns,
                null, null, null, null, null);
        try{
            if(cursor.moveToFirst()){
                do{
                    User user = cursorToUser(cursor);
                    listUsers.add(user);
                    cursor.moveToNext();
                }while(!cursor.isAfterLast());
            }

        }catch(Exception e){
            Log.d(TAG,"Error while trying to get AllUser from database");
        }finally{
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return listUsers;
    }

    //User คนเดียว
    public User getUserById(long id) {
        Cursor cursor = mDatabase.query(DBHelper.TABLE_USER, mAllColumns,
                DBHelper.COLUMN_USER_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        User user = cursorToUser(cursor);
        return user;
    }

    protected User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(0));
        user.setName(cursor.getString(1));
        user.setAge(cursor.getInt(2));
        user.setBpmBeforeTest(cursor.getDouble(3));
        user.setBpmAfterTest(cursor.getDouble(4));
        user.setGoalBpm(cursor.getString(5));
        user.setTestAble(cursor.getInt(6));
        user.setTestCount(cursor.getInt(7));
        user.setIsNoisy(cursor.getInt(8));
        user.setCharacter(cursor.getInt(9));
        return user;
    }
}
