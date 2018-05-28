package ke.co.dataintegrated.newsfeed.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ke.co.dataintegrated.newsfeed.R;
import ke.co.dataintegrated.newsfeed.dashboard.DashboardActivity;
import ke.co.dataintegrated.newsfeed.data.QueryPreferences;

/**
 * Created by Ben on 5/21/2018.
 */

public class WelcomeFragment extends Fragment {
    private TextView txtWelcome, txtSubtitle;
    private Button btnNext;

    private boolean loggedTrue;
    private String uuidString;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loggedTrue = QueryPreferences.getStoredLoggedTrueQuery(getActivity());
        uuidString = QueryPreferences.getStoredUserUuidQuery(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        txtWelcome = view.findViewById(R.id.textView_welcome);
        txtSubtitle = view.findViewById(R.id.textView_subtitle);

        btnNext = view.findViewById(R.id.button_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // loggedTrue is from Shared preferences
                // checks if its true or false
                //      if not send user to signup view
                //      else send user to Dashboard
                if (uuidString == null) {
                    SignupFragment fragment = new SignupFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment,"findThisFragment")
                            .addToBackStack(null)
                            .commit();
                } else {
                    Intent intent = new Intent(getActivity(), DashboardActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
