package com.pangypeng.weather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 作者 pangypeng
 * 时间 2016/6/10 10:24
 * 文件 Weather
 * 描述
 */
public class ListViewForScrollView extends ListView {
    public ListViewForScrollView(Context context) {
        super(context);
    }

    public ListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewForScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * MeasureSpec:（spec 为 specification 的缩写，意思为规则、规范、说明书）
     *  测量规则，封装了从父View到子View的布局请求信息。MeasureSpec为一个32位的值，其中高2位是测量模式，
     *  后30位是测量大小
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 创建自定义的测量大小和模式
        // 之所以重新计算测量高度，是因为ListView的item显示的内容数据多少不清楚，为了让每个item将
        // 全部内容显示出来而且不影响item布局样式，
        // 即有弹性的item
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
