package com.assignment.pokemonapp.data.remote.source.data;

import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.assignment.pokemonapp.data.remote.api.ApiCallback;
import com.assignment.pokemonapp.data.remote.model.Pokemon;
import com.assignment.pokemonapp.data.remote.model.PokemonSpecies;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonPaging;
import com.assignment.pokemonapp.data.remote.source.PokemonListPagingSource;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Call;

/**
 * Created by rizka on 26/07/2023
 */

public class MainRemoteDataSource {

    private final ApiCallback apiCallback;
    public MainRemoteDataSource(ApiCallback callback) {
        this.apiCallback = callback;
    }

    public Flowable<PagingData<PokemonPaging>> requestPokemonList() {
        PokemonListPagingSource pokemonListPagingSource = new PokemonListPagingSource(apiCallback);
        Pager<Integer, PokemonPaging> pager = new Pager(
                new PagingConfig(10),
                () -> pokemonListPagingSource);
        return PagingRx.getFlowable(pager);
    }

    public Call<Pokemon> requestPokemon(int pokemonId) {
        return apiCallback.requestPokemon(pokemonId);
    }

    public Call<PokemonSpecies> requestPokemonSpecies(int pokemonId) {
        return apiCallback.requestPokemonSpecies(pokemonId);
    }

}
