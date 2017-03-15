package com.kingja.kball.ui.answer;

import com.kingja.kball.injector.annotation.PerActivity;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.ui.publish.PublishActivity;

import dagger.Component;

/**
 * 项目名称：
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/13 13:01
 * 修改备注：
 */
@PerActivity
@Component(dependencies = AppComponent.class)
public interface AnswerCompnent {
    void inject(AnswerActivity activity);
}
