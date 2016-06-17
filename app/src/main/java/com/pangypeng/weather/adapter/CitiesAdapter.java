package com.pangypeng.weather.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;

/**
 * 作者 pangypeng
 * 时间 2016/6/15 9:20
 * 文件 Weather
 * 描述
 */
public class CitiesAdapter extends ArrayAdapter implements SectionIndexer {
    public CitiesAdapter(Context context, int resource) {
        super(context, resource);
    }

    public CitiesAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public CitiesAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }
}
