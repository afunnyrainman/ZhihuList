package com.example.administrator.zhihulist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Administrator on 2017/12/6 0006.
 */

public class MyImage extends AppCompatImageView {
    public MyImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private RectF mBitmapF;
    private Bitmap bitmap;
    private int mMinDy;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mMinDy = h;
        Drawable drawable = getDrawable();
        if (null ==  getDrawable()) {
            return;
        }
        bitmap = drawableToBitamp(drawable);
        Log.e("dxq",bitmap.getHeight() * w / bitmap.getWidth()+" xxxx  "+h);
        mBitmapF = new RectF(0, 0, w, bitmap.getHeight() * w / bitmap.getWidth());
    }

    private int mDy;

    public void setDy(int dy) {
        if (getDrawable() == null) {
            return;
        }
        mDy = dy - mMinDy;
        if (mDy <= 0) {
            mDy = 0;
        }

        if (mDy > mBitmapF.height() - mMinDy) {
            mDy = (int) (mBitmapF.height() - mMinDy);
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap == null) {
            return;
        }
        canvas.save();
        canvas.translate(0, -mDy);
        canvas.drawBitmap(bitmap, null, mBitmapF, null);
        canvas.restore();
    }

    private Bitmap drawableToBitamp(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }
}
