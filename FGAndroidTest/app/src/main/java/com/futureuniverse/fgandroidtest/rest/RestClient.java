package com.futureuniverse.fgandroidtest.rest;

import com.futureuniverse.fgandroidtest.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Usama Sarwar on 3/31/2017.
 */

public class RestClient {
    private static ApiInterface apiInterface;
    private static Retrofit retrofit;
    public static final String BASE_URL = BuildConfig.BASE_URL;

    private RestClient() {}

    public static ApiInterface getClient() {
        if(apiInterface != null) {
            return apiInterface;
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if(BuildConfig.DEBUG_NETWORK_LOGS) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        OkHttpClient client = builder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        return apiInterface;
    }
}