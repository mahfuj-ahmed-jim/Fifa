package com.ai.fifa.LogInSignIn;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    // sign in
    private TextView signInButton; // using textView as a button
    private Fragment selectedFragment = null;
    private static String FRAGMENT_TAG;
    private Confirmationragment confirmationragment = new Confirmationragment();

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

        // textView as button
        signInButton = view.findViewById(R.id.textViewId_signIn);

        // on click listener
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set default fragment (welcome fragment)
                selectedFragment = confirmationragment;
                FRAGMENT_TAG = String.valueOf(R.string.confirmation);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutId, selectedFragment, FRAGMENT_TAG)
                        .addToBackStack(null)
                        .commit();

            }
        });

        return view;
    }
}