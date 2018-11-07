package com.app.edukt.edukt.pojos;

import com.google.gson.annotations.SerializedName;

public class Subject {
    @SerializedName("id_subject")
    private int id_subject;

    @SerializedName("name")
    private String name;

    @SerializedName("level")
    private String level;

    public Subject(int id_subject, String name, String level) {
        this.id_subject = id_subject;
        this.name = name;
        this.level = level;
    }

    public Subject(){}

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
