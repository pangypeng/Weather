package com.pangypeng.weather;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pangypeng.weather.adapter.FutureListViewAdapter;
import com.pangypeng.weather.adapter.LifeListViewAdapter;
import com.pangypeng.weather.bean.Data;
import com.pangypeng.weather.bean.Result;
import com.pangypeng.weather.http.HttpGetData;
import com.pangypeng.weather.mInterface.GetDataListener;
import com.pangypeng.weather.utils.AirQualityShowView;
import com.pangypeng.weather.utils.ChooseImageWeather;
import com.pangypeng.weather.utils.CopyDatabase;
import com.pangypeng.weather.utils.JsonUtil;
import com.pangypeng.weather.utils.SaveDataToPhone;
import com.pangypeng.weather.utils.SaveOrGetCity;
import com.pangypeng.weather.utils.SaveUpdateTime;
import com.pangypeng.weather.view.ListViewForScrollView;


public class MainActivity extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener,AppBarLayout.OnOffsetChangedListener {

    private String mCityName;// 城市名称
    private String mJsonStr; // Json数据
    private Data mData;// 天气数据

    private HttpGetData mHttpGetData;// 获取Json数据类
    private SaveDataToPhone mSaveDataToPhone;
    private SaveUpdateTime mSaveUpdateTime;
    private SaveOrGetCity mSaveOrGetCity;
    private CopyDatabase mCopyDatabase;
    //------------- 实时天气
    private TextView mTvTemperature; //温度
    private TextView mTvCityName;// 城市名称
    private ImageView mImgWeather; // 天气图片
    private TextView mTvWeather; // 天气
    private TextView mWindDirect; //风向
    private TextView mWindSpeed; // 风速
    private TextView mhumidity; // 湿度

    //-------------- cardview root layout
    private RelativeLayout mRoot;
    private NestedScrollView mNestedScrollView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private CoordinatorLayout mCoordinatorLayout;
    private FloatingActionButton mFloatingActionButton;
    private ListViewForScrollView mFutureListView;
    private ListViewForScrollView mLifeListView;

    private FutureListViewAdapter mFutureListViewAdapter;
    private LifeListViewAdapter mLifeListViewAdapter;
    private boolean isRefreshing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mHttpGetData = HttpGetData.newInstance();
        mSaveDataToPhone = new SaveDataToPhone(this);
        mSaveUpdateTime = new SaveUpdateTime(this);
        mSaveOrGetCity = new SaveOrGetCity(this);
        mCityName = mSaveOrGetCity.getCity();
        if (TextUtils.isEmpty(mCityName)){
            mCityName = "北京";
        }
        mHttpGetData.setCityName(mCityName);

        mCopyDatabase = new CopyDatabase(this);
        mCopyDatabase.copyDatabaseFormRaw();

        // 设置Toolbar title，必须设置在CollapsingToolbarLayout上
        mCollapsingToolbarLayout.setTitle("简单天气");
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        isUpdate();
    }

    /**
     * 初始化布局组件
     */
    private void initView() {
        // ----- 实时天气
        mTvTemperature = (TextView) findViewById(R.id.tv);
        mTvCityName = (TextView) findViewById(R.id.tv_cityname);
        mTvWeather = (TextView) findViewById(R.id.tv_weather);
        mWindDirect = (TextView) findViewById(R.id.tv_direct);
        mWindSpeed = (TextView) findViewById(R.id.tv_speed);
        mImgWeather = (ImageView) findViewById(R.id.img);

        mRoot = (RelativeLayout) findViewById(R.id.root);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark,
                                                    android.R.color.holo_blue_light,
                                                    android.R.color.holo_green_dark);

        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.root_view);
        mLifeListView = (ListViewForScrollView) findViewById(R.id.life_listview);
        mFutureListView = (ListViewForScrollView) findViewById(R.id.future_listview);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this,CityActivity.class);
