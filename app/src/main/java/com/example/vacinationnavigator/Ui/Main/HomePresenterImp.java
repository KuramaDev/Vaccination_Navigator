package com.example.vacinationnavigator.Ui.Main;

import android.util.Log;

import com.example.vacinationnavigator.Model.Center;
import com.example.vacinationnavigator.Model.UserInfo;
import com.example.vacinationnavigator.Services.FirebaseService;
import com.example.vacinationnavigator.Services.rtDBResponse;
import com.example.vacinationnavigator.Ui.Base.BasePresenterImp;

import java.util.List;

class HomePresenterImp<V extends HomeView> extends BasePresenterImp<V> implements HomePresenter<V> , rtDBResponse {

    V view ;
    FirebaseService firebaseService ;
    UserInfo userInfo ;


    public HomePresenterImp () {
        firebaseService = new FirebaseService();
        firebaseService.AttachRtDBReceiver(this);
    }

    @Override
    public void onAttach(V view) {
        super.onAttach(view);
        this.view = view;
    }

    @Override
    public V getView() {
        return super.getView();
    }

    public void getAllCenters(){
        firebaseService.getAllCenters();

    }

    @Override
    public void CentersFetched(List<Center> centersList) {
        getView().CentersFetched(centersList);
    }

    @Override
    public void onError(String message) {

    }
    public void IsUserSignedIn(){
        if(firebaseService.IsUserSignedIn()){
            Log.d("User is" , String.valueOf(firebaseService.IsUserSignedIn()));

        }
        else{
            Log.d("WTF", "WTF");
            getView().OpenLogin();
        }
    }

    public void Logout(){
        firebaseService.Logout();
        getView().OpenLogin();
    }
}
