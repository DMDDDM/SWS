package com.dmdddm.sws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Developer extends AppCompatActivity {
    /**声明控件**/
    private ListView listView;
    private Button button;
    private List<Map<String,Object>> lists;
    private URL url;
    private String[] result={"","","","","","",""};
    private String[] item = {"WindSpeed","CO2","temperature","humidity"};
    private SimpleAdapter simpleAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        getSupportActionBar().hide();


        /**初始化控件**/
        listView = findViewById(R.id.developItem);

        /**填写listview控件**/

        try {
            url = new URL("https://www.dmdddm.cn/mini/Index");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        /**获取数据**/
        MyHttpConnect myHttpConnect = new MyHttpConnect();
        result = myHttpConnect.getJson(url,item,handler);


        lists = new ArrayList<>();

        for(int i = 0;i < item.length;i++){
            Map<String,Object> map =new HashMap<>();
            map.put("item",item[i]);
            map.put("item2",result[i]);
            lists.add(map);
        }
        /**
         * fragment适配器上下文使用getActivity
         * 2020年2月14日21:10:49
         * **/
        simpleAdapter = new SimpleAdapter(
                this,                                        //上下文
                lists,                                                //写入
                R.layout.listview_develop_item,                        //listview模板
                new String[]{"item","item2"},                         //与第二个参数的key名称相同
                new int[]{R.id.itemName,R.id.itemData});            //写入的控件id
        listView.setAdapter(simpleAdapter);                       //启动适配器


    }
    /**Handler对象
     * 监听线程
     * 线程完成
     * 执行以下代码
     * **/
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

        }
    };

}
