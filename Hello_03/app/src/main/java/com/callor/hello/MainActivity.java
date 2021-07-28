package com.callor.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button btn_save = null;
    private TextInputEditText input_id = null;
    private TextInputEditText input_password = null;
    private TextInputEditText input_name = null;
    private TextInputEditText input_addr = null;
    private TextInputEditText input_tel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_id = findViewById(R.id.user_id);
        input_password = findViewById(R.id.password);
        input_name = findViewById(R.id.name);
        input_addr = findViewById(R.id.addr);
        input_tel = findViewById(R.id.tel);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(view->{
            String user_id = input_id.getText().toString();
            String password = input_password.getText().toString();
            String name = input_name.getText().toString();
            String addr = input_addr.getText().toString();
            String tel = input_tel.getText().toString();

            String id_pattern = "[a-zA-Z0-9]+";
            String name_pattern = "[가-힣]+";
            String addr_pattern = "[가-힣0-9]+";
            String tel_pattern = "[0-9]+";

            if(user_id.isEmpty()){
                input_id.setError("ID는 반드시 입력해주세요");
                input_id.setFocusable(true);
                return;
            } else if(!user_id.matches(id_pattern)){
                input_id.setError("ID는 영어와 숫자로만 입력해주세요");
                input_id.setFocusable(true);
                return;
            }

            if(password.isEmpty()){
                input_password.setError("비밀번호는 반드시 입력해주세요");
                input_password.setFocusable(true);
                return;
            }

            if(name.isEmpty()){
                input_name.setError("이름은 반드시 입력해주세요");
                input_name.setFocusable(true);
                return;
            } else if(!name.matches(name_pattern)){
                input_name.setError("이름은 한글만 입력해주세요");
                input_name.setFocusable(true);
                return;
            }

            if(tel.isEmpty()){
                input_tel.setError("번호를 입력해주세요");
                input_tel.setFocusable(true);
                return;
            } else if(!tel.matches(tel_pattern)){
                input_tel.setError("번호는 숫자만 입력해주세요");
                input_tel.setFocusable(true);
                return;
            }

            if(addr.isEmpty()){
                input_addr.setError("주소는 반드시 입력해주세요");
                input_addr.setFocusable(true);
                return;
            } else if(!addr.matches(addr_pattern)){
                input_addr.setError("주소는 한글과 숫자로만 입력해주세요");
                input_addr.setFocusable(true);
                return;
            }
//            String text = String.format("id : %s\npassword : %s\nname : %s\ntel : %s\naddr : %s",user_id, password, name, tel, addr);
//
//            Toast.makeText(this, text, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            intent.putExtra("user_id", user_id);
            intent.putExtra("password", password);
            intent.putExtra("name", name);
            intent.putExtra("addr", addr);
            intent.putExtra("tel", tel);

            startActivity(intent);
        });

    }
}