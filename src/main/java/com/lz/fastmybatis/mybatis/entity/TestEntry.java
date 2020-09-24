package com.lz.fastmybatis.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

@Table(name = "t_test")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Map<String, String> params;
    private UserInfo userInfo;


}
