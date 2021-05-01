package com.example.vacinationnavigator.Ui.Base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vacinationnavigator.R;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void ShowLoading() {

    }

    @Override
    public void HideLoading() {

    }

    @Override
    public void onError(R.string resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    protected abstract void setUpView();

    protected abstract void InitializePresenter();
}
