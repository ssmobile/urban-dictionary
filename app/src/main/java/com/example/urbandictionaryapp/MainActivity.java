package com.example.urbandictionaryapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.urbandictionaryapp.model.ListItem;
import com.example.urbandictionaryapp.model.UrbanResponse;
import com.example.urbandictionaryapp.model.events.UrbanResponseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG_MainActivity";

    private List<ListItem> itemList;
    private EditText searchET;
    private RecyclerView recyclerView;
    private UrbanAdapter adapter;
    private ProgressBar progressBar;
    private Menu menu;
    private boolean thumbsUpAscending = true;
    private boolean thumbsDownAscending = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        searchET = findViewById(R.id.searchET);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_circular);

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

            adapter = new UrbanAdapter(itemList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        this.menu = menu;

        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (adapter==null) {
            Toast.makeText(this,"Nothing to sort", Toast.LENGTH_SHORT).show();
            return false;
        }

        switch (item.getItemId()) {
            case R.id.thumbsUpMenuItem:
                Log.d(TAG, "onOptionsItemSelected: ");
                adapter.sortByThumbsUpAscending(thumbsUpAscending);
                menu.getItem(0).setVisible(true);
                menu.getItem(2).setVisible(false);

                if (thumbsUpAscending) {
                    menu.getItem(0).setTitle(getString(R.string.up_arrow));
                } else {
                    menu.getItem(0).setTitle(getString(R.string.down_arrow));
                }


                thumbsUpAscending = !thumbsUpAscending;
                break;
            case R.id.thumbsDownMenuItem:
                Log.d(TAG, "onOptionsItemSelected: ");
                adapter.sortByThumbsDown(thumbsDownAscending);
                menu.getItem(2).setVisible(true);
                menu.getItem(0).setVisible(false);

                if (thumbsDownAscending) {
                    menu.getItem(2).setTitle(getString(R.string.up_arrow));
                } else {
                    menu.getItem(2).setTitle(getString(R.string.down_arrow));
                }

                thumbsDownAscending = !thumbsDownAscending;
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.search_btn) {
            progressBar.setVisibility(View.VISIBLE);
            menu.getItem(0).setVisible(false);
            menu.getItem(2).setVisible(false);
            thumbsUpAscending = true;
            thumbsDownAscending = true;

            recyclerView.setBackground(null);
            String query = searchET.getText().toString();
            new UrbanAsyncTask().execute(query);
        }
    }

}
