package com.port.ocean.shipping.consignor.adapter;
/**
 * Created by 超悟空 on 2016/3/16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.ocean.shipping.consignor.R;

import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表项仅包含文本框的列表适配器
 *
 * @author 超悟空
 * @version 1.0 2016/3/16
 * @since 1.0
 */
public class OnlyTextRecyclerViewAdapter extends RecyclerView.Adapter<OnlyTextItemViewHolder> {

    /**
     * 数据源列表
     */
    private List<String> dataList = null;

    /**
     * Item点击事件监听器
     */
    private OnItemClickListenerForRecyclerViewItem<List<String>, OnlyTextItemViewHolder>
            onItemClickListener = null;

    /**
     * 构造函数
     */
    public OnlyTextRecyclerViewAdapter() {
        this(null);
    }

    /**
     * 构造函数
     *
     * @param dataList 数据源
     */
    public OnlyTextRecyclerViewAdapter(List<String> dataList) {
        this.dataList = dataList;

        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }
    }

    /**
     * 设置数据源
     *
     * @param dataList 数据源列表
     */
    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    /**
     * 设置列表项点击事件监听器
     *
     * @param onItemClickListener 监听器
     */
    public void setOnItemClickListener(OnItemClickListenerForRecyclerViewItem<List<String>,
            OnlyTextItemViewHolder> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 在指定位置添加一条数据
     *
     * @param position 添加位置
     * @param data     数据
     */
    public void add(int position, String data) {
        this.dataList.add(position, data);
        this.notifyItemInserted(position);
    }

    /**
     * 在指定位置添加一组数据
     *
     * @param position 添加位置
     * @param dataList 数据集
     */
    public void addAll(int position, List<String> dataList) {
        this.dataList.addAll(position, dataList);
        this.notifyItemRangeInserted(position, dataList.size());
    }

    /**
     * 移除一条数据
     *
     * @param position 移除位置
     */
    public void remove(int position) {
        this.dataList.remove(position);
        this.notifyItemRemoved(position);
    }

    /**
     * 移除一条数据
     *
     * @param position 移除位置
     * @param count    移除数量
     */
    public void remove(int position, int count) {
        for (int i = 0; i < count; i++) {
            this.dataList.remove(position + i);
        }
        this.notifyItemRangeRemoved(position, count);
    }

    @Override
    public OnlyTextItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建Item根布局
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .only_mini_text_item, parent, false);

        // 创建Item布局管理器
        return new OnlyTextItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final OnlyTextItemViewHolder holder, int position) {
        holder.textView.setText(dataList.get(position));

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(dataList, holder);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
