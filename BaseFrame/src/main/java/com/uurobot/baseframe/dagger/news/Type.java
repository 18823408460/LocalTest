package com.uurobot.baseframe.dagger.news;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Administrator on 2018/6/21.
 */
@Documented
@Qualifier // 标识父，区分不同的 生产对象。

@Retention(RetentionPolicy.RUNTIME)
public @interface Type {
        String value() default "";
}
