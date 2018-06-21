package com.uurobot.baseframe.dragger.update;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.dragger.ParamTester;
import com.uurobot.baseframe.dragger.Tester;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/20.
 */

@Module
public class DownloadModule {
        private static final String TAG = "DownloadModule";
        private Activity activity;

        public DownloadModule(Activity activity) {
                this.activity = activity;
        }

        @Provides
        @PerActivity
        Tester provideTester() {
                return new Tester();
        }


        // 这里的参数，由其他的被依赖类 注入。。。。。
        @Provides
        @PerActivity
        ParamTester provideParamTester(Context context) {
                int color = context.getResources().getColor(R.color.black);
                Log.e(TAG, "provideParamTester: color === " + color);
                return new ParamTester();
        }

}
