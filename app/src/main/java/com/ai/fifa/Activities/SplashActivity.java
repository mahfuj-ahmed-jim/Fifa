package com.ai.fifa.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ai.fifa.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(getApplicationContext(), PageActivity.class);
        intent.putExtra(getApplicationContext().getString(R.string.activity), getApplicationContext().getString(R.string.LogInFragment));
        startActivity(intent);

    }
}