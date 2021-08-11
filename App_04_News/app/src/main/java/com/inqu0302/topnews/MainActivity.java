package com.inqu0302.topnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.inqu0302.topnews.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    /**
     * activity.xml파일에 선언된 view Component를 사용할때
     * findByViewId를 사용하지 않고 접근할 수 있도록 선언된 경우
     *   buildFeatures{
     *         viewBinding true
     *     }
     *     자동으로 activity_main.xml 파일을 확장하여
     *     ActivityMainBinding클래스가 생성된다
     */

    ActivityMainBinding main_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 기존에 사용하던
         * setContentView(R.layout.activity_main);를
         *  main_binding = ActivityMainBinding.inflate(getLayoutInflater());
         *         setContentView(main_binding.getRoot());
         *         로 변경
         *
         * 이 코드로 시작이 되면 activity.xml 파일에 있는 모든 view Component가
         * 한꺼번에 사용가능한 상태로 변경된다
         */
        setContentView(R.layout.activity_main);

        main_binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(main_binding.getRoot());

        setContentView(R.layout.activity_main);
    }
}