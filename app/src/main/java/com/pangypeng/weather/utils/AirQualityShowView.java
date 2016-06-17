package com.pangypeng.weather.utils;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.pangypeng.weather.MainActivity;
import com.pangypeng.weather.R;
import com.pangypeng.weather.bean.PM25;

/**
 * 作者 pangypeng
 * 时间 2016/6/10 19:40
 * 文件 Weather
 * 描述
 */
public class AirQualityShowView {

    private PM25 mPm25;
    private int mColor;
    // ------pm25
    private TextView mPmAirQuality,mPmAirDesc,mPmAirSum,mPmAir25,mPmAir10,mPmAirText;
    private ProgressBar mPbAir25,mPbAir10;
    public AirQualityShowView(MainActivity context, PM25 pm25){
        this.mPm25 = pm25;

        mPmAirQuality = (TextView) context.findViewById(R.id.air_quality);// 空气质量
        mPmAirDesc = (TextView) context.findViewById(R.id.air_dec);// 空气描述
        mPmAirSum = (TextView) context.findViewById(R.id.air_dec_sum);// 空气指数
        mPmAir25 = (TextView) context.findViewById(R.id.air_tv_25);
        mPmAir10 = (TextView) context.findViewById(R.id.air_tv_10);
        mPmAirText = (TextView) context.findViewById(R.id.air_tv_text);
        mPbAir25 = (ProgressBar) context.findViewById(R.id.air_progressBar25);
        mPbAir10 = (ProgressBar) context.findViewById(R.id.air_progressBar10);
        if (pm25.getPm25().getCurPm() != ""){
            mColor = ChooseAirQualityColor.getColor(pm25.getPm25().getCurPm());
            setColorView();
        } else {
         showView();
        }

    }

    private void showView() {
        mPmAirDesc.setText("暂无");
        mPmAirSum.setText("");
        mPmAir25.setText("0");
        mPmAir10.setText("0");
        mPbAir25.setProgress(0);
        mPbAir10.setProgress(0);
        mPmAirText.setText("");
    }

    private void setColorView() {
        mPmAirQuality.setTextColor(mColor);
        mPmAirDesc.setTextColor(mColor);
        mPmAirDesc.setText(mPm25.getPm25().getQuality());
        mPmAirSum.setTextColor(mColor);
        mPmAirSum.setText(mPm25.getPm25().getCurPm());
        mPmAir25.setText(mPm25.getPm25().getPm25());
        mPmAir10.setText(mPm25.getPm25().getPm10());
        mPmAirText.setText(mPm25.getPm25().getDes());
        mPbAir25.setProgress(Integer.parseInt(mPm25.getPm25().getPm25()));
        mPbAir10.setProgress(Integer.parseInt(mPm25.getPm25().getPm10()));
    }

}
