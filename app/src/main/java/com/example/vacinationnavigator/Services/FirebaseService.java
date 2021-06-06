package com.example.vacinationnavigator.Services;

import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.vacinationnavigator.Model.Appointment;
import com.example.vacinationnavigator.Model.Center;
import com.example.vacinationnavigator.Model.Coordinates;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FirebaseService {
    private final FirebaseAuth mAuth ;
    private FirebaseUser currentUser;
    private boolean flag  ;

    AuthRepsonse receiver ;
    rtDBResponse rtDBResponse;
    rtDBAppoint rtDBAppoint ;
    LoginResponse loginResponse ;

    private DatabaseReference dbReference;
    private DatabaseReference tbUserInfoEndPoint;
    private DatabaseReference tbVacCentersEndpoint;
    private DatabaseReference tbVacAppointments;

    List<Center> center = new ArrayList<>();

    public FirebaseService(){

        mAuth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference();
        tbUserInfoEndPoint = dbReference.child("UserInfo/");
        tbVacCentersEndpoint = dbReference.child("Centers/");
        tbVacAppointments = dbReference.child("Appointments/");
    }

    public void AttachAuthReceiver(AuthRepsonse listener){
        this.receiver = listener;
    }
    public void AttachRtDBReceiver(BaseService listener) {this.rtDBResponse =(rtDBResponse) listener;}
    public void AttachRtDBAppointReceiver(BaseService listener) {this.rtDBAppoint =(rtDBAppoint) listener;}
    public void LoginListener(BaseService listener) {this.loginResponse =(LoginResponse) listener;}

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
                            loginResponse.onLoginSuccess(currentUser ,"Welcome back" + currentUser.getEmail());
                            Log.d(TAG, "Welcome back" + currentUser.getEmail());
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            loginResponse.onLoginError(task.getException().getLocalizedMessage());
                            Log.d(TAG, "Sign in failed", task.getException());
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

    public void getCenter(Location currentLoc){
         tbVacCentersEndpoint.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                 double minDist = Integer.MIN_VALUE;
                 Location loc =new Location("GPS_PROVIDER");
                 Center toReturn  = new Center();
                 Log.d("CURRENT LAT" ,String.valueOf(currentLoc.getLatitude()) );
                 Log.d("CURRENT LONG" ,String.valueOf(currentLoc.getLongitude()));
                 for (DataSnapshot datasnap : snapshot.getChildren()
                      ){
                     if(datasnap.child("Latitude").exists() && datasnap.child("Longitude").exists()){
                         Log.d("FIREBASESERVICE" , datasnap.getValue(Center.class).getTitle() );

                         loc.setLatitude(datasnap.child("Latitude").getValue(double.class));
                         loc.setLongitude(datasnap.child("Longitude").getValue(double.class));
                         Log.d("DOWNLOADED LAT" ,String.valueOf(loc.getLatitude()) );
                         Log.d("DOWNLOADED LONG" ,String.valueOf(loc.getLongitude()));
                         Log.d("DISTANCE" ,String.valueOf(currentLoc.distanceTo(loc)) );
                         if( minDist < currentLoc.distanceTo(loc)) {
                            // rtDBAppoint.CenterFetched(datasnap.getValue(Center.class));
                             //Log.d("FIREBASESERVICE 2" , datasnap.getValue(Center.class).getTitle() );
                             minDist =currentLoc.distanceTo(loc);
                             toReturn.setTitle(datasnap.getValue(Center.class).getTitle());
                             toReturn.setDescription(datasnap.getValue(Center.class).getDescription());
                             toReturn.setCity(datasnap.getValue(Center.class).getCity());
                             toReturn.setAddress(datasnap.getValue(Center.class).getAddress());
                         }
                     }

                 }
                 Log.d("GONNA RETURN" , toReturn.getTitle());
                 rtDBAppoint.CenterFetched(toReturn);
             }

             @Override
             public void onCancelled(@NonNull @NotNull DatabaseError error) {

             }
         });
    }

    public void MakeAppointment(Appointment appointment){
        String key = tbVacAppointments.push().getKey();
        appointment.setId(key);
        tbVacAppointments.child(key).setValue(appointment);

    }

}


