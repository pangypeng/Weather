package com.pangypeng.weather.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

import java.util.Arrays;

/**
 * 作者 pangypeng
 * 时间 2016/6/12 10:36
 * 文件 Weather
 * 描述 该类主要负责索引栏和索引预览字符的显示和隐藏
 *      执行流程：
 *      1、创建该类的实例时，必须传入一个ListView的参数，而且该参数的Adapter必须是实现SectionIndexer接口
 *      2、
 */
public class IndexScroller {
    private float mIndexbarWidth; // 索引栏宽度
    private float mIndexbarMargin; // 索引栏外边距
    private float mPreviewPadding; // 索引栏索引字符预览内边距
    private float mDensity; // 手机屏幕分辨率
    private float mScaleDensity; // 手机字体比例因子
    private float mAlpaaRate; // 索引栏的透明比率
    private int mState = STATE_HIDDEN;// 索引栏当前状态
    private int mListViewWidth;//
    private int mListViewHeight;//
    private int mCurrentSection = -1; // 当前分组索引索引号
    private boolean mIsIndexing  = false; //是否显示分组索引字符
    private ListView mListView;//
    private SectionIndexer mIndexer = null;
    private String[] mSections = null;// 索引列表数组
    private RectF mIndexbarRect; // 索引栏圆角矩形外接矩形

    private static final int STATE_HIDDEN = 0;
    private static final int STATE_SHOWING = 1;
    private static final int STATE_SHOWN = 2;
    private static final int STATE_HIDING = 3;

    public IndexScroller(Context context,ListView listView){
        mDensity = context.getResources().getDisplayMetrics().density;
        mScaleDensity = context.getResources().getDisplayMetrics().scaledDensity;
        mListView = listView;
        setAdapter(mListView.getAdapter());

        mIndexbarWidth = 20*mDensity +0.5f;
        mIndexbarMargin = 10*mDensity +05f;
        mPreviewPadding = 5*mDensity +0.5f;
    }

    /**
     * 绘制
     * @param canvas
     */
    public void draw(Canvas canvas){
        if (mState == STATE_HIDDEN){
            return;
        }
        // 索引栏画笔
        Paint indexbarPaint = new Paint();
        indexbarPaint.setColor(Color.BLACK);// 画笔颜色
        indexbarPaint.setAlpha((int)(64*mAlpaaRate));// 透明度
        indexbarPaint.setAntiAlias(true);// 抗锯齿
        canvas.drawRoundRect(mIndexbarRect,5*mDensity,5*mDensity,indexbarPaint);// 绘制圆角矩形

        if (mSections != null && mSections.length > 0){
            if (mCurrentSection >= 0){
                // 索引栏索引字符预览画笔
                Paint previewPaint = new Paint();
                previewPaint.setColor(Color.BLACK);
                previewPaint.setAlpha(96);
                previewPaint.setAntiAlias(true);
                previewPaint.setShadowLayer(3,0,0,Color.argb(64,0,0,0));

                // 索引栏索引字符预览字母画笔
                Paint previewTextPaint = new Paint();
                previewTextPaint.setColor(Color.WHITE);
                previewTextPaint.setAntiAlias(true);
                previewTextPaint.setTextSize(50*mScaleDensity);

                float previewTextWidth = previewTextPaint.measureText(mSections[mCurrentSection]);
                // 预览视图的大小
                float previewSize = 2 * mPreviewPadding + previewTextPaint.descent() - previewTextPaint.ascent();
                RectF previewRect = new RectF(
                        (mListViewWidth - previewSize)/2,
                        (mListViewHeight - previewSize)/2,
                        (mListViewWidth - previewSize)/2 + previewSize,
                        (mListViewHeight - previewSize)/2 + previewSize
                );
                canvas.drawRoundRect(previewRect,5*mDensity,5*mDensity,previewPaint);
                canvas.drawText(mSections[mCurrentSection],
                        previewRect.left + (previewSize - previewTextWidth)/2-1,
                        previewRect.top + mPreviewPadding - previewTextPaint.ascent()+1,
                        previewTextPaint);
            }
            // 索引栏字符画笔
            Paint indexPaint  = new Paint();
            indexPaint.setColor(Color.WHITE);
            indexPaint.setAlpha((int)(255*mAlpaaRate));
            indexPaint.setAntiAlias(true);
            indexPaint.setTextSize(12*mScaleDensity);
            // 每一个索引字符的高度
            float sectionHeight = (mIndexbarRect.height()-2 * mIndexbarMargin)/mSections.length;
            float paddingTop = (sectionHeight - (indexPaint.descent() - indexPaint.ascent()))/2;
            for (int i=0;i<mSections.length;i++){
                float paddingLeft = (mIndexbarWidth - indexPaint.measureText(mSections[i]))/2;
//                Log.e("IndexScroller", Arrays.toString(mSections));
                canvas.drawText(mSections[i],
                        mIndexbarRect.left + paddingLeft,
                        mIndexbarRect.top + mIndexbarMargin + sectionHeight * i + paddingTop - indexPaint.ascent(),
                        indexPaint
                        );
            }
        }
    }

