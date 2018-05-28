package ke.co.dataintegrated.newsfeed.data;

import android.content.Context;
import android.preference.PreferenceManager;

public class QueryPreferences {
    private static final String USER_UUID = "userUuid";
    private static final String LOGGEDTRUE = "logged";

    public static String getStoredUserUuidQuery(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(USER_UUID, null);
    }
    public static void setStoredUserUuidQuery(Context context, String query) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(USER_UUID, query)
                .apply();
    }
    public static boolean getStoredLoggedTrueQuery(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(LOGGEDTRUE, false);
    }
    public static void  setStoredLoggedTrueQuery(Context context, boolean trueValue) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(LOGGEDTRUE, trueValue)
                .apply();
    }
}
