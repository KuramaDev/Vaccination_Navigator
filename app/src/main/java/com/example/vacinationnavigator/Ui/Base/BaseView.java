package com.example.vacinationnavigator.Ui.Base;

import com.example.vacinationnavigator.R;

public interface BaseView {
    public void ShowLoading();
    public void  HideLoading();
    public void onError(R.string resId);
    public  void onError(String message);
    public boolean isNetworkConnected();
}
