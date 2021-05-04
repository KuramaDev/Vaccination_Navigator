package com.example.vacinationnavigator.Ui.Login;

import com.example.vacinationnavigator.Model.UserInfo;
import com.example.vacinationnavigator.Services.FirebaseService;
import com.example.vacinationnavigator.Ui.Base.BasePresenter;
import com.example.vacinationnavigator.Ui.Base.BasePresenterImp;

public class LoginPresenterImp<V extends LoginView> extends BasePresenterImp<V> implements LoginPresenter<V>{

    V view ;
    UserInfo user;
    FirebaseService firebaseService ;

    public LoginPresenterImp (){
        firebaseService = new FirebaseService();
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
        return  user;
    }

    public UserInfo GoogleLogin(String email, String password){
        user = new UserInfo();
        return user ;
    }

    public void Logout(){
        firebaseService.Logout();
    }


}
