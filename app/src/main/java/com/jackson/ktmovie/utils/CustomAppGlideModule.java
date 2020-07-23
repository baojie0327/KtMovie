package com.jackson.ktmovie.utils;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;


import java.io.InputStream;

/**
 * Created by ${新根} on 2017/6/8.
 * blog ：http://blog.csdn.net/hexingen
 */
@GlideModule
public class CustomAppGlideModule extends AppGlideModule{
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //重新设置内存限制,这里10M
        builder.setMemoryCache(new LruResourceCache(10*1024*1024));
    }


    /**
     * 关闭扫描AndroidManifests.xml
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
