package sg.lie.nata.ankomedia.managers;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import sg.lie.nata.ankomedia.R;
import sg.lie.nata.ankomedia.TheApplication;

public class SharedPreferencesManager {
    private Context mContext;
    @Inject
    SharedPreferences mSharedPreferences;

    public SharedPreferencesManager(Context context) {
        TheApplication theApplication = (TheApplication) context.getApplicationContext();
        theApplication.component().inject(this);

        mContext = context;
    }

    void saveAuthToken(String authToken) {
        mSharedPreferences.edit().putString(mContext.getString(R.string.auth_token), authToken);
    }
}
