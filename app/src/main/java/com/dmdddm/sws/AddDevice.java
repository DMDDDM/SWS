package com.dmdddm.sws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class AddDevice extends AppCompatActivity {
    private EditText city;
    private EditText number;
    private Button btn_determine;
    private String Number;
    private String cityName;
    private String[] result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        getSupportActionBar().hide();//隐藏标题栏

        /**初始化控件*/
        city = findViewById(R.id.cityName);
        number = findViewById(R.id.deviceNumber);
        btn_determine = findViewById(R.id.determine);

        //点击确定按钮
        btn_determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断设备号是否为10位
                if (number.getText().length() == 10){
                 cityName = city.getText().toString();
                 if(cityName.isEmpty()){
                     Toast.makeText(AddDevice.this,"城市不能为空",Toast.LENGTH_SHORT).show();
                 }
                 else{
                     URL url = null;
                     try {
                         url = new URL("https://www.dmdddm.cn/SWS/LoginController?Mode=querycity&cityname="+ URLEncoder.encode(cityName,"UTF-8"));
                     } catch (MalformedURLException e) {
                         e.printStackTrace();
                     } catch (UnsupportedEncodingException e) {
                         e.printStackTrace();
                     }
                     //获取城市是否存在
                     MyHttpConnect myHttpConnect = new MyHttpConnect();
                     result =  myHttpConnect.getJson(url,new String[]{"QueryState","weather"},handler);
                     Number = number.getText().toString();
                 }

                }
                //设备号不为10位
                else{
                    Toast.makeText(AddDevice.this,"设备号应为10位",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                //查询成功
                if (result[0].equals("successful")){

                    //页面结束
                    Intent intent = new Intent();
                    intent.putExtra("city",cityName);
                    intent.putExtra("weather",result[1]);
                    intent.putExtra("number",Number);

                    setResult(1,intent);
                    finish();
                }
                else {
                    Toast.makeText(AddDevice.this,"输入的城市名不存在",Toast.LENGTH_LONG).show();
                }
            }
        }
    };
}
