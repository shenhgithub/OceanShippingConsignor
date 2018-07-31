package com.port.ocean.shipping.consignor.adapter;
/**
 * Created by 超悟空 on 2015/6/26.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.port.ocean.shipping.consignor.R;

/**
 * 货源管理Item的View管理器
 *
 * @author 超悟空
 * @version 1.0 2015/6/26
 * @since 1.0
 */
public class GoodsManagerItemViewHolder extends RecyclerView.ViewHolder {

    /**
     * 起始路线文本
     */
    public TextView startRouteTextView = null;

    /**
     * 终点路线文本
     */
    public TextView endRouteTextView = null;

    /**
     * 相关属性文本
     */
    public TextView attributeTextView = null;

    /**
     * 配货按钮
     */
    public Button rationingButton = null;

    /**
     * 时间文本
     */
    public TextView timeTextView = null;

    /**
     * 隐藏的操作布局
     */
    public LinearLayout operationLinearLayout = null;

    /**
     * 编辑按钮
     */
    public TextView editTextView = null;

    /**
     * 删除按钮
     */
    public TextView deleteTextView = null;

    /**
     * 构造函数
     *
     * @param itemView 根布局
     */
    public GoodsManagerItemViewHolder(View itemView) {
        super(itemView);

        // 设置子控件
        startRouteTextView = (TextView) itemView.findViewById(R.id
                .goods_supply_manager_route_start_textView);
        endRouteTextView = (TextView) itemView.findViewById(R.id
                .goods_supply_manager_route_end_textView);
        attributeTextView = (TextView) itemView.findViewById(R.id
                .goods_supply_manager_attribute_textView);
        rationingButton = (Button) itemView.findViewById(R.id
                .goods_supply_manager_rationing_button);
        operationLinearLayout = (LinearLayout) itemView.findViewById(R.id
                .goods_supply_manager_operation_layout);
        editTextView = (TextView) itemView.findViewById(R.id.goods_supply_manager_edit_textView);
        deleteTextView = (TextView) itemView.findViewById(R.id
                .goods_supply_manager_delete_textView);
        timeTextView = (TextView) itemView.findViewById(R.id.goods_supply_manager_time_textView);
    }
}
