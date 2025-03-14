package com.example.btt7.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btt7.network.APIService;
import com.example.btt7.model.Category;
import com.example.btt7.R;
import com.example.btt7.network.RetrofitClient;
import com.example.btt7.adapter.CategoryAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvCate;
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        AnhXa();
        GetCategory();
    }

    private void AnhXa(){
        rvCate = (RecyclerView) findViewById(R.id.rvCategory);

    }

    private void GetCategory(){
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    categoryList = response.body();
                    // Khoi tao adapter
                    categoryAdapter = new CategoryAdapter(MainActivity.this, categoryList);
                    rvCate.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                            LinearLayoutManager.HORIZONTAL, false);
                    rvCate.setLayoutManager(layoutManager);
                    rvCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                }
                else {
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("Log", t.getMessage());
            }
        });
    }
}