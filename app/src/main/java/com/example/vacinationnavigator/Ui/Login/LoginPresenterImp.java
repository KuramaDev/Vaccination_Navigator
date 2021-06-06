package com.example.vacinationnavigator.Ui.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.vacinationnavigator.Model.UserInfo;
import com.example.vacinationnavigator.Services.FirebaseService;
import com.example.vacinationnavigator.Services.LoginResponse;
import com.example.vacinationnavigator.Ui.Base.BasePresenter;
import com.example.vacinationnavigator.Ui.Base.BasePresenterImp;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenterImp<V extends LoginView> extends BasePresenterImp<V> implements LoginPresenter<V> , LoginResponse {

    V view ;
    UserInfo user;
    FirebaseService firebaseService ;

    public LoginPresenterImp (){
        firebaseService = new FirebaseService();
        firebaseService.LoginListener(this);
    }

    @Override
    public void onAttach(V view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    public  UserInfo Login(String email,String password){
        //TODO: Datamanager authenticate user
        firebaseService.CustomLogin(email,password);
        user = new UserInfo();
        //getView().saveToPreferences();
        return  user;
    }

    public UserInfo GoogleLogin(String email, String password){
        user = new UserInfo();
        return user ;
    }

    public void Logout(){
        firebaseService.Logout();
    }

    @Override
    public void onLoginSuccess(FirebaseUser user, String message) {
        this.view.saveToPreferences(user.getUid(), true);
        this.view.displayLoginMessage(message);
        this.view.OpenHome();
    }

    @Override
    public void onLoginError(String message) {
      this.view.displayLoginMessage(message);
    }
}
