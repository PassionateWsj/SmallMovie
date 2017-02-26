package com.sha1607.smallmovie.recommend.widget;

import android.content.Context;
import android.widget.ImageView;

import com.sha1607.smallmovie.utils.MyImageLoader;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by DiaoGe on 2016/10/26.
 */
public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
//        Glide.with(context)
//                .load(path.toString())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageView);
        MyImageLoader.with(context,path.toString(),imageView, MyImageLoader.LoaderEnum.GLIDE);
    }
}
