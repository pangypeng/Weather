package com.pangypeng.weather.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 作者 pangypeng
 * 时间 2016/6/9 17:55
 * 文件 Weather
 * 描述
 */
public class SaveDataToPhone {
    private static String TAG = "SaveDataToPhone";
    private Context mContext;
    public SaveDataToPhone(Context context){
        this.mContext = context;
    }

    /**
     * 将Json数据保存到手机
     * @param data
     */
    public void saveJsonStr(String data){
        String filePaht = null;
        // 判断SDCard是否挂载
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

        if (hasSDCard){
            filePaht = Environment.getExternalStorageDirectory().toString()+"/weather.txt";
            Log.e(TAG,filePaht);
        } else {
//            mContext.getCacheDir()
            filePaht = Environment.getDownloadCacheDirectory().toString()+"/weather.txt";
            Log.e(TAG,filePaht);
        }
        try {
            File file = new File(filePaht);
            if (!file.exists()){
                File dir = new File(file.getParent());
                dir.mkdir();
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            out.write(data.getBytes());
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 从手机获取Json数据
     * @return
     */
    public String getJsonStr(){
        String filePath = null;
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (hasSDCard){
            filePath = Environment.getExternalStorageDirectory().toString() +"/weather.txt";
        } else {
            filePath = Environment.getDownloadCacheDirectory().toString()+"/weather.txt";
        }
        try{
            File file = new File(filePath);
            // 文件不存在
            if (!file.exists()){
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(b))!= -1){
                byteArrayOutputStream.write(b,0,len);
            }
            String jsonStr = byteArrayOutputStream.toString();
            byteArrayOutputStream.close();
            fileInputStream.close();
            return jsonStr;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
