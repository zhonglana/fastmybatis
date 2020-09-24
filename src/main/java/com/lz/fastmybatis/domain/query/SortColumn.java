package com.lz.fastmybatis.domain.query;

import com.gitee.fastmybatis.core.query.Sort;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SortColumn {

    String name() default "";
    Sort sort() default Sort.ASC;
    boolean query() default false;
}