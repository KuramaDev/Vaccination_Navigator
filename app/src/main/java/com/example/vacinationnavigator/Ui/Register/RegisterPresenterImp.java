package com.example.vacinationnavigator.Ui.Register;

import android.util.Log;

import com.example.vacinationnavigator.Services.FireBaseReceiver;
import com.example.vacinationnavigator.Services.FirebaseService;
import com.example.vacinationnavigator.Ui.Base.BasePresenterImp;

public class RegisterPresenterImp<V extends RegisterView> extends BasePresenterImp<V> implements RegisterPresenter<V> , FireBaseReceiver {

    V view ;
    FirebaseService firebaseService ;


    public RegisterPresenterImp () {
        firebaseService = new FirebaseService();
        firebaseService.AttachReceiver(this);
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

    @Override
    public void onLoginComplete(String id) {

    }
}
