package com.uurobot.baseframe.activitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.drawable.CircleDrawable;
import com.uurobot.baseframe.view.CarYouBiaoView;
import com.uurobot.baseframe.view.FakeViewPager;
import com.uurobot.baseframe.view.SurfaceViewAnim;
import com.uurobot.baseframe.view.jinrong.FlowLayout;
import com.uurobot.baseframe.view.jinrong.HorProgress;
import com.uurobot.baseframe.view.jinrong.RoundProgress;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WEI on 2018/5/26.
 */

public class TestViewActivity extends BaseActivity {
        private ImageView imageView;
        private Context mContext;
        int imgs[] = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};

        @SuppressLint("ResourceType")
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                mContext = this;
                //                testAnim();

                //                testDrawable();

                testFakeViewPager();
                //                testPopupWindow();
        }


        private void testCarYouBiao() {
                setContentView(R.layout.activity_test_car_youbiao);
                final CarYouBiaoView carYouBiaoView = findViewById(R.id.car_youbiao);
                Button button = findViewById(R.id.BtnUpdate);
                button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                carYouBiaoView.update();
                        }
                });
        }

        private void testSlideDrawLayout() {
                setContentView(R.layout.activity_test_slidedrawlayout);
                Window window = getWindow();
        }


        private void testFlowLayout() {
                setContentView(R.layout.activity_flowlayout);

                FlowLayout flowLayout = findViewById(R.id.flowlayout_test_activity);
                String[] strings = {"是发送到发送到0000", "诉讼费11", "水电费问问222", "her3333", "斯蒂芬第三方44",
                        "是发送到发送到sfdsfsdf5555", "诉讼费sdfsd66", "水电费问问sferew777", "herwerer888", "sdsdggsdg斯蒂芬第三方99",
                        "水电费问问sferew10", /*"herwerer11", "sdsdggsdg斯蒂芬第三方121212"*/};
                for (int i = 0; i < strings.length; i++) {
                        Button button = new Button(this);
                        button.setText(strings[i]);
                        //  button.setBackgroundResource(R.drawable.btn_flowlayout_selectors);
                        button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        Toast.makeText(mContext, v.getId() + "'", Toast.LENGTH_SHORT).show();
                                }
                        });
                        flowLayout.addView(button);
                }
        }

        private void testRoundProgress() {
                setContentView(R.layout.activity_wave);
        }

        private void testScrollview() {
                setContentView(R.layout.activity_cus_scrollview);
                ViewPager viewPager = findViewById(R.id.viewpager_cus_scrollview);
                viewPager.setOffscreenPageLimit(1);
                ViewTreeObserver viewTreeObserver = viewPager.getViewTreeObserver();

                viewPager.setAdapter(new PagerAdapter() {
                        @Override
                        public int getCount() {
                                return imgs.length;
                        }

                        @Override
                        public boolean isViewFromObject(View view, Object object) {
                                return view == object;
                        }

                        @Override
                        public Object instantiateItem(ViewGroup container, int position) {
                                ImageView imageView = new ImageView(mContext);
                                imageView.setBackgroundResource(imgs[position]);
                                container.addView(imageView);
                                return imageView;
                        }

                        @Override
                        public void destroyItem(ViewGroup container, int position, Object object) {
                                container.removeView((View) object);
                        }
                });
        }

        private void testDraw() {
                LinearLayout linearLayout = new LinearLayout(this) {
                        @Override
                        protected void dispatchDraw(Canvas canvas) {
                                Log.e(TAG, "dispatchDraw: ");
                                super.dispatchDraw(canvas);
                        }
                };
                setContentView(linearLayout);
                Button button = new AppCompatButton(this) {
                        @Override
                        protected void onDraw(Canvas canvas) {
                                Log.e(TAG, "onDraw: ========butn");
                                super.onDraw(canvas);
                        }
                };
                final TextView textView = new AppCompatTextView(this) {
                        @Override
                        protected void onDraw(Canvas canvas) {
                                Log.e(TAG, "onDraw: ======= textviwe");
                                super.onDraw(canvas);
                        }
                };
                button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                textView.invalidate();
                        }
                });
                linearLayout.addView(button);
                linearLayout.addView(textView);
        }


        private void testDaoyingView() {
                setContentView(R.layout.activity_test_daoying);
        }

        private void testCamera() {
                setContentView(R.layout.activity_test_camera);
        }


        private void testCutomView() {
                setContentView(R.layout.activity_test_goodsselectview);
        }


        private void testFakeViewPager() {
                setContentView(R.layout.activity_test_fakeviewpager);
                final RadioGroup radioGroup = findViewById(R.id.rg_test_fakeviewpager);
                final FakeViewPager fakeViewPager = findViewById(R.id.fake_viewpager);
                fakeViewPager.setPageSelectListenter(new FakeViewPager.PageSelectListenter() {
                        @Override
                        public void onSelect(int position) {
                                Log.e(TAG, "onSelect: " + position);
                        }
                });

                View inflate = View.inflate(mContext, R.layout.layout_fakeviewpager_test, null);
                fakeViewPager.addView(inflate);
                RadioButton radioButton2 = new RadioButton(this);
                radioGroup.addView(radioButton2);

                for (int i = 0; i < imgs.length; i++) {
                        ImageView imageView = new ImageView(this);
                        imageView.setBackgroundResource(imgs[i]);
                        fakeViewPager.addView(imageView);

                        RadioButton radioButton = new RadioButton(this);
                        if (i == 0) {
                                radioButton.setChecked(true);
                        }
                        radioGroup.addView(radioButton);
                }


                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                                Log.e(TAG, "onCheckedChanged: " + checkedId);
                                fakeViewPager.scrollToItem(checkedId);
                        }
                });
        }


        private void testDimens() {
                setContentView(R.layout.activity_test_diments);
        }


        private void testPopupWindow() {
                setContentView(R.layout.activity_textview);
                Button button1 = findViewById(R.id.btn_activity_testview);
                button1.setText("水电费是否水电费水电费");
                button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                final PopupWindow popupWindow = new PopupWindow(getApplicationContext());
                                ImageView contentView = new ImageView(getApplicationContext());
                                contentView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                                popupWindow.dismiss();
                                        }
                                });
                                contentView.setBackgroundResource(R.drawable.channel1);
                                popupWindow.setContentView(contentView);
                                //  popupWindow.showAtLocation(v, Gravity.CENTER,0,0); //屏幕居中位置
                                //  popupWindow.showAsDropDown(v); // v 的左下角
                                popupWindow.showAsDropDown(v, v.getWidth() / 2 - popupWindow.getWidth() / 2, 0, -1);//v 居中位置
                        }
                });
        }

        private void testViewFlipper() {
                final int ids[] = {R.drawable.channel1, R.drawable.channel2, R.drawable.channel3, R.drawable.channel4};
                ViewFlipper viewFlipper = new ViewFlipper(this);
                for (int i = 0; i < 4; i++) {
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setBackgroundResource(ids[i]);
                        viewFlipper.addView(imageView1);
                }
                for (int i = 0; i < 4; i++) {
                        TextView textView = new TextView(this);
                        textView.setTextColor(Color.parseColor("#ff0000"));
                        textView.setTextSize(30);
                        textView.setText("sdfsfd " + Math.random());
                        viewFlipper.addView(textView);
                }
                setContentView(viewFlipper);
                viewFlipper.setFlipInterval(2000);
                viewFlipper.setAutoStart(true);
                viewFlipper.startFlipping();

        }

        private void testImageSwitcher() {
                final ImageSwitcher imageSwitcher = new ImageSwitcher(this);
                imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                        @Override
                        public View makeView() {
                                ImageView imageView = new ImageView(getApplicationContext());
                                return imageView;
                        }
                });
                setContentView(imageSwitcher);
                final int ids[] = {R.drawable.channel1, R.drawable.channel2, R.drawable.channel3};
                new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                                runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                                imageSwitcher.setBackgroundResource(ids[(int) (ids.length * Math.random())]);
                                        }
                                });
                        }
                }, 1000, 1000);
        }

        private void testTextSwitcher() {
                final TextSwitcher textSwitcher = new TextSwitcher(this);
                textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                        @Override
                        public View makeView() {
                                TextView textView = new TextView(getApplicationContext());
                                textView.setTextColor(Color.parseColor("#ff0000"));
                                textView.setTextSize(30);
                                return textView;
                        }
                });
                final String[] strings = {"11", "22", "33", "44"};
                setContentView(textSwitcher);
                new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                                runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                                textSwitcher.setText(strings[(int) (strings.length * Math.random())]);
                                        }
                                });
                        }
                }, 1000, 1000);
        }

        private void testDrawable() {
                setContentView(R.layout.activity_textview);
                imageView = findViewById(R.id.image_test);
                @SuppressLint("ResourceType")
                Bitmap bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.timg));
                //imageView.setImageDrawable(new RoundRectDrawable(bitmap));
                imageView.setImageDrawable(new CircleDrawable(bitmap));
        }

        private static final String TAG = TestViewActivity.class.getSimpleName();

        private void testRotateView() {
                @SuppressLint("ResourceType")
                Bitmap bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.small2)); //这种方式创建的bitmap是不能修改的。
                //  25 * 27
                Log.e(TAG, "testRotateView: " + bitmap.getWidth() + "   " + bitmap.getHeight());
                for (int i = 0; i < bitmap.getWidth(); i++) {
                        String txt = "";
                        for (int j = 0; j < bitmap.getHeight(); j++) {
                                txt += bitmap.getPixel(i, j) + "\t";
                                //bitmap.setPixel(i, j, Color.parseColor("#0000ff"));
                        }
                        Log.e(TAG, "testRotateView: row=" + i + "   " + txt);
                }

                Bitmap bitmap1 = Bitmap.createBitmap(10, 10, Bitmap.Config.RGB_565);
                for (int i = 0; i < bitmap1.getWidth(); i++) {
                        for (int j = 0; j < bitmap1.getHeight(); j++) {
                                bitmap1.setPixel(i, j, Color.parseColor("#FF0000"));
                        }
                }
                ImageView imageView = new ImageView(this);
                imageView.setImageBitmap(bitmap1);
                setContentView(imageView);
        }

        private void testAnim() {
                setContentView(new SurfaceViewAnim(this));
        }
}
