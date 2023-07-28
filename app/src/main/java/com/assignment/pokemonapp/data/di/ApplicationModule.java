package com.assignment.pokemonapp.data.di;

import android.app.Application;
import android.content.Context;

import com.assignment.pokemonapp.data.local.MainLocalDataSource;
import com.assignment.pokemonapp.data.local.dao.PokemonCaughtDao;
import com.assignment.pokemonapp.data.remote.api.ApiCallback;
import com.assignment.pokemonapp.data.remote.repository.MainRepository;
import com.assignment.pokemonapp.data.remote.source.data.MainRemoteDataSource;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/**
 * Created by rizka on 26/07/2023
 */

@Module(includes = {DatabaseModule.class})
@InstallIn(SingletonComponent.class)
public final class ApplicationModule {

    @Provides
    public Context provideApplication(Application application) {
        return application;
    }

    @Provides
    public MainRepository provideMainRepository(ApiCallback apiCallback, PokemonCaughtDao pokemonCaughtDao) {
        return new MainRepository(
                new MainRemoteDataSource(apiCallback),
                new MainLocalDataSource(pokemonCaughtDao)
        );
    }

}
