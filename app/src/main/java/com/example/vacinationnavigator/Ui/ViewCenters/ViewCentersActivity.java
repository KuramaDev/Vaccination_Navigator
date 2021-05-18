package com.example.vacinationnavigator.Ui.ViewCenters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.vacinationnavigator.R;
import com.example.vacinationnavigator.Ui.Base.BaseActivity;
import com.example.vacinationnavigator.Ui.Base.BaseView;

public class ViewCentersActivity extends BaseActivity implements ViewcentersView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_centers);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void InitializePresenter() {

    }
}