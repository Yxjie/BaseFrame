package com.example.yangxiangjie.baseframe.base.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by yangxiangjie on 2017/10/30.
 */

public class MethodUtil {

    /**
     * 获取Retrofit @QueryMap @FieldMap
     *
     * @param bean
     * @return
     */
    public static Map<String, String> getHttpParams(BaseRequestBean bean) {
        Map<String, String> params = new HashMap<>();
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            getFields(params, field, bean);
        }
        return params;
    }


    /**
     * 分装类里面的值
     *
     * @param params
     * @param field
     * @param bean
     */
    private static void getFields(Map<String, String> params, Field field, BaseRequestBean bean) {
        //获取属性名字
        String name = field.getName();
        //获取属性类型
        String type = field.getGenericType().toString();
        if (TextUtils.equals(String.class.toString(), type)) {
            try {
                Class clz = Class.forName(bean.getClass().getName());
                java.lang.reflect.Method m = clz.cast(bean).getClass().getMethod("get" + captureName(name));
                //调用getter方法获取属性值
                String value = (String) m.invoke(bean);
                if (value != null) {
                    params.put(name, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (TextUtils.equals(int.class.toString(), type)) {
            try {
                Class clz = Class.forName(bean.getClass().getName());
                java.lang.reflect.Method method = clz.cast(bean).getClass().getMethod("get" + captureName(name));
                Object value = method.invoke(bean);
                if (value != null) {
                    params.put(name, value + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (TextUtils.equals(List.class.toString(), type)) {
            try {
                java.lang.reflect.Method m = bean.getClass().getMethod("get" + name);
                List<?> value = (List<?>) m.invoke(bean);
                if (value != null) {
                    params.put(name, new Gson().toJson(value));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /*********
     * 首字母大写
     *
     * @param name
     * @return
     */
    public static String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;

    }


    public static abstract class BaseRequestBean {

    }


}
