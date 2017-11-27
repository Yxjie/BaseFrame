package com.example.yangxiangjie.baseframe.base.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by yangxiangjie on 2017/9/21.
 * 输入法工具类
 */

public enum InputUtil {

    INSTANCE;

    /**
     * 弹出软键盘
     *
     * @param view        EditText
     * @param delayMillis 延迟弹出事件
     */
    public void showInputMethodService(final EditText view, int delayMillis) {
        view.setFocusable(true);
        view.requestFocus();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, 0);
            }
        }, delayMillis);
    }

    /**
     * 强制弹出软键盘
     *
     * @param editText 输入框
     */
    public void showKeyboard(EditText editText) {
        showInputMethodService(editText, 1000);
    }


    /**
     * 隐藏虚拟键盘
     *
     * @param v
     */
    public void hideKeyboard(EditText v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

        }
    }


    /**
     * 输入法是否显示着
     *
     * @param edittext
     * @return
     */
    public boolean isKeyBoardShow(EditText edittext) {
        InputMethodManager imm = (InputMethodManager) edittext.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();
    }

}
