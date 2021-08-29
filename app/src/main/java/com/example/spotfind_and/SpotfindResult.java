package com.example.spotfind_and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class SpotfindResult extends AppCompatActivity implements View.OnClickListener {
    ImageView mImgVResultSpotimg;
    TextView mTxtVResultSpotmane , mTxtVResultLocation;
    Button mBtnSearchYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotfind_result);
        init();

        mBtnSearchYoutube.setOnClickListener(this);
    }

    void init(){
        mImgVResultSpotimg = findViewById(R.id.img_v_result_spotimg);
        mTxtVResultSpotmane = findViewById(R.id.txt_v_result_spotname);
        mTxtVResultLocation = findViewById(R.id.txt_v_result_location);
        mBtnSearchYoutube = findViewById(R.id.btn_search_youtube);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search_youtube:

                // todo 유튜브 라이브러리를 활용해서 유튜브 앱이로 이동이 아닌 자체 앱에 유튜브 동영상 가져오기

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/results?search_query=%EC%95%88%EC%84%B1%ED%8C%9C%EB%9E%9C%EB%93%9C"));

                intent.setPackage("com.google.android.youtube");
                startActivity(intent);
                break;
        }
    }

    // 한글을 인코딩하기 위한 함수
    String URLDecodeing(String val){
        String decodeVal = null;
        try {
            decodeVal = URLDecoder.decode(val , "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodeVal;
    }

}