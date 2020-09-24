package com.lz.fastmybatis.cotroller;

import com.lz.fastmybatis.mybatis.entity.TestEntry;
import com.lz.fastmybatis.mybatis.mapper.TestMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable Integer id){
        return testMapper.getById(id);
    }
}
