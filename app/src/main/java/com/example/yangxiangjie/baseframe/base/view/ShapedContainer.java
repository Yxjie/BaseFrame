package com.example.yangxiangjie.baseframe.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.yangxiangjie.baseframe.R;


/**
 * 带有弧度布局
 */
public class ShapedContainer extends FrameLayout {

    private float mRadius;

    public ShapedContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ShapeContainer);
        mRadius = a.getDimension(R.styleable.ShapeContainer_radiusContain, 30);

        a.recycle();
    }

    private final Path mPath = new Path();
    private final RectF mRectF = new RectF();

    @Override
    protected void dispatchDraw(Canvas canvas) {

        int left = getPaddingLeft();
        int right = getWidth() - getPaddingRight();

        int top = getPaddingTop();
        int bottom = getHeight() - getPaddingBottom();
        mRectF.set(left, top, right, bottom);
        mPath.reset();
        mPath.addRoundRect(mRectF, mRadius, mRadius, Path.Direction.CW);
        try {
            canvas.clipPath(mPath);
        } catch (UnsupportedOperationException e) {

        }
        super.dispatchDraw(canvas);
    }
}
