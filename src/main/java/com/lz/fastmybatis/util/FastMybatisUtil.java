package com.lz.fastmybatis.util;

import com.gitee.fastmybatis.core.ext.code.util.FieldUtil;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FastMybatisUtil {

    public static List<String> getColumns(Class<?> clazz){
        List<String> list = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field field: declaredFields){
            String tableName = null;
            String columnName = null;
            Column annotation = field.getAnnotation(Column.class);
            if(annotation != null && !StringUtils.isEmpty(annotation.name())){
                tableName = annotation.table();
                if(StringUtils.isEmpty(tableName)){
                    tableName = "t";
                }
                columnName = annotation.name();
            }else {
                tableName = "t";
                columnName = FieldUtil.camelToUnderline(field.getName());
            }
            list.add(tableName + "." + columnName);
        }
        return list;
    }


    /**
     * 将map集合中的数据转化为指定对象的同名属性中
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) throws Exception {
        T bean = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            Object value = map.get(FieldUtil.camelToUnderline(field.getName()));
            field.setAccessible(true);
            field.set(bean, value);
            field.setAccessible(false);
        }
        return bean;
    }

    public static <T> List<T> mapListToObjList(List<Map<String, Object>> list, Class<T> pojoClass) throws Exception {
        if (list == null) {
            return Collections.emptyList();
        }
        List<T> retList = new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            retList.add(mapToBean(map, pojoClass));
        }
        return retList;
    }
}
