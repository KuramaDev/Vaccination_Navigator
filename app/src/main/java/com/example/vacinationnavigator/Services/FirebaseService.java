package com.example.vacinationnavigator.Services;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.vacinationnavigator.Ui.Login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class FirebaseService {
    private FirebaseAuth mAuth ;
    private FirebaseUser currentUser;

    public FirebaseService(){
        mAuth = FirebaseAuth.getInstance();
    }

    public boolean IsUserSignedIn(){
        currentUser = mAuth.getCurrentUser();
        return currentUser != null;
    }

    public FirebaseUser CustomLogin(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            currentUser = mAuth.getCurrentUser();
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
        return currentUser ;
    }

    public FirebaseUser getCurrentUser(){
        return currentUser ;
    }
}


