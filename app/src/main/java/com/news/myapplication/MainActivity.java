package com.news.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView l1;
    List<Hero>hero1;
    RecyclerView v1;
    HeroAdapter hero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v1=findViewById(R.id.recyclerview);
        v1.setLayoutManager(new LinearLayoutManager(this));
        v1.setHasFixedSize(true);
       // l1=findViewById(R.id.list);
        getHero();
    }
    public void getHero()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        Call<List<Hero>> call1=api.getHeroes();
        call1.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroList=response.body();
                hero1=heroList;
                call();

                //  l1.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,heroes));
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "internet coneection error", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void call()
    {
        Log.i("hell","in");
        hero=new HeroAdapter(hero1,getApplicationContext());
        v1.setAdapter(hero);

    }
}
