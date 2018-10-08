package com.app.edukt.edukt.petitions;

import com.app.edukt.edukt.pojos.Student;
import com.google.gson.annotations.JsonAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IPetitions {
    String apiRoute = "api/v1/";
    @FormUrlEncoded
    @POST(apiRoute + "student")
    Call<Student> addStudent(
            @Field("dni_student") String dni,
            @Field("name") String name,
            @Field("lastname") String lastname,
            @Field("degree") String degree,
            @Field("birthday") String birthday,
            @Field("email") String email,
            @Field("password") String password
    );
}
