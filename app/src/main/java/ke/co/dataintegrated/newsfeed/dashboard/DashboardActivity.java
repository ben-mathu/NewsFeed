package ke.co.dataintegrated.newsfeed.dashboard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ke.co.dataintegrated.newsfeed.R;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedBaseHelper;

public class DashboardActivity extends Activity {
    private static final String EXTRA_USERNAME = "username";

    private NewsFeedBaseHelper newsFeedBaseHelper = new NewsFeedBaseHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        String username = getIntent().getStringExtra("uname");

        String pass = newsFeedBaseHelper.searchForPassword(username);
    }
}
