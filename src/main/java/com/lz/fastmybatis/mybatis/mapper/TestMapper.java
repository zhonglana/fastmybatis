package com.lz.fastmybatis.mybatis.mapper;

import com.gitee.fastmybatis.core.mapper.CrudMapper;
import com.lz.fastmybatis.mybatis.entity.TestEntry;

public interface TestMapper extends CrudMapper<TestEntry, Integer> {

    int save2(TestEntry testEntry);

}