    /**
     * 触摸监听
     * @param ev
     * @return
     */
    public boolean onTouchEvent(MotionEvent ev){
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (mState != STATE_HIDDEN && contains(ev.getX(),ev.getY())){
                    setState(STATE_SHOWN);
                    mIsIndexing = true;
                    mCurrentSection = getSetionByPontint(ev.getY());
                    mListView.setSelection(mIndexer.getPositionForSection(mCurrentSection));
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsIndexing){
                    if (contains(ev.getX(),ev.getY())){
                        mCurrentSection = getSetionByPontint(ev.getY());
                        mListView.setSelection(mIndexer.getPositionForSection(mCurrentSection));
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mIsIndexing){
                    mIsIndexing =false;
                    mCurrentSection = -1;
                }
                if (mState == STATE_SHOWN){
                    setState(STATE_HIDING);
                }
                break;
        }
        return false;
    }
    /**
     * 组件大小改变时调用
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    public void onSizeChanged(int w,int h,int oldw,int oldh){
        mListViewWidth = w;
        mListViewHeight = h;
        mIndexbarRect = new RectF(w-mIndexbarMargin-mIndexbarWidth,
                mIndexbarMargin,
                w-mIndexbarMargin,
                h-mIndexbarMargin);
    }

    /**
     * 显示索引栏
     */
    public void show(){
        if (mState == STATE_HIDDEN){// 完全隐藏
            setState(STATE_SHOWING); // 开始显示
        }else if (mState == STATE_HIDING){ //
            setState(STATE_HIDING);
        }
    }

    /**
     * 隐藏索引栏
     */
    public void hide(){
        if (mState ==  STATE_SHOWN){ // 完全显示
            setState(STATE_HIDING); // 开始隐藏
        }
    }

    /**
     * 延时消失索引栏
     * @param delay
     */
    public void fade(long delay){
        mHandler.removeMessages(0);
        mHandler.sendEmptyMessageAtTime(0, SystemClock.uptimeMillis()+delay);
    }
    /**
     * 设置适配器
     * @param adapter
     */
    public void setAdapter(Adapter adapter) {
        if ( adapter instanceof SectionIndexer){
            mIndexer = (SectionIndexer) adapter;
            mSections = (String[]) mIndexer.getSections();
        }
    }

    /**
     * 设置索引栏状态
     * @param state
     */
    private void setState(int state){
        if (state < STATE_HIDDEN || state > STATE_HIDING){
            return;
        }
        mState = state;
        switch (mState){
            case STATE_HIDDEN:// 完全隐藏
                mHandler.removeMessages(0);
                break;
            case STATE_SHOWING: // 正在显示
                mAlpaaRate = 0;
                fade(0);
                break;
            case STATE_SHOWN: // 完成显示
                mHandler.removeMessages(0);
                break;
            case STATE_HIDING:// 正在隐藏
                mAlpaaRate = 1;
                fade(3000);
                break;
        }
    }

    /**
     * 触摸点是否在索引栏内
     * @param x
     * @param y
     * @return
     */
    public boolean contains(float x,float y){
        return (x>=mIndexbarRect.left && y>mIndexbarRect.top && y<mIndexbarRect.top + mIndexbarRect.height());
    }

    /**
     * 根据触摸点的Y坐标获得分组索引号
     * @param y
     * @return
     */
    private int getSetionByPontint(float y){
        if (mSections == null || mSections.length == 0){
            return 0;
        }
        if (y < mIndexbarRect.top + mIndexbarMargin){
            return 0;
        }
        if (y >= mIndexbarRect.top + mIndexbarRect.height() - mIndexbarMargin){
            return mSections.length -1;
        }
        return (int)((y-mIndexbarRect.top -mIndexbarMargin)/((mIndexbarRect.height() - 2 * mIndexbarMargin)/mSections.length));
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (mState){
                case STATE_SHOWING:
                    mAlpaaRate += (1-mAlpaaRate) * 0.2;
                    if (mAlpaaRate > 0.9){
                        mAlpaaRate = 1;
                        setState(STATE_SHOWN);
                    }
                    mListView.invalidate();
                    fade(10);
                    break;
                case STATE_SHOWN:
                    setState(STATE_HIDING);
                    break;
                case STATE_HIDING:
                    mAlpaaRate -= mAlpaaRate*0.2;
                    if (mAlpaaRate < 0.1){
                        mAlpaaRate = 0;
                        setState(STATE_HIDDEN);
                    }
                    mListView.invalidate();
                    fade(10);
                    break;
            }
        }
    };
}
