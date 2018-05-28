package ke.co.dataintegrated.newsfeed.data.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ke.co.dataintegrated.newsfeed.data.User;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedDbSchema.UserTable;

public class NewsFeedDbActions {
    private SQLiteDatabase db;

    private NewsFeedBaseHelper newsFeedBaseHelper;

    public NewsFeedBaseHelper getNewsFeedBaseHelper() {
        return newsFeedBaseHelper;
    }

    public NewsFeedDbActions(Context context) {
        newsFeedBaseHelper = new NewsFeedBaseHelper(context);
    }

    // Add user to database
    public long addUser(User user, NewsFeedBaseHelper helper) {
        this.db = helper.getWritableDatabase();
        ContentValues contentValues = getContentValues(user);
        contentValues.put(UserTable.Cols.USER_UUID, user.getUserId().toString());
        contentValues.put(UserTable.Cols.FNAME, user.getFirstName());
        contentValues.put(UserTable.Cols.USERNAME, user.getUsername());
        contentValues.put(UserTable.Cols.EMAIL, user.getEmail());
        contentValues.put(UserTable.Cols.PASSWORD, user.getPassword());

        long result = db.insert(UserTable.NAME, null, contentValues);

        db.close();

        return result;
    }

    public String getUsername(String userId, NewsFeedBaseHelper helper) {
        this.db = helper.getReadableDatabase();
        String query = "select uuid, username from " + UserTable.NAME;
        Cursor cursor = db.rawQuery(query, null);
        String userUuid, username = "";

        while (cursor.moveToNext()) {
            userUuid = cursor.getString(0);

            if (userUuid.equals(userId)) {
                username = cursor.getString(1);
                break;
            }

        }

        cursor.close();
        return username;
    }

    public String getUserPassword(String userId, NewsFeedBaseHelper helper) {
        this.db = helper.getReadableDatabase();
        String query = "select uuid, passwd from " + UserTable.NAME;
        Cursor cursor = db.rawQuery(query, null);
        String userUuid, password = "";

        while (cursor.moveToNext()) {
            userUuid = cursor.getString(0);

            if (userUuid.equals(userId)) {
                password = cursor.getString(1);
                break;
            }

        }

        cursor.close();
        return password;
    }

    private ContentValues getContentValues(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserTable.Cols.USER_UUID, user.getUserId().toString());
        contentValues.put(UserTable.Cols.FNAME, user.getFirstName());
        contentValues.put(UserTable.Cols.USERNAME, user.getUsername());
        contentValues.put(UserTable.Cols.EMAIL, user.getEmail());

        return contentValues;
    }
}
