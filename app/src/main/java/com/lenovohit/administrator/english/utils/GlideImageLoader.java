package com.lenovohit.administrator.english.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yyydjk.library.BannerLayout;



/**
 * Created by Administrator on 2017/4/13.
 */


    public class GlideImageLoader implements BannerLayout.ImageLoader {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).centerCrop().into(imageView);
        }

}