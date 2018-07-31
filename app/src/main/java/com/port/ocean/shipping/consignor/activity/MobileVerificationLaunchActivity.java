package com.port.ocean.shipping.consignor.activity;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.port.ocean.shipping.consignor.R;
import com.port.ocean.shipping.consignor.util.StaticValue;

/**
 * 发起手机号验证
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class MobileVerificationLaunchActivity extends AppCompatActivity {

    /**
     * 传入的货源编号
     */
    private String goodsId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);

        // 初始化布局
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        // 初始化Toolbar
        initToolbar();
        // 设置标题
        setTitle(R.string.title_mobile_verification_launch);
        // 提取数据
        loadData();
        // 初始化按钮
        initButton();
    }

    /**
     * 初始化标题栏
     */
    private void initToolbar() {
        // 得到Toolbar标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // 关联ActionBar
        setSupportActionBar(toolbar);

        // 显示后退
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 与返回键相同
                onBackPressed();
            }
        });
    }

    /**
     * 提取数据
     */
    private void loadData() {
        Intent intent = getIntent();

        goodsId = intent.getStringExtra(StaticValue.IntentTag.GOODS_ID_TAG);

    }

    /**
     * 初始化按钮
     */
    private void initButton() {

        // 登录按钮
        Button button = (Button) findViewById(R.id.activity_mobile_verification_button);

        // 点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNext();
            }
        });
    }

    /**
     * 执行下一步
     */
    private void goNext() {
        EditText mobileEditText = (EditText) findViewById(R.id
                .activity_mobile_verification_mobile_edit);

        String mobile = mobileEditText.getText().toString().trim();

        if (mobile.length() == 0) {
            Toast.makeText(this, R.string.mobile_verification_edit_hint, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MobileVerificationResultActivity.class);
        if (goodsId != null) {
            intent.putExtra(StaticValue.IntentTag.GOODS_ID_TAG, goodsId);
        }
        intent.putExtra(StaticValue.IntentTag.MOBILE_TAG, mobile);
        startActivity(intent);
    }

}
