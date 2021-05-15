package com.example.vacinationnavigator.Ui.Register;

public interface RegisterFragmentListener {
    void OnNextClicked(String email, String password);
    void OnRegisteredClicked(String name, String amka, String phone, String age);
}
