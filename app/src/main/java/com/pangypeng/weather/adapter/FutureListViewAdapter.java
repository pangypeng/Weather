package com.pangypeng.weather.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pangypeng.weather.R;
import com.pangypeng.weather.bean.Weather;
import com.pangypeng.weather.utils.ChooseImageWeather;

import java.security.spec.PSSParameterSpec;
import java.util.List;

/**
 * 作者 pangypeng
 * 时间 2016/6/10 17:08
 * 文件 Weather
 * 描述
 */
public class FutureListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Weather> mWeathers;
    public FutureListViewAdapter(Context context, List<Weather> weathers){
        this.mContext = context;
        this.mWeathers = weathers;
    }

    @Override
    public int getCount() {
        return mWeathers.size();
    }

    @Override
    public Object getItem(int position) {
        return mWeathers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = View.inflate(mContext, R.layout.item_future,null);
            viewHolder = new ViewHolder();
            viewHolder.itemData = (TextView) convertView.findViewById(R.id.item_data);
            viewHolder.itemDesc = (TextView) convertView.findViewById(R.id.item_desc);
            viewHolder.itemTem = (TextView) convertView.findViewById(R.id.item_tem);
            viewHolder.itemImg = (ImageView) convertView.findViewById(R.id.item_img);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        switch (position){
            case 0:
                viewHolder.itemData.setText("今天");
                break;
            case 1:
                viewHolder.itemData.setText("明天");
                break;
            default:
                viewHolder.itemData.setText("星期"+mWeathers.get(position).getWeek());
                break;
        }
        viewHolder.itemTem.setText(mWeathers.get(position).getInfo().getNight().get(2)
                +"°-"+mWeathers.get(position).getInfo().getDay().get(2)+"°");

        viewHolder.itemDesc.setText(mWeathers.get(position).getInfo().getDay().get(1)
                +" 最高温度"+mWeathers.get(position).getInfo().getDay().get(2)
                +" "+mWeathers.get(position).getInfo().getDay().get(3)
                +" "+mWeathers.get(position).getInfo().getDay().get(4));
        viewHolder.itemImg.setImageResource(ChooseImageWeather
                .getImageWeather(mWeathers.get(position).getInfo().getDay().get(0)));
        return convertView;
    }

    class ViewHolder{
        TextView itemData;
        TextView itemDesc;
        TextView itemTem;
        ImageView itemImg;
    }
}
