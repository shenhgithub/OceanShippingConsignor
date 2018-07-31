package com.port.ocean.shipping.consignor.activity;
/**
 * Created by 超悟空 on 2015/6/25.
 */

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.port.ocean.shipping.consignor.R;
import com.port.ocean.shipping.consignor.data.Goods;
import com.port.ocean.shipping.consignor.data.PublishGoodsSupplyData;
import com.port.ocean.shipping.consignor.function.CargoTypeSelectList;
import com.port.ocean.shipping.consignor.function.VehicleLengthSelectList;
import com.port.ocean.shipping.consignor.function.VehicleTypeSelectList;
import com.port.ocean.shipping.consignor.util.StaticValue;
import com.port.ocean.shipping.consignor.work.PublishGoodsSupply;
import com.port.ocean.shipping.consignor.work.UpdateGoods;

import org.mobile.library.common.dialog.SimpleDialog;
import org.mobile.library.common.function.CitySelectList;
import org.mobile.library.common.function.InputMethodController;
import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.function.ISelectList;
import org.mobile.library.model.work.WorkBack;


/**
 * 发布货源Activity
 *
 * @author 超悟空
 * @version 1.0 2015/6/25
 * @since 1.0
 */
public class PublishGoodsSupplyActivity extends AppCompatActivity {

    /**
     * 子控件集合
     *
     * @author 超悟空
     * @version 1.0 2015/7/22
     * @since 1.0
     */
    private class ViewHolder {

        /**
         * 用于显示选择列表的窗口
         */
        public PopupWindow popupWindow = null;

        /**
         * 弹出窗口的内容布局
         */
        public CardView cardView = null;

        /**
         * 货源ID
         */
        public String goodsId = null;

        /**
         * 始发地输入框
         */
        public EditText startEditText = null;

        /**
         * 目的地输入框
         */
        public EditText endEditText = null;

        /**
         * 距离
         */
        public EditText distanceEditText = null;

        /**
         * 货物类型输入框
         */
        public EditText goodsNameEditText = null;

        /**
         * 车型输入框
         */
        public EditText vehicleTypeEditText = null;

        /**
         * 车长输入框
         */
        public EditText vehicleLengthEditText = null;

        /**
         * 重量输入框
         */
        public EditText weightEditText = null;

        /**
         * 体积输入框
         */
        public EditText volumeEditText = null;

        /**
         * 联系人
         */
        public EditText contactEditText = null;

        /**
         * 手机号
         */
        public EditText mobileEditText = null;

        /**
         * 电座机号
         */
        public EditText phoneEditText = null;

        /**
         * 货物名称选择按钮
         */
        public Button goodsNameButton = null;

        /**
         * 车长选择按钮
         */
        public Button vehicleLengthButton = null;

        /**
         * 发布按钮
         */
        public Button publishButton = null;

        /**
         * 起始城市选择列表
         */
        public CitySelectList startCitySelectList = null;

        /**
         * 终点城市选择列表
         */
        public CitySelectList endCitySelectList = null;

        /**
         * 车长选择工具
         */
        public VehicleLengthSelectList vehicleLengthSelectList = null;

        /**
         * 车型选择工具
         */
        public VehicleTypeSelectList vehicleTypeSelectList = null;

        /**
         * 货物名称选择工具
         */
        public CargoTypeSelectList cargoTypeSelectList = null;
    }

