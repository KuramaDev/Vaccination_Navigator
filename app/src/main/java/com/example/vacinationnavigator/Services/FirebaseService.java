package com.example.vacinationnavigator.Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.vacinationnavigator.Model.Center;
import com.example.vacinationnavigator.Model.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FirebaseService {
    private final FirebaseAuth mAuth ;
    private FirebaseUser currentUser;
    private boolean flag  ;
    AuthRepsonse receiver ;

    rtDBResponse rtDBResponse;

    private DatabaseReference dbReference;
    private DatabaseReference tbUserInfoEndPoint;
    private DatabaseReference tbVacCentersEndpoint;

    List<Center> center = new ArrayList<>();

    public FirebaseService(){

        mAuth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference();
        tbUserInfoEndPoint = dbReference.child("UserInfo/");
        tbVacCentersEndpoint = dbReference.child("Centers/");
    }

    public void AttachAuthReceiver(AuthRepsonse listener){
        this.receiver = listener;
    }
    public void AttachRtDBReceiver(BaseService listener) {this.rtDBResponse =(rtDBResponse) listener;}

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

    public void getAllCenters(){

        tbVacCentersEndpoint.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                for(DataSnapshot centersSnapshot : snapshot.getChildren()){
                    center.add (centersSnapshot.getValue(Center.class));
                }

                rtDBResponse.CentersFetched(center);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                rtDBResponse.onError(error.getMessage());
            }
        });
    }




}


