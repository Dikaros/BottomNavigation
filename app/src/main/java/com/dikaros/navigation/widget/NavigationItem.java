package com.dikaros.navigation.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dikaros.navigation.DensityUtil;
import com.dikaros.navigation.R;

import java.util.ArrayList;

/**
 * Created by Dikaros on 2016/5/20.
 */
public class NavigationItem extends LinearLayout {
    public NavigationItem(Context context, int textCheckedColor, int textUnCheckedColor, int imageCheckedResource, int imageUnCheckedResource) {
        super(context);
        initDefaultValue();
        initViews(context);
        this.textCheckedColor = textCheckedColor;
        this.textUnCheckedColor = textUnCheckedColor;
        this.imageUnCheckedResource = imageUnCheckedResource;
        this.imageCheckedResource = imageCheckedResource;
        addView(mImageView);
        addView(mTextView);


    }


    private void initDefaultValue() {
        imageWidth = 24;
        imageHeight = 24;
        textSize = 12;
        imagePaddingTop = 8;
        textPaddingBottom = 10;


    }


    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    boolean textVisible = true;
    boolean imageVisible = true;


    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextVisible(boolean textVisible) {
        this.textVisible = textVisible;
        mTextView.setVisibility(textVisible ? VISIBLE : GONE);
        if (!textVisible) {
            Log.e("textView", "消失");
        }
    }

    public void setImageVisible(boolean imageVisible) {
        this.imageVisible = imageVisible;
        mImageView.setVisibility(imageVisible ? VISIBLE : GONE);
    }

    public boolean isImageVisible() {
        return imageVisible;
    }

    public boolean isTextVisible() {
        return textVisible;
    }

    public TextView getmTextView() {
        return mTextView;
    }

    public ImageView getmImageView() {
        return mImageView;
    }

    private void initViews(final Context context) {

        setOrientation(VERTICAL);

        //初始化view
        mTextView = new TextView(context);
        mImageView = new ImageView(context);
        //设置veiw是否可见
        mTextView.setVisibility(textVisible ? VISIBLE : GONE);
        mImageView.setVisibility(imageVisible ? VISIBLE : GONE);
        //设置默认文字
        mTextView.setText("text");
        //设置默认文字大小
        mTextView.setTextSize(textSize);
        //设置内边距
        setPadding(0, DensityUtil.dip2px(context, imagePaddingTop), 0, DensityUtil.dip2px(context, textPaddingBottom));
        //设置自身布局
        setGravity(Gravity.CENTER);
        //设置text和image的布局参数
        LayoutParams textParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams imageParams = new LayoutParams(DensityUtil.dip2px(context, imageWidth), DensityUtil.dip2px(context, imageHeight));
        mTextView.setLayoutParams(textParams);
        mImageView.setLayoutParams(imageParams);

        //设置最小宽度
        setMinimumWidth(DensityUtil.dip2px(context, 80));
        //设置本身的布局
        LinearLayout.LayoutParams selfParam = new LayoutParams(DensityUtil.dip2px(context, 0), DensityUtil.dip2px(context, 56), 1f);
        setLayoutParams(selfParam);

        //设置文字颜色
        mTextView.setTextColor(textUnCheckedColor);
        //设置默认图片
        mImageView.setImageResource(imageUnCheckedResource);


    }

    boolean checked = false;

    public boolean isChecked() {
        return checked;
    }

    //文字
    TextView mTextView;
    //图片
    ImageView mImageView;
    //文字大小
    int textSize;

    int imageWidth;
    int imageHeight;
    int imagePaddingTop;
    int textPaddingBottom;

    int textCheckedColor;
    int textUnCheckedColor;

    int imageCheckedResource;
    int imageUnCheckedResource;


    private void initCheckValue() {
        imageWidth = 24;
        imageHeight = 24;
        textSize = 14;
        imagePaddingTop = 6;
        textPaddingBottom = 10;
    }

    public void setImageCheckedResource(int imageCheckedResource) {
        this.imageCheckedResource = imageCheckedResource;
    }

    public void setImageUnCheckedResource(int imageUnCheckedResource) {
        this.imageUnCheckedResource = imageUnCheckedResource;
    }

    public void setTextCheckedColor(int textCheckedColor) {
        this.textCheckedColor = textCheckedColor;
    }

    public void setTextUnCheckedColor(int textUnCheckedColor) {
        this.textUnCheckedColor = textUnCheckedColor;
    }


    public void showView() {
        if (checked) {
            initCheckValue();
            mTextView.setTextColor(textCheckedColor);
            mImageView.setImageResource(imageCheckedResource);
        } else {
            initDefaultValue();
            mTextView.setTextColor(textUnCheckedColor);
            mImageView.setImageResource(imageUnCheckedResource);
        }
        mTextView.setVisibility(textVisible ? VISIBLE : GONE);
        mImageView.setVisibility(imageVisible ? VISIBLE : GONE);
        mTextView.setTextSize(textSize);
        setPadding(DensityUtil.dip2px(getContext(), 12), DensityUtil.dip2px(getContext(), imagePaddingTop), DensityUtil.dip2px(getContext(), 12), DensityUtil.dip2px(getContext(), textPaddingBottom));


        //重绘
        postInvalidate();
        mImageView.postInvalidate();
        mTextView.postInvalidate();
    }

    /**
     * 更改View
     *
     */
    public void changeView() {
        checked = !checked;
        showView();

    }

}
