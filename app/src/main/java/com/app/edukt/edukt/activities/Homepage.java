package com.app.edukt.edukt.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.app.edukt.edukt.R;
import com.app.edukt.edukt.petitions.IPetitions;
import com.app.edukt.edukt.petitions.Petition;
import com.app.edukt.edukt.pojos.Subject;
import com.app.edukt.edukt.pojos.Teacher;
import java.util.ArrayList;
import java.util.List;



public class Homepage extends AppCompatActivity {

    private ListView lvTeacher;
    private Spinner spSubjects;

    public static Teacher selectedTeacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        connect();
        getSubjects();
    }


    private void connect() {
        lvTeacher = findViewById(R.id.lv_teacher);
        spSubjects = findViewById(R.id.sp_subjects);

    }

    private void getSubjects() {
        Petition petition = Petition.getInstance();
        IPetitions iPetitions = petition.getRetrofit().create(IPetitions.class);
        final Call<List<Subject>> subjectCall = iPetitions.getSubjects();
        subjectCall.enqueue(new Callback<List<Subject>>() {
            @Override
            public void onResponse(Call<List<Subject>> call, Response<List<Subject>> response) {
                setSpinnerAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Subject>> call, Throwable t) {
                Toast.makeText(Homepage.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSpinnerAdapter(@NonNull List<Subject> list) {
        List<String> subjects = new ArrayList<>();
        for (Subject s : list) {
            subjects.add(s.getName() + " - " + s.getLevel());
        }
        spSubjects.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.list_style
                , subjects));
        onSelectedSpinner(list);
    }

    private void onSelectedSpinner(final List<Subject> list) {
        spSubjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lvTeacher.clearChoices();
                getTeacherList(list.get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void getTeacherList (String subject){
        Petition petitions = Petition.getInstance();
        IPetitions iPetitions = petitions.getRetrofit().create(IPetitions.class);
        final Call<List<Teacher>> teachersCall = iPetitions.getTeacherBySubject(subject);
        teachersCall.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                if (response.body().isEmpty())
                    Toast.makeText(Homepage.this, "Sin profesores", Toast.LENGTH_SHORT).show();
                else {
                    setListAdapter(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
                Toast.makeText(Homepage.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setListAdapter (@NonNull List <Teacher> list) {
        List<String> listTeacher = new ArrayList<>();
        for (Teacher t : list) {
            if (t == null)
                return;
            else
            listTeacher.add(t.getName() + " " + t.getLastname());
        }
        lvTeacher.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                R.layout.list_style, listTeacher));


    }
}