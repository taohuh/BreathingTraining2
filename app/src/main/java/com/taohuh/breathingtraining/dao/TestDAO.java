package com.taohuh.breathingtraining.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.taohuh.breathingtraining.model.TestPractice;
import com.taohuh.breathingtraining.model.User;

/**
 * Created by pimpakarn.w on 6/27/2017.
 */

public class TestDAO {
    public static final String TAG = "TestDAO";

    private Context mContext;

    //Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = {
            DBHelper.COLUMN_TEST_ID,
            DBHelper.COLUMN_LEVEL_TEST,
            DBHelper.COLUMN_RESULT_TEST,
            DBHelper.COLUMN_TEST_USER_ID
    };

    public TestDAO(Context context){
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

    public TestPractice createdTest(int level_test, int test_result, long userId) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_LEVEL_TEST, level_test);
        values.put(DBHelper.COLUMN_RESULT_TEST, test_result);
        values.put(DBHelper.COLUMN_TEST_USER_ID, userId);

        long insertId = mDatabase
                .insert(DBHelper.TABLE_TEST, null, values);
        System.out.println("ID TestPractice : " + insertId);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_TEST, mAllColumns,
                DBHelper.COLUMN_TEST_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        TestPractice newTestPractice = cursorToTest(cursor);
        cursor.close();
        return newTestPractice;
    }

    private TestPractice cursorToTest(Cursor cursor) {
        Log.d("Count TestPractice",String.valueOf(cursor.getCount()));
        TestPractice testPractice = new TestPractice();
        testPractice.setmId(cursor.getLong(0));
        testPractice.setLevelTest(cursor.getInt(1));
        testPractice.setTestResult(cursor.getInt(2));

        // get The user by id
        long userId = cursor.getLong(3);

        UserDAO dao = new UserDAO(mContext);
        User user = dao.getUserById(userId);
        if (user != null)
            testPractice.setmUser(user);

        return testPractice;
    }
}
