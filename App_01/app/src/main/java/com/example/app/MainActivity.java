package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView myImage = null;
    private int count = 0;
    private int [] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImage = findViewById(R.id.my_image);
        images = new int[3];
        images[0] = R.drawable.my01;
        images[1] = R.drawable.my02;
        images[2] = R.drawable.my03;

        myImage.setOnClickListener(view->{

            count ++;
            int imgNum = count % 3;

            myImage.setImageResource(images[imgNum]);

        });
    }
}