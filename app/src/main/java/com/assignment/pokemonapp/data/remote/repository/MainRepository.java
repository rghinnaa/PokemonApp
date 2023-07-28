package com.assignment.pokemonapp.data.remote.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.PagingData;

import com.assignment.pokemonapp.data.local.MainLocalDataSource;
import com.assignment.pokemonapp.data.local.PokemonDatabase;
import com.assignment.pokemonapp.data.local.callback.LocalSourceCallback;
import com.assignment.pokemonapp.data.local.entity.PokemonCaught;
import com.assignment.pokemonapp.data.remote.model.Pokemon;
import com.assignment.pokemonapp.data.remote.model.PokemonSpecies;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonDetail;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonPaging;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonSpeciesDetail;
import com.assignment.pokemonapp.data.remote.source.callback.MainSourceCallback;
import com.assignment.pokemonapp.data.remote.source.data.MainRemoteDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.flow.Flow;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rizka on 26/07/2023
 */

public class MainRepository implements MainSourceCallback, LocalSourceCallback {

    private static MainRemoteDataSource remoteDataSource;
    private static MainLocalDataSource localDataSource;

    public MainRepository(
            MainRemoteDataSource mainRemoteDataSource,
            MainLocalDataSource mainLocalDataSource
    ) {
        remoteDataSource = mainRemoteDataSource;
        localDataSource = mainLocalDataSource;
    }

    @Override
    public Flowable<PagingData<PokemonPaging>> requestPokemonList() {
        return remoteDataSource.requestPokemonList();
    }

    @Override
    public Call<Pokemon> requestPokemon(int pokemonId) {
        return remoteDataSource.requestPokemon(pokemonId);
    }

    @Override
    public Call<PokemonSpecies> requestPokemonSpecies(int pokemonId) {
        return remoteDataSource.requestPokemonSpecies(pokemonId);
    }

    @Override
    public void insertPokemon(PokemonCaught pokemonCaught) {
        localDataSource.insertCaught(pokemonCaught);
    }

    @Override
    public LiveData<List<PokemonCaught>> getCaughtPokemon() {
        return localDataSource.getCaught();
    }
}
