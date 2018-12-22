package com.example.realmdb.User;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;


public class usersname {
    private FirebaseAuth auth;
    FirebaseAuth.AuthStateListener mAuthListener;
    private Person myperson;
    String email,uid;

    public usersname(){
        mAuthListener= new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user !=null){
                    email=user.getEmail();
                    uid=user.getUid();


                }

            }
        };

    }


    public usersname(String email,String uid) {
        this.email=email;
        this.uid=uid;


    }

    public Person getMyperson(){
        return  myperson;
    }

    public void setMyperson(Person myperson){
        this.myperson=myperson;
    }
}

