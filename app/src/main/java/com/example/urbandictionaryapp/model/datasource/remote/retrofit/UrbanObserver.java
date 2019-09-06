package com.example.urbandictionaryapp.model.datasource.remote.retrofit;

import android.util.Log;

import com.example.urbandictionaryapp.model.UrbanResponse;
import com.example.urbandictionaryapp.model.events.UrbanResponseEvent;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class UrbanObserver implements Observer<UrbanResponse> {

    private static final String TAG = "TAG_Observer";

    private UrbanResponse urbanResponse;

    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");

    }

    @Override
    public void onNext(UrbanResponse urbanResponse) {
        this.urbanResponse = urbanResponse;
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);

    }

    @Override
    public void onComplete() {
        EventBus.getDefault().post(new UrbanResponseEvent(urbanResponse));

    }
}
