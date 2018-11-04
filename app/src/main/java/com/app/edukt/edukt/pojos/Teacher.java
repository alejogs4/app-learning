package com.app.edukt.edukt.pojos;

import com.app.edukt.edukt.petitions.IPetitions;
import com.app.edukt.edukt.petitions.Petition;

import retrofit2.Call;

public class Teacher {
    private String name;
    private String lastname;
    private String dni_teacher;
    private String birthdate;
    private String email;

    public Teacher(String dni_teacher, String name, String lastname, String birthdate, String email) {
        this.name = name;
        this.lastname = lastname;
        this.dni_teacher = dni_teacher;
        this.birthdate = birthdate;
        this.email = email;
    }

    public Teacher(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDni_teacher() {
        return dni_teacher;
    }

    public void setDni_teacher(String dni_teacher) {
        this.dni_teacher = dni_teacher;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Call<Teacher> add(String password) {
        Petition petition = Petition.getInstance();
        IPetitions petitions = petition.getRetrofit().create(IPetitions.class);
        final Call<Teacher> teachers = petitions.addTeacher(
                dni_teacher,
                name,
                lastname,
                birthdate,
                email,
                password
        );
        return  teachers;
    }

    public Call<Teacher> login(String password) {
        Petition petition = Petition.getInstance();
        IPetitions petitions = petition.getRetrofit().create(IPetitions.class);
        final Call<Teacher> teacher = petitions.loginTeacher(email, password);
        return  teacher;
    }
}
