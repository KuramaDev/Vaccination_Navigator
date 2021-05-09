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
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabs, viewPager, true, new TabLayoutMediator.TabConfigurationStrategy(){
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                // position of the current tab and that tab
            }
        });
    }

    @Override
    protected void InitializePresenter() {

    }

    private void initializeAdapter(){
        pagerAdapter = new RegisterPagerAdapter(this);
        pagerAdapter.addFragment(new AccountCreation());
        pagerAdapter.addFragment(new PersonalInfo());
    }
}