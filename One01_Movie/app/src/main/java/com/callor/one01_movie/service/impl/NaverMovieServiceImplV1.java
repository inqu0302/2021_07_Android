package com.callor.one01_movie.service.impl;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.one01_movie.adapter.MovieViewAdapter;
import com.callor.one01_movie.config.NaverAPI;
import com.callor.one01_movie.model.NaverDTO;
import com.callor.one01_movie.model.NaverParent;
import com.callor.one01_movie.service.NaverMovieService;
import com.callor.one01_movie.service.RetrofitAPIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverMovieServiceImplV1 implements NaverMovieService {

    private RecyclerView recyclerView;
    public NaverMovieServiceImplV1(RecyclerView recyclerView) {
        this.recyclerView = recyclerView; }

    // 검색어를 받아서 DTO 에 담아 값을 돌려주는 method
    @Override
    public NaverDTO getMovie(String search) {

        Call<NaverParent> retrofitReturn = RetrofitAPIClient.getApiClient().getMovie(
                NaverAPI.CLIENT_ID,
                NaverAPI.CLIENT_SECRET,
                search,
                1,
                10);

        retrofitReturn.enqueue(new Callback<NaverParent>() {
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                int resCode = response.code();
                // 정상적으로 넘어오면
                if(resCode < 300) {
                    Log.d("네이버 데이터 : ",response.body().toString());
                    NaverParent naverParent = response.body();

                    List<NaverDTO> movieList = naverParent.items;

                    Log.d("NaverDTO", movieList.toString());

                    MovieViewAdapter movieViewAdapter = new MovieViewAdapter(movieList);
                    Log.d("Adapter",recyclerView.toString());

                    recyclerView.setAdapter(movieViewAdapter);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                    recyclerView.setLayoutManager(layoutManager);
                } else {
                    Log.d("오류발생", response.toString());
                }

            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

            }
        });
        return null;
    }
}