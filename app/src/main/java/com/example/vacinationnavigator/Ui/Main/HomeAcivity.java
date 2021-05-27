package com.example.vacinationnavigator.Ui.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.vacinationnavigator.Model.Center;
import com.example.vacinationnavigator.R;
import com.example.vacinationnavigator.Ui.Base.BaseActivity;
import com.example.vacinationnavigator.Ui.Main.Centers.CenterFragment;
import com.example.vacinationnavigator.Ui.Main.Home.HomeFragment;
import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeAcivity extends BaseActivity implements HomeView , HomeFragmentListener {

    HomeFragment homeFragment;
    CenterFragment centerFragment;
    BottomNavigationView bottomNavigationView;
    HomePresenterImp<HomeView> presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acivity);

        homeFragment = new HomeFragment();
        centerFragment = new CenterFragment();

        InitializePresenter();
        setUpView();

    }

    @Override
    protected void setUpView() {
        setCurrentFragment(homeFragment);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        setCurrentFragment(homeFragment);
                        return true;
                    case R.id.centers:
                        setCurrentFragment(centerFragment);
                        return true;

                }
                return false;
            }
        });
    }

    @Override
    protected void InitializePresenter() {
        presenter = new HomePresenterImp<HomeView>();
        presenter.onAttach(this);
    }

    private void setCurrentFragment(Fragment fragment){
       FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
       fragmentTransaction.replace(R.id.frameLayout,fragment);
      // fragmentTransaction.addToBackStack(null);
       fragmentTransaction.commit();
    }

    @Override
    public void FetchCenters() {
        presenter.getAllCenters();
    }

    @Override
    public void CentersFetched(List<Center> list) {
        centerFragment.CentersLoaded(list);
    }
}