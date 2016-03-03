package sg.lie.nata.ankomedia.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;
import sg.lie.nata.ankomedia.BuildConfig;
import sg.lie.nata.ankomedia.R;

public class LoginActivity extends AppCompatActivity {
    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupTwitter();

        initialiseTwitterButton();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setupTwitter() {
        String twitterKey = BuildConfig.twitterKey;
        String twitterSecret = BuildConfig.twitterSecret;

        TwitterAuthConfig authConfig = new TwitterAuthConfig(twitterKey, twitterSecret);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_login);
    }

    private void initialiseTwitterButton() {
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(getTwitterCallback());
    }

    @NonNull
    private Callback<TwitterSession> getTwitterCallback() {
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
        saveTokenInPrefs(token);
    }

    void saveTokenInPrefs(String token) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.auth_token), token);
        editor.apply();
    }

    void signInFailure(TwitterException exception) {
        Log.d("TwitterKit", "Login with Twitter failure", exception);
    }
}
