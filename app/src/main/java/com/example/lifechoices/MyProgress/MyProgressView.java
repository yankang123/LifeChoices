package com.example.lifechoices.MyProgress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyProgressView extends View {
    private Paint myOutPaint;//外圈笔的样式
    private Paint myInnerPaint;
    private int strokeWidth = 8;
    private int width;
    private int height;
    private int padding = 3;
    private int round;//圆角半径
    private double process = 1.0f;

    public MyProgressView(Context context) {
        super(context);
        init();
    }

    public MyProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST) {
            width = widthSpecSize;
        } else {
            width = 0;
        }
        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.EXACTLY) {
            height = heightSpecSize;
        } else {
            height = 0;
        }
        round = width / 2;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF  = new RectF(padding, padding, width - padding, height - padding);
        canvas.drawRoundRect(rectF, round, round, myOutPaint);

        rectF = new RectF(0 + strokeWidth + padding, 0 + strokeWidth + padding, (int) ((width - strokeWidth - padding) * process), height - strokeWidth - padding);
        canvas.drawRoundRect(rectF, round, round, myInnerPaint);
    }

    private void init() {
        myOutPaint = new Paint();
        myInnerPaint = new Paint();
        myInnerPaint.setStyle(Paint.Style.FILL);
        myInnerPaint.setAntiAlias(true);
        myInnerPaint.setStrokeWidth(strokeWidth);
        myInnerPaint.setColor(Color.parseColor("#FFD763"));
        myOutPaint.setColor(Color.parseColor("#F85F75"));
        myOutPaint.setAntiAlias(true);
        myOutPaint.setStyle(Paint.Style.STROKE);
        myOutPaint.setStrokeWidth(strokeWidth);
    }

    public void setPrgress(double num) {
        process = num / 20;
        invalidate();
    }
}
