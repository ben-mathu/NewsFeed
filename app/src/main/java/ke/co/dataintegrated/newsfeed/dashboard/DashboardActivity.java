package ke.co.dataintegrated.newsfeed.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import ke.co.dataintegrated.newsfeed.R;
import ke.co.dataintegrated.newsfeed.dashboard.login.LoginFragment;
import ke.co.dataintegrated.newsfeed.data.QueryPreferences;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedBaseHelper;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedDbActions;
import ke.co.dataintegrated.newsfeed.main.WelcomeActivity;

public class DashboardActivity extends FragmentActivity {
    private static final String EXTRA_USER_ID = "userId";

    private String username, userPassword;

    private boolean loggedTrue = false;

    // to pass extra value to next activity use this method
    // uses: Dashboard.newIntent(param1, param2)
//    public static Intent newIntent(Context context, UUID userId) {
//        String uId = userId.toString();
//        Intent intent = new Intent(context, DashboardActivity.class);
//        intent.putExtra(EXTRA_USER_ID, uId);
//        return intent;
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        NewsFeedDbActions newsFeedDbActions = new NewsFeedDbActions(getApplicationContext());
        NewsFeedBaseHelper newsFeedBaseHelper = newsFeedDbActions.getNewsFeedBaseHelper();

        String userId;

        userId = QueryPreferences.getStoredUserUuidQuery(getApplicationContext());
        username = newsFeedDbActions.getUsername(userId, newsFeedBaseHelper);
        userPassword = newsFeedDbActions.getUserPassword(userId, newsFeedBaseHelper);

        // assign true if user already signed up or logged in
        loggedTrue = QueryPreferences.getStoredLoggedTrueQuery(getApplicationContext());

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_dash_container);
        if (username == null || userPassword == null) {
            loggedTrue = false;
            QueryPreferences.setStoredLoggedTrueQuery(getApplicationContext(), loggedTrue);
            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
            startActivity(intent);
        } else {
            if (loggedTrue) {

                if (fragment == null) {
                    fragment = new DashboardFragment();
                    fragmentManager.beginTransaction()
                            .add(R.id.fragment_dash_container, fragment)
                            .commit();
                }
            } else {
                if (fragment == null) {
                    fragment = new LoginFragment();
                    fragmentManager.beginTransaction()
                            .add(R.id.fragment_dash_container, fragment)
                            .commit();
                }
            }
        }
    }
}
