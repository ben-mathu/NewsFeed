package ke.co.dataintegrated.newsfeed.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ke.co.dataintegrated.newsfeed.R;

public class SignupFragment extends Fragment {
    private EditText edtFirstName, edtUsername, edtPassword;
    private Button btnSubmit;

    private String fname, username, passwd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        edtFirstName = view.findViewById(R.id.editText_firstName);
        fname = edtFirstName.getText().toString();

        edtUsername = view.findViewById(R.id.editText_username);
        username = edtUsername.getText().toString();

        edtPassword = view.findViewById(R.id.editText_password);
        passwd = edtPassword.getText().toString();

        btnSubmit = view.findViewById(R.id.button_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignupFragment fragment = new SignupFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
