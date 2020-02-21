package com.dmdddm.sws;


import android.os.Handler;
import android.os.Message;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyHttpConnect {
    private String text;
    private String path;
    private
    String[] result={"","",""};

    /**获取数据
     * path 为要访问的url 地址
     * item[] 为要接要拿出来的数据的key
     * Handler 对象 用来实现多线程通信
     * 时间**/
    public String[] getJson(String path1 ,final String[] item, final Handler handler){
        path = path1;
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
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        String inputLine;
                        StringBuffer resultData = new StringBuffer();
                        while ((inputLine = bufferedReader.readLine()) != null) {
                            resultData.append(inputLine);
                        }
                        //获取json结果
                        text = resultData.toString();;
                        parseJson(item);
                    }
                    reader.close();
                    inputStream.close();
                    conn.disconnect();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                /**Message 使用**/
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = result;
                handler.sendMessage(message);
            }
        }).start();
        return result;
    }

    /**解析json***/
    public String[] parseJson(String[] item){
        //Toast.makeText(getActivity(),"parseJson",Toast.LENGTH_SHORT).show();

        try {
            JSONObject jsonObject = new JSONObject(text);

            for (int i =0;i<item.length;i++){
                 result[i]=jsonObject.getString(item[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
