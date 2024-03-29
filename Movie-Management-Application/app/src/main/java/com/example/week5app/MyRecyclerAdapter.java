package com.example.week5app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week5app.contentprovider.MovieDataBaseAttributes;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    private List<MovieDataBaseAttributes> listData2;

    public void setData(List<MovieDataBaseAttributes> Newdata) //will ask tutors
    {
        this.listData2 = Newdata;
    } //something is wrong with this

    public MyRecyclerAdapter(List<MovieDataBaseAttributes> listData3) {
        this.listData2 = listData3;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.TitleCards.setText(listData2.get(position).getSearchTitleDataBase());
        holder.CountryCards.setText(listData2.get(position).getCountryDataBase());
        holder.YearCards.setText(listData2.get(position).getYearNumDataBase() + "");
        holder.CostCards.setText(listData2.get(position).getCostDataBase() + "");
        holder.GenreCards.setText(listData2.get(position).getGenreDataBase());

    }

    @Override
    public int getItemCount() {
        return this.listData2.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView TitleCards;
        public TextView CountryCards;
        public TextView YearCards;
        public TextView CostCards;
        public TextView GenreCards;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TitleCards =itemView.findViewById(R.id.TitleCard);
            CountryCards= itemView.findViewById(R.id.CountryCard);
            YearCards = itemView.findViewById(R.id.YearCard);
            CostCards = itemView.findViewById(R.id.CostCard);
            GenreCards = itemView.findViewById(R.id.GenreCard);
        }
    }

}




