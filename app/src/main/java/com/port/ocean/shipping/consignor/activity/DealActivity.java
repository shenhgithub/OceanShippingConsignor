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
import com.port.ocean.shipping.consignor.work.Deal;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.WorkBack;


/**
 * 发货Activity
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class DealActivity extends AppCompatActivity {

    /**
     * 传入的货源编号
     */
    private String goodsId = null;

    /**
     * 传入的司机用户ID
     */
    private String userCode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);

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
        setTitle(R.string.title_deal);
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
        userCode = intent.getStringExtra(StaticValue.IntentTag.USER_CODE_TAG);
    }

    /**
     * 初始化按钮
     */
    private void initButton() {

        // 登录按钮
        final Button button = (Button) findViewById(R.id.activity_deal_button);

        // 点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭点击
                button.setEnabled(false);
                // 执行配货交易
                goNext();
            }
        });
    }

    /**
     * 执行确认
     */
    private void goNext() {

        // 登录按钮
        final Button button = (Button) findViewById(R.id.activity_deal_button);


        EditText startEditText = (EditText) findViewById(R.id.activity_deal_start_edit);
        EditText endEditText = (EditText) findViewById(R.id.activity_deal_end_edit);
        EditText feeEditText = (EditText) findViewById(R.id.activity_deal_fee_edit);

        String start = startEditText.getText().toString();
        String end = endEditText.getText().toString();
        String fee = feeEditText.getText().toString();

        if (start.length() == 0 || end.length() == 0 || fee.length() == 0) {
            Toast.makeText(this, R.string.info_incomplete, Toast.LENGTH_SHORT).show();
            // 打开点击响应
            button.setEnabled(true);
            return;
        }

        if (userCode == null || goodsId == null || !GlobalApplication.getLoginStatus().isLogin()) {
            Toast.makeText(this, R.string.info_incomplete, Toast.LENGTH_SHORT).show();
            // 打开点击响应
            button.setEnabled(true);
            return;
        }

        Deal deal = new Deal();

        deal.setWorkEndListener(new WorkBack<String>() {
            @Override
            public void doEndWork(boolean state, String data) {


                Toast.makeText(DealActivity.this, data, Toast.LENGTH_SHORT).show();

                if (state) {
                    Intent intent = new Intent(DealActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    // 打开点击响应
                    button.setEnabled(true);
                }
            }
        });

        deal.beginExecute(userCode, GlobalApplication.getLoginStatus().getUserID(), goodsId,
                start, end, fee);

    }
}
