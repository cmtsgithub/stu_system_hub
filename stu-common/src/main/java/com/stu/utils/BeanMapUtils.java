package com.stu.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * map和实体类转换工具类
 */
public class BeanMapUtils {

    /** Map 转换为 Bean
     * @param clazz Bean.class
     * @param map map
     * @param <T> 泛型
     * @return 一个Bean
     */
    public static <T> T convertMapToBean(Class<T> clazz, Map<String,Object> map) {
        T obj = null;
        try {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        obj = clazz.newInstance(); // 创建 JavaBean 对象
        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (map.containsKey(propertyName)) {
            // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                try{
                    Object value = map.get(propertyName);
                    if ("".equals(value)) {
                        value = null;
                    }
                    Object[] args = new Object[1];
                    args[0] = value;
                    descriptor.getWriteMethod().invoke(obj, args);
                }catch (IllegalArgumentException e){
                }
            }
        }
        } catch (IllegalAccessException e) {
        } catch (IntrospectionException e) {
        } catch (IllegalArgumentException e) {
        } catch (InstantiationException e) {
        } catch (InvocationTargetException e){
        } catch (Exception e){
        }
        return (T) obj;
    }

    /**
     * Bean转Map
     * @param obj Bean对象
     * @return Map
     */
    public static Map<String, Object> convertBeanToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);
                if(null==value){
                    map.put(key,"");
                }else{
                    map.put(key,value);
                }
                }
            }
        } catch (Exception e) { }
        return map;
    }
}