package com.example.vacinationnavigator.Ui.Main;

import com.example.vacinationnavigator.Model.Center;
import com.example.vacinationnavigator.Ui.Base.BaseView;

import java.util.List;

public interface HomeView extends BaseView {
    void CentersFetched (List<Center> list);
    void OpenLogin();
}
