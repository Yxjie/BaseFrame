package com.example.yangxiangjie.baseframe.base.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yangxiangjie.baseframe.R;

/**
 * Created by yangxiangjie on 2017/9/21.
 * 带有删除按钮的号码输入框
 */

public class PhoneView extends LinearLayout implements View.OnClickListener {
    private EditText mPhone;
    private ImageView mDeleteImg;

    public PhoneView(Context context) {
        this(context, null);
    }

    public PhoneView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhoneView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        addListener();
    }

    /**
     * 添加监听事件
     */
    private void addListener() {
        mDeleteImg.setOnClickListener(this);
        mPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean empty = TextUtils.isEmpty(s);
                mDeleteImg.setVisibility(empty ? View.GONE : View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_phone_edit_txt, this);
        mPhone = (EditText) findViewById(R.id.et_phone);
        mPhone.setHint("请输入手机号码");
        mDeleteImg = (ImageView) findViewById(R.id.btn_delete);
    }

    @Override
    public void onClick(View v) {
        mPhone.setText("");
    }

    /**
     * 获取手机输入框的控件
     *
     * @return EditText
     */
    public EditText getPhoneEditText() {
        return mPhone;
    }
}
