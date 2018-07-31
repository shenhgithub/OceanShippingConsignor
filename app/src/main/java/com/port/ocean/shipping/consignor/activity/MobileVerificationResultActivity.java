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
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.port.ocean.shipping.consignor.R;
import com.port.ocean.shipping.consignor.data.IdentityInfoData;
import com.port.ocean.shipping.consignor.util.StaticValue;
import com.port.ocean.shipping.consignor.work.PullIdentityInfo;

import org.mobile.library.model.work.WorkBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 手机验证结果
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class MobileVerificationResultActivity extends AppCompatActivity {

    /**
     * 传入的手机号
     */
    private String mobile = null;

    /**
     * 传入的货源编号
     */
    private String goodsId = null;

    /**
     * 司机用户ID
     */
    private String userCode = null;

    /**
     * 项目名标签
     */
    private static final String ITEM_TAG = "item_tag";

    /**
     * 内容标签
     */
    private static final String CONTENT_TAG = "content_tag";

    /**
     * 列表使用的数据适配器
     */
    private SimpleAdapter adapter = null;

    /**
     * 数据适配器的元数据
     */
    private List<Map<String, Object>> adapterDataList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification_result);

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
        setTitle(R.string.title_mobile_verification_result);
        // 提取数据
        loadData();
        // 初始化按钮
        initButton();
        // 初始化列表
        initListView();
        // 初始化数据
        initData();
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

        mobile = intent.getStringExtra(StaticValue.IntentTag.MOBILE_TAG);
    }

    /**
     * 初始化按钮
     */
    private void initButton() {

        // 登录按钮
        Button button = (Button) findViewById(R.id.activity_mobile_verification_result_button);

        // 点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNext();
            }
        });
    }

    /**
     * 执行确认
     */
    private void goNext() {

        Intent intent = new Intent(this, DealActivity.class);
        if (goodsId != null) {
            intent.putExtra(StaticValue.IntentTag.GOODS_ID_TAG, goodsId);
        }

        if (mobile != null) {
            intent.putExtra(StaticValue.IntentTag.USER_CODE_TAG, userCode);
        }
        startActivity(intent);
    }

    /**
     * 初始化列表数据
     */
    private void initListView() {
        // 片段中的列表布局
        ListView listView = (ListView) findViewById(R.id
                .activity_mobile_verification_result_listView);

        adapterDataList = getFunctionTitle();

        // 列表使用的数据适配器
        adapter = new SimpleAdapter(this, adapterDataList, R.layout.mobile_verification_item, new
                String[]{ITEM_TAG , CONTENT_TAG}, new int[]{R.id.mobile_verification_item_tag_textView , R.id.mobile_verification_item_content_textView});

        // 设置适配器
        listView.setAdapter(adapter);
    }

    /**
     * 标签资源
     *
     * @return 返回SimpleAdapter适配器使用的数据源
     */
    private List<Map<String, Object>> getFunctionTitle() {
        // 加载功能项
        List<Map<String, Object>> dataList = new ArrayList<>();

        String[] itemTagList = getResources().getStringArray(R.array
                .mobile_verification_item_tag_list);
        for (String anItemTagList : itemTagList) {
            // 新建一个功能项标签
            Map<String, Object> function = new HashMap<>();

            // 添加标签资源
            // 添加标题
            function.put(ITEM_TAG, anItemTagList);

            // 将标签加入数据源
            dataList.add(function);
        }
        return dataList;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (mobile == null) {
            return;
        }

        PullIdentityInfo pullIdentityInfo = new PullIdentityInfo();

        pullIdentityInfo.setWorkEndListener(new WorkBack<IdentityInfoData>() {
            @Override
            public void doEndWork(boolean state, IdentityInfoData data) {
                if (state) {
                    fillData(data);
                }
            }
        });

        pullIdentityInfo.beginExecute(mobile);
    }

    /**
     * 填充数据
     *
     * @param data 数据源
     */
    private void fillData(IdentityInfoData data) {
        adapterDataList.get(0).put(CONTENT_TAG, data.getUserName());
        adapterDataList.get(1).put(CONTENT_TAG, data.getIdentityCard());
        adapterDataList.get(2).put(CONTENT_TAG, data.getVehicleNumber());
        adapterDataList.get(3).put(CONTENT_TAG, data.getVehicleType());
        adapterDataList.get(4).put(CONTENT_TAG, data.getVehicleLength());
        adapterDataList.get(5).put(CONTENT_TAG, data.getTons());

        adapter.notifyDataSetChanged();

        userCode = data.getUserCode();
    }
}
