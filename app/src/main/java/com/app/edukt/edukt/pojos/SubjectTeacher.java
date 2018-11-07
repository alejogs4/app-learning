package com.app.edukt.edukt.pojos;

import com.app.edukt.edukt.petitions.IPetitions;
import com.app.edukt.edukt.petitions.Petition;
import com.google.gson.annotations.SerializedName;

public class SubjectTeacher {
    @SerializedName("dni_teacher")
    private String dni;
    @SerializedName("id_subject")
    private int id_subject;

    public SubjectTeacher(String dni, int id_subject) {
        this.dni = dni;
        this.id_subject = id_subject;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public retrofit2.Call<SubjectTeacher> subjectTeacherCall() {
        Petition petition = Petition.getInstance();
        IPetitions iPetitions = petition.getRetrofit().create(IPetitions.class);
        final retrofit2.Call<SubjectTeacher> subjectTeacherCall = iPetitions.setTeacherSubject(dni,id_subject);
        return subjectTeacherCall;
    }
}
