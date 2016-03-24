package sg.lie.nata.ankomedia.managers;

import android.content.Context;
import android.content.SharedPreferences;

import sg.lie.nata.ankomedia.R;

public class SharedPreferencesManager {
    private Context mContext;
    SharedPreferences mSharedPreferences;

    public SharedPreferencesManager(Context context, SharedPreferences sharedPreferences) {
        mContext = context;
        mSharedPreferences = sharedPreferences;
    }

    void saveAuthToken(String authToken) {
        mSharedPreferences.edit().putString(mContext.getString(R.string.auth_token), authToken);
    }
}
