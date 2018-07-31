package com.port.ocean.shipping.consignor.activity;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.port.ocean.shipping.consignor.R;
import com.port.ocean.shipping.consignor.adapter.GoodsManagerItemViewHolder;
import com.port.ocean.shipping.consignor.adapter.GoodsManagerRecyclerViewAdapter;
import com.port.ocean.shipping.consignor.data.Goods;
import com.port.ocean.shipping.consignor.util.StaticValue;
import com.port.ocean.shipping.consignor.work.PullGoodsSupply;
import com.port.ocean.shipping.consignor.work.RationingGoods;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;
import org.mobile.library.model.work.WorkBack;

import java.util.List;
import java.util.zip.Inflater;

/**
 * 货源管理Activity
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class GoodsSupplyManagerActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "GoodsSupplyManagerActivity.";

    private GoodsManagerRecyclerViewAdapter adapter = new GoodsManagerRecyclerViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_supply_manager);

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
        setTitle(R.string.title_goods_supply_manager);
        // 初始化列表
        initListView();
        // 获取数据
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
     * 初始化列表
     */
    private void initListView() {

        // RecyclerView列表对象
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id
                .activity_goods_supply_manager_recyclerView);

        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        // 设置点击事件
        Log.i(LOG_TAG + "initView", "now read message model click listener");
        adapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<Goods>,
                GoodsManagerItemViewHolder>() {
            @Override
            public void onClick(List<Goods> dataSource, GoodsManagerItemViewHolder holder) {
                if (holder.operationLinearLayout.getVisibility() == View.GONE) {
                    holder.operationLinearLayout.setVisibility(View.VISIBLE);
                } else {
                    holder.operationLinearLayout.setVisibility(View.GONE);
                }
            }
        });

        adapter.setOnEditClickListener(new OnItemClickListenerForRecyclerViewItem<List<Goods>,
                GoodsManagerItemViewHolder>() {
            @Override
            public void onClick(List<Goods> dataSource, GoodsManagerItemViewHolder holder) {
                Intent intent = new Intent(GoodsSupplyManagerActivity.this,
                        PublishGoodsSupplyActivity.class);
                intent.putExtra(StaticValue.IntentTag.GOODS_INTENT_TAG, dataSource.get(holder
                        .getAdapterPosition()));
                startActivity(intent);
            }
        });

        adapter.setOnDeleteClickListener(new OnItemClickListenerForRecyclerViewItem<List<Goods>,
                GoodsManagerItemViewHolder>() {
            @Override
            public void onClick(List<Goods> dataSource, final GoodsManagerItemViewHolder holder) {
                RationingGoods rationingGoods = new RationingGoods();
                rationingGoods.setWorkEndListener(new WorkBack<String>() {
                    @Override
                    public void doEndWork(boolean b, String s) {
                        Toast.makeText(GoodsSupplyManagerActivity.this, s, Toast.LENGTH_SHORT)
                                .show();
                        if (b) {
                            adapter.remove(holder.getAdapterPosition());
                        }
                    }
                });

                rationingGoods.beginExecute(GlobalApplication.getLoginStatus().getUserID(),
                        dataSource.get(holder.getAdapterPosition()).getGoodsId());
            }
        });

        adapter.setOnButtonClickListener(new OnItemClickListenerForRecyclerViewItem<List<Goods>,
                GoodsManagerItemViewHolder>() {
            @Override
            public void onClick(List<Goods> dataSource, GoodsManagerItemViewHolder holder) {
                Intent intent = new Intent(GoodsSupplyManagerActivity.this,
                        MobileVerificationLaunchActivity.class);
                intent.putExtra(StaticValue.IntentTag.GOODS_ID_TAG, dataSource.get(holder
                        .getAdapterPosition()).getGoodsId());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化数据
     */
    private void initData() {

        if (!GlobalApplication.getLoginStatus().isLogin()) {
            Toast.makeText(this, R.string.no_login, Toast.LENGTH_SHORT).show();
            return;
        }

        PullGoodsSupply pullGoodsSupply = new PullGoodsSupply();

        pullGoodsSupply.setWorkEndListener(new WorkBack<List<Goods>>() {
            @Override
            public void doEndWork(boolean state, List<Goods> data) {
                if (state) {
                    adapter.add(data);
                }
            }
        });

        pullGoodsSupply.beginExecute(GlobalApplication.getLoginStatus().getUserID());
    }
}
