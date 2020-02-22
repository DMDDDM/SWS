package com.dmdddm.sws;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;


public class SwitchFragment extends Fragment {

    private Button mFlash;
    private TextView textView;
    private String text = "";
    private String[] result = new String[]{};
    String[] item = {"WindSpeed","CO2","temperature"} ;
    TextView textView2;



    private final String path = "https://www.dmdddm.cn/mini/Index";

    public SwitchFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_switch, container, false);
        /**代码填写**/
        //初始化控件
        mFlash = view.findViewById(R.id.flash);
        textView = view.findViewById(R.id.tips);
        textView2 = view.findViewById(R.id.md5);

        mFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //初始化

                text="";
                //获取数据
                try {

                    URL url = new URL(path);
                    MyHttpConnect myHttpConnect = new MyHttpConnect();
                    result =myHttpConnect.getJson(url,item,handler);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
        /****/
        return view;
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
                for (int i=0;i<item.length;i++){
                    text = text + item[i]+result[i]+"\n";
                }
                textView2.setText(text);
            }
        }
    };




}