    /**
     * 子控件集合对象
     */
    private ViewHolder viewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_goods_supply);

        // 初始化控件集
        initViewHolder();

        // 尝试获取数据
        initData();

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
        setTitle(R.string.title_publish_goods_supply);
        // 初始化弹出框
        initPopupWindow();
        // 初始化选择输入框点击事件
        initSelectEdit();
        // 初始化认证按钮
        initButton();
    }

    /**
     * 初始化标题栏
     */
    @SuppressWarnings("ConstantConditions")
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
     * 初始化控件集引用
     */
    private void initViewHolder() {
        // 弹出窗口布局
        viewHolder.cardView = (CardView) LayoutInflater.from(this).inflate(R.layout
                .layout_popup_window, null);
        viewHolder.popupWindow = new PopupWindow(this);

        // 始发地
        viewHolder.startEditText = (EditText) findViewById(R.id
                .activity_publish_goods_supply_start_editText);
        // 目的地
        viewHolder.endEditText = (EditText) findViewById(R.id
                .activity_publish_goods_supply_end_editText);
        // 距离
        viewHolder.distanceEditText = (EditText) findViewById(R.id
                .activity_publish_goods_supply_distance_editText);
        // 货物类型
        viewHolder.goodsNameEditText = (EditText) findViewById(R.id
                .activity_publish_goods_supply_goods_name_editText);
        // 重量
        viewHolder.weightEditText = (EditText) findViewById(R.id
                .activity_publish_goods_supply_weight);
        // 体积
        viewHolder.volumeEditText = (EditText) findViewById(R.id.activity_publish_goods_supply_vol);
        // 车长
        viewHolder.vehicleLengthEditText = (EditText) findViewById(R.id
                .activity_publish_goods_supply_length);
        // 车型
        viewHolder.vehicleTypeEditText = (EditText) findViewById(R.id
                .activity_publish_goods_supply_model);
        // 联系人
        viewHolder.contactEditText = (EditText) findViewById(R.id
                .activity_publish_goods_supply_contact);
        // 手机号
        viewHolder.mobileEditText = (EditText) findViewById(R.id
                .activity_publish_goods_supply_mobile);
        // 座机号
        viewHolder.phoneEditText = (EditText) findViewById(R.id
                .activity_publish_goods_supply_phone);
        // 货物名称选择按钮
        viewHolder.goodsNameButton = (Button) findViewById(R.id
                .activity_publish_goods_supply_goods_name_button);
        // 车长选择按钮
        viewHolder.vehicleLengthButton = (Button) findViewById(R.id
                .activity_publish_goods_supply_length_button);
        // 发布
        viewHolder.publishButton = (Button) findViewById(R.id.activity_publish_goods_supply_button);
        // 城市选择工具
        viewHolder.startCitySelectList = new CitySelectList(this);
        viewHolder.endCitySelectList = new CitySelectList(this);
        // 车长选择工具
        viewHolder.vehicleLengthSelectList = new VehicleLengthSelectList(this);
        // 车型选择工具
        viewHolder.vehicleTypeSelectList = new VehicleTypeSelectList(this);
        // 货物名称选择工具
        viewHolder.cargoTypeSelectList = new CargoTypeSelectList(this);
    }

    /**
     * 初始化弹出框
     */
    private void initPopupWindow() {
        viewHolder.popupWindow.setContentView(viewHolder.cardView);
        viewHolder.popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        viewHolder.popupWindow.setHeight(getResources().getDimensionPixelOffset(R.dimen
                .filter_popup_window_height));
        viewHolder.popupWindow.setFocusable(true);
        viewHolder.popupWindow.setOutsideTouchable(true);
        viewHolder.popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    /**
     * 显示PopupWindow
     *
     * @param anchor 依附的布局
     * @param view   要显示的布局
     */
    private void showPopupWindow(View anchor, View view) {
        // 关闭软键盘
        InputMethodController.CloseInputMethod(PublishGoodsSupplyActivity.this);

        if (!viewHolder.popupWindow.isShowing()) {
            viewHolder.cardView.removeAllViews();
            viewHolder.cardView.addView(view);
            viewHolder.popupWindow.showAsDropDown(anchor);
        }
    }

    /**
     * 初始化选择输入框点击事件
     */
    private void initSelectEdit() {
        // 初始化货物名称选择
        initGoodsNameSelect();
        // 初始化车型选择
        initVehicleTypeSelect();
        // 初始化车长选择
        initVehicleLengthSelect();
        // 初始化城市选择
        initCitySelect();
    }

    /**
     * 初始化货物名称选择
     */
    private void initGoodsNameSelect() {
        // 设置点击事件
        viewHolder.goodsNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.cargoTypeSelectList.loadSelect());
            }
        });

        // 设置选择结果回调
        viewHolder.cargoTypeSelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                viewHolder.goodsNameEditText.setText(s);
                viewHolder.popupWindow.dismiss();
            }

            @Override
            public void onCancel(View view) {

            }
        });
    }

    /**
     * 初始化车型选择
     */
    private void initVehicleTypeSelect() {

        // 设置点击事件
        viewHolder.vehicleTypeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.vehicleTypeSelectList.loadSelect());
            }
        });

        // 设置选择结果回调
        viewHolder.vehicleTypeSelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                viewHolder.vehicleTypeEditText.setText(s);
                viewHolder.popupWindow.dismiss();
            }

            @Override
            public void onCancel(View view) {

            }
        });
    }

    /**
     * 初始化车长选择
     */
    private void initVehicleLengthSelect() {

        // 设置点击事件
        viewHolder.vehicleLengthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.vehicleLengthSelectList.loadSelect());
            }
        });

        // 设置选择结果回调
        viewHolder.vehicleLengthSelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                viewHolder.vehicleLengthEditText.setText(s);
                viewHolder.popupWindow.dismiss();
            }

            @Override
            public void onCancel(View view) {

            }
        });
    }

    /**
     * 初始化城市选择
     */
    private void initCitySelect() {
        // 设置点击事件
        viewHolder.startEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.startCitySelectList.loadSelect());
            }
        });

        viewHolder.startCitySelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                viewHolder.startEditText.setText(s);

                viewHolder.popupWindow.dismiss();
            }

            @Override
            public void onCancel(View view) {

            }
        });

        viewHolder.endEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, viewHolder.endCitySelectList.loadSelect());
            }
        });

        viewHolder.endCitySelectList.setOnSelectedListener(new ISelectList
                .OnSelectedListener<View, String>() {
            @Override
            public void onFinish(String s) {
                if (s != null) {
                    viewHolder.endEditText.setText(s);
                }
                viewHolder.popupWindow.dismiss();
            }

            @Override
            public void onCancel(View view) {

            }
        });
    }

    /**
     * 初始化发布按钮
     */
    private void initButton() {

        if (viewHolder.goodsId == null) {

            viewHolder.publishButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 关闭点击响应
                    viewHolder.publishButton.setEnabled(false);
                    // 执行发布
                    onPublishGoods();
                }
            });
        } else {

            viewHolder.publishButton.setText(R.string.update_goods_button);

            viewHolder.publishButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 关闭点击响应
                    viewHolder.publishButton.setEnabled(false);
                    // 执行更新
                    onUpdateGoods();
                }
            });
        }
    }

    /**
     * 执行修改按钮点击事件
     */
    private void onUpdateGoods() {
        // 关闭软键盘
        InputMethodController.CloseInputMethod(this);
        // 获取将要发送的数据对象
        Goods goods = fillUpdateGoodsSupplyData();

        if (goods == null) {
            // 数据不完整
            // 打开点击响应
            viewHolder.publishButton.setEnabled(true);
            return;
        }

        // 新建更新任务对象
        UpdateGoods updateGoods = new UpdateGoods();

        updateGoods.setWorkEndListener(new WorkBack<String>() {
            @Override
            public void doEndWork(boolean state, String data) {
                if (state) {
                    Toast.makeText(PublishGoodsSupplyActivity.this, data, Toast.LENGTH_SHORT)
                            .show();
                    finish();
                } else {
                    SimpleDialog.showDialog(PublishGoodsSupplyActivity.this, data);
                    // 打开点击响应
                    viewHolder.publishButton.setEnabled(true);
                }
            }
        });

        updateGoods.beginExecute(goods);
    }

    private Goods fillUpdateGoodsSupplyData() {
        // 提取文本信息

        String[] start = viewHolder.startEditText.getText().toString().trim().split("-");
        String[] end = viewHolder.endEditText.getText().toString().trim().split("-");

        String distance = viewHolder.distanceEditText.getText().toString().trim();
        String goodsName = viewHolder.goodsNameEditText.getText().toString().trim();
        String weight = viewHolder.weightEditText.getText().toString().trim();
        String volume = viewHolder.volumeEditText.getText().toString().trim();
        String vehicleLength = viewHolder.vehicleLengthEditText.getText().toString().trim();
        String vehicleType = viewHolder.vehicleTypeEditText.getText().toString().trim();
        String contact = viewHolder.contactEditText.getText().toString().trim();
        String mobile = viewHolder.mobileEditText.getText().toString().trim();
        String phone = viewHolder.phoneEditText.getText().toString().trim();

        if (viewHolder.goodsId == null) {
            Toast.makeText(this, R.string.data_error, Toast.LENGTH_SHORT).show();
            return null;
        }

        if (start.length == 0 || end.length == 0 || start[0].length() == 0 || end[0].length() ==
                0 || goodsName.length() == 0 || mobile.length() == 0) {
            Toast.makeText(this, R.string.info_incomplete, Toast.LENGTH_SHORT).show();
            return null;
        }

        // 新建货物数据对象
        Goods goods = new Goods();
        goods.setGoodsId(viewHolder.goodsId);
        goods.setSFDProvince(start[0]);
        goods.setSFDCity(start.length > 1 ? start[1] : "");
        goods.setSFDCounty(start.length > 2 ? start[2] : "");
        goods.setMDDProvince(end[0]);
        goods.setMDDCity(end.length > 1 ? end[1] : "");
        goods.setMDDCounty(end.length > 2 ? end[2] : "");
        goods.setGoods(goodsName);
        goods.setWeight(weight);
        goods.setVolume(volume);
        goods.setVehicleLen(vehicleLength);
        goods.setVehicleType(vehicleType);
        goods.setMobile(mobile);
        goods.setPhone(phone);
        goods.setDistance(distance);
        goods.setContactMan(contact);

        return goods;
    }

    /**
     * 执行发布按钮点击事件
     */
    private void onPublishGoods() {
        // 关闭软键盘
        InputMethodController.CloseInputMethod(this);
        // 获取将要发送的数据对象
        PublishGoodsSupplyData publishGoodsSupplyData = fillPublishGoodsSupplyData();

        if (publishGoodsSupplyData == null) {
            // 数据不完整
            // 打开点击响应
            viewHolder.publishButton.setEnabled(true);
            return;
        }

        // 新建发布任务对象
        PublishGoodsSupply publishGoodsSupply = new PublishGoodsSupply();

        publishGoodsSupply.setWorkEndListener(new WorkBack<String>() {
            @Override
            public void doEndWork(boolean state, String data) {

                if (state) {
                    Toast.makeText(PublishGoodsSupplyActivity.this, data, Toast.LENGTH_SHORT)
                            .show();
                    finish();
                } else {
                    SimpleDialog.showDialog(PublishGoodsSupplyActivity.this, data);
                    // 打开点击响应
                    viewHolder.publishButton.setEnabled(true);
                }
            }
        });

        publishGoodsSupply.beginExecute(publishGoodsSupplyData);
    }

    /**
     * 填充货源数据
     *
     * @return 货源数据对象
     */
    private PublishGoodsSupplyData fillPublishGoodsSupplyData() {

        // 提取文本信息
        String[] start = viewHolder.startEditText.getText().toString().trim().split("-");
        String[] end = viewHolder.endEditText.getText().toString().trim().split("-");

        String distance = viewHolder.distanceEditText.getText().toString().trim();
        String goodsName = viewHolder.goodsNameEditText.getText().toString().trim();
        String weight = viewHolder.weightEditText.getText().toString().trim();
        String volume = viewHolder.volumeEditText.getText().toString().trim();
        String vehicleLength = viewHolder.vehicleLengthEditText.getText().toString().trim();
        String vehicleType = viewHolder.vehicleTypeEditText.getText().toString().trim();
        String contact = viewHolder.contactEditText.getText().toString().trim();
        String mobile = viewHolder.mobileEditText.getText().toString().trim();
        String phone = viewHolder.phoneEditText.getText().toString().trim();

        if (!GlobalApplication.getLoginStatus().isLogin()) {
            Toast.makeText(this, R.string.no_login, Toast.LENGTH_SHORT).show();
            return null;
        }

        if (start.length == 0 || end.length == 0 || start[0].length() == 0 || end[0].length() ==
                0 || goodsName.length() == 0 || mobile.length() == 0) {
            Toast.makeText(this, R.string.info_incomplete, Toast.LENGTH_SHORT).show();
            return null;
        }

        // 新建数据对象
        PublishGoodsSupplyData publishGoodsSupplyData = new PublishGoodsSupplyData();
        publishGoodsSupplyData.setAccount(GlobalApplication.getLoginStatus().getUserID());
        publishGoodsSupplyData.setSFDProvince(start[0]);
        publishGoodsSupplyData.setSFDCity(start.length > 1 ? start[1] : "");
        publishGoodsSupplyData.setSFDCounty(start.length > 2 ? start[2] : "");
        publishGoodsSupplyData.setMDDProvince(end[0]);
        publishGoodsSupplyData.setMDDCity(end.length > 1 ? end[1] : "");
        publishGoodsSupplyData.setMDDCounty(end.length > 2 ? end[2] : "");
        publishGoodsSupplyData.setDistance(distance);
        publishGoodsSupplyData.setGoods(goodsName);
        publishGoodsSupplyData.setWeight(weight);
        publishGoodsSupplyData.setVolume(volume);
        publishGoodsSupplyData.setVehicleLen(vehicleLength);
        publishGoodsSupplyData.setVehicleType(vehicleType);
        publishGoodsSupplyData.setContact(contact);
        publishGoodsSupplyData.setMobile(mobile);
        publishGoodsSupplyData.setPhone(phone);
        return publishGoodsSupplyData;
    }

    /**
     * 尝试加载数据
     */
    private void initData() {
        // 取出传递的参数
        Object o = getIntent().getSerializableExtra(StaticValue.IntentTag.GOODS_INTENT_TAG);

        if (o instanceof Goods) {
            // 得到消息对象
            Goods goods = (Goods) o;

            fillData(goods);
        }
    }

    /**
     * 拼接完整地址
     *
     * @param province 省
     * @param city     市
     * @param county   区
     *
     * @return 用'-'分隔的完整字符串
     */
    private String fillFinalCity(String province, String city, String county) {
        String value = province;

        if (city == null || city.length() > 0) {
            value += "-" + city;
        }

        if (county != null && county.length() > 0) {
            value += "-" + county;
        }

        return value;
    }

    /**
     * 填充数据
     *
     * @param goods 数据对象
     */
    private void fillData(Goods goods) {

        // 获取货源ID
        viewHolder.goodsId = goods.getGoodsId();

        // 填充始发地
        viewHolder.startEditText.setText(fillFinalCity(goods.getSFDProvince(), goods.getSFDCity()
                , goods.getSFDCounty()));
        // 填充目的地
        viewHolder.endEditText.setText(fillFinalCity(goods.getMDDProvince(), goods.getMDDCity(),
                goods.getMDDCounty()));

        viewHolder.distanceEditText.setText(goods.getDistance());
        viewHolder.goodsNameEditText.setText(goods.getGoods());
        viewHolder.weightEditText.setText(goods.getWeight());
        viewHolder.volumeEditText.setText(goods.getVolume());
        viewHolder.vehicleLengthEditText.setText(goods.getVehicleLen());
        viewHolder.vehicleTypeEditText.setText(goods.getVehicleType());
        viewHolder.contactEditText.setText(goods.getContactMan());
        viewHolder.mobileEditText.setText(goods.getMobile());
        viewHolder.phoneEditText.setText(goods.getPhone());
    }
}
