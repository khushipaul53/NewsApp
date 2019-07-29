package com.example.newapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.newapp.api.Api;
import com.example.newapp.adapter.NewsAdapter;
import com.example.newapp.onClickListener.OnClickListener;
import com.example.newapp.R;
import com.example.newapp.model.NewsModel;
import com.example.newapp.model.ResponseModel;
import com.example.newapp.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    NewsModel newsModel = new NewsModel();
    TextView tvnews;
    Result result;
    ResponseModel responseModel;
    NewsAdapter newsAdapter;
    RecyclerView recyclerView;
    List<Result> list = new ArrayList<Result>();
    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvnews = findViewById(R.id.tvnews);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<NewsModel> call = api.getNews();
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {

                if (response.body().getResponse().getStatus().equals("ok")) {


//
                    int statusCode = response.code();
                    System.out.println(statusCode);
                    NewsModel newsModel = response.body();
                    responseModel = newsModel.getResponse();
                    //  list.add(newsModel);
                    list = responseModel.getResults();


                    Log.d("tag", "" + list.size());
                    newsAdapter = new NewsAdapter(list);

//                    newsAdapter.setOnRecyclerViewItemClickListener(MainActivity.this);
                    recyclerView.setAdapter(newsAdapter);
                    newsAdapter.SetOnItemClickListener(new OnClickListener() {
                        @Override
                        public void OnItemClick(Result Result, int j) {
                            // get current book Link.
                            String link = list.get(j).getWebUrl();
                            // start intent.
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(link));
                            startActivity(i);

                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {

            }
        });
    }
}
