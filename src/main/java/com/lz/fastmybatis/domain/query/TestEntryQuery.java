package com.lz.fastmybatis.domain.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gitee.fastmybatis.core.query.annotation.Condition;
import com.gitee.fastmybatis.core.query.annotation.ConditionConfig;
import com.gitee.fastmybatis.core.query.param.PageSortParam;
import com.lz.fastmybatis.mybatis.entity.UserInfo;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;

/**
 * PageSortParam 分页 + 排序
 *
 * @Condition 条件查询
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestEntryQuery extends PageSortParam {

    private Integer id;
    private Map<String, String> params;

    @Condition(ignore = true)
    @Column(name = "user_info")
    private UserInfo userInfo;

    @Condition(ignoreEmptyString = true)
    private String email;

}
