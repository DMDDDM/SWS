package com.dmdddm.sws;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CenterFragment extends Fragment{

    //声明控件
    private ImageView userImage;
    private TextView userName;
    private ListView listView;
    private List<Map<String,Object>> lists;

    public CenterFragment() {
        // Required empty public constructor
    }
    /*
    * 区别：
    *    1. Fragment中onCreate类似于Activity.onCreate，在其中可初始化除了view之外的一切；
    *   2. onCreateView是创建该fragment对应的视图，其中需要创建自己的视图并返回给调用者；
    */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_center, container, false);
        final Intent login = new Intent(getActivity(),Login.class);
    /**此处填写代码**/
        userImage = view.findViewById(R.id.userImage);
        userName = view.findViewById(R.id.userName);
        listView = view.findViewById(R.id.listviewBox);
        /**头像点击事件**/
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        /***用户名点击事件**/
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(login);

            }
        });

        /**listView填充**/

        String[] Item = {"设置","账号安全","开发者模式","退出账户"};
        int[] imageViews ={ R.drawable.setting,R.drawable.lock,R.drawable.develop,R.drawable.logout};

        lists = new ArrayList<>();

        for(int i = 0;i < Item.length;i++){
            Map<String,Object> map =new HashMap<>();
            map.put("image",imageViews[i]);
            map.put("Item",Item[i]);
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
                R.layout.listview_item,                               //listview模板
                new String[]{"image","Item"},                         //与第二个参数的key名称相同
                new int[]{R.id.ItemImage,R.id.ItemTitle});            //写入的控件id
        listView.setAdapter(simpleAdapter);                       //启动适配器

        final Intent iDeveloper = new Intent(getActivity(),Developer.class);
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
                        startActivity(iDeveloper);
                        break;
                    case 3:
                        Toast.makeText(getActivity(),"第4个",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });



    /*******/
        return view;
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
