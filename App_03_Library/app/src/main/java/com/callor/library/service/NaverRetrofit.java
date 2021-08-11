package com.callor.library.service;

import com.callor.library.model.NaverParent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Retrofit 를 사용하여 API 접속을 할때
 * 최종 end point, header값, method type, parameter등을
 * 설정하는 인터페이스
 */
public interface NaverRetrofit {

    // method의 return type을 void형식이 아닌 Call 클래스 타입으로 설정
    // 1. 가장 기본타입, 아무일도 할수 없다.
    public Call getBook();

    /**
     * 2. naver openAPI를 사용하기 위해서는
     * header에 Clinet ID와 Client Secret값을 전달해 주어야 한다
     *
     * 가. method의 매개변수에 해당속성을 지정해 준다
     * 나. @Header() anotation을 부착한다
     * 다. Header의 이름을 지정한다
     *
     * 3. method의 endpoint와 요청 method type을 지정한다
     *
     * 4. OpenAPI를 통해 데이터를 요청할 때 값, 변수등을 같이 전달하려면
     * @Query()를 사용하여 전달한다
     *
     * 5. return type인 Call 클래스에 Data를 Mapping할
     *      DTO(VO) 클래스를 Generic으로 설정해 준다
     */
    @GET("book.json")
    public Call<NaverParent> getBook(
            @Header("X-Naver-Client-Id") String clientId,
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query,
            @Query("start") int start,
            @Query("display") int display
    );

}
