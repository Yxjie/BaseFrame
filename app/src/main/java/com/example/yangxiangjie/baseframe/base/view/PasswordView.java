package com.example.yangxiangjie.baseframe.base.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.yangxiangjie.baseframe.R;

/**
 * Created by yangxiangjie on 2017/9/21.
 * 密码登陆框
 */

public class PasswordView extends LinearLayout {

    private EditText mPassword;
    private CheckBox mShowBox;

    public PasswordView(Context context) {
        this(context, null);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        addListener();
    }

    /**
     * 添加监听事件
     */
    private void addListener() {
        mShowBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //显示密码
                    mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    //隐藏密码
                    mPassword.setInputType(InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                //光标设置末尾
                mPassword.setSelection(mPassword.getText().toString().length());
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_password_edit_txt, this);
        mPassword = (EditText) findViewById(R.id.et_password);
        mShowBox = (CheckBox) findViewById(R.id.ck_show);
        mPassword.setHint("请设置6—16位密码");
    }

    /**
     * 获取密码输入框
     *
     * @return EditText
     */
    public EditText getPasswordEditText() {
        return mPassword;
    }


}
