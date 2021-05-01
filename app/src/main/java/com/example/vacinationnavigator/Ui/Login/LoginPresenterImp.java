package com.example.vacinationnavigator.Ui.Login;

import com.example.vacinationnavigator.Ui.Base.BasePresenter;
import com.example.vacinationnavigator.Ui.Base.BasePresenterImp;

public class LoginPresenterImp<V extends LoginView> extends BasePresenterImp<V> implements LoginPresenter<V>{

    V view ;

    @Override
    public void onAttach(V view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }
}
