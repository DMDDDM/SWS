package com.dmdddm.sws;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class IndexFragment extends Fragment {
    private Button Manual;
    private Button Auto;

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


        View view = inflater.inflate(R.layout.fragment_index, container, false);
        Manual = view.findViewById(R.id.Manual);
        Auto = view.findViewById(R.id.Auto);
        //手动模式点击事件
        Manual.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"手动模式已启动",Toast.LENGTH_LONG).show();
            }
        });
        //智能模式点击事件
        Auto.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"自动模式已启动",Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
