package com.assignment.pokemonapp.ui.main.adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.pokemonapp.R;
import com.assignment.pokemonapp.data.local.entity.PokemonCaught;
import com.assignment.pokemonapp.databinding.ItemPokemonCaughtBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rizka on 28/07/2023
 */

public class PokemonCaughtAdapter extends RecyclerView.Adapter<PokemonCaughtAdapter.PokemonViewHolder> {

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        holder.bind(differ.getCurrentList().get(position));
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPokemonCaughtBinding binding = ItemPokemonCaughtBinding.inflate(inflater, parent, false);

        return new PokemonViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        final ItemPokemonCaughtBinding view;

        PokemonViewHolder(ItemPokemonCaughtBinding view) {
            super(view.getRoot());
            this.view = view;
        }

        public void bind(final PokemonCaught item) {
            view.tvNumber.setText(
                    view.getRoot().getContext().getString(
                            R.string.hashtag, String.valueOf(item.number)
                    )
            );
            String name = item.name.substring(0, 1).toUpperCase() + item.name.substring(1).toLowerCase();
            String nameGiven = item.nameGiven.substring(0, 1).toUpperCase() + item.nameGiven.substring(1).toLowerCase();
            view.tvNameGiven.setText(nameGiven);
            view.tvName.setText(name);

            view.cgType.removeAllViews();

            List<String> type = new ArrayList<>();
            if(!item.type1.equals("")) type.add(item.type1);
            if(!item.type2.equals("")) type.add(item.type2);
            if(type.size() > 0) {
                type.forEach(element -> {
                    ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(
                            view.getRoot().getContext(),
                            null,
                            0,
                            R.style.Chip_Filter
                    );

                    Chip chip = new Chip(view.getRoot().getContext());
                    chip.setChipDrawable(chipDrawable);
                    String chipText = element.substring(0, 1).toUpperCase() + element.substring(1).toLowerCase();
                    chip.setText(chipText);
                    chip.setChipBackgroundColor(view.getRoot().getContext().getColorStateList(R.color.white));
                    chip.setClickable(false);
                    chip.setFocusable(false);

                    view.cgType.addView(chip);
                });
            }

            Glide.with(itemView.getContext())
                    .load(item.image)
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
                                        view.cvPokemon.setCardBackgroundColor(lightMutedColor);
                                    }
                                });
                            }

                            return false;
                        }
                    })
                    .into(view.ivPokemon);

        }
    }

    DiffUtil.ItemCallback<PokemonCaught> differCallBack = new DiffUtil.ItemCallback<PokemonCaught>() {
        @Override
        public boolean areItemsTheSame(PokemonCaught oldExampleModel, PokemonCaught newExampleModel) {
            return oldExampleModel.id == newExampleModel.id;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(PokemonCaught oldExampleModel, PokemonCaught newExampleModel) {
            return oldExampleModel.equals(newExampleModel);
        }
    };

    // Creating the AsyncListDiffer instance
    public AsyncListDiffer<PokemonCaught> differ = new AsyncListDiffer<>(this, differCallBack);
}
