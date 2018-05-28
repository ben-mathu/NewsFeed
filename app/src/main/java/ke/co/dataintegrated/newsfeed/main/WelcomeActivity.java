package ke.co.dataintegrated.newsfeed.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import ke.co.dataintegrated.newsfeed.R;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedBaseHelper;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedDbActions;

/**
 * Created by Ben on 5/21/2018.
 */

public class WelcomeActivity extends FragmentActivity {

    private NewsFeedDbActions newsFeedDbActions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        newsFeedDbActions = new NewsFeedDbActions(getApplicationContext());

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new WelcomeFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
