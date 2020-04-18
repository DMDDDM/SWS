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

import static com.dmdddm.sws.EncoderByMd5.getMD5String;

public class ChangePassword extends AppCompatActivity {
    private EditText oldpwd;
    private EditText newpwd;
    private EditText repwd;
    private Button btn;
    private Intent intent = getIntent();
    private String  pwd ;
    private String UserName;
    private URL url;
    private String[] result;
    private String newpwdString;
    private String rewpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().hide();//隐藏标题栏

        //初始化控件
        oldpwd = findViewById(R.id.OldPassdword);
        newpwd = findViewById(R.id.NewPassword);
        repwd = findViewById(R.id.reNewPassword);
        btn = findViewById(R.id.change);



        Bundle bundle = this.getIntent().getExtras();
        UserName = bundle.getString("username");

                btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断旧密码是否正确
                pwd = getMD5String(oldpwd.getText().toString());
                String[] item={"LoginStatus"};

                //判断新密码是否合适
                if(newpwd.getText().toString().equals(repwd.getText().toString())) {       //两次密码输入一样时
                    /**判断密码是否符合长度规定**/
                    if (newpwd.getText().toString().length() < 6) {
                        Toast.makeText(ChangePassword.this, "密码最短需要6位,请重新输入!", Toast.LENGTH_LONG).show();
                    }
                    else if (newpwd.getText().toString().length() > 16) {

                        Toast.makeText(ChangePassword.this, "密码不能超过16位,请重新输入!", Toast.LENGTH_LONG).show();
                    }
                    else if(newpwd.getText().toString().equals(oldpwd.getText().toString())){

                        Toast.makeText(ChangePassword.this, "新旧密码不能相同,请重新输入!", Toast.LENGTH_LONG).show();
                    }

                    //密码的长度合适,进行查询旧密码是否正确
                    else {
                        newpwdString = getMD5String(newpwd.getText().toString());
                        Toast.makeText(ChangePassword.this, "进入1", Toast.LENGTH_LONG).show();

                        try {
                            url = new URL("https://www.dmdddm.cn/SWS/LoginController?Mode=login&name="+ URLEncoder.encode(UserName,"UTF-8")+"&pwd="+pwd);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        MyHttpConnect myHttpConnect = new MyHttpConnect();
                        result =  myHttpConnect.getJson(url,item,handler);

                    }
                }
                //两次输入不一样
                else {
                    Toast.makeText(ChangePassword.this, "两次密码不一致,请重新输入!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            URL url2 = null;
            if (msg.what == 1) {
                //旧密码 核对成功
                if (result[0].equals("successful")){
                    //更新密码

                    try {
                        url2 = new URL("https://www.dmdddm.cn/SWS/LoginController?Mode=update&name="+ URLEncoder.encode(UserName,"UTF-8")+"&pwd="+newpwdString);
                       

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } 
                    MyHttpConnect myHttpConnect = new MyHttpConnect();
                    result =  myHttpConnect.getJson(url2,new String[] {"InsertState"},handler1);

                }
                else{
                    Toast.makeText(ChangePassword.this, "旧密码输入错误", Toast.LENGTH_LONG).show();
                }
            }
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler handler1 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                //返回界面
                finish();
            }
        }
    };

}
