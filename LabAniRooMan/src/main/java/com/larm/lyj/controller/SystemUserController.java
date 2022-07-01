package com.larm.lyj.controller;


import com.larm.lyj.entity.AnimalTransportPersonnel;
import com.larm.lyj.entity.SystemUser;
import com.larm.lyj.form.RuleForm;
import com.larm.lyj.service.SystemUserService;
import com.larm.lyj.util.ResultVOUtil;
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

    @GetMapping("/list")
    public ResultVO list(){
        List<SystemUser> systemUserList = this.systemUserService.list();
        return ResultVOUtil.success(systemUserList);
    }

}

