package com.assignment.pokemonapp.ui.main;

import static com.assignment.pokemonapp.utils.Const.MAX_POKEMON;
import static com.assignment.pokemonapp.utils.Const.MIN_POKEMON;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.palette.graphics.Palette;

import com.assignment.pokemonapp.R;
import com.assignment.pokemonapp.data.local.entity.PokemonCaught;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonDetail;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonSpeciesDetail;
import com.assignment.pokemonapp.databinding.DialogCatchPokemonBinding;
import com.assignment.pokemonapp.databinding.FragmentCatchPokemonBinding;
import com.assignment.pokemonapp.ui.main.adapter.PokemonCaughtAdapter;
import com.assignment.pokemonapp.viewmodel.MainViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;
import java.util.Random;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by rizka on 28/07/2023
 */

@AndroidEntryPoint
public class CatchPokemonFragment extends Fragment {

    FragmentCatchPokemonBinding binding;

    MainViewModel viewModel;

    Dialog customDialog;

    PokemonDetail pokemonDetail;

    PokemonCaughtAdapter pokemonCaughtAdapter = new PokemonCaughtAdapter();

    int catchProbability;
    int random;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        binding = FragmentCatchPokemonBinding.inflate(inflater, parent, false);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getCaught();
        initDataCaught();

        binding.ibBack.setOnClickListener(onClickCallback);
        binding.btnCatchPokemon.setOnClickListener(onClickCallback);

    }

    private void initCustomDialog() {
        DialogCatchPokemonBinding bindingDialog = DialogCatchPokemonBinding.inflate(getLayoutInflater());

        customDialog = new Dialog(this.getContext());
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(bindingDialog.getRoot());

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(customDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;

        customDialog.getWindow().setAttributes(lp);

        customDialog.show();
        customDialog.setCancelable(true);

        initViewModel();

        viewModel.getPokemon().observe(requireActivity(), new Observer<PokemonDetail>() {
            @Override
            public void onChanged(PokemonDetail item) {
                pokemonDetail = item;

                bindingDialog.pbLoad.setVisibility(View.GONE);
                bindingDialog.clCatch.setVisibility(View.VISIBLE);

                Glide.with(bindingDialog.getRoot().getContext())
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
                            public boolean onResourceReady(Drawable resource,
                                                           Object model,
                                                           Target<Drawable> target,
                                                           DataSource dataSource,
                                                           boolean isFirstResource) {
                                if (resource != null) {
                                    Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                                    Palette.Builder paletteBuilder = Palette.from(bitmap);
                                    paletteBuilder.generate(palette -> {
                                        if (palette != null) {
                                            int lightMutedColor = palette.getLightMutedColor(Color.GRAY);
                                            bindingDialog.cvCatchProbability.setCardBackgroundColor(lightMutedColor);
                                        }
                                    });
                                }

                                return false;
                            }
                        })
                        .into(bindingDialog.ivPokemon);

                viewModel.requestPokemonSpecies(item.pokemonNumber);
            }
        });

        viewModel.getPokemonSpecies().observe(this, new Observer<PokemonSpeciesDetail>() {
            @Override
            public void onChanged(PokemonSpeciesDetail item) {
                /*
                    catch probability = captureRate * 100 / Ball
                    default ball = 255
                */

                catchProbability = (item.pokemonCatchRate * 100) / 255;
                String catchh = bindingDialog.getRoot().getContext().getString(R.string.catch_probability) + " " + catchProbability + "%";

                if (catchProbability >= 50) {
                    bindingDialog.boxGiveName.setVisibility(View.VISIBLE);
                    bindingDialog.btnSubmit.setVisibility(View.VISIBLE);
                    bindingDialog.btnCatchAgain.setVisibility(View.GONE);

                    bindingDialog.tvInfo.setText(bindingDialog.getRoot().getContext().getString(
                            R.string.caught, pokemonDetail.pokemonName
                    ));
                    bindingDialog.tvCatchProbability.setText(catchh);
                } else {
                    bindingDialog.boxGiveName.setVisibility(View.GONE);
                    bindingDialog.btnSubmit.setVisibility(View.GONE);
                    bindingDialog.btnCatchAgain.setVisibility(View.VISIBLE);

                    bindingDialog.tvInfo.setText(bindingDialog.getRoot().getContext().getString(
                            R.string.no_catch, pokemonDetail.pokemonName
                    ));
                    bindingDialog.tvCatchProbability.setText(catchh);
                }
            }
        });

        bindingDialog.btnCatchAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initViewModel();
            }
        });

        bindingDialog.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {

                if(!String.valueOf(bindingDialog.etGiveName.getText()).equals("")) {
                    String type1, type2;

                    if (pokemonDetail.pokemonType.get(0) != null)
                        type1 = pokemonDetail.pokemonType.get(0);
                    else type1 = "";

                    if (pokemonDetail.pokemonType.size() > 1) type2 = pokemonDetail.pokemonType.get(1);
                    else type2 = "";

                    viewModel.insertPokemonCaught(
                            new PokemonCaught(
                                    pokemonDetail.pokemonName,
                                    String.valueOf(bindingDialog.etGiveName.getText()),
                                    pokemonDetail.pokemonNumber,
                                    pokemonDetail.pokemonImage,
                                    type1,
                                    type2
                            )
                    );
                    bindingDialog.etGiveName.setText("");
                    pokemonCaughtAdapter.notifyDataSetChanged();

                    customDialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Please input the name", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void initViewModel() {
        random = new Random().nextInt((MAX_POKEMON - MIN_POKEMON) + 1) + MIN_POKEMON;

        viewModel.requestPokemon(random);
    }

    public void initDataCaught() {
        viewModel.getCaught().observe(this.requireActivity(), new Observer<List<PokemonCaught>>() {
            @Override
            public void onChanged(List<PokemonCaught> pokemonCaughts) {
                binding.rvPokemon.setAdapter(pokemonCaughtAdapter);
                pokemonCaughtAdapter.differ.submitList(pokemonCaughts);
            }
        });
    }

    public View.OnClickListener onClickCallback = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (binding.ibBack.equals(view)) {
                Navigation.findNavController(view).navigateUp();
            }
            if (binding.btnCatchPokemon.equals(view)) {
                initCustomDialog();
            }
        }
    };
}
