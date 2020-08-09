package com.mobile.todayoutfit;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import androidx.appcompat.app.AppCompatActivity;


public class SecSubActivity  extends AppCompatActivity {
    ImageView image1, image2, image3, image4;
    Button btnAdd;
    final int PICTURE_REQUEST_CODE = 100;
    int degree = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myoutfit);
        setTitle("오늘 뭐입지?");

        //UI
        image1 = (ImageView)findViewById(R.id.image1);
        image2 = (ImageView)findViewById(R.id.image2);
        image3 = (ImageView)findViewById(R.id.image3);
        image4 = (ImageView)findViewById(R.id.image4);
        btnAdd = (Button)findViewById(R.id.btnAdd);


        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                //사진을 여러개 선택할수 있도록 한다
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),  PICTURE_REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

            case R.id.turn30:
                image1.setRotation(degree);
                image2.setRotation(degree);
                image3.setRotation(degree);
                image4.setRotation(degree);
                degree +=30;
                break;


            case R.id.twice:
                if(item.isChecked())
                {
                    item.setChecked(false);
                    image1.setScaleX(1);
                    image1.setScaleY(1);
                    image2.setScaleX(1);
                    image2.setScaleY(1);
                    image3.setScaleX(1);
                    image3.setScaleY(1);
                    image4.setScaleX(1);
                    image4.setScaleY(1);
                }
                else
                {
                    item.setChecked(true);
                    image1.setScaleX(2);
                    image1.setScaleY(2);
                    image2.setScaleX(2);
                    image2.setScaleY(2);
                    image3.setScaleX(2);
                    image3.setScaleY(2);
                    image4.setScaleX(2);
                    image4.setScaleY(2);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICTURE_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {

                //기존 이미지 지우기
                image1.setImageResource(0);
                image2.setImageResource(0);
                image3.setImageResource(0);

                //ClipData 또는 Uri를 가져온다
                Uri uri = data.getData();
                ClipData clipData = data.getClipData();

                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
                if(clipData!=null)
                {

                    for(int i = 0; i < 4; i++)
                    {
                        if(i<clipData.getItemCount()){
                            Uri urione =  clipData.getItemAt(i).getUri();
                            switch (i){
                                case 0:
                                    image1.setImageURI(urione);
                                    break;
                                case 1:
                                    image2.setImageURI(urione);
                                    break;
                                case 2:
                                    image3.setImageURI(urione);
                                    break;
                                case 3:
                                    image4.setImageURI(urione);
                                    break;
                            }
                        }
                    }
                }
                else if(uri != null)
                {
                    image1.setImageURI(uri);
                }
            }
        }
    }



}

