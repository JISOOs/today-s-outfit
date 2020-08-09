package com.mobile.todayoutfit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class MainActivity extends AppCompatActivity {
    Button outfit, myOutfit;
    String text;
    ImageView imageWeather;
    // 네트워크 작업은 AsyncTask 를 사용해야 한다
    public class WeatherConnection extends AsyncTask<String, String, String>{

        // 백그라운드에서 작업하게 한다
        @Override
        protected String doInBackground(String... params) {


            // Jsoup을 이용한 날씨데이터 Pasing하기.
            try {

                String path = "http://weather.naver.com/rgn/townWetr.nhn?naverRgnCd=09650510";

                Document document = Jsoup.connect(path).get();

                Elements elements = document.select("em");

                System.out.println(elements);

                Element targetElement = elements.get(2);

                text = targetElement.text();

                System.out.println(text);

                return text;



            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("오늘 뭐입지?");


         outfit = (Button)findViewById(R.id.outfit);
         myOutfit = (Button)findViewById(R.id.myOutfit);
        imageWeather = (ImageView)findViewById(R.id.imageWeather);




        outfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("날씨",text);
                startActivity(intent);

            }
        });
        myOutfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecSubActivity.class);
                startActivity(intent);
            }
        });

        TextView textView = (TextView)findViewById(R.id.textView);

        WeatherConnection weatherConnection = new WeatherConnection();

        AsyncTask<String, String, String> result = weatherConnection.execute("","");

        System.out.println("RESULT");

        try{
            String msg = result.get();
            System.out.println(msg);

            textView.setText(msg.toString());

        }catch (Exception e) {
        }

    }

}

