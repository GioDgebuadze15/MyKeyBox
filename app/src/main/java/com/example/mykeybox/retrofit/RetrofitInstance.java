package com.example.mykeybox.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static Retrofit retrofit = null;
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static IDeviceInfo getInstance(){

        // If retrofit instance is not created
        if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        // If retrofit instance is already created
        return retrofit.create(IDeviceInfo.class);
    }
}
