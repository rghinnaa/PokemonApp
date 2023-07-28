package com.assignment.pokemonapp.data.remote.source.callback;

import androidx.paging.PagingData;

import com.assignment.pokemonapp.data.local.entity.PokemonCaught;
import com.assignment.pokemonapp.data.remote.model.Pokemon;
import com.assignment.pokemonapp.data.remote.model.PokemonSpecies;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonDetail;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonPaging;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonSpeciesDetail;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by rizka on 26/07/2023
 */

public interface MainSourceCallback {

    Flowable<PagingData<PokemonPaging>> requestPokemonList();

    Call<Pokemon> requestPokemon(int pokemonId);

    Call<PokemonSpecies> requestPokemonSpecies(int pokemonId);

}
