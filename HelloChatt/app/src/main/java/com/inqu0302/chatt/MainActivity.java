package com.inqu0302.chatt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.inqu0302.chatt.Adapter.ChattAdapter;
import com.inqu0302.chatt.model.Chatt;
import com.inqu0302.chatt.service.FirebaseServiceImplV1;

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

    // firebase와 연결하는 Connection을 위한 객체 선언
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_msg = findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);

        chat_list_view = findViewById(R.id.chatt_list_view);

        chattList = new ArrayList<Chatt>();

        // 1. Adapter 객체생성
        // Adapter 객체를 생성할때 보여줄 데이터 객체를 생성자 매개변수로 주입해 주어야 한다.
        chattAdapter = new ChattAdapter(chattList);

        // 2. RecyclerView.Adapter의 RecyclerView 를 서로연결
        chat_list_view.setAdapter(chattAdapter);

        // 3. RecyclerView의 데이터를 표현할때 사용할 Layout 매니저를 설정하기
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        chat_list_view.setLayoutManager(layoutManager);
        /**
         *  setContentView(R.layout.activity_main)
         *  layout.xml 파일을 읽어서 화면에 뛰우는 메서드
         */

        FirebaseDatabase dbConn = FirebaseDatabase.getInstance();

        // 사용할 table(path)
        // realtimeDatabase에서는 table을 path라는 개념으로 부른다
        dbRef = dbConn.getReference("chatting");

        // firebase로부터 데이터 변화 이벤트가 전달되면 이벤트를 수신하여 할일을 지정하기 위한 핸들러 선언
        ChildEventListener childEventListener = new FirebaseServiceImplV1(chattAdapter);

        // 이벤트 핸들러 연결결
        dbRef.addChildEventListener(childEventListener);

        // 보여줄 데이터 객체 생성
//        Chatt chatt = new Chatt();
//        chatt.setName("홍길동");
//        chatt.setMsg("반갑습니다");
//        chattList.add(chatt);
//
//        chatt = new Chatt();
//        chatt.setName("성춘향");
//        chatt.setMsg("안녕하세요");
//        chattList.add(chatt);
//
//        chatt = new Chatt();
//        chatt.setName("이몽룡");
//        chatt.setMsg("반가워요");
//        chattList.add(chatt);



        /**
         * EditBox에 메시지를 입력하고 Send 버튼을클릭했을때 할일 지정하기
         *
         * EditBox에 메시지를 입력하고 Send를 하면 Firebase의 Realtime DataBase에 데이터를 insert 하기기         */

        btn_send.setOnClickListener(view->{

            // EditBox에 입력된 문자열을 추출하여 문자열 변수에 담기
            String msg = txt_msg.getText().toString();
            if(msg != null && !msg.isEmpty()){

                String toastMsg = String.format("메시지 : %s", msg);
                Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();

                Chatt chattVO = new Chatt();
                chattVO.setMsg(msg);
                chattVO.setName("홍길동");

                Log.d("클릭",chattVO.toString());
//                chattList.add(chattVO);
                // firebase의 realtime DB의 table에 데이터를 push(insert) 하기
                dbRef.push().setValue(chattVO);
                txt_msg.setText("");
            }
       });
    }
}