package com.dmdddm.sws;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyConnection {
    /**在request对象中添加参数的方法**/
    /**变量**/
    URL url;


    public MyConnection(String StringUrl , final String[] PropertyName, final String[] Property,final Handler handler) {
        try {
            url = new URL(StringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //新建进程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    //链接方式为post
                    conn.setRequestMethod("POST");
                    //链接文件头
                    conn.setRequestProperty("Content-Type","plain/text; charset=UTF-8");
                    //超时时间设置
                    conn.setConnectTimeout(5000);
                    //使用缓存
                    conn.setUseCaches(true);
                    //加入参数
                    for (int i=0;i<PropertyName.length;i++){
                        conn.setRequestProperty(PropertyName[i],Property[i]);
                    }
                    //开启链接
                    conn.connect();
                    //获取数据
                    InputStream inputStream = conn.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
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



                    }
                    reader.close();
                    inputStream.close();
                    conn.disconnect();

                    /**Message 使用**/
                    Message message = handler.obtainMessage();
                    message.what = 2;
                    message.obj = "";
                    handler.sendMessage(message);





                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
