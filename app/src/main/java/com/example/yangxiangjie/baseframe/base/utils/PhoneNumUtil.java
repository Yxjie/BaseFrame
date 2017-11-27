package com.example.yangxiangjie.baseframe.base.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by yangxiangjie on 2017/9/21.
 * 手机号码转化工具类
 */

public class PhoneNumUtil {


    /**
     * 手机号码中间显示✳️号
     *
     * @param phone String 手机号码
     * @return String 可返回null
     */
    @Nullable
    public static String getPhoneNumFormat(String phone) {
        if (!TextUtils.isEmpty(phone) && phone.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < phone.length(); i++) {
                char c = phone.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return null;
        }
    }


}
