package com.dmdddm.sws;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
    String[] item = {"WindSpeed","CO2","temperature"} ;
    TextView textView2;
    private ListView listView;
    private List<Map<String,Object>> lists;

    @Override
    public void onStart() {
        super.onStart();
    }

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
        listView = view.findViewById(R.id.devicelist);

        mFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddDevice.class);
                startActivity(intent);
            }
        });


        /**listView填充**/

        String[] Item = {"设备名","设备1","设备2","设备3"};
        String[] item2 ={"设备状态","开","开","开"};

        lists = new ArrayList<>();
        for(int i = 0;i < Item.length;i++){
            Map<String,Object> map =new HashMap<>();
            map.put("Item",Item[i]);
            map.put("Item2",item2[i]);
            lists.add(map);
        }
        /**
         * fragment适配器上下文使用getActivity
         * 2020年2月14日21:10:49
         * **/
        SimpleAdapter simpleAdapter;
        simpleAdapter = new SimpleAdapter(
                getActivity(),                                        //上下文
                lists,                                                //写入
                R.layout.listview_switch,                               //listview模板
                new String[]{"Item2","Item"},                         //与第二个参数的key名称相同
                new int[]{R.id.deviceName,R.id.deviceStatus});            //写入的控件id
        listView.setAdapter(simpleAdapter);                       //启动适配器

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Toast.makeText(getActivity(),"第1个",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(),"第2个",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(),"第3个",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(),"第4个",Toast.LENGTH_SHORT).show();
                        break;
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
