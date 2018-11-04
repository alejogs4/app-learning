package com.app.edukt.edukt.pojos;

import com.app.edukt.edukt.petitions.IPetitions;
import com.app.edukt.edukt.petitions.Petition;

import java.util.List;

import retrofit2.Call;

public class Student {
    private String dni_student;
    private String name;
    private String lastname;
    private String degree;
    private String birthdate;
    private String email;

    public Student(String dni_student, String name, String lastname, String birthdate, String email, String degree) {
        this.dni_student = dni_student;
        this.name = name;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.email = email;
        this.degree = degree;
    }

    public Student(String email) {
        this.email = email;
    }

    public String getDni_student() {
        return dni_student;
    }

    public void setDni_student(String dni_student) {
        this.dni_student = dni_student;
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

    public Call<Student> add(String password) {
        Petition petition = Petition.getInstance();
        IPetitions petitions = petition.getRetrofit().create(IPetitions.class);
        final Call<Student> students = petitions.addStudent(
                dni_student,
                name,
                lastname,
                degree,
                birthdate,
                email,
                password
        );
        return  students;
    }

    public Call<Student> login(String password) {
        Petition petition = Petition.getInstance();
        IPetitions petitions = petition.getRetrofit().create(IPetitions.class);
        final Call<Student> students = petitions.loginStudent(email, password);
        return  students;
    }

}
