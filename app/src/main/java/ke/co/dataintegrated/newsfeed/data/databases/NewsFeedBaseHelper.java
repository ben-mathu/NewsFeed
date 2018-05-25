package ke.co.dataintegrated.newsfeed.data.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import ke.co.dataintegrated.newsfeed.data.User;

public class NewsFeedBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "newsfeed.db";
    private static final int VERSION = 1;

    private static final String UUID = "uuid";
    private static final String FNAME = "fname";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "passwd";
    private static final String DATE = "create_timestamp";

    private static final String NAME = "users_tbl";

    private static final String TBL_CREATE = "CREATE TABLE users_tbl " +
            "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "uuid INTEGER, " +
            "fname TEXT," +
            "username TEXT," +
            "email TEXT," +
            "passwd TEXT," +
            "create_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")";

    private SQLiteDatabase db;
    private Context context;

    public NewsFeedBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TBL_CREATE);

        this.db = sqLiteDatabase;
    }

    // Add user to database
    public long addUser(User user) {
        db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UUID, user.getUserId().toString());
        contentValues.put(FNAME, user.getFirstName());
        contentValues.put(USERNAME, user.getUsername());
        contentValues.put(EMAIL, user.getEmail());
        contentValues.put(DATE, user.getDate().getTime());

        long result = db.insert(NAME, null, contentValues);

        db.close();

        return result;
    }

    public String searchForPassword(String uname) {
        db = this.getReadableDatabase();
        String query = "select username, passwd from " + NAME;
        Cursor cursor = db.rawQuery(query, null);
        String username, password = "Does not exist";
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                username = cursor.getString(0);

                if (username.equals(uname)) {
                    password = cursor.getString(1);
                    break;
                }

            }
        }

        cursor.close();
        return password;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "drop table if exists " + NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
