package com.unisrobot.robothead.visualedit.type;

import android.os.Bundle;

import java.util.Set;

/**
 * Created by WEI on 2018/7/2.
 */

public class BundleUtil {
    public static boolean compare(Bundle waitBundle, Bundle msgBundle) {
        boolean result = false;
        if (msgBundle == null) {
            return result;
        }
        int count = 0;
        Set<String> stringSet = waitBundle.keySet();
        for (String key : stringSet) {
            String waitValue = waitBundle.getString(key);
            String msgValue = msgBundle.getString(key);
            if (waitValue.equals(msgValue)) {
                count++;
            }
        }
        if (count == stringSet.size()) {
            result = true;
        }
        return result;
    }
}
