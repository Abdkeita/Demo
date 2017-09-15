package com.example.abd.intermediate_challenge.controller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abd.intermediate_challenge.ItemAdapter;
import com.example.abd.intermediate_challenge.R;
import com.example.abd.intermediate_challenge.api.Client;
import com.example.abd.intermediate_challenge.api.Service;
import com.example.abd.intermediate_challenge.model.Item;
import com.example.abd.intermediate_challenge.model.ItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView Disconnected;
    ProgressDialog pd;
    private RecyclerView recyclerView;
    private Item Item;
    private SwipeRefreshLayout swipecontainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitViews();

        swipecontainer = (SwipeRefreshLayout) findViewById(R.id.swipecontainer); // we get the swipe container using the FindViewById from the

        swipecontainer.setColorSchemeResources(android.R.color.holo_green_light);
        swipecontainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                loadJSON();
                Toast.makeText(MainActivity.this, "Github users Refreshed", Toast.LENGTH_SHORT).show(); // this pops up when the Item is updated successfully.
            }
        });
    }

    private void InitViews() { // Method for the progressDialog that will first appear on lauching the application.
        pd = new ProgressDialog(this);
        pd.setMessage("Retrieving data from Github"); // Message it will display while the progress is On.
        pd.setCancelable(false); // setting this to false disable users from cancelling the process
        pd.show(); // this will display this Initview method
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();
    }

    private void loadJSON() {
        Disconnected = (TextView) findViewById(R.id.disconnected);
        try {
            Client Client = new Client();
            Service apiservice =
                    com.example.abd.intermediate_challenge.api.Client.getClient().create(Service.class);
            Call<ItemResponse> call = apiservice.getItem();
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    List<Item> items = response.body().getItems();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(0);
                    swipecontainer.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d("Error Encountered", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data", Toast.LENGTH_SHORT).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
