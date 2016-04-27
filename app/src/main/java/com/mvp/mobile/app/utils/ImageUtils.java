package com.mvp.mobile.app.utils;

import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by kirijin on 2016/4/20.
 */
public class ImageUtils {

    //ImageLoader简单封装
    static public void loadImageView(ImageView imageView, String url){
        if(TextUtils.isEmpty(url)){ return; }
        if(imageView instanceof SimpleDraweeView){
            SimpleDraweeView draweeView = (SimpleDraweeView) imageView;
            draweeView.setImageURI(Uri.parse(url));
        }
    }

}
