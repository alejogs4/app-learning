package com.app.edukt.edukt.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.app.edukt.edukt.R;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        welcomeMessage();
    }

    private void welcomeMessage() {
        if (LogIn.userType == 1) {
            Toast.makeText(this, "Bienvenido " + LogIn.teacher.getName()+ " "+ LogIn.teacher.getLastname()
                    , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bienvenido " + LogIn.student.getName()+ " "+ LogIn.student.getLastname()
                    , Toast.LENGTH_SHORT).show();
        }
    }
}
