package com.dmdddm.sws;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SwitchFragment extends Fragment {

    private Button mFlash;
    private TextView textView;
    private String text = "";
    private String[] result = new String[]{};
    TextView textView2;
    private ListView listView;
    private List<Map<String,Object>> lists;
    private int itemIndex = 0;


    private SimpleAdapter simpleAdapter;


    private String[] item1 = {"","","","","","","","","",""};
    private String[] item2 = {"","","","","","","","","",""};
    private String[] item3 = {"","","","","","","","","",""};
    private String[] item4 = {"","","","","","","","","",""};


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1){

            String cityName =data.getStringExtra("city");
            String weather =data.getStringExtra("weather");
            String Number =data.getStringExtra("number");

            item1[itemIndex] = cityName;
            item2[itemIndex] = weather;
            item3[itemIndex] = Number;
            item4[itemIndex] = "OFF";

            Map<String,Object> map =new HashMap<>();
            map.put("item1",item1[itemIndex]);
            map.put("item2",item2[itemIndex]);
            map.put("item3",item3[itemIndex]);
            map.put("item4",item4[itemIndex]);
            lists.add(map);
            itemIndex++;

            simpleAdapter.notifyDataSetChanged();

        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }


    public SwitchFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_switch, container, false);
        /**代码填写**/
        //初始化控件
        mFlash = view.findViewById(R.id.flash);
        textView = view.findViewById(R.id.tips);
        listView = view.findViewById(R.id.devicelist);

        mFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemIndex<=10){
                    Intent intent = new Intent(getActivity(),AddDevice.class);
                    startActivityForResult(intent,1);
                }
                else{
                    Toast.makeText(getActivity(),"试用版最多可添加10个设备",Toast.LENGTH_SHORT).show();
                }
            }

        });


        /**listView填充**/

        lists = new ArrayList<>();
        for(int i = 0;i < itemIndex;i++){
            Map<String,Object> map =new HashMap<>();
            map.put("item1",item1[i]);
            map.put("item2",item2[i]);
            map.put("item3",item3[i]);
            map.put("item4",item4[i]);
            lists.add(map);
        }
        /**
         * fragment适配器上下文使用getActivity
         * 2020年2月14日21:10:49
         * **/
        simpleAdapter = new SimpleAdapter(
                getActivity(),                                        //上下文
                lists,                                                //写入
                R.layout.item_device,                               //listview模板
                new String[]{"item1","item2","item3","item4"},                         //与第二个参数的key名称相同
                new int[]{R.id.city,R.id.cityweather,R.id.device_no,R.id.wStatus});            //写入的控件id
        listView.setAdapter(simpleAdapter);                       //启动适配器

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lists.clear();
                if (item4[i].equals("OFF")){
                    item4[i] ="ON";
                }
                else{
                    item4[i] = "OFF";
                }
                for (int h=0;h<itemIndex;h++){

                    Map<String,Object> map =new HashMap<>();
                    map.put("item1",item1[h]);
                    map.put("item2",item2[h]);
                    map.put("item3",item3[h]);
                    map.put("item4",item4[h]);
                    lists.add(map);
                }
                simpleAdapter.notifyDataSetChanged();

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
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){

            }
        }
    };




}
