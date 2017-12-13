package com.example.yangxiangjie.baseframe.base.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.yangxiangjie.baseframe.R;

/**
 * Created by yangxiangjie on 2017/12/13.
 */

public class BaseDialogFragment extends DialogFragment implements View.OnClickListener {
    private TextView cancelBtn, okBtn, messageContent;
    private DialogClickEvent mOkEvent, mCancelEvent;

    private String okStr, cancelStr, messageStr;


    public static BaseDialogFragment newInstance() {
        return new BaseDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View inflateView = layoutInflater.inflate(R.layout.dialog_custom, null);
        messageContent = (TextView) inflateView.findViewById(R.id.dialog_txt_message);
        cancelBtn = (TextView) inflateView.findViewById(R.id.dialog_btn_cancel);
        okBtn = (TextView) inflateView.findViewById(R.id.dialog_btn_ok);

        if (!TextUtils.isEmpty(messageStr)) {
            messageContent.setText(messageStr);
        }

        if (!TextUtils.isEmpty(okStr)) {
            okBtn.setText(okStr);
        }

        if (!TextUtils.isEmpty(cancelStr)) {
            cancelBtn.setText(cancelStr);
        }

        cancelBtn.setOnClickListener(this);
        okBtn.setOnClickListener(this);
        builder.setView(inflateView);
        return builder.create();
    }

    /**
     * 设置Dialog提示内容
     *
     * @param message 提示内容
     * @return
     */
    public BaseDialogFragment setMessage(String message) {
        messageStr = TextUtils.isEmpty(message) ? "" : message;
        return this;
    }


    /**
     * 确定按钮的显示字段
     *
     * @param str   名称
     * @param event 对应点击事件
     * @return
     */
    public BaseDialogFragment setOkBtn(String str, @Nullable DialogClickEvent event) {
        okStr = TextUtils.isEmpty(str) ? "" : str;
        mOkEvent = event;
        return this;
    }


    /**
     * 取消按钮的显示字段
     *
     * @param str   名称
     * @param event 对应点击事件
     * @return
     */
    public BaseDialogFragment setCancelBtn(String str, @Nullable DialogClickEvent event) {
        cancelStr = TextUtils.isEmpty(str) ? "" : str;
        mCancelEvent = event;
        return this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_btn_cancel:
                if (mCancelEvent != null) {
                    mCancelEvent.onClick(v);
                }
                break;

            case R.id.dialog_btn_ok:
                if (mOkEvent != null) {
                    mOkEvent.onClick(v);
                }
                break;

            default:
                break;

        }
        dismiss();
    }


    public interface DialogClickEvent {
        void onClick(View view);
    }
}
