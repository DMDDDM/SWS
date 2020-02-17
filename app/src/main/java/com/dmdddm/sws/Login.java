package com.dmdddm.sws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.dmdddm.sws.EncoderByMd5.getMD5String;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText nameText;
    private EditText passwordText;
    private Button mLogin;
    private ImageView uIamge;
    private TextView tRegistration;
    private TextView tForget;

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
        String name = nameText.getText().toString();
        String pwd = getMD5String(passwordText.getText().toString());

        //如果账号为空 事件
        if (name.isEmpty()){
            nameText.setHint("账户名不能为空!!!");
            nameText.setHintTextColor(Color.parseColor("#FF0000"));
        }
        //账号名不为空时
        else {

           // MyHttpConnect myHttpConnect = new MyHttpConnect();

            //myHttpConnect.getJson("");

        }

    }
}
