package com.app.edukt.edukt.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.edukt.edukt.R;
import com.app.edukt.edukt.petitions.Petition;

public class MainActivity extends AppCompatActivity {

    private Petition petition = Petition.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
