package ke.co.dataintegrated.newsfeed.data.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ke.co.dataintegrated.newsfeed.data.User;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedDbSchema.ArticlesTable;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedDbSchema.UserTable;

public class NewsFeedBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "newsfeed.db";
    private static final int VERSION = 1;

    private SQLiteDatabase db;
    private Context context;

    public NewsFeedBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserTable.CREATE_USERS_TBL);
        sqLiteDatabase.execSQL(ArticlesTable.CREATE_ARTICLES_TBL);

        this.db = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query_users = "drop table if exists " + UserTable.NAME;
        String query_articles = "drop table if exists " + ArticlesTable.NAME;
        db.execSQL(query_articles);
        db.execSQL(query_users);
        this.onCreate(db);
    }
}
