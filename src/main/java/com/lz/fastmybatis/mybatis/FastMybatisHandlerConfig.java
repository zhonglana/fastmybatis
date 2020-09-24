package com.lz.fastmybatis.mybatis;


import com.gitee.fastmybatis.core.ext.code.util.JavaTypeUtil;
import com.gitee.fastmybatis.core.handler.BaseFill;
import com.gitee.fastmybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import com.gitee.fastmybatis.spring.boot.autoconfigure.MybatisProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class FastMybatisHandlerConfig implements BeanPostProcessor {

    private static boolean init = false;

    @Autowired
    private MybatisProperties properties;

    public FastMybatisHandlerConfig(MybatisProperties properties){
        this.properties = properties;
    }

    private void handlerRegister(){
        Map<String, String> fill = properties.getFill();
        if(fill == null){
            fill = new HashMap<>();
            properties.setFill(fill);
        }
        if(StringUtils.isEmpty(properties.getTypeHandlersPackage())){
            return;
        }
        Reflections reflections = new Reflections(properties.getTypeHandlersPackage());
        Set<Class<? extends BaseFill>> subTypes = reflections.getSubTypesOf(BaseFill.class);
        for(Class clazz : subTypes){
            fill.put(clazz.getName(), "");
            addJavaType(clazz);
        }
    }

    private void addJavaType(Class clazz) {

        MappedJdbcTypes annotationJdbcType = (MappedJdbcTypes)clazz.getAnnotation(MappedJdbcTypes.class);
        JdbcType jdbcType = null;
        if(annotationJdbcType == null){
            return;
        }else {
            jdbcType = annotationJdbcType.value()[0];
        }

        MappedTypes annotationJavaType = (MappedTypes)clazz.getAnnotation(MappedTypes.class);
        Class<?>[] javaTypes = null;
        if(annotationJavaType == null){
            return;
        }else {
            javaTypes = annotationJavaType.value();
        }

        for(Class javaType : javaTypes){
            if(!JavaTypeUtil.isJavaType(javaType.getSimpleName())){
                JavaTypeUtil.addJavaType(javaType.getSimpleName(), javaType.getSimpleName(), jdbcType.name());
                JavaTypeUtil.addMybatisType(javaType.getSimpleName(), jdbcType.name());
            }
        }
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(!init){
            handlerRegister();
            init = true;
        }
        return bean;
    }
}
