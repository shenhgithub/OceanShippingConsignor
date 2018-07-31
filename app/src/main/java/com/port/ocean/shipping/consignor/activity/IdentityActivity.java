package com.port.ocean.shipping.consignor.activity;
/**
 * Created by 超悟空 on 2015/6/24.
 */

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.port.ocean.shipping.consignor.R;
import com.port.ocean.shipping.consignor.data.IdentityConsignorInfoData;
import com.port.ocean.shipping.consignor.work.IdentityAuthenticate;
import com.port.ocean.shipping.consignor.work.PullIdentityConsignorInfo;

import org.mobile.library.common.dialog.SimpleDialog;
import org.mobile.library.global.GlobalApplication;
import org.mobile.library.model.work.WorkBack;


/**
 * 身份认证
 *
 * @author 超悟空
 * @version 1.0 2015/6/24
 * @since 1.0
 */
public class IdentityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_authentication);

        // 初始化布局
        initView();
        // 初始化数据
        initData();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        // 初始化Toolbar
        initToolbar();
        // 设置标题
        setTitle(R.string.title_identity_authentication);
        // 初始化认证按钮
        initButton();

        // 初始化拍照按钮
        initCamera();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        PullIdentityConsignorInfo pullIdentityInfo = new PullIdentityConsignorInfo();

        pullIdentityInfo.setWorkEndListener(new WorkBack<IdentityConsignorInfoData>() {
            @Override
            public void doEndWork(boolean state, IdentityConsignorInfoData data) {
                if (state) {
                    // 填充数据
                    onFillData(data);
                }
            }
        });

        pullIdentityInfo.beginExecute(GlobalApplication.getLoginStatus().getUserID());
    }

    /**
     * 填充数据
     *
     * @param data 数据源
     */
    private void onFillData(IdentityConsignorInfoData data) {
        // 获取文本对象
        EditText nameEditText = (EditText) findViewById(R.id
                .activity_identity_authentication_name_edit);
        EditText idCardEditText = (EditText) findViewById(R.id
                .activity_identity_authentication_id_card_edit);

        // 填充数据
        nameEditText.setText(data.getUserName());
        idCardEditText.setText(data.getIdentityCard());
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
     * 初始化认证按钮
     */
    private void initButton() {

        // 认证按钮
        Button button = (Button) findViewById(R.id.activity_identity_authentication_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAuthentication();
            }
        });

    }

    /**
     * 执行认证按钮点击事件
     */
    private void onAuthentication() {
        // 提取文本信息
        EditText nameEditText = (EditText) findViewById(R.id
                .activity_identity_authentication_name_edit);
        EditText idCardEditText = (EditText) findViewById(R.id
                .activity_identity_authentication_id_card_edit);

        String name = nameEditText.getText().toString().trim();
        String idCard = idCardEditText.getText().toString().trim();


        if (!GlobalApplication.getLoginStatus().isLogin()) {
            Toast.makeText(this, R.string.no_login, Toast.LENGTH_SHORT).show();
            return;
        }

        if (name.length() == 0 || idCard.length() == 0) {
            Toast.makeText(this, R.string.info_incomplete, Toast.LENGTH_SHORT).show();
            return;
        }

        // 新建认证对象
        IdentityAuthenticate identityAuthenticate = new IdentityAuthenticate();

        identityAuthenticate.setWorkEndListener(new WorkBack<String>() {
            @Override
            public void doEndWork(boolean state, String data) {
                if (state) {
                    Toast.makeText(IdentityActivity.this, data, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    SimpleDialog.showDialog(IdentityActivity.this, data);
                }
            }
        });

        identityAuthenticate.beginExecute(GlobalApplication.getLoginStatus().getUserID(), name,
                idCard);
    }

    /**
     * 初始化拍照按钮
     */
    private void initCamera() {
        TextView idCardCameraTextView = (TextView) findViewById(R.id
                .activity_identity_authentication_id_card_camera);
        TextView businessCardCameraTextView = (TextView) findViewById(R.id
                .activity_identity_authentication_business_card_camera);

        idCardCameraTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        businessCardCameraTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
    }

    /**
     * 打开相机
     */
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
}
