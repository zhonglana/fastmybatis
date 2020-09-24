package com.lz.fastmybatis.domain.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gitee.fastmybatis.core.query.Sort;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskRecordQuery extends QueryBase {

    private Integer id;
    private String taskId;
    private String templateId;
    private String templateName;
    private String templateTitle;
    private String messageType;
    private String appId;
    private String appName;
    private Integer status;

    @SortColumn(name = "create_time", sort = Sort.DESC)
    private Sort createTimeSort;

}
