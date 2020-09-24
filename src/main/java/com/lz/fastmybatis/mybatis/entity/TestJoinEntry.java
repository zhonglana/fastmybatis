package com.lz.fastmybatis.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Table(name = "t_user")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestJoinEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(table = "t")
    private Integer id;
    private Map<String, String> params;
    private UserInfo userInfo;
    
    private Integer userId;
    private Integer age;


}
