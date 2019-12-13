package com.example.tugaskrs;

import com.example.tugaskrs.Admin.Model.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataMahasiswaService {

    @GET("/api/progmob/mhs/{nim_progmob}")
    Call<ArrayList<Mahasiswa>> getMahasiswaAll(@Path("nim_progmob") String nim_progmob);

    @FormUrlEncoded
    @POST("/api/progmob/mhs/create")
    Call<Mahasiswa> insert_mahasiswa(
            @Field("nama") String nama,
            @Field("nim") String nim,
            @Field("alamat") String alamat,
            @Field("email") String email,
            @Field("foto") String foto,
            @Field("nim_progmob") String nim_progmob
    );

    @FormUrlEncoded
    @PUT("api/progmob/mhs/update")
    Call<Mahasiswa> update_mahasiswa(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("nim") String nim,
            @Field("alamat") String alamat,
            @Field("email") String email,
            @Field("foto") String foto,
            @Field("nim_progmob") String nim_progmob
    );
}
