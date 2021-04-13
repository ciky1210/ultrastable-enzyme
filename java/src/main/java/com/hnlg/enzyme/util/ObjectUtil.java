package com.hnlg.enzyme.util;



import com.hnlg.enzyme.constant.ObjectTypeConstant;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 对象工具类
 */
public class ObjectUtil {

    @SuppressWarnings("unused")
    private static void reflect(Object obj) throws Exception {
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            System.out.println(f.getGenericType());
            System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(obj));
        }
    }

    /**
     * 将一个对象转换成一个map
     * key为属性名，value为属性值
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> getAttributeMap(Object obj) throws IllegalAccessException {
        Map<String, Object> attributeMap = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            attributeMap.put(field.getName().toLowerCase(), field.get(obj));
        }
        return attributeMap;
    }

    /**
     * 把对象中属于string类型的参数 trim
     */
    public static void objectStrParamTrim(Object obj) {
        Class<?> tempClass = obj.getClass();
        List<Field> fieldList = new ArrayList<>();
        //递归获取所有的类
        int i = 0;
        while (i <= 2) {// 为了速度只遍历两层
            if (tempClass != null) {
                fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
            }
            i++;
        }
        // 遍历对象
        for (Field f : fieldList) {
            f.setAccessible(true);
            Type genericType = f.getGenericType();
            // 如果为空，或者不是String 类型，跳出
            if (genericType == null || !genericType.toString().equals(ObjectTypeConstant.CLASS_TYPE_STRING)) {
                continue;
            }
            // 获取对象属性，如果不为空，则trim
            try {
                Object paramObj = f.get(obj);
                if (paramObj != null) {
                    f.set(obj, paramObj.toString().trim());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 把对象中属于string类型的参数 如果为空，则转换为"NaN" Integer 类型为0
     */
    public static void objectNullToEmptyOrZero(Object obj) {
        Class<?> tempClass = obj.getClass();
        List<Field> fieldList = new ArrayList<>();
        //递归获取所有的类
        int i = 0;
        while (i <= 2) {// 为了速度只遍历两层
            if (tempClass != null) {
                fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
            }
            i++;
        }
        // 遍历对象
        for (Field f : fieldList) {
            f.setAccessible(true);
            Type genericType = f.getGenericType();
            // 如果为空，或者不是String 类型，跳出
            if (genericType == null) {
                continue;
            }
            if (genericType.toString().equals(ObjectTypeConstant.CLASS_TYPE_STRING)) {
                // 获取对象属性，如果不为空，则trim
                try {
                    Object paramObj = f.get(obj);
                    if (paramObj == null) {
                        f.set(obj, "NaN");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (genericType.toString().equals(ObjectTypeConstant.CLASS_TYPE_INTEGER)) {
                // 获取对象属性，如果不为空，则trim
                try {
                    Object paramObj = f.get(obj);
                    if (paramObj == null) {
                        f.set(obj, 0);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 根据变量 名字获取变量属性
     *
     * @param obj
     * @param fieldName
     * @throws Exception
     */
    public static Object getObjField(Object obj, String fieldName) throws Exception {
        Class<?> cls = obj.getClass();
        //设置访问权限
        Field field = cls.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    /**
     * 判断Object对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().equals("");
        }
        if (obj instanceof Integer) {
            return (Integer) obj == 0;
        }
        return false;
    }
}
