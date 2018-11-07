package com.app.edukt.edukt.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.edukt.edukt.R;

public class TeacherSettings extends AppCompatActivity {

    private Spinner spn_subject;
    private Button btn_addSubject;
    private EditText et_dniTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_settings);
        connect();
    }

    private void connect(){
        spn_subject = findViewById(R.id.spn_subject);
        btn_addSubject = findViewById(R.id.btn_addSubject);
        et_dniTeacher = findViewById(R.id.et_dniTeacher);
    }
}
