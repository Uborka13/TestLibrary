package ubi.soft.testlibraries.ui.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ubi.soft.testlibraries.R;
import ubi.soft.testlibraries.ui.fragments.LoginFragment;
import ubi.soft.testlibraries.ui.fragments.RegistrationFragment;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener, RegistrationFragment.OnFragmentInteractionListener {


    @BindView(R.id.frame_login_fragment_container)
    FrameLayout frameLayoutFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_login_fragment_container, LoginFragment.newInstance()).commitAllowingStateLoss();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // Still empty
    }
}
