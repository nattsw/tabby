package sg.lie.nata.ankomedia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;
import sg.lie.nata.ankomedia.BuildConfig;
import sg.lie.nata.ankomedia.R;
import sg.lie.nata.ankomedia.managers.TwitterSessionManager;

public class LoginActivity extends AppCompatActivity {
    private TwitterLoginButton loginButton;
    TwitterSessionManager twitterSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupTwitter();
        twitterSessionManager = new TwitterSessionManager(this);

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
        loginButton.setCallback(twitterSessionManager.getTwitterLoginCallback());
    }
}
