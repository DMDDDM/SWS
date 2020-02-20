package com.dmdddm.sws;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.*;

public class Registration extends AppCompatActivity {

    private EditText userName;
    private EditText userPwd;
    private EditText rePwd;
    private Button mRegist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        //初始化控件
        userName = findViewById(R.id.Name);
        userPwd = findViewById(R.id.pwd);
        rePwd = findViewById(R.id.rWpd);
        mRegist = findViewById(R.id.mRegist);

        //点击注册按钮的点击事件
        mRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName.getText().toString().isEmpty()){   //用户名为空时
                    /**弹出对话框显示"用户名不能为空"**/
                    userName.setHint("账户名不能为空!!!");
                    userName.setHintTextColor(Color.parseColor("#FF0000"));

                }
                else if(userPwd.getText().toString().equals(rePwd.getText().toString())){       //两次密码输入一样时
                    /**提交 用户名 密码**/
                    String uName = userName.getText().toString();
                    String uPwd = EncoderByMd5.getMD5String( userPwd.getText().toString());
                    String path = "https://www.dmdddm.cn/SWS/LoginController?Mode=register&name="+uName+"&pwd="+uPwd;
                    MyHttpConnect myHttpConnect = new MyHttpConnect();

                    myHttpConnect.MyHttpconnect(path);

                    myHttpConnect.getJson(new String[]{"InsertState"});

                    userPwd.setText(uPwd);
                    rePwd.setText(path);


                }
                else {      //两次密码输入不一样时
                    /**弹出对话框 显示"两次密码不一致"**/
                    userPwd.setText("");
                    userPwd.setHint("两次密码不一致,请重新输入!!!");
                    userPwd.setHintTextColor(Color.parseColor("#FF0000"));
                    rePwd.setText("");
                }
            }
        });

    }
}
