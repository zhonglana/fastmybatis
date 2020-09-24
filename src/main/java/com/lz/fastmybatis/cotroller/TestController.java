package com.lz.fastmybatis.cotroller;

import com.gitee.fastmybatis.core.query.Query;
import com.lz.fastmybatis.domain.CommResponse;
import com.lz.fastmybatis.domain.PageInfo;
import com.lz.fastmybatis.domain.query.TaskRecordQuery;
import com.lz.fastmybatis.domain.query.TestEntryQuery;
import com.lz.fastmybatis.mybatis.entity.TestEntry;
import com.lz.fastmybatis.mybatis.entity.TestJoinEntry;
import com.lz.fastmybatis.mybatis.mapper.TestMapper;
import com.lz.fastmybatis.util.FastMybatisUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Resource
    private TestMapper testMapper;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Object save(@RequestBody TestEntry testEntry){
        return testMapper.save(testEntry);
    }

//    @RequestMapping(value = "update", method = RequestMethod.POST)
//    public Object update(@RequestBody TestEntry testEntry){
//        return testMapper.save(testEntry);
//    }
//
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public CommResponse get(@RequestBody TestEntryQuery testEntryQuery){
        Query query = testEntryQuery.toQuery();
        List<TestEntry> list = testMapper.list(query);
        return CommResponse.success(list);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageInfo taskRecordList(TaskRecordQuery taskRecordQuery) throws Exception {
        Query query = taskRecordQuery.getQuery();
        query.join("left join t_task t2 on t2.task_id = t.task_id");
        long totalCount = testMapper.getCount(query);
        taskRecordQuery.buildPage().sort();
        List<String> column = FastMybatisUtil.getColumns(TestJoinEntry.class);
        List<Map<String, Object>> mapList = testMapper.listMap(column, query);
        List<TestJoinEntry> list = FastMybatisUtil.mapListToObjList(mapList, TestJoinEntry.class);
        return PageInfo.set(list, taskRecordQuery.getPageNo(), list.size(), (int)totalCount);
    }
}
