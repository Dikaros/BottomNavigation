# BottomNavigation
遵循Android Material Design的底部导航栏

##样式
* NORMAL<br/>
![image](https://raw.githubusercontent.com/Dikaros/BottomNavigation/master/screenshots/b_normal.jpg)

* NO_TEXT<br/>
![image](https://raw.githubusercontent.com/Dikaros/BottomNavigation/master/screenshots/b_no_text.jpg)

* NO_IMAGE<br/>
![image](https://raw.githubusercontent.com/Dikaros/BottomNavigation/master/screenshots/b_no_image.jpg)

* CHECK_SHOW_TEXT<br/>
![image](https://raw.githubusercontent.com/Dikaros/BottomNavigation/master/screenshots/b_check_show_text.jpg)



##使用方法

* 在xml文件中声明BottomNavigation
* 设置控件的样式，字体颜色等
* 在Java 代码中增加底部导航项
* 实现回调方法


> 在布局xml文件中放置,textCheckedColor是选中项字体的颜色,textUnChecked是未选中项字体的颜色

```xml
 <com.dikaros.navigation.widget.BottomNavigationBar
        android:id="@+id/bnBar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        app:textCheckedColor="@color/checked"
        app:textUnCheckedColor="@color/unchecked"
        app:show_type="no_image">
    </com.dikaros.navigation.widget.BottomNavigationBar>
```

> 在java代码中为bar添加子项

```java
bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bnBar);
        /*
        add bottom navigation item
        param1 bottom text
        param2 bottom image checked
        param3 bottom image unchecked
         */
        bottomNavigationBar.addItemView("Recents",R.drawable.ic_restore_green_24dp,R.drawable.ic_restore_black_24dp);
        bottomNavigationBar.addItemView("Favorites", R.drawable.ic_insert_emoticon_check_24dp,R.drawable.ic_insert_emoticon_black_24dp);
        bottomNavigationBar.addItemView("Nearby",R.drawable.ic_place_green_24dp,R.drawable.ic_place_black_24dp);
```
> 设置点击回调,以处理之后与ViewPager或Fragment的交互

```java
//set Callback
        bottomNavigationBar.setOnItemViewSelectedListener(new BottomNavigationBar.OnItemViewSelectedListener() {
            /**
             * call when item clicked
             * @param v clicked item
             * @param index item index
             */
            @Override
            public void onItemClcik(View v, int index) {

            }
        });
```
