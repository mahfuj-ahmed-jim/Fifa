package com.ai.fifa.Authentication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ai.fifa.Activities.IOnBackPressed;
import com.ai.fifa.Database.User.User;
import com.ai.fifa.R;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

public class Confirmationragment extends Fragment implements IOnBackPressed {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // user
    private User user = new User();

    // shared preference
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    // firebase
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack; // send otp
    private FirebaseAuth firebaseAuth;
    private String verifyCode; // will hold otp to verify// Firebase
    private DatabaseReference databaseReference;

    // header
    private TextView titleTextView, textView;

    // button
    private Button backButton, nextButton;

    // edit text
    private LinearLayout nameEditTextLayout;
    private ConstraintLayout numberEditTextLayout, passwordEditTextLayout;
    private EditText firstNameEditText, lastNameEditText, numberEditText, passwordEditText;

    // pinView
    private PinView pinView;

    // cross button for edit text
    private LinearLayout firstNameCrossButtonLayout, lastNameCrossButtonLayout, crossButtonLayout;
    private Button firstNameCrossButton, lastNameCrossButton, crossButton;

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Confirmationragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Confirmationragment newInstance(String param1, String param2) {
        Confirmationragment fragment = new Confirmationragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirmationragment, container, false);

        // firebase
        firebaseAuth = FirebaseAuth.getInstance(); // initialize firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        //init shared Preference
        preferences = getActivity().getSharedPreferences(String.valueOf(R.string.userInformation), MODE_PRIVATE);

        // header
        titleTextView = view.findViewById(R.id.textViewId_title);
        textView = view.findViewById(R.id.textViewId_text);

        // buttons
        backButton = view.findViewById(R.id.buttonId_back);
        nextButton = view.findViewById(R.id.buttonId_next);

        // edit text
        nameEditTextLayout = view.findViewById(R.id.linearLayoutId_name);
        numberEditTextLayout = view.findViewById(R.id.constraintLayoutId_textField);
        passwordEditTextLayout = view.findViewById(R.id.constraintLayoutId_textField3);
        firstNameEditText = view.findViewById(R.id.editTextId_firstName);
        lastNameEditText = view.findViewById(R.id.editTextId_lastName);
        numberEditText = view.findViewById(R.id.editTextId_phoneNumber);
        passwordEditText = view.findViewById(R.id.editTextId_password);

        // pinView
        pinView = view.findViewById(R.id.pinViewId);

        // cross button for edit text
        firstNameCrossButtonLayout = view.findViewById(R.id.layoutId_firstNameCrossButton);
        lastNameCrossButtonLayout = view.findViewById(R.id.layoutId_lastNameCrossButton);
        crossButtonLayout = view.findViewById(R.id.layoutId_numberCrossButton);
        firstNameCrossButton = view.findViewById(R.id.buttonId_firstNameCrossButton);
        lastNameCrossButton = view.findViewById(R.id.buttonId_lastNameCrossButton);
        crossButton = view.findViewById(R.id.buttonId_numberCrossButton);

        mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                // instant verification
                // no need to send the code
                Log.d("Verify", "Verified");
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.d("Verify", e.getMessage());

