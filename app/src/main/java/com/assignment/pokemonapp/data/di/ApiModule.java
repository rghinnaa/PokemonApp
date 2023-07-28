package com.assignment.pokemonapp.data.di;

import com.assignment.pokemonapp.data.remote.api.ApiCallback;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

/**
 * Created by rizka on 26/07/2023
 */

@Module
@InstallIn(SingletonComponent.class)
public final class ApiModule {
    @Provides
    public ApiCallback provideApiCallback(Retrofit retrofit) {
        return retrofit.create(ApiCallback.class);
    }
}
