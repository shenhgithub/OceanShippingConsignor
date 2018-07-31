package com.port.ocean.shipping.consignor.adapter;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.ocean.shipping.consignor.R;
import com.port.ocean.shipping.consignor.data.Goods;

import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 货源管理列表数据适配器
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class GoodsManagerRecyclerViewAdapter extends RecyclerView
        .Adapter<GoodsManagerItemViewHolder> {

    /**
     * 消息列表
     */
    private List<Goods> goodsList = null;

    /**
     * Item点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<List<Goods>, GoodsManagerItemViewHolder>
            onItemClickListener = null;

    /**
     * 按钮点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<List<Goods>, GoodsManagerItemViewHolder>
            onButtonClickListener = null;

    /**
     * 编辑点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<List<Goods>, GoodsManagerItemViewHolder>
            onEditClickListener = null;

    /**
     * 删除点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<List<Goods>, GoodsManagerItemViewHolder>
            onDeleteClickListener = null;

    /**
     * 空构造函数
     */
    public GoodsManagerRecyclerViewAdapter() {
        goodsList = new ArrayList<>();
    }

    /**
     * 构造函数
     *
     * @param goodsList 初始化数据集
     */
    public GoodsManagerRecyclerViewAdapter(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    /**
     * 删除指定位置的一条数据
     *
     * @param position 位置索引
     *
     * @return 被删除的对象
     */
    public Goods remove(int position) {
        // 要移除的消息对象
        Goods goods = this.goodsList.remove(position);
        this.notifyItemRemoved(position);
        return goods;
    }

    /**
     * 在列表头部添加一组数据
     *
     * @param goodsList 数据集
     */
    public void add(List<Goods> goodsList) {
        this.goodsList.addAll(goodsList);
        this.notifyItemRangeInserted(0, goodsList.size());
    }

    /**
     * 设置数据集
     *
     * @param goodsList 数据集
     */
    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    /**
     * 设置Item点击事件
     *
     * @param onItemClickListener 点击事件
     */
    public void setOnItemClickListener(OnItemClickListenerForRecyclerViewItem<List<Goods>,
            GoodsManagerItemViewHolder> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 设置按钮点击事件监听器
     *
     * @param onButtonClickListener 点击事件
     */
    public void setOnButtonClickListener(OnItemClickListenerForRecyclerViewItem<List<Goods>,
            GoodsManagerItemViewHolder> onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    /**
     * 设置编辑点击事件监听器
     *
     * @param onEditClickListener 点击事件
     */
    public void setOnEditClickListener(OnItemClickListenerForRecyclerViewItem<List<Goods>,
            GoodsManagerItemViewHolder> onEditClickListener) {
        this.onEditClickListener = onEditClickListener;
    }

    /**
     * 设置撤销点击事件监听器
     *
     * @param onDeleteClickListener 点击事件
     */
    public void setOnDeleteClickListener(OnItemClickListenerForRecyclerViewItem<List<Goods>,
            GoodsManagerItemViewHolder> onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @Override
    public GoodsManagerItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建Item根布局
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                .goods_supply_manager_item, viewGroup, false);

        // 创建Item布局管理器
        return new GoodsManagerItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GoodsManagerItemViewHolder goodsManagerItemViewHolder, int
            position) {

        // 数据绑定
        Goods goods = goodsList.get(position);

        String start = null;

        if (goods.getSFDProvince().trim().length() != 0) {
            start = goods.getSFDProvince().trim();
        }

        if (goods.getSFDCity().trim().length() != 0) {
            start += "-" + goods.getSFDCity().trim();
        }

        if (goods.getSFDCounty().trim().length() != 0) {
            start += "-" + goods.getSFDCounty().trim();
        }

        String end = null;

        if (goods.getMDDProvince().trim().length() != 0) {
            end = goods.getMDDProvince().trim();
        }

        if (goods.getMDDCity().trim().length() != 0) {
            end += "-" + goods.getMDDCity().trim();
        }

        if (goods.getMDDCounty().trim().length() != 0) {
            end += "-" + goods.getMDDCounty().trim();
        }

        goodsManagerItemViewHolder.startRouteTextView.setText(start);
        goodsManagerItemViewHolder.endRouteTextView.setText(end);

        String attribute = null;

        if (goods.getGoods().trim().length() != 0) {
            attribute = goods.getGoods().trim();
        }

        if (goods.getWeight().trim().length() != 0) {
            attribute += "," + goods.getWeight().trim() + GlobalApplication.getGlobal().getString
                    (R.string.unit_quality_tons);
        }

        if (goods.getVehicleLen().trim().length() != 0) {
            attribute += "," + goods.getVehicleLen().trim() + GlobalApplication.getGlobal()
                    .getString(R.string.unit_length_meter);
        }

        if (goods.getVehicleType().trim().length() != 0) {
            attribute += "," + goods.getVehicleType().trim();
        }

        if (attribute != null && attribute.startsWith(",")) {
            attribute = attribute.substring(1);
        }

        goodsManagerItemViewHolder.attributeTextView.setText(attribute);

        goodsManagerItemViewHolder.timeTextView.setText(goods.getTime());

        if (onItemClickListener != null) {
            goodsManagerItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(goodsList, goodsManagerItemViewHolder);
                }
            });
        }

        if (onButtonClickListener != null) {
            goodsManagerItemViewHolder.rationingButton.setOnClickListener(new View
                    .OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClickListener.onClick(goodsList, goodsManagerItemViewHolder);
                }
            });
        }

        if (onEditClickListener != null) {
            goodsManagerItemViewHolder.editTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEditClickListener.onClick(goodsList, goodsManagerItemViewHolder);
                }
            });
        }

        goodsManagerItemViewHolder.deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClickListener.onClick(goodsList, goodsManagerItemViewHolder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }
}
