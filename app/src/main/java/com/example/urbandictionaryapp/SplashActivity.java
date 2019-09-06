package com.example.urbandictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.urbandictionaryapp.model.UrbanResponse;
import com.example.urbandictionaryapp.model.datasource.remote.retrofit.RetrofitHelper;
import com.example.urbandictionaryapp.model.events.UrbanResponseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {


    public static final String TAG = "TAG_SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new UrbanAsyncTask().execute();

    }


    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUrbanEvent(UrbanResponseEvent urbanResponseEvent) {
        if (urbanResponseEvent.getUrbanResponse() != null) {
            UrbanResponse urbanResponse = urbanResponseEvent.getUrbanResponse();
            Log.d(TAG, "onUrbanEvent: " + urbanResponse.getList().toString());
        }
    }
}
