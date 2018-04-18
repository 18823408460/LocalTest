package com.unisrobot.comlib.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.security.Permission;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/18.
 */

public class PermissionUtil {

        private static int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 0;

        public static boolean checkLocationPermission(Context context) {
                ArrayList<String> arrayList = new ArrayList<>();
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        arrayList.add(Manifest.permission.ACCESS_FINE_LOCATION);
                }

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        arrayList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
                }
                if (arrayList.size() > 0) {
                        ActivityCompat.requestPermissions((Activity) context, arrayList.toArray(new String[arrayList.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                        return false;
                }
                return true;
        }
}
