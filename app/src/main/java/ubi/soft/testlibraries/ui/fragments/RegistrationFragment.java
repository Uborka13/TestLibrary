package ubi.soft.testlibraries.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.SyncCredentials;
import ubi.soft.testlibraries.R;
import ubi.soft.testlibraries.users.User;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {


    public static final int MINIMUM_AGE = 18;

    @BindView(R.id.button_registration)
    public Button buttonRegistration;
    @BindView(R.id.check_box_registration_age_validation)
    public CheckBox checkBoxRegistrationAgeValidation;
    @BindView(R.id.edit_text_registration_username)
    public EditText registrationUsername;
    @BindView(R.id.edit_text_registration_email_first)
    public EditText registrationEmailFirst;
    @BindView(R.id.edit_text_registration_email_second)
    public EditText registrationEmailSecond;
    @BindView(R.id.edit_text_registration_password_first)
    public EditText registrationPasswordFirst;
    @BindView(R.id.edit_text_registration_password_second)
    public EditText registrationPasswordSecond;

    private Calendar userBirthDate;


    private OnFragmentInteractionListener mListener;


    public RegistrationFragment() {
        // Required empty public constructor
    }

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
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
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @OnClick(R.id.check_box_registration_age_validation)
    public void datePickerSelected() {
        if (checkBoxRegistrationAgeValidation.isChecked()) {
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar cal = Calendar.getInstance();
                            cal.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                            userBirthDate = cal;
                            ageValidation(cal);
                        }
                    }
            );
            dpd.dismissOnPause(true);
            dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
        }
    }

    private void ageValidation(Calendar birthDate) {
        Calendar nowDate = Calendar.getInstance();
        int userAge = nowDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        birthDate.add(Calendar.YEAR, userAge);
        if (userAge == MINIMUM_AGE) {
            if (nowDate.before(birthDate)) {
                checkBoxRegistrationAgeValidation.setChecked(false);
                userBirthDate = null;
            }
        } else if (userAge < MINIMUM_AGE) {
            checkBoxRegistrationAgeValidation.setChecked(false);
            userBirthDate = null;
        }
    }

    @OnClick(R.id.button_registration)
    public void registrationButtonPressed() {
        mListener.userAttemptToRegister(
                SyncCredentials.usernamePassword(
                        registrationUsername.getText().toString(),
                        registrationPasswordFirst.getText().toString(),
                        true),
                new User(registrationEmailFirst.getText().toString(),
                        userBirthDate.getTime()));
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void userAttemptToRegister(SyncCredentials credentials, User user);
    }
}
