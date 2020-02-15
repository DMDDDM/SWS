package com.dmdddm.sws;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SwitchFragment extends Fragment {

    private Button mFlash;
    private TextView textView;
    private String text;
    private String result="";

    private String path = "https://www.dmdddm.cn/mini/Index2";

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

        mFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"1",Toast.LENGTH_SHORT).show();
                getJson();

               // Toast.makeText(getActivity(),"2",Toast.LENGTH_SHORT).show();
                parseJson();
            }
        });



        /****/
        return view;
    }

    public void getJson(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    //链接属性
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setUseCaches(true);

                    //开启链接
                    conn.connect();
                    //获取数据
                    InputStream inputStream = conn.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream);

                    //解析
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    //状态码为200,链接成功

                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK){

                        String inputLine;
                        StringBuffer resultData = new StringBuffer();

                        while((inputLine = bufferedReader.readLine()) !=null){
                            resultData.append(inputLine);
                        }
                        //获取json结果
                        text = resultData.toString();
                    }
                    reader.close();
                    inputStream.close();
                    conn.disconnect();


                    //调用解析json函数

                } catch (Exception e) {
                    e.printStackTrace();
                    //textView.setText(e.toString());
                }
            }
        }).start();
    }
    /**解析json**/
    public void parseJson(){
        //Toast.makeText(getActivity(),"parseJson",Toast.LENGTH_SHORT).show();
        try {
            JSONObject jsonObject = new JSONObject(text);

            result = jsonObject.getString("WindSpeed");
            result = result +"CO2:"+ jsonObject.getString("CO2");
            textView.setText(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
