package com.example.spotfind_and;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.spotfind_and.privateData.NetWorkPrivateInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class  SpotfindResult extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SpotfindResult.class.getSimpleName();
    ImageView mImgVResultSpotimg;
    TextView mTxtVResultSpotmane , mTxtVResultLocation;
    Button mBtnSearchYoutube , mBtnSearchMap;
    String sportTitle, sportLocation, sportImageUrl;
    NetWorkPrivateInfo netWorkPrivateInfo = new NetWorkPrivateInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotfind_result);
        init();

        Intent intent = getIntent();
        sportTitle = intent.getExtras().getString(netWorkPrivateInfo.getRequestPrivateData1());
        sportLocation= intent.getExtras().getString(netWorkPrivateInfo.getRequestPrivateData2());
        sportImageUrl = intent.getExtras().getString(netWorkPrivateInfo.getRequestPrivateData3());


        mTxtVResultSpotmane.setText("관광지명 : "+sportTitle);
        mTxtVResultLocation.setText("위치 : "+sportLocation);

        Glide.with(getApplicationContext()).load(sportImageUrl).into(mImgVResultSpotimg);

        mBtnSearchYoutube.setOnClickListener(this);
        mBtnSearchMap.setOnClickListener(this);

    }

    void init(){
        mImgVResultSpotimg = findViewById(R.id.img_v_result_spotimg);
        mTxtVResultSpotmane = findViewById(R.id.txt_v_result_spotname);
        mTxtVResultLocation = findViewById(R.id.txt_v_result_location);
        mBtnSearchYoutube = findViewById(R.id.btn_search_youtube);
        mBtnSearchMap = findViewById(R.id.btn_search_map);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search_youtube:

                // todo 유튜브 라이브러리를 활용해서 유튜브 앱이로 이동이 아닌 자체 앱에 유튜브 동영상 가져오기

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/results?search_query="+URLDecodeing(sportTitle)));

                intent.setPackage("com.google.android.youtube");
                startActivity(intent);
                break;

            case R.id.btn_search_map:

//                String url = "kakaomap://open";
                //kakaomap://open?page=placeSearch
                String url = "kakaomap://search?q="+sportTitle;

                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent2);
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