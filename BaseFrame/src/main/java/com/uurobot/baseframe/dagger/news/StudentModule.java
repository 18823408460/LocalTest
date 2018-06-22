package com.uurobot.baseframe.dagger.news;

import android.util.Log;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/21.
 */

/**
 * // 有参构造函数
 // 标明该Module必须初始化
 // 如果未初始化，编译时不会报错，
 // 但,在运行时报错“IllegalStateException”，提示初始化该Module

 如果module 是无参数， 则可以省略
 */
@Module
public class StudentModule {
        private static final String TAG = "StudentModule";


        @Provides
        public StudentBean provideStudentBena(){
                Log.e(TAG, "provideStudentBena: ");
                return  new StudentBean();
        }
}
