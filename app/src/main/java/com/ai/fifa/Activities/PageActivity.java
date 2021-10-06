package com.ai.fifa.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.ai.fifa.LogInSignIn.LogInFragment;
import com.ai.fifa.R;

public class PageActivity extends AppCompatActivity {

    // fragment layout
    private Fragment selectedFragment = null;
    private static String FRAGMENT_TAG;

    private LogInFragment logInFragment = new LogInFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        // status bar color -> black
        try{
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(this.getResources().getColor(R.color.black));
            }
        }catch (Exception e){
        }

        String activity = getIntent().getStringExtra(getApplicationContext().getString(R.string.activity));

        if(activity.equals(getApplicationContext().getString(R.string.logIn))){

            //set fragment (log in fragment)
            selectedFragment = logInFragment;
            FRAGMENT_TAG = getApplicationContext().getString(R.string.logIn);

        }

        // shift to another fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId, selectedFragment, FRAGMENT_TAG).commit();

    }
}