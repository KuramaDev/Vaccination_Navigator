package com.example.vacinationnavigator.Services;

import com.google.firebase.auth.FirebaseUser;

public interface LoginResponse extends BaseService {
    void onLoginSuccess(FirebaseUser currentUser, String message);
    void onLoginError(String message);

}
