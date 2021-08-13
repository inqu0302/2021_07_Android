package com.callor.one01_movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.SearchView;


import com.callor.one01_movie.service.NaverMovieService;
import com.callor.one01_movie.service.impl.NaverMovieServiceImplV1;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 이부분을 지우면 activity_main.xml 을 열지 못해서 아무것도 할수 없습니다다
       setContentView(R.layout.activity_main);

        Toolbar main_toolbar = findViewById(R.id.main_app_toolbar);
        setSupportActionBar(main_toolbar);

        recyclerView = findViewById(R.id.movie_list_view);
        Log.d("MAIN ACT",recyclerView.toString());

        NaverMovieService naverBookService = new NaverMovieServiceImplV1(recyclerView);
        naverBookService.getMovie("오늘");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_toolbar_menu,menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setQueryHint("영화 제목 검색");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                NaverMovieService naverMovieService = new NaverMovieServiceImplV1(recyclerView);
                naverMovieService.getMovie(query.trim());

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}