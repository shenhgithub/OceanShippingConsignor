package com.port.ocean.shipping.consignor.fragment;
/**
 * Created by 超悟空 on 2015/6/30.
 */

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.port.ocean.shipping.consignor.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 探索片段
 *
 * @author 超悟空
 * @version 1.0 2015/6/30
 * @since 1.0
 */
public class ExploreFragment extends Fragment {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "ExploreFragment.";

    /**
     * 功能标题的取值标签，用于数据适配器中
     */
    private static final String FUNCTION_TITLE = "function_title";

    /**
     * 功能图标取值图标
     */
    private static final String FUNCTION_IMAGE = "function_image";

    /**
     * 列表使用的数据适配器
     */
    private SimpleAdapter adapter = null;

    /**
     * 数据适配器的元数据
     */
    private List<Map<String, Object>> adapterDataList = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_explore, container, false);
        // 初始化功能列表
        initListView(rootView);

        return rootView;
    }

    /**
     * 初始化功能表格布局
     *
     * @param rootView 根布局
     */
    private void initListView(View rootView) {

        // 片段中的列表布局
        ListView listView = (ListView) rootView.findViewById(R.id.fragment_explore_list_view);

        // 列表使用的数据适配器
        adapter = new SimpleAdapter(getActivity(), getFunctionTitle(), R.layout
                .explore_function_item, new String[]{FUNCTION_TITLE , FUNCTION_IMAGE}, new
                int[]{R.id.explore_function_item_textView , R.id.explore_function_item_imageView});

        // 设置适配器
        listView.setAdapter(adapter);

    }

    /**
     * 生成功能项标签资源的数据源
     *
     * @return 返回SimpleAdapter适配器使用的数据源
     */
    private List<Map<String, Object>> getFunctionTitle() {
        // 加载功能项
        List<Map<String, Object>> dataList = new ArrayList<>();

        String[] functionTitle = getActivity().getResources().getStringArray(R.array
                .explore_function_title);
        // 资源类型数组
        TypedArray images = getResources().obtainTypedArray(R.array.explore_function_image);

        for (int i = 0; i < functionTitle.length; i++) {
            // 新建一个功能项标签
            Map<String, Object> function = new HashMap<>();

            // 添加标签资源
            // 添加标题
            function.put(FUNCTION_TITLE, functionTitle[i]);
            // 添加功能标签图标资源
            function.put(FUNCTION_IMAGE, images.getResourceId(i, R.mipmap.ic_launcher));

            // 将标签加入数据源
            dataList.add(function);
        }
        return dataList;
    }
}
