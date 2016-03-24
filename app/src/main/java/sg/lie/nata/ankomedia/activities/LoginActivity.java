package sg.lie.nata.ankomedia.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;
import sg.lie.nata.ankomedia.BuildConfig;
import sg.lie.nata.ankomedia.R;
import sg.lie.nata.ankomedia.managers.SharedPreferencesManager;
import sg.lie.nata.ankomedia.managers.TwitterSessionManager;

public class LoginActivity extends AppCompatActivity {
    private TwitterLoginButton loginButton;
    TwitterSessionManager twitterSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupTwitter();
        SharedPreferencesManager sharedPreferencesManager =
                new SharedPreferencesManager(
                        this,
                        PreferenceManager.getDefaultSharedPreferences(this));
        twitterSessionManager = new TwitterSessionManager(sharedPreferencesManager);

        initialiseTwitterButton();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void setupTwitter() {
        String twitterKey = BuildConfig.twitterKey;
        String twitterSecret = BuildConfig.twitterSecret;

        TwitterAuthConfig authConfig = new TwitterAuthConfig(twitterKey, twitterSecret);
        Fabric.with(this, new Twitter(authConfig));
    }

    private void initialiseTwitterButton() {
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(twitterSessionManager.getTwitterLoginCallback());
    }
}
