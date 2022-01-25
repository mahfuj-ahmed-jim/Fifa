package com.ai.fifa.Database.Firebase;

import com.ai.fifa.Database.User.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserFirebase {

    // firebase
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public UserFirebase() {
        firebaseAuth = FirebaseAuth.getInstance(); // initialize firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void setData(User user){
        databaseReference.child(user.getUserId()).setValue(user); // set the object info in real time database
    }

}
