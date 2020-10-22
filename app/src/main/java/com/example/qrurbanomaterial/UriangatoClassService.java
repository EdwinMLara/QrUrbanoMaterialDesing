package com.example.qrurbanomaterial;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UriangatoClassService {
    public static final String TAG = UriangatoClassService.class.getSimpleName();

    public static final Uriangatoservice getService(final String id_hash){

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalUrl = originalRequest.url();
                HttpUrl httpUrl = originalUrl.newBuilder()
                        .addQueryParameter(Uriangatoservice.HASH_KEY,id_hash)
                        .build();

                Request.Builder requester = originalRequest.newBuilder().url(httpUrl);
                Request request = requester.build();
                Log.d(TAG, httpUrl.toString());
                return chain.proceed(request);
            }
        }).build();

        return new Retrofit.Builder()
                .baseUrl(Uriangatoservice.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Uriangatoservice.class);
    }

    public static final Uriangatoservice postCreateUserService(){
        return new Retrofit.Builder()
                .baseUrl(Uriangatoservice.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Uriangatoservice.class);
    }

    public static final Uriangatoservice getLicenciaByToken(String token){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        return new Retrofit.Builder()
                .baseUrl(Uriangatoservice.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Uriangatoservice.class);
    }

}
