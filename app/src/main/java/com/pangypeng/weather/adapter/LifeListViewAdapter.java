package com.pangypeng.weather.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pangypeng.weather.R;
import com.pangypeng.weather.bean.Life;

/**
 * 作者 pangypeng
 * 时间 2016/6/10 17:09
 * 文件 Weather
 * 描述
 */
public class LifeListViewAdapter extends BaseAdapter {
    private Context mContext;
    private Life.Info mInfo;
    public LifeListViewAdapter(Context context, Life.Info info){
        this.mContext = context;
        this.mInfo = info;
    }
    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return mInfo.getList(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = View.inflate(mContext, R.layout.item_life,null);
            viewHolder = new ViewHolder();
            viewHolder.itemImg = (ImageView) convertView.findViewById(R.id.item_life_img);
            viewHolder.itemSum = (TextView) convertView.findViewById(R.id.item_life_sum);
            viewHolder.itemLife = (TextView) convertView.findViewById(R.id.item_life_suggest);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.itemImg.setImageResource(mInfo.getLifeImg(position));
        viewHolder.itemSum.setText(mInfo.getLife(position)+mInfo.getList(position).get(0));
        viewHolder.itemLife.setText(mInfo.getList(position).get(1));
        return convertView;
    }

    class ViewHolder{
        ImageView itemImg;
        TextView itemSum,itemLife;
    }
}
