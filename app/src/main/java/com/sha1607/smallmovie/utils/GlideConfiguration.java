package com.sha1607.smallmovie.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Name: GlideConfiguration
 * Author: Kisaten
 * Comment: //TODO
 * Date: 2016-10-31 17:02
 */

public class GlideConfiguration implements GlideModule{

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "image_catch", 150 * 1024 * 1024));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
