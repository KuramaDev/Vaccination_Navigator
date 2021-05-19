package com.example.vacinationnavigator.Services;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.vacinationnavigator.Model.UserInfo;
import com.example.vacinationnavigator.Ui.Login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class FirebaseService {
    private final FirebaseAuth mAuth ;
    private FirebaseUser currentUser;
    private boolean flag  ;
    FireBaseReceiver receiver ;

    private DatabaseReference dbReference;
    private DatabaseReference tbUserInfoEndPoint;
    private DatabaseReference tbVacCentersEndpoint;

    public FirebaseService(){

        mAuth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference();
        tbUserInfoEndPoint = dbReference.child("UserInfo/");
        tbVacCentersEndpoint = dbReference.child("Centers/");
    }

    public void AttachReceiver(FireBaseReceiver listener){
        this.receiver = listener;
    }

    public boolean IsUserSignedIn(){
        currentUser = mAuth.getCurrentUser();
        return currentUser != null;
    }

    public void CustomLogin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            currentUser = mAuth.getCurrentUser();
                            Log.d(TAG, "Welcome back" + currentUser.getEmail());
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "Sign in failed", task.getException());
                        }
                    }
                });
    }

    public FirebaseUser getCurrentUser(){
        return currentUser ;
    }

    public void Logout(){
        mAuth.signOut();
        currentUser = null;
    }

    public void CreateUser(String email, String password){

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    currentUser = mAuth.getCurrentUser();
                    receiver.onRegisterComplete(currentUser.getUid(),"Registration Comfirmed");
                }
                else {
                    // If sign in fails, display a message to the user.
                    Log.e(TAG, "createUserWithEmail:failure", task.getException());
                    receiver.onRegisterComplete(null,task.getException().getLocalizedMessage());
                }
            }
        });


    }

    public void AddUserinfo(UserInfo info){
        tbUserInfoEndPoint.child(currentUser.getUid()).setValue(info);
    }


}


