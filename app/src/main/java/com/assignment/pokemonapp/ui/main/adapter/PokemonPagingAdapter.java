package com.assignment.pokemonapp.ui.main.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.pokemonapp.R;
import com.assignment.pokemonapp.data.remote.model.mapper.PokemonPaging;
import com.assignment.pokemonapp.databinding.ItemPokemonBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;

import java.util.List;
import java.util.Objects;

/**
 * Created by rizka on 27/07/2023
 */

public class PokemonPagingAdapter extends PagingDataAdapter<PokemonPaging, PokemonPagingAdapter.PokemonViewHolder> {

    private final OnItemClickListener listener;
    public PokemonPagingAdapter(OnItemClickListener listener) {
        super(Differ.INSTANCE);

        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        PokemonPaging item = getItem(position);
        if(item != null) {
            holder.bind(item, listener);
        }
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPokemonBinding binding = ItemPokemonBinding.inflate(inflater, parent, false);
        return new PokemonViewHolder(binding);
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        final ItemPokemonBinding view;

        PokemonViewHolder(ItemPokemonBinding view) {
            super(view.getRoot());
            this.view = view;
        }

        public void bind(final PokemonPaging item, final OnItemClickListener listener) {
            view.tvNumber.setText(
                    view.getRoot().getContext().getString(
                            R.string.hashtag, String.valueOf(item.pokemonNumber)
                    )
            );
            String name = item.pokemonName.substring(0, 1).toUpperCase() + item.pokemonName.substring(1).toLowerCase();
            view.tvName.setText(name);

            view.cgType.removeAllViews();
            item.pokemonType.forEach(element -> {
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

            Glide.with(itemView.getContext())
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
                                        view.cvPokemon.setCardBackgroundColor(lightMutedColor);
                                    }
                                });
                            }

                            return false;
                        }
                    })
                    .into(view.ivPokemon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PokemonPaging item);
    }

    public static class Differ extends DiffUtil.ItemCallback<PokemonPaging> {
        public static final Differ INSTANCE = new Differ();

        @Override
        public boolean areItemsTheSame(@NonNull PokemonPaging oldItem, @NonNull PokemonPaging newItem) {
            // Id is unique.
            return oldItem.pokemonNumber == newItem.pokemonNumber;
        }

        @Override
        public boolean areContentsTheSame(@NonNull PokemonPaging oldItem, @NonNull PokemonPaging newItem) {
            return Objects.equals(oldItem, newItem);
        }
    }
}
