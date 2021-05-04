package com.example.vacinationnavigator.Ui.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.vacinationnavigator.R;
import com.example.vacinationnavigator.Ui.Base.BaseActivity;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void InitializePresenter() {

    }
}