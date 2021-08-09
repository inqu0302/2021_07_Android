package com.callor.library.service;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.library.adapter.BookAdapter;
import com.callor.library.config.Naver;
import com.callor.library.databinding.FragmentFirstBinding;
import com.callor.library.model.BookDTO;
import com.callor.library.model.NaverParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverAPIServiceV1 {

    private BookAdapter bookAdapter = null;
    FragmentFirstBinding binding = null;

    public NaverAPIServiceV1(BookAdapter bookAdapter,
                             FragmentFirstBinding binding) {
        this.bookAdapter = bookAdapter;
        this.binding = binding;
    }

    //3. 가져온 데이터를 parsing하여 books 객체에 담기
    public void getNaverBooks(String search) {

        // 다음 코드가 실행되면 Retrofit 에 설저된 값을 기준으로 naver에 요청을 한다
        // 이 코드는 비동기 방식으로 작동된다
        Call<NaverParent> naverCall = RetrofitClient.getClient()
                .getNaverBook(Naver.CLIENT_ID, Naver.CLIENT_PW, search, 30, 1);

        /**
         *  Retrofit은 API요청을 비동기 방식으로 수행한다
         * 일반적으로 Network나 외부 다른 장치와 데이터를 주고 받을때는
         * 대부분 비동기, 또는 Thread방식으로 사용한다
         *
         * 동기방식
         * 요청수행 ==> 결과가 return 되어올때까지 대기
         *
         * 비동기 방식
         * 요청수행 ==> 결과가 return 되지 않아도 다른일 수행
         * 결과가 return 되면 결과를 수신하여 처리한 event handler 를 작성해야 한다
         */

        // 이벤트 핸들러
        naverCall.enqueue(new Callback<NaverParent>() {

            private NaverParent naverParent;

            @Override // Callback 성공시
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {

                // return 된 response 확인
                Log.d("Naver respone",response.toString());
                int resCode = response.code();
                if(resCode < 300){
                    // return 된 response 객체에서 데이터 추출
                    naverParent = response.body();
                    Log.d("Naver Book", naverParent.toString());

                    List<BookDTO> bookDTOList = naverParent.items;
                    bookAdapter = new BookAdapter(bookDTOList);
                    binding.bookListView.setAdapter(bookAdapter);
                    RecyclerView.LayoutManager layoutManager
                            = new LinearLayoutManager(binding.getRoot().getContext());
                    binding.bookListView.setLayoutManager(layoutManager);
                }
            }

            @Override // 오류발생시
            public void onFailure(Call<NaverParent> call, Throwable t) {
                Log.d("오류발생",t.toString());
            }
        });
    }

}