//                startActivity(intent);
                startActivityForResult(intent,1);
            }
        });
        // 隐藏
        mRoot.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            mCityName = data.getStringExtra("CityName");
            mHttpGetData.setCityName(mCityName);
            mSaveOrGetCity.saveCity(mCityName);
            onRefresh();
        }
    }

    /**
     * 判断是否需要更新数据
     */
    private void isUpdate() {
        if (mSaveUpdateTime.isUpdate()){// 需要更新
            // 先加载sd卡中的数据
            mJsonStr = mSaveDataToPhone.getJsonStr();
            if (!TextUtils.isEmpty(mJsonStr)&& mJsonStr.length() > 100){
                // 解析数据
                mData = JsonUtil.parseJson(mJsonStr).getData();
                // 展示数据
                showView();
            }
            // 更新数据
            getDateFormHttp();
        } else {
            // 先加载sd卡中的数据
            mJsonStr = mSaveDataToPhone.getJsonStr();
            if (!TextUtils.isEmpty(mJsonStr)&& mJsonStr.length() > 100){
                // 解析数据
                mData = JsonUtil.parseJson(mJsonStr).getData();
                // 展示数据
                showView();
            }
        }
    }

    /**
     * 从网络加载数据
     */
    private void getDateFormHttp(){
        isRefreshing = true;
        mHttpGetData.run();
        mHttpGetData.setOnGetDataListener(new GetDataListener() {
            @Override
            public void success() {
                mJsonStr = mHttpGetData.getJsonStr();
//                Log.e("MainActivity",mJsonStr+mJsonStr.length());
                successLink();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void failed() {
                Snackbar.make(mCoordinatorLayout,"请求失败",Snackbar.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    /**
     * 获取请求数据成功
     */
    private void successLink() {
        if (TextUtils.isEmpty(mJsonStr) || mJsonStr.length() < 100){
            // 提示信息
            Snackbar.make(mCoordinatorLayout,"暂时不支持该城市！",Snackbar.LENGTH_SHORT).show();
            return;
        }
        // 保存数据
        mSaveDataToPhone.saveJsonStr(mJsonStr);
        // 保存更新时间
        mSaveUpdateTime.setUpdateTime();
        // 解析数据并显示
        if (!TextUtils.isEmpty(mJsonStr)){
            Result result = JsonUtil.parseJson(mJsonStr);
            mData = result.getData();
            showView();
        }
    }

    /**
     * 展示数据
     */
    private void showView() {
        mFutureListViewAdapter = new FutureListViewAdapter(this,mData.getWeather());
        mLifeListViewAdapter = new LifeListViewAdapter(this,mData.getLife().getInfo());
        // ----- 实时天气
        mTvTemperature.setText(mData.getRealtime().getWeather().getTemperature());
        mTvCityName.setText(mData.getRealtime().getCity_name());
        mTvWeather.setText(mData.getRealtime().getWeather().getInfo());
        mWindDirect.setText(mData.getRealtime().getWind().getDirect());
        mWindSpeed.setText(mData.getRealtime().getWind().getWindspeed());
        mImgWeather.setImageResource(ChooseImageWeather
                            .getImageWeather(mData.getRealtime().getWeather().getImg()));
        // ----- 空气质量
        new AirQualityShowView(this,mData.getPm25());

        // ----- 一周天气
        mFutureListView.setAdapter(mFutureListViewAdapter);
        mFutureListView.setFocusable(false);
        // ------- 生活
        mLifeListView.setAdapter(mLifeListViewAdapter);
        mLifeListView.setFocusable(false);
        // 滚动到顶端
        mNestedScrollView.smoothScrollTo(0,0);
        // 显示布局
        mRoot.setVisibility(View.VISIBLE);
        if (isRefreshing){
            Snackbar.make(mCoordinatorLayout,"更新成功", Snackbar.LENGTH_SHORT).show();
            isRefreshing = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAppBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAppBarLayout.removeOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0){
            mSwipeRefreshLayout.setEnabled(true);
        }else {
            mSwipeRefreshLayout.setEnabled(false);
        }
        Log.e("MainActivity",verticalOffset + "");
        mFloatingActionButton.animate().alpha(verticalOffset/600 + 1);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        getDateFormHttp();
    }


}
