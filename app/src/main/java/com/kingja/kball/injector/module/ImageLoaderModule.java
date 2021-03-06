package com.kingja.kball.injector.module;

import com.kingja.kball.imgaeloader.GlideLoader;
import com.kingja.kball.imgaeloader.IImageLoader;

import dagger.Module;
import dagger.Provides;

/**
 * Description：TODO
 * Create Time：2017/3/9 11:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Module
public class ImageLoaderModule {
    @Provides
    public IImageLoader provideImageLoader() {
        return new GlideLoader();
    }
}
