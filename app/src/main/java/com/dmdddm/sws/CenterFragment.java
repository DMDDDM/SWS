package com.dmdddm.sws;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
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

import static com.dmdddm.sws.VerifyStoragePermissions.verifyStoragePermissions;


public class CenterFragment extends Fragment{

    //声明控件
    private ImageView userImage;
    private TextView userName;
    private ListView listView;
    private List<Map<String,Object>> lists;
    private Boolean isLogin= false;
    private String Tname = "";

    public CenterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2){
            Tname = data.getStringExtra("UserName");
            userName.setText(Tname);
            isLogin = true;



        }
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
                if(!isLogin) {
                    startActivityForResult(login, 1);
                }
            }
        });

        /**listView填充**/

        String[] Item = {"设置","更改密码",/*"开发者模式",*/"退出账户"};
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("username",Tname);

                Intent intent = new Intent(getActivity(),ChangePassword.class);
                intent.putExtras(bundle);

                switch (i){
                    case 0:

                       // verifyStoragePermissions(getActivity());
                        Toast.makeText(getActivity(),"暂未开放",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        //Toast.makeText(getActivity(),"第2个",Toast.LENGTH_SHORT).show();
                        //修改密码
                        if (isLogin){
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                        }
                        break;
                   /* case 2:
                        startActivity(iDeveloper);
                        break;*/
                    case 2:
                        //退出账户
                        //弹出对话框 点击确定后 清除数据 并退出账户
                        if(isLogin){
                            userName.setText("点击登录");
                            isLogin = false;
                        }
                        else{
                            Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });



    /*******/
        return view;
    }

}
