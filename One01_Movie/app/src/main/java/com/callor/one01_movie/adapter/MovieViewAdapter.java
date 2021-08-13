package com.callor.one01_movie.adapter;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.one01_movie.R;
import com.callor.one01_movie.model.NaverDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieViewAdapter extends RecyclerView.Adapter {

    private List<NaverDTO> movieList;

    public MovieViewAdapter(List<NaverDTO> movieList) {

        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.item_view,parent,false);

        MovieItemHolder viewHolder = new MovieItemHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MovieItemHolder movieHolder = (MovieItemHolder) holder;

        NaverDTO movieDTO = movieList.get(position);

        String item_title = movieDTO.getTitle();
        Spanned sp_title = Html.fromHtml(item_title, Html.FROM_HTML_MODE_LEGACY);
        movieHolder.item_title.setText(sp_title);

        String item_director = movieDTO.getDirector();
        Spanned sp_director = Html.fromHtml(("감독 : " + item_director), Html.FROM_HTML_MODE_LEGACY);
        movieHolder.item_director.setText(sp_director);

        String item_actor = movieDTO.getActor();
        Spanned sp_actor = Html.fromHtml(("배우 : " + item_actor), Html.FROM_HTML_MODE_LEGACY);
        movieHolder.item_actor.setText(sp_actor);

        String item_userRating = movieDTO.getUserRating();
        Spanned sp_userRating = Html.fromHtml(("평점 : " + item_userRating), Html.FROM_HTML_MODE_LEGACY);
        movieHolder.item_userRating.setText(sp_userRating);

        Log.d("영화 검색", movieDTO.toString());

        if(!movieDTO.getImage().isEmpty()){
            Picasso.get().load(movieDTO.getImage()).into(movieHolder.item_image);
        }
    }

    @Override
    public int getItemCount() {

        return movieList == null ? 0 : movieList.size();
    }

    public static class MovieItemHolder extends RecyclerView.ViewHolder{

        public TextView item_title;
        public ImageView item_image;
        public TextView item_director;
        public TextView item_actor;
        public TextView item_userRating;

        public MovieItemHolder(@NonNull View itemView) {
            super(itemView);
            item_title = itemView.findViewById(R.id.movie_item_title);
            item_image = itemView.findViewById(R.id.movie_item_image);
            item_director = itemView.findViewById(R.id.movie_item_diractor);
            item_actor = itemView.findViewById(R.id.movie_item_actor);
            item_userRating = itemView.findViewById(R.id.movie_item_userRating);

        }
    }

}
