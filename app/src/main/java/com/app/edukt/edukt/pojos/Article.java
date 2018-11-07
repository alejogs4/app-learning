package com.app.edukt.edukt.pojos;

import com.google.gson.annotations.SerializedName;

public class Article {
    @SerializedName("id_article")
    private int id_article;
    @SerializedName("dni_teacher")
    private String dni_teacher;
    @SerializedName("id_subject")
    private String id_subject;
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("body")
    private String body;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("description")
    private String descripition;
    @SerializedName("teacher")
    private Teacher teacher;

    public Article() {}

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getDni_teacher() {
        return dni_teacher;
    }

    public void setDni_teacher(String dni_teacher) {
        this.dni_teacher = dni_teacher;
    }

    public String getId_subject() {
        return id_subject;
    }

    public void setId_subject(String id_subject) {
        this.id_subject = id_subject;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescripition() {
        return descripition;
    }

    public void setDescripition(String descripition) {
        this.descripition = descripition;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
