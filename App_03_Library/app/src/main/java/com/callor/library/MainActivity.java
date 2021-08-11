package com.callor.library;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.SearchView;

import com.callor.library.service.NaverBookService;
import com.callor.library.service.impl.NaverBookServiceImplV1;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    /**
     * life Cycle
     * App을 실행시켜 사용후 종료까지
     *
     * 화면 구성요소를 읽어 화면을 그릴때
     * onCreate***() method 내에 관련된 코드 실행
     */

    @Override
    // life cycle에서 startUp method, start point method
    // App이 실행될때 가장 먼저 호출되어 코드가 실행되는 method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar main_toolbar = findViewById(R.id.main_app_toolbar);
        setSupportActionBar(main_toolbar);

        recyclerView = findViewById(R.id.book_list_view);

        NaverBookService naverBookService = new NaverBookServiceImplV1(recyclerView);
        naverBookService.getBooks("집");

    }

    /**
     * res/menu/menu.xml 파일을 읽어서 화면의 ActionBar에 메뉴 등을 표현할 때
     * 사용하는 method
     *
     * 이 method 는 Android가 App 을 화면에 띄울때 자동으로 호출하여 사용하는 method
     *
     * activity.xml 파일에 toolbar관련된 항목이 있으면 onCreateOptionsMenu()존재를 확인후
     * method를 호출한다
     *
     * ActionBar에 보여줄 항목이나, 기능 등의 설정(선언)을 여기에서 수행
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 매개변수로 받은 menu는 toolbar에 기본으로 포함된 아무것도 없는 상태의 menu
        // 현재 toolbar에 기본으로 포함된 blank menu 객체에 main_toolbar_menu.xml 파일에
        // 작성되어 있는 item요소를 추가하여 toolbar에 menu가 나타나도록 한다
        getMenuInflater().inflate(R.menu.main_toolbar_menu,menu);

        /**
         * ActionBar(AppBar)에 설정된 search 기능 구현하기
         * 1. SearchView 객체 생성하기
         */
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();

        // Integer.MAX_VALUE = JAVA에서 표현할수 있는 정수의 최대값
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setQueryHint("도서명 검색");

        //검색창에 값이 입력되고 돋보기(검색)을 클릭했을때 반응할 EventHandler
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 검색창에 문자열을 입력하고 돋보기(검색)을 클릭했을때 반응하는 method
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("검색 버튼 클릭 : ",query);

                NaverBookService naverBookService
                        = new NaverBookServiceImplV1(recyclerView);
                naverBookService.getBooks(query.trim());
                return false;
            }

            // 검색창에 문자열을 입력할때 반응하는 method
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("현재 입력하는 문자열 : ",newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}