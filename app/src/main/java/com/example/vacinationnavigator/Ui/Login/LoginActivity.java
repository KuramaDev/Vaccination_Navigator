package com.example.vacinationnavigator.Ui.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vacinationnavigator.R;
import com.example.vacinationnavigator.Ui.Base.BaseActivity;
import com.example.vacinationnavigator.Ui.Main.HomeAcivity;
import com.example.vacinationnavigator.Ui.Register.RegisterActivity;

import java.net.Inet4Address;

public class LoginActivity extends BaseActivity implements LoginView , View.OnClickListener {

    private EditText Password;
    private Button btnLogin;
    private Button btnGglLogin;
    private EditText Email;
    private Button registerBtn;
    private LoginPresenterImp <LoginView> presenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        Password = (EditText) findViewById(R.id.editTextTextPassword);
        btnLogin = (Button) findViewById(R.id.login);
        registerBtn = (Button) findViewById(R.id.register_btn);

        InitializePresenter();
        setUpView();

    }

    @Override
    protected void setUpView() {
        registerBtn.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void InitializePresenter() {
        presenter = new LoginPresenterImp<>();
        presenter.onAttach(this);
        //presenter.Logout();
    }

    @Override
    public void onClick(View v) {
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        switch(v.getId()){
            case R.id.login:
                attemptLogin(email,password);
                break;
            case R.id.register_btn:
                OpenRegister();
                break;
        }
    }

    void OpenRegister(){
        Intent register = new Intent(this,RegisterActivity.class);
        startActivity(register);
    }

    @Override
    public void saveToPreferences(String id, boolean connected) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("User", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(id , connected);
        editor.commit();
    }

    @Override
    public void displayLoginMessage(String message) {
        Toast.makeText(this, message , Toast.LENGTH_LONG).show();
    }

    @Override
    public void OpenHome() {
        Intent home = new Intent(this, HomeAcivity.class);
        home.setFlags(home.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(home);
    }



    void attemptLogin(String email, String password){
        if(!email.isEmpty() && email != null && !password.isEmpty() && password != null) {
            presenter.Login(email, password);
        }
        else{
            Toast.makeText(this, "Fields are mandatory", Toast.LENGTH_LONG).show();
        }
    }
}