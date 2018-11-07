package com.app.edukt.edukt.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.edukt.edukt.R;
import com.app.edukt.edukt.pojos.Article;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        welcomeMessage();
        findViewById(R.id.btn_list_teachers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Homepage.class));
            }
        });
        findViewById(R.id.btn_articles).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Articles.class));
            }
        });
    }

    private void welcomeMessage() {
        TextView tv = findViewById(R.id.tv_welcome_std);
        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
        tv.setText("Bienvenido "+ LogIn.student.getName() + " " + LogIn.student.getLastname());
    }
}
