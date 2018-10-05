package com.app.edukt.edukt.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.app.edukt.edukt.R;

public class SignUp extends AppCompatActivity {

    //Components
    TextView tvUserType;

    //Variables


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        connect();
    }

    private void connect() {
        tvUserType = findViewById(R.id.tv_user_type);
        tvUserType.setText(MainActivity.userType);
    }
}
