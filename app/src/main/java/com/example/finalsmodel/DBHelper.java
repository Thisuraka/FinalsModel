package com.example.finalsmodel;

import android.provider.BaseColumns;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final int VERSION = 3;
    private static final String DB_NAME = "fina1db";
    private static final String TABLE_1 = "user";

    //  TABLE_1 Column names
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String DOB = "dob";
    private static final String GENDER = "gender";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY_1 = "CREATE TABLE " + TABLE_1 + " " +
                "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT,"
                + PASSWORD + " TEXT,"
                + DOB + " TEXT,"
                + GENDER + " TEXT" +
                ");";

        db.execSQL(TABLE_CREATE_QUERY_1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE_QUERY_1 = "DROP TABLE IF EXISTS " + TABLE_1;
        db.execSQL(DROP_TABLE_QUERY_1);
        onCreate(db);
    }

    public long addInfo(UserProfile.Users user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, user.getUsername());
        contentValues.put(PASSWORD, user.getPassword());
        contentValues.put(DOB, user.getDob());
        contentValues.put(GENDER, user.getGender());

        long rowId = sqLiteDatabase.insert(TABLE_1, null, contentValues);
        sqLiteDatabase.close();

        return rowId;
    }

    public boolean updateInfo(String username, String password, String dob, String gender) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, username);
        contentValues.put(PASSWORD, password);
        contentValues.put(DOB, dob);
        contentValues.put(GENDER, gender);

        String selection = NAME + " LIKE ?";
        String[] selectionArgs = {username};

        int count = db.update(
                TABLE_1,
                contentValues,
                selection,
                selectionArgs);

        if (count >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteInfo(String username) {

        SQLiteDatabase db = getWritableDatabase();

        String selection = NAME + " LIKE ?";

        String[] selectionArgs = {username};

        db.delete(TABLE_1, selection, selectionArgs);
    }

    public List readAllInfo() {
        SQLiteDatabase db = getReadableDatabase();

        String username = "username";

        String[] projection = {
                _ID,
                NAME,
                PASSWORD,
                DOB,
                GENDER
        };

        String selection = NAME + " = ?";
        String[] selectionArgs = {username};

        String sortOrder =  NAME + " ASC";

        Cursor cursor = db.query(
                TABLE_1,                     // The table to query
                projection,                 // The array of columns to return (pass null to get all)
                null,            // The columns for the WHERE clause
                null,       // The values for the WHERE clause
                null,         // don't group the rows
                null,         // don't filter by row groups
                sortOrder           // The sort order
        );

        List usernames = new ArrayList<>();
        while (cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
            usernames.add(user);
        }
        cursor.close();
        return usernames;
    }

    public List readAllInfo(String username) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                _ID,
                NAME,
                PASSWORD,
                DOB,
                GENDER
        };

        String selection = NAME + " = ?";
        String[] selectionArgs = {username};

        String sortOrder = NAME + " ASC";

        Cursor cursor = db.query(
                TABLE_1,                   // The table to query
                projection,               // The array of columns to return (pass null to get all)
                selection,               // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,         // don't group the rows
                null,         // don't filter by row groups
                sortOrder           // The sort order
        );

        List userInfo = new ArrayList<>();
        while (cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(PASSWORD));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(DOB));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(GENDER));

            userInfo.add(user);
            userInfo.add(password);
            userInfo.add(dob);
            userInfo.add(gender);
        }
        cursor.close();
        return userInfo;
    }
}
