package com.app.edukt.edukt.petitions;

import com.app.edukt.edukt.pojos.Article;
import com.app.edukt.edukt.pojos.Student;
import com.app.edukt.edukt.pojos.Subject;
import com.app.edukt.edukt.pojos.SubjectTeacher;
import com.app.edukt.edukt.pojos.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @FormUrlEncoded
    @POST(apiRoute + "teacher")
    Call<Teacher> addTeacher(
            @Field("dni_teacher") String dni,
            @Field("name") String name,
            @Field("lastname") String lastname,
            @Field("birthday") String birthday,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST(apiRoute + "teacher/login")
    Call<Teacher> loginTeacher(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST(apiRoute + "student/login")
    Call<Student> loginStudent(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET(apiRoute + "teachers/subject/{subject}")
    Call<List<Teacher>> getTeacherBySubject(@Path("subject") String subject);

    @GET(apiRoute + "teachers/subjects")
    Call<List<Subject>> getSubjects();

    @FormUrlEncoded
    @POST(apiRoute + "teacher/subject")
    Call<SubjectTeacher> setTeacherSubject(
            @Field("dni_teacher") String dni_teacher,
            @Field("id_subject") int id_subject
    );

    @GET(apiRoute + "articles")
    Call<List<Article>> getArticles();

}
