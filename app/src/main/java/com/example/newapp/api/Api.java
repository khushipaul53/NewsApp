package com.example.newapp.api;

import com.example.newapp.model.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_API = "https://content.guardianapis.com/";

    @GET("search?q=debates&api-key=test")
    Call<NewsModel> getNews();

}
