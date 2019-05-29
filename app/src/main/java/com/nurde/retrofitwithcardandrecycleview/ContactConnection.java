package com.nurde.retrofitwithcardandrecycleview;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactConnection {

        public static Retrofit instance;
        public static synchronized Retrofit getInstance(){
            if (instance == null){
                instance = new Retrofit.Builder()
                        .baseUrl(ContactApi.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return  instance;
        }
}
