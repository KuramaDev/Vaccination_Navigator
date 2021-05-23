package com.example.vacinationnavigator.Ui.Register;

import com.example.vacinationnavigator.Model.UserInfo;
import com.example.vacinationnavigator.Services.AuthRepsonse;
import com.example.vacinationnavigator.Services.FirebaseService;
import com.example.vacinationnavigator.Ui.Base.BasePresenterImp;

public class RegisterPresenterImp<V extends RegisterView> extends BasePresenterImp<V> implements RegisterPresenter<V> , AuthRepsonse {

    V view ;
    FirebaseService firebaseService ;
    UserInfo  userInfo ;


    public RegisterPresenterImp () {
        firebaseService = new FirebaseService();
        firebaseService.AttachAuthReceiver(this);
    }

    public void  RegisterUser (String email ,String password){
       firebaseService.CreateUser(email,password);
    }

    @Override
    public void onAttach(V view) {
        super.onAttach(view);
        this.view = view;
    }

    @Override
    public void onRegisterComplete(String id, String Message) {
        if (id!= null){
            view.RegisterCompleted();
        }
        else{
            view.RegisterFailure(Message);
        }
    }

    public void onAddPersonalInfo( String name, String amka, String phone, String age){
        userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setAge(age);
        userInfo.setAmka(amka);
        userInfo.setPhone(phone);
        firebaseService.AddUserinfo(userInfo);
        view.ShowConfirmation();
    }
}
