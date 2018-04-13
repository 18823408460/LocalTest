package com.unisrobot.comlib.router;

import com.baronzhang.android.router.annotation.router.CombinationUri;
import com.baronzhang.android.router.annotation.router.FullUri;
import com.baronzhang.android.router.annotation.router.IntentExtrasParam;
import com.baronzhang.android.router.annotation.router.UriParam;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/13.
 */

/**
 * 所有activity的路由 在这里 调用（最终通过 注解----隐式调用activity）
 */
public interface RouterService {

//        @FullUri("router://com.unisrobot.firstmodule.firstmudulemainactivity")

        @CombinationUri(scheme = "router", host = "com.unisrobot.firstmodule.firstmudulemainactivity")
        void startFirstModuleMainActivity(@IntentExtrasParam("cityId") String cityId, @IntentExtrasParam("brokerIdList") String test);
}
