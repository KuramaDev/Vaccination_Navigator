package com.example.vacinationnavigator.Ui.Login;

import android.icu.text.MessagePattern;

import com.example.vacinationnavigator.Ui.Base.BaseView;

public interface LoginView extends BaseView {
    void saveToPreferences(String id, boolean connected);
    void displayLoginMessage(String message);
    void OpenHome();
}
