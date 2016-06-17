package com.pangypeng.weather;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;

import com.pangypeng.weather.sql.SelectedCity;
import com.pangypeng.weather.utils.StringMatcher;
import com.pangypeng.weather.view.IndexableListView;

import java.util.List;

/**
 * 作者 pangypeng
 * 时间 2016/6/15 9:07
 * 文件 Weather
 * 描述
 */
public class CityActivity extends AppCompatActivity {
    private IndexableListView mIndexableListView;
    private List<String> mCities;
    private List<String> mPinYinCities;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_city);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setTitle("城市");
//        setSupportActionBar(toolbar);

        mIndexableListView = (IndexableListView) findViewById(R.id.indexableListView);
        mCities = SelectedCity.queryCitiesByCity(this);
        mPinYinCities = SelectedCity.queryCitiesByPinyin(this);
        CitiesAdapter citiesAdapter = new CitiesAdapter(this, android.R.layout.simple_list_item_1,mCities);
        mIndexableListView.setAdapter(citiesAdapter);
        mIndexableListView.setFastScrollEnabled(true);

        mIndexableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO
                Intent intent = getIntent();
                String cityName = mCities.get(position);
                intent.putExtra("CityName",cityName);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    public class CitiesAdapter extends ArrayAdapter implements SectionIndexer{
        // 索引栏字符
        private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        public CitiesAdapter(Context context, int resource,List<String> cities) {
            super(context, resource,cities);
        }

        /**
         * 返回一个数组，封装了索引栏的字符分组
         * @return
         */
        @Override
        public Object[] getSections() {
            String[] sections = new String[mSections.length()];
            for (int i=0;i<mSections.length();i++){
                sections[i] = String.valueOf(mSections.charAt(i));
            }
//            Log.e("CityActivity", Arrays.toString(sections));
            return sections;
        }

        /**
         * 通过索引栏分组字符定位到当前字符开头的第一个位置
         * @param sectionIndex 分组索引
         * @return
         */
        @Override
        public int getPositionForSection(int sectionIndex) {

            for (int j=0;j<getCount();j++){// 遍历列表
                if (sectionIndex==0){// 分组索引为0，对应 #
                    // 查询数字
                    for (int k=0;k<=9;k++){
                        String value = String.valueOf(((String) getItem(j)).charAt(0));
                        String keyword = String.valueOf(k);
                        if (StringMatcher.match(value,keyword)){
                            return j;
                        }
                    }
                } else {
                    String value = String.valueOf(mPinYinCities.get(j).charAt(0));
                    String keyword = String.valueOf(mSections.charAt(sectionIndex));
                    if (StringMatcher.match(value,keyword)){
                        return j;
                    }
                }
            }

            return 0;
        }

        /**
         * 通知列表位置定位索引栏分组字符
         * @param position
         * @return
         */
        @Override
        public int getSectionForPosition(int position) {
            return 0;
        }
    }
}
