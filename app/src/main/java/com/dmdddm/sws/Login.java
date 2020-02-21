package com.dmdddm.sws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;

import static com.dmdddm.sws.EncoderByMd5.getMD5String;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText nameText;
    private EditText passwordText;
    private Button mLogin;
    private ImageView uIamge;
    private TextView tRegistration;
    private TextView tForget;
    private String[] result;
    private Intent iFinish = new Intent();
    private String name;
    private Boolean LoginStatus = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();//隐藏标题栏

        nameText = findViewById(R.id.uName);
        passwordText = findViewById(R.id.upwd);
        uIamge = findViewById(R.id.uImage);

        mLogin = findViewById(R.id.login);
        mLogin.setOnClickListener(this);

        tRegistration = findViewById(R.id.registration);
        tRegistration.setOnClickListener(this);

        tForget = findViewById(R.id.Forget);
        tForget.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent iregistration = new Intent(this,Registration.class);
        switch (view.getId()){
            case R.id.login :   //点击 登录 按钮点击事件
                login();break;
            case R.id.registration:     //点击 免费注册 按钮 点击事件
                startActivity(iregistration);break;
            default:;break;
        }
    }

    /**登录函数**/
    public void login(){
        name = nameText.getText().toString();
        String pwd = getMD5String(passwordText.getText().toString());
        String path = "https://www.dmdddm.cn/SWS/LoginController?Mode=login&name="+name+"&pwd="+pwd;

        String[] item={"LoginStatus"};
        //如果账号为空 事件
        if (name.isEmpty()){
            nameText.setHint("账户名不能为空!!!");
            nameText.setHintTextColor(Color.parseColor("#FF0000"));
        }
        //账号名不为空时
        else {
            MyHttpConnect myHttpConnect = new MyHttpConnect();
            result =  myHttpConnect.getJson(path,item,handler);

        }

    }
    /**Handler对象
     * 监听线程
     * 线程完成
     * 执行以下代码
     * **/
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                if (result[0].equals("successful")){
                    /**登录成功**/
                    LoginStatus = true;
                    iFinish.putExtra("UserName",name);
                    setResult(2,iFinish);
                    finish();
                }
                else {
                    /**登录失败**/
                    passwordText.setHint("请重新输入");
                    passwordText.setText("");

                }
            }
        }
    };
}
