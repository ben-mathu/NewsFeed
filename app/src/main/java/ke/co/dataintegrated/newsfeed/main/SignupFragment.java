package ke.co.dataintegrated.newsfeed.main;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.UUID;

import ke.co.dataintegrated.newsfeed.R;
import ke.co.dataintegrated.newsfeed.dashboard.DashboardActivity;
import ke.co.dataintegrated.newsfeed.data.User;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedBaseHelper;

public class SignupFragment extends Fragment {
    private static final String EXTRA_USER_ID = "userid";

    private User user;
    private NewsFeedBaseHelper newsFeedBaseHelper = new NewsFeedBaseHelper(getActivity());

    private EditText edtFirstName, edtUsername, edtPassword, edtEmail;
    private Button btnSubmit;
    private long result;

    private String fname, username, passwd, email;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = new User();

        UUID id = UUID.randomUUID();
        Date date = new Date();

        user.setFirstName(fname);
        user.setUsername(username);
        user.setPassword(passwd);
        user.setEmail(email);

        user.setUserId(id);
        user.setDate(date);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        edtFirstName = view.findViewById(R.id.editText_firstName);
        fname = edtFirstName.getText().toString();

        edtUsername = view.findViewById(R.id.editText_username);
        username = edtUsername.getText().toString();

        edtEmail = view.findViewById(R.id.editText_email);
        email = edtEmail.getText().toString();

        edtPassword = view.findViewById(R.id.editText_password);
        passwd = edtPassword.getText().toString();

        btnSubmit = view.findViewById(R.id.button_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = newsFeedBaseHelper.addUser(user);

                if (result == -1) {
                    Toast.makeText(getContext(), "The table was not populated", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), DashboardActivity.class);
                    startActivity(intent);
                }

            }
        });

        return view;
    }
}
