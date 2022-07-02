package com.larm.lyj.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.larm.lyj.entity.AnimalTransportPersonnel;
import com.larm.lyj.entity.SystemUser;
import com.larm.lyj.form.RuleForm;
import com.larm.lyj.form.SearchForm;
import com.larm.lyj.service.SystemUserService;
import com.larm.lyj.util.ResultVOUtil;
import com.larm.lyj.vo.PageVO;
import com.larm.lyj.vo.ResultVO;
import org.apache.ibatis.executor.loader.ResultLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-07-01
 */
@RestController
@RequestMapping("/systemUser")
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @GetMapping("/login")
    public ResultVO login(RuleForm ruleForm){
        ResultVO resultVO = this.systemUserService.login(ruleForm);
        return resultVO;
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody SystemUser systemUser){
        boolean save = this.systemUserService.save(systemUser);
        if(!save)return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        PageVO pageVO = this.systemUserService.list(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        PageVO pageVO = this.systemUserService.search(searchForm);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/findById/{id}")
    public ResultVO findById(@PathVariable("id") String id){
        SystemUser systemUser = this.systemUserService.getById(id);
        return ResultVOUtil.success(systemUser);
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody SystemUser systemUser){
        boolean update = this.systemUserService.updateById(systemUser);
        if(!update) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id") String id){
        boolean remove = this.systemUserService.removeById(id);
        if(!remove) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}

