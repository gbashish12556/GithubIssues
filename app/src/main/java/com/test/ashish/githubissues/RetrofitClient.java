package com.test.ashish.githubissues;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "https://api.github.com";
    private static RetrofitClient mRetrofitInstance;
    private Retrofit retrofit;

    private RetrofitClient(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()    ;
        httpClient.addInterceptor(logging);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){

        if(mRetrofitInstance == null) {
            mRetrofitInstance = new RetrofitClient();
        }

        return mRetrofitInstance;

    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}