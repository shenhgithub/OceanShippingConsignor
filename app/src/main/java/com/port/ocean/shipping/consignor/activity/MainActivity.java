package com.port.ocean.shipping.consignor.activity;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.port.ocean.shipping.consignor.R;
import com.port.ocean.shipping.consignor.adapter.ViewPagerAdapter;

import org.mobile.library.common.function.CheckUpdate;
import org.mobile.library.common.view.SlidingTabLayout;
import org.mobile.library.model.operate.BackHandle;


public class MainActivity extends AppCompatActivity {

    /**
     * 滑动分页
     */
    private ViewPager viewPager = null;

    /**
     * ViewPager的Fragment适配器
     */
    private ViewPagerAdapter viewPagerAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 加载界面
        initView();
        // 执行检查更新
        checkUpdate();
    }

    /**
     * 检查新版本
     */
    private void checkUpdate() {
        // 新建版本检查工具
        CheckUpdate checkUpdate = new CheckUpdate(this);
        // 执行检查
        checkUpdate.checkInBackground();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 初始化Toolbar
        initToolbar();
        setTitle(R.string.title_app_name);
        // 初始化ViewPager和导航栏
        initViewPagerAndTab();
    }

    /**
     * 初始化标题栏
     */
    private void initToolbar() {
        // 得到Toolbar标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // 得到标题文本
        //toolbarTitleTextView = (TextView) findViewById(R.id.toolbar_title);

        // 关联ActionBar
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        // 取消原actionBar标题
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    /**
     * 初始化ViewPager和底部导航栏
     */
    private void initViewPagerAndTab() {
        // 底部导航栏
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tab);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(viewPagerAdapter);

        // 获取图片ID和颜色代码
        // 资源类型数组
        TypedArray defaultImages = getResources().obtainTypedArray(R.array
                .bottom_navigation_default_image_list);

        int[] defaultImageIds = new int[defaultImages.length()];
        for (int i = 0; i < defaultImages.length(); i++) {
            defaultImageIds[i] = defaultImages.getResourceId(i, R.mipmap.main_navigation_icon);
        }

        TypedArray selectedImages = getResources().obtainTypedArray(R.array
                .bottom_navigation_selected_image_list);

        int[] selectedImageIds = new int[selectedImages.length()];
        for (int i = 0; i < selectedImages.length(); i++) {
            selectedImageIds[i] = selectedImages.getResourceId(i, R.mipmap.main_navigation_icon);
        }

        // 设置SlidingTab的自定义标签布局
        slidingTabLayout.setCustomTabView(R.layout.tab_item, R.id.tab_item_textView, R.id
                .tab_item_image, defaultImageIds, selectedImageIds);
        // 设置选中后文字颜色
        slidingTabLayout.setTabViewTextViewTextSelectedColors(getResources().getColor(R.color.red));
        // 设置底部滚动条颜色
        slidingTabLayout.setSelectedIndicatorColors(Color.TRANSPARENT);
        // 装载ViewPager
        slidingTabLayout.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // 获取当前显示的对象
        Object currentObject = viewPagerAdapter.instantiateItem(viewPager, viewPager
                .getCurrentItem());

        // 判断是否实现了返回操作接口
        if (currentObject instanceof BackHandle) {
            // 转换为返回操作接口
            BackHandle backHandled = (BackHandle) currentObject;
            // 执行返回动作
            if (!backHandled.onBackPressed()) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }
}
