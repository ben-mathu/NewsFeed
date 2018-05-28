package ke.co.dataintegrated.newsfeed.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import ke.co.dataintegrated.newsfeed.R;
import ke.co.dataintegrated.newsfeed.dashboard.DashboardActivity;
import ke.co.dataintegrated.newsfeed.data.QueryPreferences;
import ke.co.dataintegrated.newsfeed.data.User;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedBaseHelper;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedDbActions;

public class SignupFragment extends Fragment {

    private User user;
    private NewsFeedBaseHelper newsFeedBaseHelper;
    private NewsFeedDbActions newsFeedDbActions;

    private EditText edtFirstName, edtUsername, edtPassword, edtEmail;
    private Button btnSubmit;
    private TextView txtLogin;

    private long result;

    private String fname, username, passwd, email;

    private UUID id;
    private boolean boolTrue = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newsFeedDbActions = new NewsFeedDbActions(getActivity());
        newsFeedBaseHelper = newsFeedDbActions.getNewsFeedBaseHelper();

        user = new User();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        edtFirstName = view.findViewById(R.id.editText_firstName);
        edtFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Left Blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                user.setFirstName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Left Blank
            }
        });

        edtUsername = view.findViewById(R.id.editText_username);
        edtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Left Blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                user.setUsername(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Left Blank
            }
        });

        edtEmail = view.findViewById(R.id.editText_email);
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Left Blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                user.setEmail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Left Blank
            }
        });

        edtPassword = view.findViewById(R.id.editText_password);
        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Left Blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                user.setPassword(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Left Blank
            }
        });

        id = UUID.randomUUID();

        user.setUserId(id);

        btnSubmit = view.findViewById(R.id.button_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = newsFeedDbActions.addUser(user, newsFeedBaseHelper);

                if (result == -1) {
                    Toast.makeText(getContext(), "The table was not populated", Toast.LENGTH_SHORT).show();
                } else {
                    boolTrue = true;
                    QueryPreferences.setStoredUserUuidQuery(getActivity(), id.toString());
                    QueryPreferences.setStoredLoggedTrueQuery(getActivity(), boolTrue);
                    Intent intent = new Intent(getActivity(), DashboardActivity.class);
                    startActivity(intent);
                }

            }
        });

        txtLogin = view.findViewById(R.id.textView_login);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolTrue = false;
                QueryPreferences.setStoredLoggedTrueQuery(getActivity(), boolTrue);
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
