package com.app.edukt.edukt.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.edukt.edukt.R;
import com.app.edukt.edukt.pojos.Student;
import com.app.edukt.edukt.pojos.Teacher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity {

    //Components
    private EditText etEmail;
    private EditText etPassword;
    private ImageView imgPassword;
    private Button btnLogin;
    private LinearLayout btnLoginStudent;
    private LinearLayout btnLoginTeacher;

    //Variables
    public static byte userType;
    public static Student student;
    public static Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        connect();
    }


    private void connect() {
        btnLoginTeacher = findViewById(R.id.ly_user_teacher);
        btnLoginStudent = findViewById(R.id.ly_user_student);
        etEmail = findViewById(R.id.et_email_login);
        etPassword = findViewById(R.id.et_password_login);
        imgPassword = findViewById(R.id.img_password);
        btnLogin = findViewById(R.id.btn_login);
    }


    //TODO: Verificar el usuario e implementar el incio de sesion

    public void defineUserType(View v) {
        if (v.getId() == R.id.ly_user_teacher) userType = 1;
        else userType = 0;
        setVisibitily();
    }

    /**
     * Se encarga de hacer el login
     * @param v Vista que llama al método
     */
    public void login(View v) {
        if (!(etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty())) {
            if (userType == 1) loginTeacher();
            else loginStudent();
        }
        else {
            cleanFields();
            Toast.makeText(this, "Inicio de sesion incorrecto",Toast.LENGTH_SHORT).show();
        }
    }


    private void loginTeacher() {
        Teacher teacher = new Teacher(etEmail.getText().toString());
        retrofit2.Call<Teacher> teacherCall = teacher.login(etPassword.getText().toString());
        teacherCall.enqueue(new Callback<Teacher>() {
            @Override
            public void onResponse(Call<Teacher> call, Response<Teacher> response) {
                if (response.body() == null)
                    Toast.makeText(LogIn.this, "RRr", Toast.LENGTH_SHORT).show();
                else {
                    asignTeacher(response.body());
                    imgPassword.setImageDrawable(getResources()
                            .getDrawable(R.drawable.checked_password_icon, getTheme()));

                    startActivity(new Intent(getApplicationContext(), TeacherSettings.class));
                }
            }

            @Override
            public void onFailure(Call<Teacher> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error al inicio de Sesion",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void asignTeacher(Teacher tch) {
        teacher = tch;
    }

    private void asignStudent(Student std) {
        student = std;
    }


    private void loginStudent() {
        Student student = new Student(etEmail.getText().toString());
        retrofit2.Call<Student> studentCall = student.login(etPassword.getText().toString());
        studentCall.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.body() == null)
                    Toast.makeText(LogIn.this, "RrR", Toast.LENGTH_SHORT).show();
                else {
                    asignStudent(response.body());
                    imgPassword.setImageDrawable(getResources()
                            .getDrawable(R.drawable.checked_password_icon, getTheme()));

                    startActivity(new Intent(getApplicationContext(), MainPage.class));
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error al inicio de Sesión",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setVisibitily() {
        findViewById(R.id.ly_login_email).setVisibility(View.VISIBLE);
        findViewById(R.id.ly_login_password).setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.VISIBLE);
        btnLoginTeacher.setVisibility(View.INVISIBLE);
        btnLoginStudent.setVisibility(View.INVISIBLE);
    }

    private void cleanFields() {
        etEmail.setText("");
        etPassword.setText("");
    }
}
