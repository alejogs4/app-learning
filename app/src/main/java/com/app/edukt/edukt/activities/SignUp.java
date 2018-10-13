package com.app.edukt.edukt.activities;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import static android.graphics.Color.GRAY;

public class SignUp extends AppCompatActivity {

    //Components
    private TextView tvUserType;

    private EditText dni;
    private EditText name;
    private EditText lastname;
    private MaterialBetterSpinner spGrade;
    private EditText birthday;
    private EditText email;
    private EditText password;
    private Button signupButton;
    //Variables
    static String grade;


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
        spGrade = findViewById(R.id.sp_grade);
        birthday = findViewById(R.id.et_signup_birthdate);
        email = findViewById(R.id.et_signup_email);
        password = findViewById(R.id.et_signup_password);
        signupButton = findViewById(R.id.e_signup_signup);
        tvUserType = findViewById(R.id.tv_user_type);
        tvUserType.setText(MainActivity.userType);

        configureTheSpinner();
    }

    /**
     * Configura el spinner y su visibilidad segun el tipo de usuario
     */
    private void configureTheSpinner() {
        //fills the spinner
        spGrade.setAdapter(new ArrayAdapter<>(this,
                 android.R.layout.simple_dropdown_item_1line,
                 getResources().getStringArray(R.array.sp_elements)));
        //set colors
        spGrade.setHintTextColor(GRAY);
        spGrade.setTextColor(getResources().getColor(R.color.colorAccent));
        spGrade.setFloatingLabelTextColor(getResources().getColor(R.color.colorAccent));

        //verify the type
        if (MainActivity.userType.equals("Bienvenido Profesor"))
            spGrade.setVisibility(View.INVISIBLE);
        else
            spGrade.setVisibility(View.VISIBLE);

        //TODO: Obtener el grado de escolaridad seleccionado
        //get the item selected
        spGrade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    //TODO: configurar el grado de escolaridad

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
