package com.app.edukt.edukt.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.edukt.edukt.R;
import com.app.edukt.edukt.petitions.Petition;

public class MainActivity extends AppCompatActivity {

    //Components
    private Button btnImTeacher;
    private Button btnImStudent;

    private Petition petition = Petition.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect();
    }

    private void connect() {
        btnImTeacher = findViewById(R.id.btn_im_teacher);
        btnImStudent = findViewById(R.id.btn_im_student);
    }

    public void onClickButtonSignUp(View v) {
        startActivity(new Intent(getApplicationContext(),SignUp.class));
    }

    public void onClickButtonLogIn(View v) {
        startActivity(new Intent(getApplicationContext(), LogIn.class));
    }
}
