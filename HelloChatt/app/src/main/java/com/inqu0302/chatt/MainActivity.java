package com.inqu0302.chatt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.inqu0302.chatt.Adapter.ChattAdapter;
import com.inqu0302.chatt.model.Chatt;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // chatt 메시지를 전달하는 view
    private EditText txt_msg;
    private AppCompatButton btn_send;

    // chatt 메시지를 표현할 view
    private RecyclerView chat_list_view;
    private ChattAdapter chattAdapter;
    private List<Chatt> chattList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *  setContentView(R.layout.activity_main)
         *  layout.xml 파일을 읽어서 화면에 뛰우는 메서드
         */

        txt_msg = findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);

        chat_list_view = findViewById(R.id.chatt_list_view);

        // 보여줄 데이터 객체 생성
        chattList = new ArrayList<Chatt>();
        Chatt chatt = new Chatt();
        chatt.setName("홍길동");
        chatt.setMsg("반갑습니다");
        chattList.add(chatt);

        chatt = new Chatt();
        chatt.setName("성춘향");
        chatt.setMsg("안녕하세요");
        chattList.add(chatt);

        chatt = new Chatt();
        chatt.setName("이몽룡");
        chatt.setMsg("반가워요");
        chattList.add(chatt);

        // 1. Adapter 객체생성
        // Adapter 객체를 생성할때 보여줄 데이터 객체를 생성자 매개변수로 주입해 주어야 한다.
        chattAdapter = new ChattAdapter(chattList);

        // 2. RecyclerView.Adapter의 RecyclerView 를 서로연결
        chat_list_view.setAdapter(chattAdapter);

        // 3. RecyclerView의 데이터를 표현할때 사용할 Layout 매니저를 설정하기
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        chat_list_view.setLayoutManager(layoutManager);
    }
}