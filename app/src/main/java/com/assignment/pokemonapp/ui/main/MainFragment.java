package com.assignment.pokemonapp.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.assignment.pokemonapp.R;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonPaging;
import com.assignment.pokemonapp.databinding.FragmentMainBinding;
import com.assignment.pokemonapp.ui.main.MainFragmentDirections.ActionDetailFragment;
import com.assignment.pokemonapp.ui.main.adapter.PokemonLoadStateAdapter;
import com.assignment.pokemonapp.ui.main.adapter.PokemonPagingAdapter;
import com.assignment.pokemonapp.ui.main.adapter.PokemonPagingAdapter.OnItemClickListener;
import com.assignment.pokemonapp.viewmodel.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by rizka on 26/07/2023
 */

@AndroidEntryPoint
public class MainFragment extends Fragment {

    FragmentMainBinding binding;
    MainViewModel viewModel;
    PokemonPagingAdapter pokemonPagingAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, parent, false);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        return binding.getRoot();
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        pokemonPagingAdapter = new PokemonPagingAdapter(new OnItemClickListener() {
            @Override
            public void onItemClick(PokemonPaging item) {
                ActionDetailFragment action =
                        MainFragmentDirections.actionDetailFragment();
                action.setPokemonId(item.pokemonNumber);
                Navigation.findNavController(view).navigate(action);
            }
        });

        binding.rvPokemon.setAdapter(pokemonPagingAdapter.withLoadStateFooter(
                new PokemonLoadStateAdapter(v -> {
                    pokemonPagingAdapter.retry();
                })
        ));

        viewModel.requestPokemonList().subscribe(data -> {
            pokemonPagingAdapter.submitData(getLifecycle(), data);
        });

        binding.ibCaught.setOnClickListener(onClickCallback);
    }

    public View.OnClickListener onClickCallback = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (binding.ibCaught.equals(view)) {
                Navigation.findNavController(view).navigate(R.id.actionCaughtPokemonFragment);
            }
        }
    };

}
