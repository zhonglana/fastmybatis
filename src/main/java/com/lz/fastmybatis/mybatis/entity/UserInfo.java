package com.lz.fastmybatis.mybatis.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo implements Serializable {

    private String id;
    private String name;

}
