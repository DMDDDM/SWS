package com.dmdddm.sws;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static com.dmdddm.sws.EncoderByMd5.getMD5String;

public class Login extends AppCompatActivity implements View.OnClickListener , TextWatcher {
    /**初始化控件**/
    private EditText nameText;
    private EditText passwordText;
    private Button mLogin;
    private ImageView uIamge;
    private TextView tRegistration;
    /**全局变量**/
    private String[] result;
    private Intent iFinish = new Intent();
    private String name;
    private URL url;
    private MySQLiteHelper mySQLiteHelper;
    private String pwd;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**将注册成功时的账号密码 回传到本页面进行登录**/
        if(requestCode == 1 && resultCode == 1){
            nameText.setText(data.getStringExtra("UserName"));
            passwordText.setText(data.getStringExtra("UserPwd"));
        }
        else if (requestCode == 1 && resultCode == 2){
            Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG).show();
        }
    }

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

        mySQLiteHelper = new MySQLiteHelper(this,"SWS",null,1);
    }


    @Override
    public void onClick(View view) {
        Intent iregistration = new Intent(this,Registration.class);
        switch (view.getId()){
            //点击 登录 按钮点击事件
            case R.id.login :
                login();break;
            //点击 免费注册 按钮 点击事件
            case R.id.registration:
                startActivityForResult(iregistration,1);break;
            default:;break;
        }
    }

    /**登录函数**/
    public void login(){
        name = nameText.getText().toString();
        pwd = getMD5String(passwordText.getText().toString());

        String[] item={"LoginStatus"};
        String[] PropertyName = {"Mode","name","pwd"};
        String[] Property = {"login",name,pwd};

        //如果账号为空 事件
        if (name.isEmpty()){
            nameText.setHint("账户名不能为空!!!");
            nameText.setHintTextColor(Color.parseColor("#FF0000"));
        }
        else if (passwordText.getText().toString().isEmpty()){
            passwordText.setHint("密码不能为空!!!");
            passwordText.setHintTextColor(Color.parseColor("#FF0000"));

        }
        //账号名不为空时

        else {
            try {
                url = new URL("https://www.dmdddm.cn/SWS/LoginController?Mode=login&name="+ URLEncoder.encode(name,"UTF-8")+"&pwd="+pwd);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            MyHttpConnect myHttpConnect = new MyHttpConnect();
            result =  myHttpConnect.getJson(url,item,handler);

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
                    //保存到数据库
                    //SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();
                    //db.execSQL("INSERT INTO info(name,pwd) VALUES(?,?)",new String[]{name,pwd});
                    //db.close();

                    iFinish.putExtra("UserName",name);
                    setResult(2,iFinish);
                    finish();
                    /*****/
                }
                else {
                    /**登录失败**/
                    Toast.makeText(getApplicationContext(),"账号或密码有误,请确认后输入",Toast.LENGTH_LONG).show();
                    passwordText.setText("");
                }
            }
        }
    };

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Toast.makeText(getApplicationContext(),"改变前",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Toast.makeText(getApplicationContext(),"改变中",Toast.LENGTH_LONG).show();
    }

    @Override
    public void afterTextChanged(Editable s) {
        Toast.makeText(getApplicationContext(),"改变后",Toast.LENGTH_LONG).show();

    }
}
