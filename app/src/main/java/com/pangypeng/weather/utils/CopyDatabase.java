package com.pangypeng.weather.utils;

import android.content.Context;
import android.util.Log;

import com.pangypeng.weather.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
/**
 * 作者 pangypeng
 * 时间 2016/6/10 21:19
 * 文件 Weather
 * 描述
 */
public class CopyDatabase {
    private Context mContext;
    public CopyDatabase(Context context){
        mContext = context;
    }

    /**
     * 复制数据库
     */
    public void copyDatabaseFormRaw() {
        String path = mContext.getDatabasePath("cities.db").getAbsolutePath();
//        String path = "data/data/"+mContext.getPackageName()+"/databases/cities.db";
        Log.d("CopyDatabase",path);
        ///data/data/com.pangypeng.weather/databases/cities.db
        try{
            File file = new File(path);
            if (!file.exists()){
                File dir = new File(file.getParent());
                dir.mkdir();
                file.createNewFile();
            } else {
                return;
            }
            InputStream in = mContext.getResources().openRawResource(R.raw.cities);
            FileOutputStream out = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.close();
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
