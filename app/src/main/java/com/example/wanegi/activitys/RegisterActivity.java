package com.example.wanegi.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.wanegi.R;
import com.example.wanegi.utils.UserUtils;
import com.example.wanegi.views.InputView;

public class RegisterActivity extends BaseActivity {

    private InputView mInputPhone, mInputPassword, mInputPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    /**
     * 初始化View
     */
    private void initView () {
        initNavBar(true, "注册", false);
        mInputPhone = fd(R.id.input_phone);
        mInputPassword = fd(R.id.input_password);
        mInputPasswordConfirm = fd(R.id.input_password_confirm);
    }


    /**
     * 注册按钮点击事件
     * 1、用户输入合法性验证
     *      1、用户输入的手机号是不是合法的
     *      2、用户是否输入了密码和确定密码，并且这两次输入是否相同
     *      3、输入的手机号是否已经被注册
     * 2、保存用户输入的手机号和密码（MD5加密密码）
     */
    public void onRegisterClick (View v) {
        String phone = mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();
        String passwordConfirm = mInputPasswordConfirm.getInputStr();

        boolean result = UserUtils.registerUser(this, phone, password, passwordConfirm);

        if (!result) return;

        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();

//        后退页面
        onBackPressed();
    }
}
