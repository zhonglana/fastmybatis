package com.lz.fastmybatis.domain.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gitee.fastmybatis.core.ext.code.util.FieldUtil;
import com.gitee.fastmybatis.core.query.Query;
import com.gitee.fastmybatis.core.query.Sort;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import java.lang.reflect.Field;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryBase {

    private static Integer DEFAULT_PAGE_NO = 1;
    private static Integer DEFAULT_PAGE_SIZE = 10;

    protected Integer pageNo;
    protected Integer pageSize;

    private Query query = new Query();

    public Query getQuery() throws IllegalAccessException {
        Class<? extends QueryBase> aClass = this.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for(Field field: declaredFields){
            SortColumn SortColumnAnnotation = field.getAnnotation(SortColumn.class);
            if(SortColumnAnnotation != null){
                continue;
            }
            String columnName = null;
            Column annotation = field.getAnnotation(Column.class);
            if(annotation != null && !StringUtils.isEmpty(annotation.name())){
                columnName = annotation.name();
            }else {
                columnName = FieldUtil.camelToUnderline(field.getName());
            }
            field.setAccessible(true);
            Object value = field.get(this);
            if(field.getType() == String.class){
                if(!StringUtils.isEmpty(value)){
                    query.eq(columnName, value);
                }
            }else {
                if(value != null){
                    query.eq(columnName, value);
                }
            }
            field.setAccessible(false);
        }
        return query;
    }

    public QueryBase buildPage(){
        if(pageNo == null || pageNo <= 0){
            pageNo = DEFAULT_PAGE_NO;
        }
        if(pageSize == null || pageSize <= 0){
            pageSize = DEFAULT_PAGE_SIZE;
        }
        query.page(pageNo, pageSize);
        return this;
    }

    public Query sort() throws IllegalAccessException {
        Class<? extends QueryBase> aClass = this.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for(Field field: declaredFields){
            field.setAccessible(true);
            SortColumn annotation = field.getAnnotation(SortColumn.class);
            if(annotation == null){
                continue;
            }
            String columnName = null;
            if(StringUtils.isEmpty(annotation.name())){
                columnName = FieldUtil.camelToUnderline(field.getName());
            }else {
                columnName = annotation.name();
            }
            Object value = field.get(this);
            if(value != null){
                query.addSort(columnName, (Sort)value);
            }else {
                query.addSort(columnName, annotation.sort());
            }

            field.setAccessible(false);
        }
        return query;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
