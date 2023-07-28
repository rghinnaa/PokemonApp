package com.assignment.pokemonapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.assignment.pokemonapp.data.local.PokemonDatabase;
import com.assignment.pokemonapp.data.local.dao.PokemonCaughtDao;
import com.assignment.pokemonapp.data.local.entity.PokemonCaught;
import com.assignment.pokemonapp.data.remote.model.Pokemon;
import com.assignment.pokemonapp.data.remote.model.PokemonSpecies;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonDetail;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonPaging;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonSpeciesDetail;
import com.assignment.pokemonapp.data.remote.model.mapper.mapper;
import com.assignment.pokemonapp.data.remote.repository.MainRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rizka on 26/07/2023
 */

@HiltViewModel
public class MainViewModel extends ViewModel {

    final Application application;
    final MainRepository mainRepository;

    private LiveData<List<PokemonCaught>> pokemonCaught;
    private PokemonCaughtDao pokemonCaughtDao;

    public final MutableLiveData<PokemonDetail> getPokemon = new MutableLiveData<>();
    private final MutableLiveData<PokemonSpeciesDetail> getPokemonSpecies = new MutableLiveData<>();
    @Inject
    public MainViewModel(Application application, MainRepository mainRepository) {
        PokemonDatabase db = PokemonDatabase.getDatabase(application);
        pokemonCaughtDao = db.pokemonCaughtDao();

        this.application = application;
        this.mainRepository = mainRepository;
    }

    public LiveData<PokemonDetail> getPokemon() {
        return getPokemon;
    }
    public LiveData<PokemonSpeciesDetail> getPokemonSpecies() {
        return getPokemonSpecies;
    }

    public Flowable<PagingData<PokemonPaging>> requestPokemonList() {
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(mainRepository.requestPokemonList(), coroutineScope);
        return mainRepository.requestPokemonList();
    }

    public void requestPokemon(int pokemonId) {
        mainRepository.requestPokemon(pokemonId).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.body() != null) {
                    getPokemon.postValue(mapper.mapDetail(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                getPokemon.postValue(null);
            }
        });
    }

    public void requestPokemonSpecies(int pokemonId) {
        mainRepository.requestPokemonSpecies(pokemonId).enqueue(new Callback<PokemonSpecies>() {
            @Override
            public void onResponse(Call<PokemonSpecies> call, Response<PokemonSpecies> response) {
                if(response.body() != null) {
                    getPokemonSpecies.postValue(mapper.mapSpecies(response.body()));
                }
            }

            @Override
            public void onFailure(Call<PokemonSpecies> call, Throwable t) {
                getPokemonSpecies.postValue(null);
            }
        });
    }

    public LiveData<List<PokemonCaught>> getCaught() {
        return mainRepository.getCaughtPokemon();
    }

    public void insertPokemonCaught(PokemonCaught pokemonCaught) {
        PokemonDatabase.databaseWriteExecutor.execute(() -> {
            mainRepository.insertPokemon(pokemonCaught);
        });
    }

}
