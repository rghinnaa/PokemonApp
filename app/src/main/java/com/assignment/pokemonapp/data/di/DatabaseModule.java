package com.assignment.pokemonapp.data.di;

import static com.assignment.pokemonapp.utils.Const.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.assignment.pokemonapp.data.local.PokemonDatabase;
import com.assignment.pokemonapp.data.local.dao.PokemonCaughtDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

/**
 * Created by rizka on 26/07/2023
 */

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Singleton
    @Provides
    public PokemonDatabase provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context,
                PokemonDatabase.class,
                DATABASE_NAME
        ).fallbackToDestructiveMigration().build();
    }

    @Singleton
    @Provides
    public PokemonCaughtDao provideCaughtDao(PokemonDatabase database) {
        return database.pokemonCaughtDao();
    }

}
