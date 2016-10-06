package com.kingja.kball.app;


import com.kingja.kball.Api;
import com.kingja.kball.ApiModule;

import dagger.Component;

/**
 * 项目名称：
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/13 9:42
 * 修改备注：
 */
@Component(modules = {ApiModule.class})
public interface AppComponent {
    Api getDoubanApi();
}
