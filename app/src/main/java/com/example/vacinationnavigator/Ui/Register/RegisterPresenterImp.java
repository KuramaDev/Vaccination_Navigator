package com.example.vacinationnavigator.Ui.Register;

import com.example.vacinationnavigator.Services.FirebaseService;
import com.example.vacinationnavigator.Ui.Base.BasePresenterImp;

public class RegisterPresenterImp<V extends RegisterView> extends BasePresenterImp<V> implements RegisterPresenter<V> {

    V view ;
    FirebaseService firebaseService ;


    public RegisterPresenterImp () {
        firebaseService = new FirebaseService();
    }

    public void  RegisterUser (String email ,String password){
       if( firebaseService.CreateUser(email,password)){
            view.RegisterCompleted();
       }
       else {
            view.RegisterFailure("Register Failed ");
       }
    }

    @Override
    public void onAttach(V view) {
        super.onAttach(view);
        this.view = view;
    }
}
