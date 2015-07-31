package com.logoocc.adbanner.views;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.logoocc.adbanner.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by samchen on 7/31/15.
 */
public class Adbanner extends RelativeLayout {

    private MyViewPagerView vp_banner;
    private TextView tv_banner;
    private LinearLayout ll_banner;
    private AdAdapter adAdapter;
    private boolean isRemove;
    private Handler handle = new Handler();


    public Adbanner(Context context) {

        super(context);
        init(null);


    }

    public Adbanner(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs);


    }

    public Adbanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }


    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_banner, this);
        // 初始化控件
        vp_banner = (MyViewPagerView) findViewById(R.id.banner_vp);
        ll_banner = (LinearLayout) findViewById(R.id.banner_dian);
        tv_banner = (TextView) findViewById(R.id.banner_text);

    }


    private ViewPager.OnPageChangeListener pageLis = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            if (adAdapter != null && ll_banner != null) {
                for (int i = 0; i < ll_banner.getChildCount(); i++) {
                    if (position == i) {
                        ll_banner.getChildAt(i).setEnabled(true);
                    } else {
                        ll_banner.getChildAt(i).setEnabled(false);
                    }
                }
                tv_banner.setText(adAdapter.getDescribe(position));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == 0 && isRemove) {
                isRemove = false;
                handle.postDelayed(r, 3000);

            } else if (state == 1) {
                handle.removeCallbacks(r);
                isRemove = true;
            }
        }
    };

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            int page = vp_banner.getCurrentItem();
            if (page > adAdapter.getCount() - 2) {
                page = 0;
            } else {
                page += 1;
            }
            vp_banner.setCurrentItem(page);
            handle.postDelayed(r, 3000);
        }
    };

    private void createDian() {
        LinearLayout.MarginLayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int margin = getResources().getDimensionPixelSize(R.dimen.home_header_id_margin);
        lp.leftMargin = margin;
        lp.rightMargin = margin;
        for (int i = 0; i < adAdapter.getCount(); i++) {
            ImageView img = new ImageView(getContext());
            img.setImageResource(R.drawable.selector_home_header_dian);
            if (i == 0) {
                img.setEnabled(true);
            } else {
                img.setEnabled(false);
            }
            ll_banner.addView(img, lp);

        }

    }

    class AdAdapter extends PagerAdapter {
        private List<Map<String, Object>> data;

        public AdAdapter(List<Map<String, Object>> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data != null ? data.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView((View) data.get(position).get("view"));
            return data.get(position).get("view");
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public String getDescribe(int position) {
            return (String) data.get(position).get("describe");
        }
    }


    private ViewPager.LayoutParams lp;

    private View getVpView(int resId) {
        ImageView v = new ImageView(getContext());
        v.setScaleType(ImageView.ScaleType.FIT_XY);
        v.setImageResource(resId);
        if (lp == null) {
            //代码添加layout_width、、、layout_height充满
            lp = new ViewPager.LayoutParams();
            lp.width = ViewPager.LayoutParams.MATCH_PARENT;
            lp.height = ViewPager.LayoutParams.MATCH_PARENT;
        }
        v.setLayoutParams(lp);
        return v;
    }



    public void setBannerData(String[] bannerstr,int[] bannerimg){

        ArrayList<Map<String, Object>> bannerdata = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();


        for (int i = 0; i <bannerstr.length ; i++) {

            if (bannerstr.length ==bannerimg.length){
                map = new HashMap<String, Object>();
                map.put("describe", bannerstr[i]);
                map.put("view", getVpView(bannerimg[i]));
                bannerdata.add(map);

            }
        }

        if (bannerdata != null) {
            adAdapter = new AdAdapter(bannerdata);
        }
        if (adAdapter != null) {
            vp_banner.setAdapter(adAdapter);
        }
        createDian();
        vp_banner.setOnPageChangeListener(pageLis);



    }


    public void startbanner() {
        if (vp_banner != null) {
            handle.postDelayed(r, 3000);
        }

    }

}
