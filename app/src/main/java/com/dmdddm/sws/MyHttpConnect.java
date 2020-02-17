package com.dmdddm.sws;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyHttpConnect {
    private String text;
    private String result = null;

    public String getJson(final String path){
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


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //获得数据 数据不为空 解析数据

        while(true){
            if(text != null){
                parseJson();
                break;
            }
        }
        text = null;

        return result;
    }
    /**解析json**/
    private void parseJson(){
        //Toast.makeText(getActivity(),"parseJson",Toast.LENGTH_SHORT).show();
        try {
            JSONObject jsonObject = new JSONObject(text);

            result = jsonObject.getString("WindSpeed");
            result = result +"CO2:"+ jsonObject.getString("CO2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
