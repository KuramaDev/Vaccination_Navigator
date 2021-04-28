package com.example.vacinationnavigator.Ui.Base;

public interface BasePresenter <V extends BaseView>{

    public void onAttach(V view);
    public void onDetach();

}
