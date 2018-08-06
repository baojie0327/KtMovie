package com.jackson.ktmovie.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jackson.ktmovie.R;
import com.youth.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.default_square_four)
                //  .transform(new GlideCircleTransform(mContext))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        GlideApp.with(context)
                .asBitmap()
                .apply(options)
                .load(path)
                .apply(options)
                .into(imageView);
        //  GlideApp.with(context).asBitmap().load(path).error(R.drawable.default_square_four).placeholder(R.drawable.default_square_four).into(imageView);
        // GlideUtils.loadUrlImage(context,path.toString(),imageView);
    }

}
