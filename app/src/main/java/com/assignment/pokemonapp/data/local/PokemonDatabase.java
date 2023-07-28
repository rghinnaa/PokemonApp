package com.assignment.pokemonapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.assignment.pokemonapp.data.local.dao.PokemonCaughtDao;
import com.assignment.pokemonapp.data.local.entity.PokemonCaught;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by rizka on 28/07/2023
 */

@Database(
        entities = {PokemonCaught.class},
        version = 2,
        exportSchema = false
)
public abstract class PokemonDatabase extends RoomDatabase {
    public abstract PokemonCaughtDao pokemonCaughtDao();

    private static volatile PokemonDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PokemonDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PokemonDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PokemonDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


