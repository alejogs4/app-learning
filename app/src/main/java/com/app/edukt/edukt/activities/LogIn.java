package com.app.edukt.edukt.activities;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.app.edukt.edukt.R;
import com.app.edukt.edukt.pojos.Student;

public class LogIn extends AppCompatActivity {

    //Components
    private EditText etEmail;
    private EditText etPassword;
    private ImageView imgPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        connect();
    }


    private void connect() {
        etEmail = findViewById(R.id.et_email_login);
        etPassword = findViewById(R.id.et_password_login);
        imgPassword = findViewById(R.id.img_password);
    }


    //TODO: Verificar el usuario y implementar el incio de sesion
    /**
     * Se encarga de hacer el login
     * @param v Vista que llama al método
     */
    public void login(View v) {
        if (!(etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty())) {
            imgPassword.setImageDrawable(getResources()
                    .getDrawable(R.drawable.checked_password_icon, getTheme()));
        }
        else {
            cleanFields();
            Toast.makeText(this, "Inicio de sesion incorrecto",Toast.LENGTH_SHORT).show();
        }
    }


    private void cleanFields() {
        etEmail.setText("");
        etPassword.setText("");
    }
}
