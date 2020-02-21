package com.dmdddm.sws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity {

    private EditText userName;
    private EditText userPwd;
    private EditText rePwd;
    private Button mRegist;
    private  String uName;
    private String uPwd;
    private  String path;
    private String[] InsertState;


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
                    uName = userName.getText().toString();
                    uPwd = EncoderByMd5.getMD5String( userPwd.getText().toString());
                    path = "https://www.dmdddm.cn/SWS/LoginController?Mode=register&name="+uName+"&pwd="+uPwd;

                    /**注册账号**/
                    MyHttpConnect myHttpConnect = new MyHttpConnect();
                    InsertState = myHttpConnect.getJson(path,new String[]{"InsertState"},handler);

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
                }
                else {
                    /**注册失败**/
                }
            }
        }
    };
}
