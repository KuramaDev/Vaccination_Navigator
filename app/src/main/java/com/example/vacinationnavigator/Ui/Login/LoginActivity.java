package com.example.vacinationnavigator.Ui.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vacinationnavigator.R;
import com.example.vacinationnavigator.Ui.Base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginView , View.OnClickListener {

    private EditText Password;
    private Button btnLogin;
    private Button btnGglLogin;
    private EditText Email;
    private LoginPresenterImp <LoginView> presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        Password = (EditText) findViewById(R.id.editTextTextPassword);
        btnLogin = (Button) findViewById(R.id.login);
        btnGglLogin = (Button) findViewById(R.id.googleLogin);

        InitializePresenter();
        setUpView();

    }

    @Override
    protected void setUpView() {
        btnGglLogin.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void InitializePresenter() {
        presenter = new LoginPresenterImp<>();
        presenter.onAttach(this);
        presenter.Logout();
    }

    @Override
    public void onClick(View v) {
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        switch(v.getId()){
            case R.id.login:
                presenter.Login(email, password );
                break;
            case R.id.googleLogin:
                presenter.GoogleLogin(email, password );
                break;
        }
    }

}