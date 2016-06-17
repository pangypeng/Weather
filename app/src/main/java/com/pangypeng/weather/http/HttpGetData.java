package com.pangypeng.weather.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.pangypeng.weather.mInterface.GetDataListener;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 作者 pangypeng
 * 时间 2016/6/9 17:16
 * 文件 Weather
 * 描述
 */
public class HttpGetData {
    private String mCityName;// 城市名称
    private String mJsonStr;// json数据
    private OkHttpClient mOkHttpClient;
    private Gson mGson;
    private GetDataListener mListener;
    private static int FAILED = 0;
    private static int SUCCESS = 1;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    if (mListener != null){
                        mListener.failed();
                    }
                    break;
                case 1:
                    mJsonStr = (String)msg.obj;
                    if (mListener != null) {
                        mListener.success();
                    }
                    break;
            }
        }
    };

    private static HttpGetData mInstance;
    private HttpGetData(){
        mOkHttpClient = new OkHttpClient();
        mGson = new Gson();
    }

    /**
     * 单例设计模式
     * @return
     */
    public static HttpGetData newInstance(){
        if (mInstance == null){
            mInstance = new HttpGetData();
        }
        return mInstance;
    }

    /**
     * 设置城市名称
     * @param cityName
     */
    public void setCityName(String cityName){
        this.mCityName = cityName;
    }

    /**
     * 设置回调函数
     * @param listener
     */
    public void setOnGetDataListener(GetDataListener listener){
        this.mListener = listener;
    }

    /**
     * 获取Json数据
     * @return
     */
    public String getJsonStr(){
        return this.mJsonStr;
    }

    /**
     * 加载数据
     */
    public void run(){
        String urlPath = "http://op.juhe.cn/onebox/weather/query?cityname="+mCityName+"&key=8cec263e8c75a544c26f060efe406805";
        Request request = new Request.Builder()
                .url(urlPath)
                .build();
        // 在子线程中加载数据后返回主线程
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mHandler.sendEmptyMessage(FAILED);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String jsonData = response.body().string();
                Message msg = mHandler.obtainMessage();
                msg.what = SUCCESS;
                msg.obj = jsonData;
                mHandler.sendMessage(msg);
            }
        });

//        getDate();
    }

    public void getDate(){
        final String urlPath = "http://op.juhe.cn/onebox/weather/query?cityname="
                + URLEncoder.encode(mCityName)+"&key=8cec263e8c75a544c26f060efe406805";
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(urlPath);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.connect();
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        InputStream in = conn.getInputStream();
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        byte[] b = new byte[1024];
                        int len = 0;
                        while ((len = in.read(b))!=-1){
                            out.write(b,0,len);
                        }
                        String jsonData = new String(out.toByteArray());
                        Log.e("HttpGetData",jsonData);
                        out.close();
                        in.close();
                        Message msg = mHandler.obtainMessage();
                        msg.what = SUCCESS;
                        msg.obj = jsonData;
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
