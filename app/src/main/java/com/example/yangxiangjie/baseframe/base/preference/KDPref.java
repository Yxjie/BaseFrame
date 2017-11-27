package com.example.yangxiangjie.baseframe.base.preference;

import android.content.Context;

import com.example.yangxiangjie.baseframe.base.utils.PreferencesUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * 封装一个Preference
 */
public class KDPref {

    @Pref(type = Pref.Type.String, StringValue = "默认值")
    public static final String TAG_TEST = "TAG_TEST";

    @Pref(type = Pref.Type.Bool, boolValue = true)
    public static final String TAG_TEST_BOOL = "TAG_TEST_BOOL";


    public static int getInt(Context context, String prefName) {
        if (get(context, prefName) != null) {
            return (Integer) get(context, prefName);
        } else {
            return 0;
        }
    }

    public static long getLong(Context context, String prefName) {
        if (get(context, prefName) != null) {
            return (Long) get(context, prefName);
        } else {
            return 0;
        }
    }

    public static String getString(Context context, String prefName) {
        if (get(context, prefName) != null) {
            return (String) get(context, prefName);
        } else {
            return "";
        }
    }

    public static float getFloat(Context context, String prefName) {
        if (get(context, prefName) != null) {
            return (Float) get(context, prefName);
        } else {
            return 0;
        }
    }

    public static boolean getBoolean(Context context, String prefName) {
        if (get(context, prefName) != null) {
            return (boolean) get(context, prefName);
        } else {
            return false;
        }
    }

    /**
     * 设置值
     *
     * @param context  context
     * @param prefName pref名
     * @param obj      值
     */
    public static void set(Context context, String prefName, Object obj) {
        if (obj instanceof Integer) {
        } else if (obj instanceof String) {
            PreferencesUtils.putString(context, prefName, (String) obj);
        } else if (obj instanceof Long) {
            PreferencesUtils.putLong(context, prefName, (Long) obj);
        } else if (obj instanceof Float) {
            PreferencesUtils.putFloat(context, prefName, (Float) obj);
        }
    }

    /**
     * 获得值
     *
     * @param context  context
     * @param prefName pref名
     * @return 结果
     */
    private static Object get(Context context, String prefName) {
        try {
            Field field = KDPref.class.getField(prefName);
            Pref column = field.getAnnotation(Pref.class);
            if (column != null) {
                Pref.Type type = column.type();
                switch (type) {
                    case Long:
                        long val_long = column.longValue();
                        return PreferencesUtils.getLong(context, prefName, val_long);
                    case Int:
                        int val_int = column.intValue();
                        return PreferencesUtils.getInt(context, prefName, val_int);
                    case Bool:
                        boolean val_bool = column.boolValue();
                        return PreferencesUtils.getBoolean(context, prefName, val_bool);
                    case String:
                        String val_Str = column.StringValue();
                        return PreferencesUtils.getString(context, prefName, val_Str);
                    default:
                        return null;
                }
            }

        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Pref {

        Type type() default Type.Int;

        int intValue() default 0;

        long longValue() default 0;

        float floatValue() default 0;

        boolean boolValue() default false;

        String StringValue() default "";

        enum Type {
            Long, Int, Bool, String, Float
        }
    }

}
