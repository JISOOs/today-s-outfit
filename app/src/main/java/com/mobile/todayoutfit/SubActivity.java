package com.mobile.todayoutfit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity  extends AppCompatActivity {

    TextView textView, textView2;
    Button shop1, shop2, shop3;
    String input="30";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        setTitle("오늘 뭐입지?");

        textView=(TextView) findViewById(R.id.textView);
        textView2=(TextView) findViewById(R.id.textView2);
        shop1=(Button) findViewById(R.id.shop1);
        shop2=(Button) findViewById(R.id.shop2);
        shop3=(Button) findViewById(R.id.shop3);

        Intent secondIntent = getIntent();
        String message = secondIntent.getStringExtra("날씨");
        textView.setText(message);

        String message2 = "오늘 기온은 22~20도 사이로 얇은 가디건, 긴팔티, 면바지, 청바지가 적당합니다.";
        textView2.setText(message2);



        shop1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ssfshop.com/8Seconds/main?dspCtgryNo=&brandShopNo=BDMA07A01&brndShopId=8SBSS&leftBrandNM=8SECONDS_8SBSS"));
                startActivity(intent);


            }
        });
        shop2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www2.hm.com/ko_kr/index.html"));
                startActivity(intent);


            }


        });
        shop3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.musinsa.com/"));
                startActivity(intent);


            }
        });
    }


}


