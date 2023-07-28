package com.assignment.pokemonapp.data.di;

import android.content.Context;

import com.assignment.pokemonapp.utils.Const;
import com.intuit.sdp.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rizka on 26/07/2023
 */

@Module(includes = { ApiModule.class, ApplicationModule.class })
@InstallIn(SingletonComponent.class)
public final class NetworkModule {

    static final String ACCESS_KEY = "pokemon";

    @Provides
    @Named(ACCESS_KEY)
    public String provideBaseURL() {
        return Const.BASE_URL;
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if(BuildConfig.DEBUG) interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder(OkHttpClient okHttpClient) {
        return okHttpClient.newBuilder();
    }

    @Provides
    @Singleton
    public Cache provideCache(Context context) {
        int cacheSize = 10*1024*1024;
        return new Cache(context.getCacheDir(), Long.parseLong(String.valueOf(cacheSize)));
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpCallback(HttpLoggingInterceptor interceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, @Named(ACCESS_KEY) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

}
