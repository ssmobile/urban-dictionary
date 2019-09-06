package com.example.urbandictionaryapp.model.datasource.remote.retrofit;

import android.util.Log;

import com.example.urbandictionaryapp.model.datasource.remote.retrofit.services.ObservableUrbanService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.urbandictionaryapp.model.datasource.remote.UrlConstants.BASE_URL;

public class RetrofitHelper {

    public static final String TAG = "TAG_RetrofitHelper";

    private Retrofit getRetrofitInstance() {
        Log.d(TAG, "getRetrofitInstance: ");
        return  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public ObservableUrbanService getUrbanService() {
        return getRetrofitInstance().create(ObservableUrbanService.class);
    }
}
