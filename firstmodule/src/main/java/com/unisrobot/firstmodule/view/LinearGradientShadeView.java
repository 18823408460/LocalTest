package com.unisrobot.firstmodule.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.unisrobot.firstmodule.utils.ScreenUtil;

/**
 * Created by WEI on 2018/4/16.
 */

public class LinearGradientShadeView extends View {
    private Context context ;
    public LinearGradientShadeView(Context context) {
     this(context, null);
    }

    public LinearGradientShadeView(Context context, @Nullable AttributeSet attrs) {
       this(context, null,-1);
    }

    public LinearGradientShadeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context ;
        initData();
    }

    private Paint paint ;
    private void initData() {
        paint = new Paint();

        // shade相当一个纹理，用于渲染到View上，纹理可以小于view的大小，view中多余的部分，通过设置纹理模式， 系统就会根据这个模式来处理。
        // 我们可以把 纹理 想象成一个图片，最终贴在view上，那 这个图片就可以设置选择不同方向，贴在view上。。
        // 也可以选择不同的位置（起点）贴在view上。。。理解了这几个，就知道下面几个参数的意思了。。。。

        // 下面的两个点的连线--------就是 渲染的方向        。。。。。。。
        LinearGradient linearGradient = new LinearGradient(0,0,getMeasuredWidth(),0,new int[]{Color.RED,
        Color.GREEN,Color.BLUE},null, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(ScreenUtil.dp2px(context,256),ScreenUtil.dp2px(context,256));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
