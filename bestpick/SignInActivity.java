package com.bestpickindia.android.bestpick;

/**
 * Created by HP on 6/20/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class SignInActivity extends Activity {

    public static String firstName;
    public static String lastName;
    public static String userLoginId;
    public static String userId;
    public static String profileImageUrl;
    SessionManager session;


    private CallbackManager mCallbackManager;
    public static final String PROFILE_USER_ID = "USER_ID";
    public static final String PROFILE_FIRST_NAME = "PROFILE_FIRST_NAME";
    public static final String PROFILE_LAST_NAME = "PROFILE_LAST_NAME";
    public static final String PROFILE_IMAGE_URL = "PROFILE_IMAGE_URL";
    //public static  final String PROFILE_EMAIL="PROFILE_EMAIL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_signin);
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton mLoginButton = (LoginButton)findViewById(R.id.login_button);
        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                userLoginId = loginResult.getAccessToken().getUserId();
                Intent facebookIntent = new Intent(SignInActivity.this, MainActivity.class);
                Profile mProfile = Profile.getCurrentProfile();
                firstName = mProfile.getFirstName();
                lastName = mProfile.getLastName();
                userId = mProfile.getId().toString();
                profileImageUrl = mProfile.getProfilePictureUri(96, 96).toString();
                session.createLoginSession(firstName,lastName,userId,profileImageUrl);
                facebookIntent.putExtra(PROFILE_USER_ID, userId);
                facebookIntent.putExtra(PROFILE_FIRST_NAME, firstName);
                facebookIntent.putExtra(PROFILE_LAST_NAME, lastName);
                facebookIntent.putExtra(PROFILE_IMAGE_URL, profileImageUrl);
                startActivity(facebookIntent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    /**
     * Create login session
     * */


}
