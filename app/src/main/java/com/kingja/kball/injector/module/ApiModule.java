package com.kingja.kball.injector.module;

import com.kingja.kball.model.Api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 项目名称：
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/13 9:50
 * 修改备注：
 */
@Module
public class ApiModule {
    @Singleton
    @Provides
    public Api provideApi() {
        return new Api();
    }
}
