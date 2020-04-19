package com.dmdddm.sws;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class IndexFragment extends Fragment {
    private Button Manual;
    private Button Auto;
    private boolean isAuto = false;
    private boolean isManul = false;


    public IndexFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final View view = inflater.inflate(R.layout.fragment_index, container, false);
        Manual = view.findViewById(R.id.Manual);
        Auto = view.findViewById(R.id.Auto);
        //手动模式点击事件
        Manual.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                if (!isManul){
                    MyDialog("手动模式");
                   //自动模式 变色
                    GradientDrawable gd = (GradientDrawable)Manual.getBackground();
                    gd.setColor(R.color.gray);
                    if (isAuto) {
                        GradientDrawable gd2 = (GradientDrawable)Auto.getBackground();
                        gd2.setColor(R.color.purple);

                    }
                    isManul = true;
                    isAuto = false;
                }
                //Toast.makeText(getActivity(),"手动模式已启动",Toast.LENGTH_LONG).show();
            }
        });
        //智能模式点击事件
        Auto.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (!isAuto){
                    MyDialog("自动模式");

                    //智能模式 变色
                    GradientDrawable gd = (GradientDrawable)Auto.getBackground();
                    gd.setColor(R.color.gray);
                    if (isManul) {
                        GradientDrawable gd2 = (GradientDrawable)Manual.getBackground();
                        gd2.setColor(R.color.purple);

                    }
                    isAuto = true;
                    isManul = false;
                }
                //Toast.makeText(getActivity(),"自动模式已启动",Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }

    public void MyDialog(String mode){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //设置Title图标
        builder.setIcon(R.drawable.default_avatar);

        builder.setTitle("转换模式成功");
        builder.setMessage("当前模式为:"+mode);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }

}
