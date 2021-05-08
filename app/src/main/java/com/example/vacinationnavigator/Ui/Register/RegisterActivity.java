package com.example.vacinationnavigator.Ui.Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.vacinationnavigator.R;
import com.example.vacinationnavigator.Ui.Base.BaseActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class RegisterActivity extends BaseActivity implements RegisterView {

    ViewPager2 viewPager ;
    RegisterPagerAdapter pagerAdapter;
    TabLayout tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        viewPager = findViewById(R.id.viewPager2);
        tabs = findViewById(R.id.tabLayout);
        initializeAdapter();
        setUpView();
    }

    @Override
    protected void setUpView() {
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void InitializePresenter() {

    }

    private void initializeAdapter(){
        pagerAdapter.addFragment(new AccountCreation());
        pagerAdapter.addFragment(new PersonalInfo());
        pagerAdapter = new RegisterPagerAdapter(this);
    }
}