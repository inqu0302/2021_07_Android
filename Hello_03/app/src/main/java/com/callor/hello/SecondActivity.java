package com.callor.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView txt_message = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        String user_id = intent.getExtras().getString("user_id");
        String password = intent.getExtras().getString("password");
        String name = intent.getExtras().getString("name");
        String tel = intent.getExtras().getString("tel");
        String addr = intent.getExtras().getString("addr");

        txt_message = findViewById(R.id.text_second);

        String text = String.format("id : %s\npassword : %s\nname : %s\ntel : %s\naddr : %s",user_id, password, name, tel, addr);

        txt_message.setText(text);
    }
}