package com.port.ocean.shipping.consignor.function;
/**
 * Created by 超悟空 on 2015/7/18.
 */

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.port.ocean.shipping.consignor.R;
import com.port.ocean.shipping.consignor.adapter.OnlyTextItemViewHolder;
import com.port.ocean.shipping.consignor.adapter.OnlyTextRecyclerViewAdapter;

import org.mobile.library.model.function.ISelectList;
import org.mobile.library.model.operate.OnItemClickListenerForRecyclerViewItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 车长选择列表
 *
 * @author 超悟空
 * @version 2.0 2016/3/24
 * @since 1.0
 */
public class VehicleLengthSelectList implements ISelectList<View, String> {

    /**
     * 结果监听器
     */
    private OnSelectedListener<View, String> onSelectedListener = null;

    /**
     * 上下文
     */
    private Context context = null;

    /**
     * 当前的选择布局
     */
    private View view = null;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public VehicleLengthSelectList(Context context) {
        this.context = context;
    }

    @Override
    public void setOnSelectedListener(OnSelectedListener<View, String> onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }

    @Override
    public View loadSelect() {
        if (view == null) {
            view = onCreateView();
        }
        return view;
    }

    /**
     * 创建布局
     *
     * @return 布局实例
     */
    private View onCreateView() {
        View view = LayoutInflater.from(context).inflate(R.layout.only_recycler_view_layout, null);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id
                .only_recycler_view_layout_recyclerView);

        // 创建布局管理器
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 4);

        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        // 车长数据源
        String[] vehicleLengthList = context.getResources().getStringArray(R.array
                .vehicle_length_select_list);

        List<String> dataList = new ArrayList<>();
        Collections.addAll(dataList, vehicleLengthList);

        OnlyTextRecyclerViewAdapter adapter = new OnlyTextRecyclerViewAdapter(dataList);

        if (onSelectedListener != null) {
            // 绑定点击事件
            adapter.setOnItemClickListener(new OnItemClickListenerForRecyclerViewItem<List<String>, OnlyTextItemViewHolder>() {
                @Override
                public void onClick(List<String> dataSource, OnlyTextItemViewHolder holder) {

                    int position = holder.getAdapterPosition();

                    onSelectedListener.onFinish(dataSource.get(position));
                }
            });
        }

        recyclerView.setAdapter(adapter);

        return view;
    }
}
