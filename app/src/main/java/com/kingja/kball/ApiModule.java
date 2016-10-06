package com.kingja.kball;

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
    @Provides
    public Api provideDoubanApi() {
        return new Api();
    }
}
