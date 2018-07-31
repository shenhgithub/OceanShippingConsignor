package com.port.ocean.shipping.consignor.fragment;
/**
 * Created by 超悟空 on 2015/6/23.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.ocean.shipping.consignor.R;


/**
 * 配货页面
 *
 * @author 超悟空
 * @version 1.0 2015/6/23
 * @since 1.0
 */
public class RationingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rationing, container, false);

        return rootView;
    }
}
