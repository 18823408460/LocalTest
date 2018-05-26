package com.uurobot.baseframe.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.utils.SizeUtil;

/**
 * Created by WEI on 2018/5/26.
 */

public class ImageTextView extends View {
    private static final String TAG = ImageTextView.class.getSimpleName();
    private int bitmapResId;
    private String textUpLeft;
    private String textUpRight;
    private String textDown;
    private int textNormalColor;
    private int textDynamicColor;
    private int lineColor;
    private int lineSize;
    private float textNormalSize;
    private float textDynamicSize;
    private TextPaint textPaintNoraml;
    private TextPaint textPaintDynamic;
    private Paint linePaint;
    private int textPaddingLeft;
    private int textPaddingUp;
    private int viewPadding;
    private Bitmap bitmap;

    private float textNormalH;
    private float textUpLeftW;

    public ImageTextView(Context context) {
        this(context, null);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initType(attrs);
        initData();
    }

    private void initType(AttributeSet attrs) {
        TypedArray typedArray = null;
        try {
            typedArray = getResources().obtainAttributes(attrs, R.styleable.ImageTextView);
            bitmapResId = typedArray.getResourceId(R.styleable.ImageTextView_bitmapResId,0);
            textUpLeft = typedArray.getString(R.styleable.ImageTextView_textUpLeft);
            textUpRight = typedArray.getString(R.styleable.ImageTextView_textUpRight);
            textDown = typedArray.getString(R.styleable.ImageTextView_textDown);
            textNormalColor = typedArray.getColor(R.styleable.ImageTextView_textNormalColor,Color.parseColor("#0000FF"));
            textDynamicColor = typedArray.getColor(R.styleable.ImageTextView_textDynamicColor,Color.parseColor("#FF0000"));
            lineColor = typedArray.getColor(R.styleable.ImageTextView_lineColor,Color.parseColor("#FF0000"));
            textNormalSize = typedArray.getDimensionPixelSize(R.styleable.ImageTextView_textNormalSize, SizeUtil.dp2px(getContext(), 20));
            textDynamicSize = typedArray.getDimensionPixelSize(R.styleable.ImageTextView_textDynamicSize, SizeUtil.dp2px(getContext(), 30));
            lineSize = typedArray.getDimensionPixelSize(R.styleable.ImageTextView_lineSize, SizeUtil.dp2px(getContext(), 2));
        } finally {
            typedArray.recycle();
        }
    }

    private void initData() {
       // mockData();
        textPaintNoraml = new TextPaint();
        textPaintNoraml.setAntiAlias(true);
        textPaintNoraml.setColor(textNormalColor);
        textPaintNoraml.setTextSize(textNormalSize);

        textPaintDynamic = new TextPaint();
        textPaintNoraml.setAntiAlias(true);
        textPaintDynamic.setColor(textDynamicColor);
        textPaintDynamic.setTextSize(textDynamicSize);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(lineColor);
        linePaint.setTextSize(lineSize);

        textPaddingLeft = SizeUtil.dp2px(getContext(), 5);
        textPaddingUp = SizeUtil.dp2px(getContext(), 5);
        viewPadding = SizeUtil.dp2px(getContext(), 5);
    }

    private void mockData() {
        bitmapResId = R.drawable.wendu;
        textUpLeft = "湿度:";
        textUpRight = "0.009C";
        textDown = "适湿约0.3-0.7";
        textNormalColor = Color.parseColor("#0000ff");
        textDynamicColor = Color.parseColor("#ff0000");
        lineColor = Color.parseColor("#ff0000");
        textNormalSize = SizeUtil.dp2px(getContext(), 20);
        textDynamicSize = SizeUtil.dp2px(getContext(), 35);
        lineSize = SizeUtil.dp2px(getContext(), 2);
    }

    public void updateDynaimcText(String textUpRight){
        this.textUpRight = textUpRight ;
        invalidate();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        bitmap = BitmapFactory.decodeResource(getResources(), bitmapResId);
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        textUpLeftW = textPaintNoraml.measureText(textUpLeft);
        float textUpRightW = textPaintDynamic.measureText(textUpRight);
        float textDownW = textPaintNoraml.measureText(textDown);

        Paint.FontMetrics fontMetrics = textPaintNoraml.getFontMetrics();
        textNormalH = Math.abs(fontMetrics.ascent) + fontMetrics.descent;
        Paint.FontMetrics fontMetrics1 = textPaintDynamic.getFontMetrics();
        float textDynamicH = Math.abs(fontMetrics1.ascent) + fontMetrics1.descent;

        int viewWidth = (int) (bitmapWidth + textPaddingLeft + Math.max(textDownW, (textUpLeftW + textUpRightW))) + viewPadding * 2;
        int viewHeight = (int) (Math.max(bitmapHeight, textNormalH + textPaddingUp + textDynamicH) + viewPadding * 2);
        //Log.e(TAG, "onMeasure: textDynamicH="+textDynamicH + "  textNormalH="+textNormalH +"  textPaddingUp="+textPaddingUp );
        //Log.e(TAG, "onMeasure: bitmapWidth="+bitmapWidth + "  viewHeight="+viewHeight );
        setMeasuredDimension(viewWidth, viewHeight);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.d(TAG, "draw: " + getWidth() + "    " + getHeight());
        int Viewheight = getHeight();
        int Viewwidth = getWidth();
        // 绘制图片
        canvas.drawBitmap(bitmap, viewPadding, Viewheight / 2 - bitmap.getHeight() / 2, null);
        float lineY = Viewheight / 2 + bitmap.getHeight() / 2 + textPaddingUp ;
        canvas.drawLine(viewPadding, lineY, Viewwidth - viewPadding, lineY, linePaint);

        // 绘制文字--- (x,y) 是 文字的左下角坐标
        int textStartX = viewPadding + bitmap.getWidth() + textPaddingLeft;
        canvas.drawText(textUpLeft, textStartX, Viewheight / 2 - textPaddingUp, textPaintNoraml);
        canvas.drawText(textUpRight, textStartX + textUpLeftW, Viewheight / 2 - textPaddingUp, textPaintDynamic);
        canvas.drawText(textDown, textStartX, Viewheight / 2 + textNormalH , textPaintNoraml);
    }
}
