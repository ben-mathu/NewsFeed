package ke.co.dataintegrated.newsfeed.data.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ke.co.dataintegrated.newsfeed.data.User;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedDBSchema.UserTable;

public class NewsFeedBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "newsfeed.db";
    private static final int VERSION = 1;

    public NewsFeedBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + UserTable.NAME +
                "( _id integer primary key autoincrement, " +
                        UserTable.Cols.UUID + ", " +
                UserTable.Cols.FNAME + ", " +
                UserTable.Cols.USERNAME + ", " +
                UserTable.Cols.PASSWORD + ", " +
                UserTable.Cols.DATE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
