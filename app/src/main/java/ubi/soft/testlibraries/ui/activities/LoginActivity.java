package ubi.soft.testlibraries.ui.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.ObjectServerError;
import io.realm.PermissionManager;
import io.realm.SyncCredentials;
import io.realm.SyncUser;
import io.realm.permissions.AccessLevel;
import io.realm.permissions.PermissionRequest;
import io.realm.permissions.UserCondition;
import ubi.soft.testlibraries.R;
import ubi.soft.testlibraries.ui.fragments.LoginFragment;
import ubi.soft.testlibraries.ui.fragments.RegistrationFragment;
import ubi.soft.testlibraries.users.User;

import static ubi.soft.testlibraries.BuildConfig.BASE_URL;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener, RegistrationFragment.OnFragmentInteractionListener {


    @BindView(R.id.login_progress)
    ProgressBar loginProgress;

    @BindView(R.id.login_form)
    ScrollView loginForm;

    @BindView(R.id.frame_login_fragment_container)
    FrameLayout frameLayoutFragmentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (SyncUser.current() != null) {
            this.goToMenuActivity();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_login_fragment_container, LoginFragment.newInstance()).commitAllowingStateLoss();
    }

    @Override
    public void userAttemptToLogin(final SyncCredentials credentials) {
        showProgress(true);
        SyncUser.logInAsync(credentials, BASE_URL + "/auth", new SyncUser.Callback<SyncUser>() {
            @Override
            public void onSuccess(SyncUser result) {
                showProgress(false);
                goToMenuActivity();
//                checkUserRealmPermissions(result);
            }

            @Override
            public void onError(ObjectServerError error) {
                showProgress(false);
                Log.d("LOGIN_ACTIVITY", "Nem sikerült Message: " + error.getErrorMessage());
                Log.d("LOGIN_ACTIVITY", "Nem sikerült Category: " + error.getCategory());
                Log.d("LOGIN_ACTIVITY", "Nem sikerült ErrorCode: " + error.getErrorCode());
                Log.d("LOGIN_ACTIVITY", "Nem sikerült Exception: " + error.getException());
            }
        });
    }


    private void checkUserRealmPermissions(SyncUser user) {
        PermissionManager pm = user.getPermissionManager();

        // Create request
        UserCondition condition = UserCondition.noExistingPermissions();
        AccessLevel accessLevel = AccessLevel.READ;
        PermissionRequest request = new PermissionRequest(condition, BASE_URL + "/items", accessLevel);

        pm.applyPermissions(request, new PermissionManager.ApplyPermissionsCallback() {
            @Override
            public void onSuccess() {
                goToMenuActivity();
            }

            @Override
            public void onError(ObjectServerError error) {
                // Something went wrong
            }
        });
    }

    @Override
    public void userAttemptToRegister(final SyncCredentials credentials, final User user) {
        showProgress(true);
        SyncUser.logInAsync(credentials, BASE_URL + "/items", new SyncUser.Callback<SyncUser>() {
            @Override
            public void onSuccess(SyncUser result) {
                showProgress(false);
                getSupportFragmentManager().popBackStack();
            }

            @Override
            public void onError(ObjectServerError error) {
                showProgress(false);
                Log.d("LOGIN_ACTIVITY", "Nem sikerült Message: " + error.getErrorMessage());
                Log.d("LOGIN_ACTIVITY", "Nem sikerült Category: " + error.getCategory());
                Log.d("LOGIN_ACTIVITY", "Nem sikerült ErrorCode: " + error.getErrorCode());
                Log.d("LOGIN_ACTIVITY", "Nem sikerült Exception: " + error.getException());
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        loginForm.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });
        loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
        loginProgress.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void goToMenuActivity() {
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
    }

}
