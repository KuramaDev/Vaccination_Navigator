package com.example.vacinationnavigator.Ui.Register;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.Toast;

import com.example.vacinationnavigator.R;
import com.example.vacinationnavigator.Ui.Base.BaseActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class RegisterActivity extends BaseActivity implements RegisterView, RegisterFragmentListener {

    ViewPager2 viewPager ;
    RegisterPagerAdapter pagerAdapter;
    TabLayout tabs;
    TabLayoutMediator tabLayoutMediator;
    String[] titles =  {"Create Account" , "Personal Info" };

    RegisterPresenterImp<RegisterView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        viewPager = findViewById(R.id.viewPager2);
        tabs = findViewById(R.id.tabLayout);
        initializeAdapter();
        InitializePresenter();
        setUpView();
    }

    @Override
    protected void setUpView() {
        viewPager.setAdapter(pagerAdapter);
        tabLayoutMediator = new TabLayoutMediator(tabs, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull  TabLayout.Tab tab, int position) {
                //position of the current tab and the tab
                tab.setText(titles[position]) ;
                viewPager.setCurrentItem(position, true);

            }
        });

        tabLayoutMediator.attach();

    }

    @Override
    protected void InitializePresenter() {
        presenter = new RegisterPresenterImp<>();
        presenter.onAttach(this);
    }

    private void initializeAdapter(){
        pagerAdapter = new RegisterPagerAdapter(this);
        pagerAdapter.addFragment(new AccountCreation());
    }

    @Override
    public void OnNextClicked(String email, String password) {
        //TODO: Prepare the email and password to create a user.
        Log.d("TEST", "Called from create account fragment");
        presenter.RegisterUser(email,password);


    }

    @Override
    public void OnRegisteredClicked(String name, String amka, String phone, String age) {
        //TODO: Register information to firebase.
        presenter.onAddPersonalInfo(name,amka,phone,age);
    }

    @Override
    public void RegisterCompleted() {
        pagerAdapter.addFragment(new PersonalInfo());
        pagerAdapter.notifyItemInserted(1);
        viewPager.setCurrentItem(1,true);
        tabs.setClickable(true);

    }

    @Override
    public void RegisterFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG ).show();
    }

    @Override
    public void ShowConfirmation() {
        Toast.makeText(this , "Your account creation completed succesfully ", Toast.LENGTH_LONG).show();
    }
}