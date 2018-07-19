package com.jackson.ktmovie.utils.glide;

import android.content.Context;
import android.widget.ImageView;

import com.jackson.ktmovie.R;
import com.jackson.ktmovie.utils.GlideApp;


/**
 * Created by ${新根} on 2017/6/8.
 * blog ：http://blog.csdn.net/hexingen
 *
 *  GlideApp无法引用的解决方式：https://github.com/bumptech/glide/issues/1939
 *  在Kotlin编程中无法使用GlideApp的引用（Unresolved reference: GlideApp）,采取曲线救国的方式，java来编写
 */

public class GlideUtils {
    public static void loadUrlImage(Context context, String url, ImageView imageView){
        GlideApp.with(context).asBitmap().load(url).error(R.drawable.default_square_four).placeholder(R.drawable.default_square_four).into(imageView);
      //  GlideApp.with(context).asBitmap().load(url).error(R.drawable.default_square_four).placeholder(R.drawable.default_square_four).into(new CircularBitmapImageViewTarget(context,imageView));
    }
}
