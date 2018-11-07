package com.app.edukt.edukt.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.app.edukt.edukt.R;
import com.app.edukt.edukt.petitions.IPetitions;
import com.app.edukt.edukt.petitions.Petition;
import com.app.edukt.edukt.pojos.Subject;
import com.app.edukt.edukt.pojos.SubjectTeacher;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherSettings extends AppCompatActivity {

    private Spinner spn_subject;
    private Button btn_addSubject;
    private EditText et_dniTeacher;

    private int id_subject;
    private List<Subject> subjectsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_settings);
        connect();
        getSelectedSubjectId();
        btn_addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSubjectTeacher(et_dniTeacher.getText().toString());
            }
        });
    }

    private void getSelectedSubjectId() {
        spn_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_subject = subjectsList.get(position).getId_subject();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSubjectTeacher(@NonNull String dniTeacher) {
        final Call<SubjectTeacher> stCall;
        if (dniTeacher.isEmpty())
            Toast.makeText(this, "Ingrese su dni", Toast.LENGTH_SHORT).show();
        else {
            et_dniTeacher.setText("");
            SubjectTeacher subjectTeacher = new SubjectTeacher(dniTeacher, id_subject);
            stCall = subjectTeacher.subjectTeacherCall();
            stCall.enqueue(new Callback<SubjectTeacher>() {
                @Override
                public void onResponse(Call<SubjectTeacher> call, Response<SubjectTeacher> response) {
                    Toast.makeText(TeacherSettings.this, "Se añadió correctamente",
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<SubjectTeacher> call, Throwable t) {
                    Toast.makeText(TeacherSettings.this, "Error: "+t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void connect(){
        spn_subject = findViewById(R.id.spn_subject);
        btn_addSubject = findViewById(R.id.btn_add_subject);
        et_dniTeacher = findViewById(R.id.et_dni_teacher);

        getSubjects();
    }

    private void getSubjects() {
        Petition petition = Petition.getInstance();
        IPetitions iPetitions = petition.getRetrofit().create(IPetitions.class);
        final Call<List<Subject>> listCall = iPetitions.getSubjects();
        listCall.enqueue(new Callback<List<Subject>>() {
            @Override
            public void onResponse(Call<List<Subject>> call, Response<List<Subject>> response) {
                setAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Subject>> call, Throwable t) {
                Toast.makeText(TeacherSettings.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setAdapter(@NonNull List<Subject> list) {
        subjectsList = list;
        List<String> subjects = new ArrayList<>();
        for (Subject s: list) {
            if (s == null)
                return;
            else
                subjects.add(s.getName() + " - " + s.getLevel());
        }
        spn_subject.setAdapter( new ArrayAdapter<>(getApplication(), R.layout.list_style, subjects));
    }


}
