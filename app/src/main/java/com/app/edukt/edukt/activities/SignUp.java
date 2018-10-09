package com.app.edukt.edukt.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.app.edukt.edukt.R;
import com.app.edukt.edukt.petitions.Petition;
import com.app.edukt.edukt.pojos.Student;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    //Components
    private TextView tvUserType;

    private EditText dni;
    private EditText name;
    private EditText lastname;
    private MaterialBetterSpinner degree;
    private EditText birthday;
    private EditText email;
    private EditText password;

    private Button signupButton;
    //Variables


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        connect();

        // Eventos
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(thereAreNotFieldsEmpty()) {
                    Toast.makeText(getApplicationContext(), "Todos los campos deben estar vacios", Toast.LENGTH_SHORT).show();
                    return;
                }
                signup();
            }
        });
    }

    private void connect() {
        dni = findViewById(R.id.et_signup_dni);
        name = findViewById(R.id.et_signup_fullname);
        lastname = findViewById(R.id.et_signup_lastname);
        degree = findViewById(R.id.sp_signup);
        birthday = findViewById(R.id.et_signup_birthdate);
        email = findViewById(R.id.et_signup_email);
        password = findViewById(R.id.et_signup_password);
        signupButton = findViewById(R.id.e_signup_signup);

        tvUserType = findViewById(R.id.tv_user_type);
        tvUserType.setText(MainActivity.userType);

    }

    //TODO: Configurar la opcion de escolaridad con un spinner

    private void signup() {
        Student student = new Student(
                dni.getText().toString(),
                name.getText().toString(),
                lastname.getText().toString(),
                birthday.getText().toString() + " 00:00:00",
                email.getText().toString(),
                "s"
        );
        retrofit2.Call<Student> studentService = student.add(password.getText().toString());
        studentService.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(retrofit2.Call<Student> call, Response<Student> response) {
                Toast.makeText(getApplicationContext(), R.string.signup_msg + response.body().getName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(retrofit2.Call<Student> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Verifica si los campos estan vacios
     * @return
     */
    private boolean thereAreNotFieldsEmpty() {
        return dni.getText().toString().isEmpty() ||
               name.getText().toString().isEmpty() ||
               lastname.getText().toString().isEmpty() ||
               birthday.getText().toString().isEmpty() ||
               email.getText().toString().isEmpty() ||
               password.getText().toString().isEmpty();
    }

    //TODO: Configurar el cambio de titulo del toolbar
    public void setToolbar() {
        android.support.v7.widget.Toolbar a = findViewById(R.id.app_toolbar);
        a.setTitle(R.string.signup_toobar_tittle);
    }

}
