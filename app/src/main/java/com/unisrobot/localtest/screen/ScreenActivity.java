package com.unisrobot.localtest.screen;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import com.unisrobot.comlib.wifi.LinuxCmd;

/**
 * Created by Administrator on 2018/4/19.
 */

public class ScreenActivity extends AppCompatActivity {
        private static final String TAG = "ScreenActivity";

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                getDisplayInfomation();
                getDensity();
                getScreenSizeOfDevice();
                getScreenSizeOfDevice2();

                LinuxCmd.execCmd("i2cdetect -l");
        }

        private void getDisplayInfomation() {
                Point point = new Point();
                getWindowManager().getDefaultDisplay().getSize(point);
                Log.d(TAG, "the screen size is " + point.toString());
                getWindowManager().getDefaultDisplay().getRealSize(point);
                Log.d(TAG, "the screen real size is " + point.toString());
        }

        private void getDensity() {
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                Log.d(TAG, "Density is " + displayMetrics.density + " densityDpi is " + displayMetrics.densityDpi + " height: " + displayMetrics.heightPixels +
                        " width: " + displayMetrics.widthPixels);
        }

        private void getScreenSizeOfDevice() {
                DisplayMetrics dm = getResources().getDisplayMetrics();
                int width = dm.widthPixels;
                int height = dm.heightPixels;
                double x = Math.pow(width, 2);
                double y = Math.pow(height, 2);
                double diagonal = Math.sqrt(x + y);

                int dens = dm.densityDpi;
                double screenInches = diagonal / (double) dens;
                Log.d(TAG, "The screenInches " + screenInches);
        }

        private void getScreenSizeOfDevice2() {
                Point point = new Point();
                getWindowManager().getDefaultDisplay().getRealSize(point);
                DisplayMetrics dm = getResources().getDisplayMetrics();
                double x = Math.pow(point.x / dm.xdpi, 2);
                double y = Math.pow(point.y / dm.ydpi, 2);
                double screenInches = Math.sqrt(x + y);
                Log.d(TAG, "Screen inches : " + screenInches);
        }

        //pixel = dip*density;
        private int convertDpToPixel(int dp) {
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                return (int) (dp * displayMetrics.density);
        }

        private int convertPixelToDp(int pixel) {
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                return (int) (pixel / displayMetrics.density);
        }
}
