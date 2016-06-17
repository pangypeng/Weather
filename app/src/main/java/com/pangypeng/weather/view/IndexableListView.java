package com.pangypeng.weather.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 作者 pangypeng
 * 时间 2016/6/11 8:24
 * 文件 Weather
 * 描述
 */
public class IndexableListView extends ListView {

    private boolean mIsFastScrollEnabled = false;
    private IndexScroller mScroller = null;
    private GestureDetector mGestureDetector = null;

    public IndexableListView(Context context) {
        super(context);
    }

    public IndexableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IndexableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFastScrollEnabled() {
        return mIsFastScrollEnabled;
    }

    @Override
    public void setFastScrollEnabled(boolean enabled) {
        mIsFastScrollEnabled = enabled;
        if (mIsFastScrollEnabled){
            if (mScroller == null){
                mScroller = new IndexScroller(getContext(),this);
            }
        } else {
            if (mScroller != null){
                mScroller.hide();
                mScroller = null;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mScroller != null){
            mScroller.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mScroller!= null && mScroller.onTouchEvent(ev)){
            return true;
        }

        if (mGestureDetector == null){
            mGestureDetector = new GestureDetector(getContext(),new GestureDetector.SimpleOnGestureListener(){
                // 执行了有惯性的滑动，开启快速滑动
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    if (mScroller != null){
                        mScroller.show();
                    }
                    return super.onFling(e1, e2, velocityX, velocityY);
                }
            });
        }
        mGestureDetector.onTouchEvent(ev);
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mScroller.contains(ev.getX(),ev.getY())){
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mScroller!= null){
            mScroller.onSizeChanged(w,h,oldw,oldh);
        }
    }
}
