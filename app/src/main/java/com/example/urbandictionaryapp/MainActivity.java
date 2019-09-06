package com.example.urbandictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.print.PrintHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.urbandictionaryapp.model.ListItem;
import com.example.urbandictionaryapp.model.UrbanResponse;
import com.example.urbandictionaryapp.model.datasource.remote.retrofit.OkHttpHelper;
import com.example.urbandictionaryapp.model.datasource.remote.retrofit.RetrofitHelper;
import com.example.urbandictionaryapp.model.events.UrbanResponseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG_MainActivity";

    List<ListItem> itemList;
    EditText searchET;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        searchET = findViewById(R.id.searchET);
        recyclerView = findViewById(R.id.recycler_view);
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
        Log.d(TAG, "onUrbanEvent: ");
        if (urbanResponseEvent.getUrbanResponse() != null) {
            UrbanResponse urbanResponse = urbanResponseEvent.getUrbanResponse();
            itemList = urbanResponse.getList();
            Log.d(TAG, "onUrbanEvent: itemList: " + itemList.toString());
            Log.d(TAG, "onUrbanEvent: " + urbanResponse.getList().toString());

            recyclerView.setAdapter(new UrbanAdapter(itemList));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.search_btn) {
            String query = searchET.getText().toString();
            new UrbanAsyncTask().execute(query);
        }
    }
}
