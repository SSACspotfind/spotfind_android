package com.example.spotfind_and;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class. getSimpleName();
    // View
    ImageView mImgVSelectImg;
    Button mBtnSearch;

    // 사진 요청 코드
    private static final int REQUEST_CODE = 0;
    
    // View 초기화
    void init(){
        mImgVSelectImg = findViewById(R.id.img_v_select_img);
        mBtnSearch = findViewById(R.id.btn_search);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        mImgVSelectImg.setOnClickListener(this);
        mBtnSearch.setOnClickListener(this);
        
        // Crop 추가
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_v_select_img:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent , REQUEST_CODE);
                break;
                
            case R.id.btn_search:
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // 이미지를 넣지 않았을때 처리
                if(mImgVSelectImg.getDrawable().getClass() == VectorDrawable.class){
                    Toast.makeText(this, "이미지를 넣어주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap bitmap = ((BitmapDrawable)mImgVSelectImg.getDrawable()).getBitmap();

                float scale = (float) (1024/(float)bitmap.getWidth());
                int image_w = (int) (bitmap.getWidth() * scale);
                int image_h = (int) (bitmap.getHeight() * scale);
                Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent sendintent = new Intent(MainActivity.this, UploadImg.class);
                sendintent.putExtra("image", byteArray);

                startActivity(sendintent);
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                try {
                    Uri uri = data.getData();
                    Glide.with(getApplicationContext()).load(uri).into(mImgVSelectImg);
                }catch (Exception e){
                    Toast.makeText(this, "사진 업로드 에러", Toast.LENGTH_SHORT).show();
                }

            }else if(resultCode == RESULT_CANCELED){    // 취소시 호출할 행동 쓰기
                
            }
        }

    }
}