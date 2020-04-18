package com.dmdddm.sws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDevice extends AppCompatActivity {
    private EditText city;
    private EditText number;
    private Button btn_determine;

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
                if (number.getText().length()!=10){
                    Toast.makeText(AddDevice.this,"设备号应为10位",Toast.LENGTH_LONG).show();
                }
                //判断城市是否存在
                //else if ()
            }
        });

    }
}
