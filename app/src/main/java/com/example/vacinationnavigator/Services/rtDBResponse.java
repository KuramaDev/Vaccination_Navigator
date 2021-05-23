package com.example.vacinationnavigator.Services;

import com.example.vacinationnavigator.Model.Center;

import java.util.List;

public interface rtDBResponse extends BaseService {
    void CentersFetched (List<Center> centersList);
    void onError(String message);
}
