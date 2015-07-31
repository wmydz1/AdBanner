# AdBanner
![Alt text](http://7xir7h.com1.z0.glb.clouddn.com/banner.png)


xml
```


   <com.logoocc.adbanner.views.Adbanner
       android:id="@+id/main_banner"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"/>




```
java code

```
        mybanner = (Adbanner) findViewById(R.id.main_banner);
        String[] strs ={"xx1","xx2","xxx3","xxx4"};
        int[] imgs ={R.mipmap.yu1,R.mipmap.yu2,R.mipmap.yu4,R.mipmap.yu6};
        mybanner.setBannerData(strs,imgs);
        mybanner.startbanner();


```
