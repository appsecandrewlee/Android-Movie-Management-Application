package com.example.week5app;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView TitleCards,YearCards,CountryCards,GenreCards,CostCards;


    public ViewHolder(@NonNull View Cards) {
        super(Cards);
        TitleCards = Cards.findViewById(R.id.TitleCard);
        YearCards = Cards.findViewById(R.id.YearCard);
        CountryCards = Cards.findViewById(R.id.CountryCard);
        GenreCards = Cards.findViewById(R.id.GenreCard);
        CostCards = Cards.findViewById(R.id.CostCard);

    }
    }
