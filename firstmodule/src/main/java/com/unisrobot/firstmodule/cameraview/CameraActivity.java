package com.unisrobot.firstmodule.cameraview;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;

import com.unisrobot.firstmodule.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/5/5.
 */
// 类SurfaceHolder,可以把它当成surface的控制器，用来操纵surface
public class CameraActivity extends AppCompatActivity {
        private static final String TAG = "CameraActivity";
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first_module_activity_camera);

                final CameraSurface cameraSurface = findViewById(R.id.camerasurface);
                SurfaceHolder holder = cameraSurface.getHolder();
                new Manager().doWork();
                holder.addCallback(new SurfaceHolder.Callback() {
                        @Override
                        public void surfaceCreated(SurfaceHolder holder) {
                                Log.e(TAG, "surfaceCreated: ");
                                //cameraSurface.setWillNotDraw(false);
                        }

                        @Override
                        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                                Log.e(TAG, "surfaceChanged: " );
                                init(holder);
                                cameraSurface.startDraw(holder);
                        }

                        @Override
                        public void surfaceDestroyed(SurfaceHolder holder) {

                        }
                });

        }

        /**
         * i = i++;//等效于下面的语句：
         *
         int temp = i;//这个temp就是i++这个表达式的值
         i++; //i自增
         i = temp;//最终，将表达式的值赋值给i
         这是java里的实现，当然在其他的语言如c或是c++中可能并不是这么处理的，每种语言都有各自的理由去做相应的处理。
         */
        private  int m_nResult;
        private class Manager{

                int temp = 0 ;
                public Manager(){
                        m_nResult = 0;
                }
                public void doWork(){
                        m_nResult = m_nResult++ ;
                        temp = m_nResult;
                        Log.e(TAG, "doWork: m_nResult  = " +m_nResult );
                        Log.e(TAG, "doWork: temp  = " +temp );
                }
        }
        private boolean istop =  false;
        private static class MyThread extends Thread{
                private CameraActivity activity ;
                public MyThread(CameraActivity activity) {
                        this.activity = activity ;
                }

                @Override
                public void run() {
                        while (true){
                                try {
                                        Log.e(TAG, "run: "+ activity.istop);
                                        Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }

        private void recycleTime(){
                TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                                istop = !istop;
                        }
                };
                new Timer().schedule(timerTask,100,1000);
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
