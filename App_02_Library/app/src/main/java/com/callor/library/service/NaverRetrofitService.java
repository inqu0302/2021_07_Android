package com.callor.library.service;

import com.callor.library.model.NaverParent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Connection 객체를 생성시 baseURL과 @GET() 부분에 설정된 Endpoint를 합성하여 URL을 전달한다
 *
 * 또한 @Query("query")로 설정된 부분이 있으면 ?query=변수값 형식으로 URL에 추가한다
 *
 * @Header() 로 선언된 부분은 HttpProtocal의 header에 정보를 담아서 전송한다다
*/

public interface NaverRetrofitService {

    @GET("book.json") // Retrofit 을 사용하여 GET method로 OpenAPI에 접속
    Call<NaverParent> getNaverBook(

            @Header("X-Naver-Client-Id") String clientId,
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query,
            @Query("display") int display,
            @Query("start") int start

            // book.json?query=query&display=10&start=1
    );

}
