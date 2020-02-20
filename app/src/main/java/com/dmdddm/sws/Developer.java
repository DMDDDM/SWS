package com.dmdddm.sws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Developer extends AppCompatActivity {
    /**声明控件**/
    private ListView listView;
    private Button button;
    private List<Map<String,Object>> lists;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        getSupportActionBar().hide();

        /**初始化控件**/
        listView = findViewById(R.id.developItem);

        /**填写listview控件**/

        String[] item = {"WindSpeed","CO2","temperature","humidity"};
        String[] item2 ={ "200","199","198","197"};

        lists = new ArrayList<>();

        for(int i = 0;i < item.length;i++){
            Map<String,Object> map =new HashMap<>();
            map.put("item",item[i]);
            map.put("item2",item2[i]);
            lists.add(map);
        }
        /**
         * fragment适配器上下文使用getActivity
         * 2020年2月14日21:10:49
         * **/
        SimpleAdapter simpleAdapter;
        simpleAdapter = new SimpleAdapter(
                this,                                        //上下文
                lists,                                                //写入
                R.layout.listview_develop_item,                        //listview模板
                new String[]{"item","item2"},                         //与第二个参数的key名称相同
                new int[]{R.id.itemName,R.id.itemData});            //写入的控件id
        listView.setAdapter(simpleAdapter);                       //启动适配器


    }
}
