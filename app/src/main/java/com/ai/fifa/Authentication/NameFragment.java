package com.ai.fifa.Authentication;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ai.fifa.Constant.ConstantUser;
import com.ai.fifa.R;

public class NameFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // editText
    private EditText firstNameEditText, lastNameEditText;

    // cross button for editText
    private Button firstNameCrossButton, lastNameCrossButton;
    private ConstraintLayout firstNameCrossButtonLayout, lastNameCrossButtonLayout;

    // button
    private Button nextButton;

    // for next page
    private Fragment selectedFragment = null;
    private static String FRAGMENT_TAG;
    private PhoneNumberFragment phoneNumberFragment = new PhoneNumberFragment();

    // for constant
    private ConstantUser constantUser;

    public NameFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NameFragment newInstance(String param1, String param2) {
        NameFragment fragment = new NameFragment();
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
        View view = inflater.inflate(R.layout.fragment_name, container, false);

        /* initialize widgets start */
        // editText
        firstNameEditText = view.findViewById(R.id.editTextId_firstName);
        lastNameEditText = view.findViewById(R.id.editTextId_lastName);

        // cross button for edit text
        firstNameCrossButton = view.findViewById(R.id.buttonId_firstNameCrossButton);
        lastNameCrossButton = view.findViewById(R.id.buttonId_lastnameCrossButton);
        firstNameCrossButtonLayout = view.findViewById(R.id.layoutId_firstNameCrossButton); // layout
        lastNameCrossButtonLayout = view.findViewById(R.id.layoutId_lastNameCrossButton); // layout

        // button
        nextButton = view.findViewById(R.id.buttonId_next);
        /* initialize widgets edn */

        /* editTexts on change listener start */
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
                    firstNameEditText.setBackgroundDrawable(ContextCompat.getDrawable(
                            getActivity().getApplicationContext(), R.drawable.background_textfield));
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
                    lastNameEditText.setBackgroundDrawable(ContextCompat.getDrawable(
                            getActivity().getApplicationContext(), R.drawable.background_textfield));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        /* editTexts on change listener end */

        /* on click listeners stat */
        // cross buttons
        firstNameCrossButton.setOnClickListener(new View.OnClickListener() {
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

        // next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstNameEditText.getText().toString().trim().isEmpty()){

                    firstNameEditText.setError("First Name");
                    firstNameEditText.setBackgroundDrawable(ContextCompat.getDrawable(
                            getActivity().getApplicationContext(), R.drawable.background_textfield_red));

                }else if(lastNameEditText.getText().toString().trim().isEmpty()){

                    lastNameEditText.setError("Last Name");
                    lastNameEditText.setBackgroundDrawable(ContextCompat.getDrawable(
                            getActivity().getApplicationContext(), R.drawable.background_textfield_red));

                }else{

                    // save value for the next page
                    constantUser.setFirstName(firstNameEditText.getText().toString().trim());
                    constantUser.setLastName(lastNameEditText.getText().toString().trim());

                    // for next page
                    selectedFragment = phoneNumberFragment;
                    FRAGMENT_TAG = String.valueOf(R.string.PhoneNumberFragment);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameLayoutId, selectedFragment, FRAGMENT_TAG)
                            .addToBackStack(null)
                            .commit();

                }
            }
        });
        /* on click listeners end */

        return view;
    }
}