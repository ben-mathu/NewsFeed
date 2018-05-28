package ke.co.dataintegrated.newsfeed.dashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ke.co.dataintegrated.newsfeed.BuildConfig;
import ke.co.dataintegrated.newsfeed.R;
import ke.co.dataintegrated.newsfeed.data.QueryPreferences;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedBaseHelper;
import ke.co.dataintegrated.newsfeed.data.databases.NewsFeedDbActions;

public class DashboardFragment extends Fragment {
    // API
    private static final String ACCESS_KEY = "4c499b744ed24f6180595ff80e1585cd";
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String ENDPOINT = "top-headlines";

    private static final String TAG = "message";

    private String username;
    private String strBuild;

    private List<String> sourceNames;
    private List<String> titles;
    private List<String> descriptions;
    private List<String> urlList;

    private RecyclerView recyclerView;

    private ArticleAdapter articleAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        super.onCreate(savedInstanceState);

        sourceNames = new ArrayList<>();
        titles = new ArrayList<>();
        descriptions = new ArrayList<>();
        urlList = new ArrayList<>();

//        String userId = QueryPreferences.getStoredUserUuidQuery(getActivity());
//        Log.d(TAG, userId);
//
//        NewsFeedDbActions newsFeedDbActions = new NewsFeedDbActions(getActivity());
//        NewsFeedBaseHelper newsFeedBaseHelper = newsFeedDbActions.getNewsFeedBaseHelper();
//
//        username = newsFeedDbActions.getUsername(userId, newsFeedBaseHelper);
//
//        Log.d(TAG, username);

        try {
            strBuild = getJson(BASE_URL + ENDPOINT + "?country=us&category=technology&apiKey=" + ACCESS_KEY);

            JSONObject jsonObject;
            jsonObject = new JSONObject(strBuild);

            String status = jsonObject.getString("status");
            Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();

            if (status.equals("ok")) {
                List<JSONArray> jsonArray = new ArrayList<>();

                for (int i = 0; i < 20; i++) {
                    jsonArray.add(jsonObject.getJSONArray("articles"));
                }

                String strTitle = jsonArray.get(0).getJSONObject(0).getJSONObject("source").getString("name");
                Toast.makeText(getActivity(), strTitle, Toast.LENGTH_LONG).show();

                for (int i = 0; i < 20; i++) {
                    sourceNames.add(jsonArray.get(i).getJSONObject(i).getJSONObject("source").getString("name"));
                    titles.add(jsonArray.get(i).getJSONObject(i).getString("title"));
                    descriptions.add(jsonArray.get(i).getJSONObject(i).getString("description"));
                    urlList.add(jsonArray.get(i).getJSONObject(i).getString("url"));
                }
            } else {
                Toast.makeText(getActivity(), "Status is not okay", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        Log.d(TAG, "created the Dashboard Activity.");
//
//        if (username == null) {
//            Toast.makeText(getActivity(), "username has no value", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getActivity(), "username: " + username, Toast.LENGTH_SHORT).show();
//        }
//
//        TextView txtUsername = view.findViewById(R.id.textView_username);
//        txtUsername.setText(username);

        recyclerView = view.findViewById(R.id.articles_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    public void updateUI() {
        articleAdapter = new ArticleAdapter(urlList, sourceNames, titles, descriptions);
        recyclerView.setAdapter(articleAdapter);
    }

    private class ArticleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtSource;
        private TextView txtTitle;
        private TextView txtDescription;
        private String url;

        public ArticleHolder(View itemView) {
            super(itemView);

            txtSource = itemView.findViewById(R.id.textView_source_name);
            txtTitle = itemView.findViewById(R.id.textView_title);
            txtDescription = itemView.findViewById(R.id.textView_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    private class ArticleAdapter extends RecyclerView.Adapter<ArticleHolder> {
        private List<String> urlString;
        private List<String> sourceNames;
        private List<String> titles;
        private List<String> descriptions;

        public ArticleAdapter(List<String> url, List<String> sourceNames, List<String> titles, List<String> descriptions) {
            this.urlString = url;
            this.sourceNames = sourceNames;
            this.titles = titles;
            this.descriptions = descriptions;
        }

        @Override
        public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_articles, parent, false);

            return new ArticleHolder(view);
        }

        @Override
        public void onBindViewHolder(ArticleHolder holder, int position) {
            try {
                holder.txtSource.setText(sourceNames.get(position));
                holder.txtTitle.setText(titles.get(position));
                holder.txtDescription.setText(descriptions.get(position));
                holder.setUrl(urlString.get(position));
            } catch (NullPointerException e) {
                Toast.makeText(getActivity(), "You lists are empty", Toast.LENGTH_SHORT).show();
                e.getMessage();
            }
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    public String getJson(String urlString) {

        URL url;
        HttpURLConnection urlConnection;
        String build = "";

        try {
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            BufferedReader br = new BufferedReader(isw);

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
            br.close();

            build = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return build;
    }
}
