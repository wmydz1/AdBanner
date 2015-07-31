package com.logoocc.adbanner;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.logoocc.adbanner.views.Adbanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    private Adbanner mybanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mybanner = (Adbanner) findViewById(R.id.main_banner);
        String[] strs ={"xx1","xx2","xxx3","xxx4"};
        int[] imgs ={R.mipmap.yu1,R.mipmap.yu2,R.mipmap.yu4,R.mipmap.yu6};
        mybanner.setBannerData(strs,imgs);
        mybanner.startbanner();

//        mybanner.setData(getViewPagerData());
//        mybanner.startbanner();


    }







}
