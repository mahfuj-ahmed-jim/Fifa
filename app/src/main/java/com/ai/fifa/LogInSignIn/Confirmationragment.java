package com.ai.fifa.LogInSignIn;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ai.fifa.R;

public class Confirmationragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // button
    private Button backButton;

    // edit text
    private EditText numberEditText;

    // cross button for edit text
    private LinearLayout crossButtonLayout;
    private Button crossButton;

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

        // buttons
        backButton = view.findViewById(R.id.buttonId_back);

        // edit text
        numberEditText = view.findViewById(R.id.editTextId_phoneNumber);

        // cross button for edit text
        crossButtonLayout = view.findViewById(R.id.layoutId_numberCrossButton);
        crossButton = view.findViewById(R.id.buttonId_numberCrossButton);

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

        // cross button
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
}