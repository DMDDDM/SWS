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
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Registration extends AppCompatActivity {

    private EditText userName;
    private EditText userPwd;
    private EditText rePwd;
    private Button mRegist;
    private  String uName;
    private String uPwd;
    private String[] InsertState;
    private Intent iFinish = new Intent();
    private String BuPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        //默认返回为注册失败结果码2, 若注册成功返回的是1
        setResult(2,iFinish);

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
                    /**判断密码是否符合长度规定**/
                    if (userPwd.getText().toString().length() < 6){
                        Toast.makeText(Registration.this,"密码最短需要6位,请重新输入!",Toast.LENGTH_LONG).show();
                    }
                    else if (userPwd.getText().toString().length() > 16){

                        Toast.makeText(Registration.this,"密码不能超过16位,请重新输入!",Toast.LENGTH_LONG).show();
                    }
                    else {
                        /**提交 用户名 密码**/
                        uName = userName.getText().toString();
                        BuPwd = userPwd.getText().toString();
                        uPwd = EncoderByMd5.getMD5String(userPwd.getText().toString());
                        try {
                            URL url = new URL("https://www.dmdddm.cn/SWS/LoginController?Mode=register&name=" + URLEncoder.encode(uName, "UTF-8") + "&pwd=" + uPwd);

                            /**注册账号**/
                            MyHttpConnect myHttpConnect = new MyHttpConnect();
                            InsertState = myHttpConnect.getJson(url, new String[]{"InsertState"}, handler);

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

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
                if (InsertState[0].equals("successful")){
                    /**注册成功**/
                    Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();

                    iFinish.putExtra("UserName",uName);
                    iFinish.putExtra("UserPwd",BuPwd);
                    setResult(1,iFinish);
                    finish();
                }
                else if (InsertState[0].equals("AlreadyExists")){

                    Toast.makeText(getApplicationContext(),"用户已存在,请重新输入用户名",Toast.LENGTH_LONG).show();
                }
                else {
                    /**注册失败**/
                    Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG).show();
                }
            }
        }
    };
}
