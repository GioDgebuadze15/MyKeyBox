package com.example.mykeybox.retrofit;

import com.example.mykeybox.models.DeviceInfoModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IDeviceInfo {
    @GET
    Call<ArrayList<DeviceInfoModel>> getResult();
}
