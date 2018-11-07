package com.app.edukt.edukt.pojos;

public class Subject {
    private int id_subject;
    private String name;
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
