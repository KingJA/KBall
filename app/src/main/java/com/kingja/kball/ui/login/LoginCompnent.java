package com.kingja.kball.ui.login;

import com.kingja.kball.injector.annotation.PerActivity;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.injector.module.ActivityModule;

import dagger.Component;

/**
 * 项目名称：
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/13 13:01
 * 修改备注：
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = {ActivityModule.class})
public interface LoginCompnent {
    void inject(LoginActivity activity);
}
