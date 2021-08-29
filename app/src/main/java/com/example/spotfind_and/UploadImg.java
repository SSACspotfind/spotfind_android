package com.example.spotfind_and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class UploadImg extends AppCompatActivity {
    ImageView mImgVUploadImg;

    void init(){
        mImgVUploadImg = findViewById(R.id.img_v_upload_img);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_img);
        init();

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray , 0 , byteArray.length);
        mImgVUploadImg.setImageBitmap(bitmap);
        
        // todo  검색하는 로직 구현 필요

    }

    @Override
    protected void onResume() {
        super.onResume();
        // 잠시 기다리는 로직
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(UploadImg.this , SpotfindResult.class);
                startActivity(intent);
            }
        });

        thread.start();
    }
}