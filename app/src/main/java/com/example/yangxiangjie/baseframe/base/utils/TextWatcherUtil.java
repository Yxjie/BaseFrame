package com.example.yangxiangjie.baseframe.base.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by yangxiangjie on 2017/9/21.
 * TextWatcher监听工具类
 */

public enum TextWatcherUtil {

    INSTACE;

    /**
     * 手机号码长度
     */
    private static final int PHONE_LENGTH = 11;
    /**
     * 验证码和密码最小长度
     */
    private static final int CODE_OR_PASS_LENGTH = 6;

    private boolean enable;

    /**
     * 根据手机号码，验证码或密码长度判断底部按钮 是否可以点击
     *
     * @param phone     手机号
     * @param codeOrPwd 验证码或密码
     * @return true:btn可点击；否则，btn不可点击
     */
    public boolean isButtonEnable(final EditText phone, final EditText codeOrPwd) {
        if (phone == null || codeOrPwd == null) {
            //有一个控件为null 直接返回false
            return false;
        }

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String phoneStr = phone.getText().toString();
                String codeOrPwdStr = codeOrPwd.getText().toString();
                enable = phoneStr.length() == PHONE_LENGTH && codeOrPwdStr.length() >= CODE_OR_PASS_LENGTH;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        phone.addTextChangedListener(watcher);
        codeOrPwd.addTextChangedListener(watcher);
        return enable;
    }


}
