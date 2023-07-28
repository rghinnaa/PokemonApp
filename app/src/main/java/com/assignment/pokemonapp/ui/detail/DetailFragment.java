package com.assignment.pokemonapp.ui.detail;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.palette.graphics.Palette;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.assignment.pokemonapp.R;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonDetail;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonSpeciesDetail;
import com.assignment.pokemonapp.databinding.FragmentDetailBinding;
import com.assignment.pokemonapp.viewmodel.MainViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by rizka on 28/07/2023
 */

@AndroidEntryPoint
public class DetailFragment extends Fragment {

    FragmentDetailBinding binding;
    MainViewModel viewModel;

    PokemonDetail pokemonDetail;
    PokemonSpeciesDetail pokemonSpeciesDetail;

    FragmentStateAdapter pagerAdapter;

    Gson gson = new Gson();

    private int pokemonId;

    private static final int NUM_PAGES = 2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, parent, false);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        if(getArguments() != null) {
            pokemonId = DetailFragmentArgs.fromBundle(getArguments()).getPokemonId();
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewModel();

        initPokemon();
        initPokemonSpecies();

        binding.ibBack.setOnClickListener(onClickCallback);
    }

    public void initViewModel() {
        viewModel.requestPokemon(pokemonId);
    }

    public void initPokemon() {
        viewModel.getPokemon().observe(requireActivity(), new Observer<PokemonDetail>() {
            @Override
            public void onChanged(PokemonDetail item) {
                pokemonDetail = item;

                binding.tvNumber.setText(
                        binding.getRoot().getContext().getString(
                                R.string.hashtag, String.valueOf(item.pokemonNumber)
                        )
                );
                String name = item.pokemonName.substring(0, 1).toUpperCase() + item.pokemonName.substring(1).toLowerCase();
                binding.tvName.setText(name);

                binding.cgType.removeAllViews();
                item.pokemonType.forEach(element -> {
                    ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(
                            binding.getRoot().getContext(),
                            null,
                            0,
                            R.style.Chip_Filter
                    );

                    Chip chip = new Chip(binding.getRoot().getContext());
                    chip.setChipDrawable(chipDrawable);
                    String chipText = element.substring(0, 1).toUpperCase() + element.substring(1).toLowerCase();
                    chip.setText(chipText);
                    chip.setChipBackgroundColor(binding.getRoot().getContext().getColorStateList(R.color.white));
                    chip.setChipStrokeWidth(1f);
                    chip.setChipStrokeColor(binding.getRoot().getContext().getColorStateList(R.color.dove_gray));
                    chip.setClickable(false);
                    chip.setFocusable(false);

                    binding.cgType.addView(chip);
                });

                Glide.with(binding.getRoot().getContext())
                        .load(item.pokemonImage)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(
                                    GlideException e,
                                    Object model,
                                    Target<Drawable> target,
                                    boolean isFirstResource
                            ) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                if (resource != null) {
                                    Bitmap bitmap = ((BitmapDrawable)resource).getBitmap();
                                    Palette.Builder paletteBuilder = Palette.from(bitmap);
                                    paletteBuilder.generate(palette -> {
                                        if (palette != null) {
                                            int lightMutedColor = palette.getLightMutedColor(Color.GRAY);
                                            binding.clDetail.setBackgroundColor(lightMutedColor);
                                            binding.tlInfo.setSelectedTabIndicatorColor(
                                                    palette.getLightMutedColor(
                                                            Color.GRAY
                                                    )
                                            );
                                        }
                                    });
                                }

                                return false;
                            }
                        })
                        .into(binding.ivPokemon);


                viewModel.requestPokemonSpecies(pokemonId);
            }
        });
    }

    public void initPokemonSpecies() {
        viewModel.getPokemonSpecies().observe(requireActivity(), new Observer<PokemonSpeciesDetail>() {
            @Override
            public void onChanged(PokemonSpeciesDetail item) {
                pokemonSpeciesDetail = item;

                initTabLayout();
            }
        });
    }

    private static class BasePagerAdapter extends FragmentStateAdapter {
        final String dataDetail;
        final String dataSpecies;

        public BasePagerAdapter(Fragment fa, String dataDetail, String dataSpecies) {
            super(fa);
            this.dataDetail = dataDetail;
            this.dataSpecies = dataSpecies;
        }

        @Override
        public Fragment createFragment(int position) {
            List<Fragment> pages = new ArrayList<>();

            BaseInfoFragment baseInfoFragment = BaseInfoFragment.getInstance(dataDetail, dataSpecies);
            BaseStatFragment baseStatFragment = BaseStatFragment.getInstance(dataDetail);

            pages.add(baseInfoFragment);
            pages.add(baseStatFragment);

            return pages.get(position);
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

    public void initTabLayout() {
        pagerAdapter = new BasePagerAdapter(
                this,
                gson.toJson(pokemonDetail),
                gson.toJson(pokemonSpeciesDetail)
        );
        binding.vpInfo.setAdapter(pagerAdapter);
        new TabLayoutMediator(binding.tlInfo, binding.vpInfo,
                (tab, position) -> tab.setText(
                        binding.getRoot().getContext().getResources()
                                .getStringArray(R.array.infoTitle)[position]
                )
        ).attach();
    }

    public View.OnClickListener onClickCallback = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (binding.ibBack.equals(view)) {
                Navigation.findNavController(view).navigateUp();
            }
        }
    };
}
