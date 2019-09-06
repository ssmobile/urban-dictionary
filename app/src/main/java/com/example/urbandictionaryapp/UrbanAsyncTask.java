package com.example.urbandictionaryapp;

import android.os.AsyncTask;
import com.example.urbandictionaryapp.model.datasource.remote.retrofit.RetrofitHelper;
import com.example.urbandictionaryapp.model.datasource.remote.retrofit.UrbanObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UrbanAsyncTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... params) {

        new RetrofitHelper()
                .getUrbanService()
                .getResponses(params[0])
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new UrbanObserver());
        return null;
    }



}

