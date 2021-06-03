package com.example.a5baaar;

import com.example.a5baaar.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
    @GET("v2/top-headlines")
    Call<NewsResponse> getNews(@Query("country") String country, @Query("apiKey") String apiKey);

}
