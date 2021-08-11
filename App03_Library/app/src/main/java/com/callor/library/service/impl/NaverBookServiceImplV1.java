package com.callor.library.service.impl;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.library.adapter.BookViewAdapter;
import com.callor.library.config.NaverAPI;
import com.callor.library.model.NaverBookDTO;
import com.callor.library.model.NaverParent;
import com.callor.library.service.NaverBookService;
import com.callor.library.service.RetrofitAPIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverBookServiceImplV1 implements NaverBookService {
    protected RecyclerView recyclerView;

    public NaverBookServiceImplV1(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public NaverBookDTO getBooks(String search){

        // 1. Retrofit을 사용하여 네이버에서 데이터 가져오기
        // RetrofitAPIClient의 getAPIClient() method는
        // Retrofit interface에 선언된 method를 동반한다
        // 동반된 method( getBook() )를 호출하면서 필요한 매개변수를 전달하면
        // Retrofit 은 naver server에 요청을 하고 비동기 방식으로 기다린다
        // 비동기 방식 = 호출하는곳과 응답을 처리하는 곳이 다르다
        // 동기방식 = 호출한곳에서 응답을 처리한다
        Call<NaverParent> retrofitReturn = RetrofitAPIClient.getApiClient()
                .getBook(NaverAPI.CLIENT_ID, NaverAPI.CLIENT_SECRET, search, 1,10);

        // 응답(Callback)을 처리할 EventHandler
        // CallBack interface를 익명클래스로 선언하는 코드(Skeletone Code 라고 부른다)
        retrofitReturn.enqueue(new Callback<NaverParent>() {
            // 정상 수행시
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                // 응답을 받았을때 Http Code가 무엇인지 확인하기 위해 Http rsponse code 가져오기
                int resCode = response.code();
                if(resCode < 300){
                    Log.d("네이버 응답 데이터 : ", response.body().toString());

                    NaverParent naverParent = response.body();

                    List<NaverBookDTO> bookList = naverParent.items;

                    // 도서리스트를 RecyclerView에 표현하기 위한 Adapter생성
                    BookViewAdapter bookViewAdapter = new BookViewAdapter(bookList);

                    // MainActivity에서 전달받은 recyclerView에 setting
                    recyclerView.setAdapter(bookViewAdapter);

                    RecyclerView.LayoutManager layoutManager
                            = new LinearLayoutManager(recyclerView.getContext());
                    recyclerView.setLayoutManager(layoutManager);
                } else{
                    Log.d("오류 발생 : ", response.toString());
                }
            }

            // 오류 발생시
            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

            }
        });

        return null;
    }

}
