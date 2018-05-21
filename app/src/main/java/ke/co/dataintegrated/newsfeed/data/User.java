package ke.co.dataintegrated.newsfeed.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.util.UUID;

import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedBaseHelper;

public class User {
    private Context mContext;
    private SQLiteDatabase database;

    private String firstName, username, password;
    private UUID userId;
    private Date date;

    public User(Context context) {
        mContext = context.getApplicationContext();
        database = new NewsFeedBaseHelper(mContext)
                .getWritableDatabase();

        userId = UUID.randomUUID();
        date = new Date();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
