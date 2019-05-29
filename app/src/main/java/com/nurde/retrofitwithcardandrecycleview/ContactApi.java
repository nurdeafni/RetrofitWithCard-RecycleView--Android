package com.nurde.retrofitwithcardandrecycleview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactApi {
    String BASE_URL = "http://210.210.154.65/api/";

    @GET("kontak")
    Call<List<Contact>> getAllContact();

    @GET("kontak/{id}")
    Call<Contact> getContact(@Path("id") int contactId);

    @POST("kontak")
    Call<Contact> saveContact(@Body Contact kontak);


    @FormUrlEncoded
    @POST("kontak")
    Call<Contact> saveContact(@Field("nama") String name,
                             @Field("email") String email,
                             @Field("nohp") String phone,
                             @Field("alamat") String addres);

    @PUT("kontak/{id}")
    Call<Contact> putContact (@Path("id") int contactId, @Body Contact kontak);

    @PATCH("kontak/{id}")
    Call<Contact> patchContact (@Path("id") int contactId, @Body Contact kontak);

    @DELETE("kontak/{id}")
    Call<Void> deleteContact (@Path("id") int contactId);
}
