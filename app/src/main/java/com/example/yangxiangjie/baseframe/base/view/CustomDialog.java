package com.example.yangxiangjie.baseframe.base.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by yangxiangjie on 2017/9/21.
 * 自定义dialog
 */

public class CustomDialog {
    private AlertDialog mAlertDialog;

    public CustomDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        mAlertDialog = builder.create();
    }


}

