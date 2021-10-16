package com.ai.fifa.Database.SharedPreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.ai.fifa.Database.User.User;
import com.ai.fifa.R;

import static android.content.Context.MODE_PRIVATE;

public class UserSharePreference {

    private Context context;
    private User user = new User();

    // shared preference
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public UserSharePreference(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(String.valueOf(R.string.userInformation), MODE_PRIVATE); //initialize shared Preference
    }

    public void setData(User user){

        this.user = user;
        editor = preferences.edit();

        editor.putString(String.valueOf(R.string.userId), user.getUserId()); // write first name
        editor.putString(String.valueOf(R.string.firstName), user.getFirstName()); // write first name
        editor.putString(String.valueOf(R.string.lastName), user.getLastName()); // write last name
        editor.putString(String.valueOf(R.string.phoneNumber), user.getPhoneNumber()); // write last name
        editor.putString(String.valueOf(R.string.password), user.getPassword()); // write password

        editor.commit(); // write to shared preference

    }

    public User getData(){

        // start
        if(preferences.contains(String.valueOf(R.string.firstName))){ // read from shared preference

            String firstName = preferences.getString(String.valueOf(R.string.firstName), null);
            String lastName = preferences.getString(String.valueOf(R.string.lastName), null);
            String userId = preferences.getString(String.valueOf(R.string.userId), null);
            String phoneNumber = preferences.getString(String.valueOf(R.string.phoneNumber), null);
            String password = preferences.getString(String.valueOf(R.string.password), null);

            // start
            if(userId != null){
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setUserId(userId);
                user.setPhoneNumber(phoneNumber);
                user.setPassword(password);
            }else{
                user.setUserId(null);
            }
            // end

        }else{

            user.setUserId(null);

        }
        // end

        return this.user;

    }

}
