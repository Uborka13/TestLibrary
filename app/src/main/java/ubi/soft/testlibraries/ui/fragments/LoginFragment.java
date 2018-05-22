package ubi.soft.testlibraries.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.SyncCredentials;
import ubi.soft.testlibraries.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    @BindView(R.id.input_layout_login_username)
    TextInputLayout inputLayoutUsername;

    @BindView(R.id.input_layout_login_password)
    TextInputLayout inputLayoutPassword;

    @BindView(R.id.edit_text_login_username)
    EditText editTextUsername;

    @BindView(R.id.edit_text_login_password)
    EditText editTextPassword;

    @BindView(R.id.button_login)
    Button buttonLogin;
    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CardContentFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.button_login)
    public void attemptToLogin() {
        if (editTextUsername.getText() != null
                && editTextUsername.getText().length() > 0
                && editTextPassword.getText() != null
                && editTextPassword.getText().length() > 0) {
            SyncCredentials credentials = SyncCredentials.usernamePassword(editTextUsername.getText().toString(), editTextPassword.getText().toString());
            mListener.userAttemptToLogin(credentials);
        } else {
            Snackbar.make(getView(), "Empty username or password", Snackbar.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.button_registration)
    public void startRegistrationFragment() {
        getFragmentManager().beginTransaction().replace(R.id.frame_login_fragment_container, RegistrationFragment.newInstance()).addToBackStack(null).commitAllowingStateLoss();
    }

    public interface OnFragmentInteractionListener {
        void userAttemptToLogin(SyncCredentials credentials);
    }
}
