package com.example.btt7;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("categories.php")
    Call<List<Category>> getCategoryAll();
}
