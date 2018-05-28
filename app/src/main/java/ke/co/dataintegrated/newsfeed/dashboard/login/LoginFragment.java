package ke.co.dataintegrated.newsfeed.dashboard.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ke.co.dataintegrated.newsfeed.R;
import ke.co.dataintegrated.newsfeed.dashboard.DashboardFragment;
import ke.co.dataintegrated.newsfeed.data.QueryPreferences;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedBaseHelper;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedDbActions;

public class LoginFragment extends Fragment {
    private static final String EXTRA_USER_ID = "userId";

    private String userId, pass;

    private String username, userPassword;
    private boolean valueTrue = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewsFeedDbActions newsFeedDbActions = new NewsFeedDbActions(getActivity());
        NewsFeedBaseHelper newsFeedBaseHelper = newsFeedDbActions.getNewsFeedBaseHelper();

        userId = QueryPreferences.getStoredUserUuidQuery(getActivity());

        username = newsFeedDbActions.getUsername(userId, newsFeedBaseHelper);
        userPassword = newsFeedDbActions.getUserPassword(userId, newsFeedBaseHelper);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        EditText edtUsername = view.findViewById(R.id.editText_username);
        final String uname = edtUsername.getText().toString();

        EditText edtPassword = view.findViewById(R.id.editText_password);
        final String password = edtPassword.getText().toString();

        Button btnLogin = view.findViewById(R.id.button_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.equals(uname) && userPassword.equals(password)) {
                    valueTrue = true;
                    QueryPreferences.setStoredLoggedTrueQuery(getActivity(), valueTrue);
                    QueryPreferences.setStoredUserUuidQuery(getActivity(), userId);
                    DashboardFragment fragment = new DashboardFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_dash_container, fragment, "search for fragment")
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        return view;
    }
}
