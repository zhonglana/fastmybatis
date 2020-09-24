package com.lz.fastmybatis.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageInfo {

    private List<?> list;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;

    public PageInfo() {
    }

    public PageInfo(List<?> list, Integer pageNo, Integer pageSize, Integer totalCount) {
        this.list = list;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public static PageInfo set(List<?> list, Integer pageNo, Integer pageSize, Integer totalCount){
        return new PageInfo(list, pageNo, pageSize, totalCount);
    }
}
