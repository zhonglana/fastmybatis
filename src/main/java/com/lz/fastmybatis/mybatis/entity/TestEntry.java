package com.lz.fastmybatis.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gitee.fastmybatis.core.query.annotation.ConditionConfig;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@ConditionConfig()
@Table(name = "t_user")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestEntry{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Map<String, String> params;

    @Column(name = "user_info")
    private UserInfo userInfo;

    private String email;

}
