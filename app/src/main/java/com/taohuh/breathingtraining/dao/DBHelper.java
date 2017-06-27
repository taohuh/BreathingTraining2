package com.taohuh.breathingtraining.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pimpakarn.w on 6/27/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static String TAG = "BT: DBHelper";

    // columns of the users table
    public static final String TABLE_USER = "users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_AGE = "age";
    public static final String COLUMN_USER_BPM_BEFORE_TEST = "bpm_before_test";
    public static final String COLUMN_USER_BPM_AFTER_TEST = "bpm_after_test";
    public static final String COLUMN_USER_GOAL_BPM = "goal_bpm";
    public static final String COLUMN_USER_TESTABLE = "testable";
    public static final String COLUMN_USER_TEST_COUNT = "test_count";
    public static final String COLUMN_USER_IS_NOISY = "is_noisy";
    public static final String COLUMN_USER_CHARACTER = "character";

    // columns of the practices table
    public static final String TABLE_PRACTICE = "practices";
    public static final String COLUMN_PRACTICE_ID = "_id";
    public static final String COLUMN_LEVEL_GAME = "level_game";
    public static final String COLUMN_BPM_RESULT = "bpm_result";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PRACTICE_USER_ID = "user_id";

    // Column of the histories table
    public static final String TABLE_HISTORY = "histories";
    public static final String COLUMN_HISTORY_ID = "_id";
    public static final String COLUMN_BPM_AVG = "bpm_avg";
    public static final String COLUMN_BPM_MIN = "bpm_min";
    public static final String COLUMN_BPM_MAX = "bpm_max";
    public static final String COLUMN_DATE_HISTORY = "date";
    public static final String COLUMN_HISTORY_PRACTICE = "practice_level";
    public static final String COLUMN_HISTORY_USER = "user_id";

    //Column of the settings table
    public static final String TABLE_SETTING = "settings";
    public static final String COLUMN_SETTING_ID = "_id";
    public static final String COLUMN_LEVEL_MAP = "level_map";
    public static final String COLUMN_SETTING_MAP = "setting_map";
    public static final String COLUMN_SETTING_USER_ID = "user_id";

    //Column of the tests table
    public static final String TABLE_TEST = "tests";
    public static final String COLUMN_TEST_ID = "_id";
    public static final String COLUMN_LEVEL_TEST = "level_test";
    public static final String COLUMN_RESULT_TEST = "result_test";
    public static final String COLUMN_TEST_USER_ID = "user_id";


    private static final String DATABASE_NAME = "new_db.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement of the practices table creation
    private static final String SQL_CREATE_TABLE_PRACTICES = "CREATE TABLE " + TABLE_PRACTICE + "("
            + COLUMN_PRACTICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LEVEL_GAME + " INTEGER NOT NULL, "
            + COLUMN_BPM_RESULT + " INTEGER NOT NULL, "
            + COLUMN_DATE + " DATETIME NOT NULL, "
            + COLUMN_PRACTICE_USER_ID + " INTEGER NOT NULL "
            +");";

    // SQL statement of the histories table creation
    private static final String SQL_CREATE_TABLE_HISTORIES = "CREATE TABLE " + TABLE_HISTORY + "("
            + COLUMN_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_BPM_AVG + " FLOAT, "
            + COLUMN_BPM_MIN + " FLOAT, "
            + COLUMN_BPM_MAX + " FLOAT, "
            + COLUMN_DATE_HISTORY + " DATETIME NOT NULL, "
            + COLUMN_HISTORY_PRACTICE + " INTEGER NOT NULL, "
            + COLUMN_HISTORY_USER + " INTEGER NOT NULL "
            +");";

    // SQL statement of the users table creation
    private static final String SQL_CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_NAME + " TEXT NOT NULL, "
            + COLUMN_USER_AGE + " INTEGER NOT NULL, "
            + COLUMN_USER_BPM_BEFORE_TEST + " INTEGER NOT NULL, "
            + COLUMN_USER_BPM_AFTER_TEST + " INTEGER NOT NULL, "
            + COLUMN_USER_GOAL_BPM + " TEXT NOT NULL, "
            + COLUMN_USER_TESTABLE + " INTEGER NOT NULL, "
            + COLUMN_USER_TEST_COUNT + " INTEGER NOT NULL, "
            + COLUMN_USER_IS_NOISY + " INTEGER NOT NULL, "
            + COLUMN_USER_CHARACTER + " INTEGER NOT NULL"
            +");";

    // SQL statement of the tests table creation
    private static final String SQL_CREATE_TABLE_TESTS = "CREATE TABLE " + TABLE_TEST + "("
            + COLUMN_TEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LEVEL_TEST + " INTEGER NOT NULL, "
            + COLUMN_RESULT_TEST + " INTEGER NOT NULL, "
            + COLUMN_TEST_USER_ID + " INTEGER NOT NULL "
            + ");";

    // SQL statement of the settings table creation
    private static final String SQL_CREATE_TABLE_SETTINGS = "CREATE TABLE " + TABLE_SETTING + "("
            + COLUMN_SETTING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SETTING_MAP + " INTEGER NOT NULL, "
            + COLUMN_LEVEL_MAP + " INTEGER NOT NULL, "
            + COLUMN_SETTING_USER_ID + " INTEGER NOT NULL "
            + ");";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_TABLE_USERS);
        database.execSQL(SQL_CREATE_TABLE_PRACTICES);
        database.execSQL(SQL_CREATE_TABLE_HISTORIES);
        database.execSQL(SQL_CREATE_TABLE_TESTS);
        database.execSQL(SQL_CREATE_TABLE_SETTINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRACTICE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTING);

        // recreate the tables
        onCreate(db);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
}
