package com.app.edukt.edukt.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.app.edukt.edukt.R;
import com.app.edukt.edukt.petitions.IPetitions;
import com.app.edukt.edukt.petitions.Petition;
import com.app.edukt.edukt.pojos.Article;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Articles extends AppCompatActivity {

    private ListView lvArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        connect();
    }

    private void connect() {
        lvArticles = findViewById(R.id.lv_articles);
        getArticles();
    }

    private void getArticles() {
        Petition petition = Petition.getInstance();
        IPetitions iPetitions = petition.getRetrofit().create(IPetitions.class);
        final Call<List<Article>> articleCall = iPetitions.getArticles();
        articleCall.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                setAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Toast.makeText(Articles.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setAdapter(@NonNull List<Article> list) {
        List<String> articles = new ArrayList<>();
        for (Article a: list) {
            if (a == null)
                return;
            else {
                articles.add(a.getFecha() + "\n"+a.getTitle()+
                        "\n" + a.getTeacher().getName() + " " + a.getTeacher().getLastname());
            }
        }
        lvArticles.setAdapter(new ArrayAdapter<>(getApplication(), R.layout.list_style, articles));
    }
}
