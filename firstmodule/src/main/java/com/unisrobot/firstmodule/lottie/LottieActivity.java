package com.unisrobot.firstmodule.lottie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieImageAsset;
import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.dialog.LoadingDialog;
import com.unisrobot.firstmodule.view.MoveView;
import com.unisrobot.firstmodule.view.SideBar;
import com.unisrobot.firstmodule.view.VerticalProgress;

/**
 * Created by Administrator on 2018/4/16.
 */

public class LottieActivity extends AppCompatActivity {

        LottieAnimationView lottieAnimationView;

        private static final String TAG = "LottieActivity";
        private Button button;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first_module_activity_lottie);
                button = findViewById(R.id.btn_lottie);
                button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(LottieActivity.this,"click",Toast.LENGTH_SHORT).show();
                        }
                });
//                setContentView(R.layout.first_module_activity_lottie);
//                lottieAnimationView = findViewById(R.id.first_module_lottieview);
//                // 从文件读取，，从网络读取。。。
//                //                LottieComposition.Factory.fromAssetFileName()
//                Button button = findViewById(R.id.btn_lottie);
//                Log.e(TAG, "onCreate: " + lottieAnimationView.isShown());
//                Log.e(TAG, "onCreate: " + lottieAnimationView.isAnimating());
//                button.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                                if (!lottieAnimationView.isAnimating()) {
//                                        lottieAnimationView.playAnimation();
//                                }
//                                else {
//                                        lottieAnimationView.pauseAnimation();
//                                        lottieAnimationView.clearAnimation();
//
//                                }
//                        }
//                });

        }
}
