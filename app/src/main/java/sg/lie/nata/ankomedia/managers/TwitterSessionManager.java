package sg.lie.nata.ankomedia.managers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

public class TwitterSessionManager {
    private SharedPreferencesManager mSharedPreferencesManager;

    public TwitterSessionManager(SharedPreferencesManager sharedPreferencesManager) {
        mSharedPreferencesManager = sharedPreferencesManager;
    }

    @NonNull
    public Callback<TwitterSession> getTwitterLoginCallback() {
        return new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                signInSuccessful(result);
            }

            @Override
            public void failure(TwitterException exception) {
                signInFailure(exception);
            }
        };
    }

    void signInSuccessful(Result<TwitterSession> result) {
        TwitterSession session = result.data;

        // Successfully logged in
        // Save the auth token into shared prefs
        String token = session.getAuthToken().token;
        mSharedPreferencesManager.saveAuthToken(token);
    }

    void signInFailure(TwitterException exception) {
        Log.d("TwitterKit", "Login with Twitter failure", exception);
    }
}
