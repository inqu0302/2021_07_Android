package com.inqu0302.topnews.service;

import com.inqu0302.topnews.model.NaverNewsDTO;
import com.inqu0302.topnews.model.NaverParent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Retrofit을 사용하여 API조회를 할때 필요한
 * 코드를 자동으로 생성하기 위한 interface
 * Retrofit의 inerface는 기본적으로 return type이 정해져있다.
 *
 * return type Call<DTO>
 * DTO(VO)클래스를 Generic으로 갖는 Call 클래스 return type으로
 * method를 선언해야 한다
 *
 * @GET("endpoint") 형식의 annotation을 설정해 둔다
 *
 * API 조회를 할때 전달할 데이터(변수, 값)를 method의 매개변수로 설정한다.
 *
 * NaverAPI
 * 필수항목 : header 2개, query 1개
 * 선택항목 : star 1개, display 1개
 */
public interface NaverRetrofit {

    @GET("news.json")
    public Call<NaverParent> getNews(
            @Header("X-Naver-Client-Id") String clientId,
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query,
            @Query("start") int start,
            @Query("display") int display
    );

}
