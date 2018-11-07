package com.app.edukt.edukt.activities;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.app.edukt.edukt.R;
import com.app.edukt.edukt.petitions.IPetitions;
import com.app.edukt.edukt.petitions.Petition;
import com.app.edukt.edukt.pojos.Teacher;

import java.util.ArrayList;
import java.util.List;


public class Homepage extends AppCompatActivity {

    private ListView lv_teacher;
    private TextInputEditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        connect();
    }

    private void connect(){
        lv_teacher = findViewById(R.id.lv_teacher);
        et_search = findViewById(R.id.et_search);
    }

    private void getTeacherList(String subject) {
        Petition petitions = Petition.getInstance();
        IPetitions iPetitions = petitions.getRetrofit().create(IPetitions.class);
        final Call<List<Teacher>> teachersCall = iPetitions.getTeacherBySubject(subject);
        teachersCall.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {

            }
        });
    }
}
