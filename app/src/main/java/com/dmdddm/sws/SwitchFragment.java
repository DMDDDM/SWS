package com.dmdddm.sws;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class SwitchFragment extends Fragment {

    private Button mFlash;
    private TextView textView;
    private String text = null;
    TextView textView2;


    private String path = "https://www.dmdddm.cn/mini/Index";

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

                //获取数据
                text = new MyHttpConnect().getJson(path);
                textView.setText(text);


            }
        });
        /****/
        return view;
    }



}
