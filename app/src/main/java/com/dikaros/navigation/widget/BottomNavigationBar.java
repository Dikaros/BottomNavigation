package com.dikaros.navigation.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


import com.dikaros.navigation.R;

import java.util.ArrayList;

/**
 * Created by Dikaros on 2016/5/20.
 */
public class BottomNavigationBar extends LinearLayout {
    public BottomNavigationBar(Context context) {
        super(context);
    }

    /**
     * 底部的显示模式
     */
    public enum NavShowType {
        NORMAL, //普通模式，包含文字和图片
        NO_TEXT, //无文字模式
        NO_IMAGE, //无图模式
        CHECKED_SHOW_TEXT //普通无文字 选中出现文字
    }

    public static final int DEFAULT_UNCHECKED_IMAGE = R.drawable.ic_android_black_24dp;
    public static final int DEFAULT_CHECKED_IMAGE = R.drawable.ic_android_green_24dp;

    public static final int DEFAULT_CHECKED_TEXT = 0xff138768;
    public static final int DEFAULT_UNCHECKED_TEXT = 0xff747575;

    NavShowType type = NavShowType.NORMAL;


    public void setType(NavShowType type) {
        this.type = type;
        boolean tv = true, iv = true;
        switch (type) {
            case NORMAL:
                tv = true;
                iv = true;
                break;
            case NO_IMAGE:
                tv = true;
                iv = false;
                break;
            case NO_TEXT:
                tv = false;
                iv = true;
                break;
            case CHECKED_SHOW_TEXT:
                tv = false;
                iv = true;
        }
        for (NavigationItem item : items) {
            item.setTextVisible(tv);
            item.setImageVisible(iv);
            item.showView();
        }
        if (type == NavShowType.CHECKED_SHOW_TEXT) {
            items.get(currentChecked).setTextVisible(true);
        }
    }

    //当前被选中的元素
    int currentChecked = 0;

    public BottomNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.BottomNavigationBar);
        String t = a.getString(R.styleable.BottomNavigationBar_show_type);
        if (t == null) {
            setType(NavShowType.NORMAL);
        } else {
            if (t.equals("1")) {
                setType(NavShowType.NO_TEXT);

            } else if (t.equals("2")) {
                setType(NavShowType.NO_IMAGE);

            } else if (t.equals("3")) {
                setType(NavShowType.CHECKED_SHOW_TEXT);

            } else if (t.equals("0")) {
                setType(NavShowType.NORMAL);

            }
        }

        textCheckdColor = a.getColor(R.styleable.BottomNavigationBar_textCheckedColor,textCheckdColor);
        textUnCheckColor = a.getColor(R.styleable.BottomNavigationBar_textUnCheckedColor,textUnCheckColor);
        a.recycle();
    }

    public BottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
    }

    public void addItemView(final NavigationItem item) {
        boolean tv = true, iv = true;
        switch (type) {
            case NORMAL:
                tv = true;
                iv = true;
                break;
            case NO_IMAGE:
                tv = true;
                iv = false;
                break;
            case NO_TEXT:
                tv = false;
                iv = true;
                break;
            case CHECKED_SHOW_TEXT:
                tv = false;
                iv = true;
        }
        item.setTextVisible(tv);
        item.setImageVisible(iv);
        item.setTextCheckedColor(textCheckdColor);
        item.setTextUnCheckedColor(textUnCheckColor);
        items.add(item);
        final int i = items.size() - 1;
        item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //这个item在集合中的第几个
                int index = i;
                //如果不是当前的view
                boolean noChecked = index != currentChecked;
                if (noChecked) {
                    //改变上一个view
                    items.get(currentChecked).changeView();
                    if (type == NavShowType.CHECKED_SHOW_TEXT) {
                        items.get(currentChecked).setTextVisible(false);
                    }
                    currentChecked = index;
                    if (onItemViewSelectedListener != null) {
                        onItemViewSelectedListener.onItemClcik(v, index);
                    }
                    //改变当前点击的view
                    item.changeView();
                    if (type == NavShowType.CHECKED_SHOW_TEXT) {
                        item.setTextVisible(true);
                    }

                }

            }
        });
        addView(item);
        if (items.size() == 1) {
            item.changeView();
        }
        item.showView();
    }

    int textCheckdColor = DEFAULT_CHECKED_TEXT;
    int textUnCheckColor=DEFAULT_UNCHECKED_TEXT;



    /**
     * 增加
     *
     * @param text
     */
    public void addItemView(String text, int textCheckdColor, int textUnCheckColor, int imageCheckedResource, int imageUnCheckedResource) {
        NavigationItem item = new NavigationItem(getContext(), textCheckdColor, textUnCheckColor, imageCheckedResource, imageUnCheckedResource);
        item.getmTextView().setText(text);
        addItemView(item);
    }

    public void setTextColor(int checkedColor,int unCheckedColor){
        this.textCheckdColor = checkedColor;
        this.textUnCheckColor = unCheckedColor;
        for (NavigationItem item:items){
            item.setTextCheckedColor(checkedColor);
            item.setTextUnCheckedColor(unCheckedColor);
            item.showView();
        }
    }

    public void setTextColor(int checkedColor){
        this.textCheckdColor = checkedColor;
        for (NavigationItem item:items){
                item.setTextCheckedColor(checkedColor);
                item.setTextUnCheckedColor(textUnCheckColor);
                item.showView();
        }
    }

    public void setTextColorRes(int checkedColorId,int unCheckedColorId){
        setTextColor(getResources().getColor(checkedColorId),getResources().getColor(unCheckedColorId));
    }

    public void setTextColorRes(int checkedColorId){
        setTextColor(getResources().getColor(checkedColorId));
    }

//    public void addItemView(String text) {
//        NavigationItem item = new NavigationItem(getContext(), textCheckdColor, textUnCheckColor, DEFAULT_CHECKED_IMAGE, DEFAULT_UNCHECKED_IMAGE);
//        item.getmTextView().setText(text);
//        addItemView(item);
//    }

    public void addItemView(String text,int imageCheckedResource, int imageUnCheckedResource){
        addItemView(text,textCheckdColor,textUnCheckColor,imageCheckedResource,imageUnCheckedResource);
    }


    OnItemViewSelectedListener onItemViewSelectedListener;

    public void setOnItemViewSelectedListener(OnItemViewSelectedListener onItemViewSelectedListener) {
        this.onItemViewSelectedListener = onItemViewSelectedListener;
    }

    //item列表
    ArrayList<NavigationItem> items = new ArrayList<>();

    public interface OnItemViewSelectedListener {
        public void onItemClcik(View v, int index);
    }


}
