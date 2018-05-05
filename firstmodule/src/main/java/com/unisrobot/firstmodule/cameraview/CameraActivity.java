package com.unisrobot.firstmodule.cameraview;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;

import com.unisrobot.firstmodule.R;

import java.io.IOException;

/**
 * Created by Administrator on 2018/5/5.
 */

public class CameraActivity extends AppCompatActivity {
        private static final String TAG = "CameraActivity";
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first_module_activity_camera);

                final CameraSurface cameraSurface = findViewById(R.id.camerasurface);
                SurfaceHolder holder = cameraSurface.getHolder();
                holder.addCallback(new SurfaceHolder.Callback() {
                        @Override
                        public void surfaceCreated(SurfaceHolder holder) {
                                Log.e(TAG, "surfaceCreated: ");
                                cameraSurface.setWillNotDraw(false);
                        }

                        @Override
                        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                                Log.e(TAG, "surfaceChanged: " );
                                init(holder);
                        }

                        @Override
                        public void surfaceDestroyed(SurfaceHolder holder) {

                        }
                });

        }

        private void init(SurfaceHolder holder){
                Camera camera = Camera.open();
                try {
                        camera.setPreviewDisplay(holder);
                        camera.startPreview();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}