                //We have blocked all requests from this device due to unusual activity. Try again later.

            }

            @Override
            public void onCodeSent(@NonNull String verifyCode, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(verifyCode, forceResendingToken);

                setVerifyCode(verifyCode);
                changeButtonForOtp();

            }
        };

        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // hide the cross button when edit text is empty
                // easy to erase the text for the user
                if(firstNameEditText.getText().toString().trim().isEmpty()){
                    firstNameCrossButtonLayout.setVisibility(View.GONE);
                }else{
                    firstNameCrossButtonLayout.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // hide the cross button when edit text is empty
                // easy to erase the text for the user
                if(lastNameEditText.getText().toString().trim().isEmpty()){
                    lastNameCrossButtonLayout.setVisibility(View.GONE);
                }else{
                    lastNameCrossButtonLayout.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        numberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // hide the cross button when edit text is empty
                // easy to erase the text for the user
                if(numberEditText.getText().toString().trim().isEmpty()){
                    crossButtonLayout.setVisibility(View.GONE);
                }else{
                    crossButtonLayout.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // on click listeners
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nextButton.getText().equals("Enter your name")){

                    if(firstNameEditText.getText().toString().trim().isEmpty()){

                        firstNameEditText.setError("First Name");

                    }else if(lastNameEditText.getText().toString().trim().isEmpty()){

                        lastNameEditText.setError("Last Name");

                    }else{

                        user.setFirstName(firstNameEditText.getText().toString().trim());
                        user.setLastName(lastNameEditText.getText().toString().trim());

                        changeButtonForNumber();

                    }

                }else if(nextButton.getText().equals("Send Code")){

                    if(numberEditText.getText().toString().trim().isEmpty()){
                        numberEditText.setError("Fill up the number");
                    }else{

                        String number = numberEditText.getText().toString().trim();

                        //manipulate values
                        if(number.contains("+880")){
                            number = number.substring(4);
                        }
                        if(number.charAt(0)=='0' && number.length() == 11){
                            number = number.substring(1);
                        }
                        //end

                        number = "+880"+number;

                        Log.d("Verify", number);

                        try{
                            startPhoneNumberVerification(number);
                        }catch (Exception e){
                            Log.d("Verify", e.getMessage());
                        }

                    }

                }else if(nextButton.getText().equals("Next")){

                    try{
                        verifyWithCode(verifyCode, pinView.getText().toString());
                    }catch (Exception e){
                        Log.d("Verify", e.getMessage());
                    }

                }else{

                    if(passwordEditText.getText().toString().length() < 6){

                        passwordEditText.setError("at least 6 character");

                    }else{

                        user.setPassword(passwordEditText.getText().toString());
                        saveToDatabase();

                    }

                }

            }
        });

        // cross button
        firstNameCrossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameEditText.setText("");
            }
        });

        firstNameCrossButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameEditText.setText("");
            }
        });

        lastNameCrossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastNameEditText.setText("");
            }
        });

        lastNameCrossButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastNameEditText.setText("");
            }
        });

        crossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberEditText.setText("");
            }
        });

        crossButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberEditText.setText("");
            }
        });

        return view;
    }

    private void changeButtonForName() {

        nameEditTextLayout.setVisibility(View.VISIBLE);
        numberEditTextLayout.setVisibility(View.GONE);
        pinView.setVisibility(View.GONE);
        passwordEditTextLayout.setVisibility(View.GONE);

        titleTextView.setText("What's your name?");
        textView.setText("Enter the name you use in real life");
        nextButton.setText("Enter your name");

    }

    private void changeButtonForNumber() {

        nameEditTextLayout.setVisibility(View.GONE);
        numberEditTextLayout.setVisibility(View.VISIBLE);
        pinView.setVisibility(View.GONE);
        passwordEditTextLayout.setVisibility(View.GONE);

        titleTextView.setText("Enter your mobile number");
        textView.setText("Enter the mobile number where you can be reached. You can hide this from your profile later");
        nextButton.setText("Send Code");

    }

    private void changeButtonForOtp() {

        nameEditTextLayout.setVisibility(View.GONE);
        numberEditTextLayout.setVisibility(View.GONE);
        pinView.setVisibility(View.VISIBLE);
        passwordEditTextLayout.setVisibility(View.GONE);

        titleTextView.setText("Choose a password");
        textView.setText("Create a password with at least 6 characters. It should be something others couldn't guess");
        nextButton.setText("Next");

    }

    private void changeButtonForPassword() {

        nameEditTextLayout.setVisibility(View.GONE);
        numberEditTextLayout.setVisibility(View.GONE);
        pinView.setVisibility(View.GONE);
        passwordEditTextLayout.setVisibility(View.VISIBLE);

        nextButton.setText("Set Password");

    }

    private void saveToDatabase(){

        user.setUserId(firebaseAuth.getCurrentUser().getUid()); // get firebase unique id

        // add data to shared preference to start the apps from the main activity next time
        preferences = getActivity().getSharedPreferences(String.valueOf(R.string.userData), Context.MODE_PRIVATE); // initialize the shared preference
        editor = preferences.edit();

        editor.putString(String.valueOf(R.string.userId), user.getUserId()); // write first name
        editor.putString(String.valueOf(R.string.firstName), user.getFirstName()); // write first name
        editor.putString(String.valueOf(R.string.lastName), user.getLastName()); // write last name
        editor.putString(String.valueOf(R.string.phoneNumber), user.getPhoneNumber()); // write last name
        editor.putString(String.valueOf(R.string.password), user.getPassword()); // write password

        editor.commit(); // write to shared preference

        databaseReference.child(user.getUserId()).setValue(user); // set the object info in real time database

    }

    private void startPhoneNumberVerification(String number) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(number) // set phone number
                .setTimeout(60L, TimeUnit.SECONDS) // set timer for submit the code
                .setActivity(getActivity())
                .setCallbacks(mCallBack) // call back action
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void verifyWithCode(String verifyCode, String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyCode, code);
        try{
            signInWithPhoneAuthCredential(credential);
        }catch (Exception e){
            Log.d("Verify", e.getMessage().toString());
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        // if the code works successfully
        firebaseAuth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onSuccess(AuthResult authResult) {
                user.setPhoneNumber("+880"+numberEditText.getText().toString().trim());
                changeButtonForPassword();
            }
        });
        // if the code doesn't works
        firebaseAuth.signInWithCredential(credential).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Verify", e.getMessage());
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        if (nextButton.getText().equals("Send Code")) {

            //action not popBackStack
            changeButtonForName();
            return true;

        } else if(nextButton.getText().equals("Next")){

            //action not popBackStack
            changeButtonForNumber();
            return true;

        }else if(nextButton.getText().equals("Set Password")){

            //action not popBackStack
            changeButtonForNumber();
            return true;

        }else {

            return false;

        }
    }

}