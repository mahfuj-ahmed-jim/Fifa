package com.ai.fifa.Authentication;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ai.fifa.R;

public class LogInFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // button
    private Button nextButton;

    // edit text
    private EditText numberEditText;

    // cross button for edit text
    private ConstraintLayout crossButtonLayout;
    private Button crossButton;

    // sign in
    private Button signInButton; // using textView as a button
    private Fragment selectedFragment = null;
    private static String FRAGMENT_TAG;
    private NameFragment nameFragment = new NameFragment();

    public LogInFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LogInFragment newInstance(String param1, String param2) {
        LogInFragment fragment = new LogInFragment();
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
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        // button
        nextButton = view.findViewById(R.id.buttonId_next);

        // edit text
        numberEditText = view.findViewById(R.id.editTextId_phone);

        // cross button for edit text
        crossButtonLayout = view.findViewById(R.id.layoutId_numberCrossButton);
        crossButton = view.findViewById(R.id.buttonId_numberCrossButton);

        // textView as button
        signInButton = view.findViewById(R.id.buttonId_signIn);

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

        // on click listener
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // for next page
                selectedFragment = nameFragment;
                FRAGMENT_TAG = String.valueOf(R.string.NameFragment);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutId, selectedFragment, FRAGMENT_TAG)
                        .addToBackStack(null)
                        .commit();

            }
        });

        // cross button
        crossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberEditText.setText("");
            }
        });

        return view;
    }
}