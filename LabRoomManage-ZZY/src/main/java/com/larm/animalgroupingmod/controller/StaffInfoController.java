package com.larm.animalgroupingmod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.larm.animalgroupingmod.entity.StaffInfo;
import com.larm.animalgroupingmod.form.SearchForm;
import com.larm.animalgroupingmod.service.StaffInfoService;
import com.larm.animalgroupingmod.util.ResultVOUtil;
import com.larm.animalgroupingmod.vo.PageVO;
import com.larm.animalgroupingmod.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-07-05
 */
@RestController
@RequestMapping("/staffInfo")
public class StaffInfoController {

    @Autowired
    private StaffInfoService staffInfoService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody StaffInfo staffInfo) {
        boolean flag = this.staffInfoService.save(staffInfo);
        if (!flag) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO pageVO = this.staffInfoService.list(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm) {
        PageVO pageVO = this.staffInfoService.search(searchForm);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/findById/{staffId}")
    public ResultVO findById(@PathVariable("staffId") String staffId) {
        StaffInfo staffInfo = this.staffInfoService.getById(staffId);
        return ResultVOUtil.success(staffInfo);
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody StaffInfo staffInfo) {
        boolean update = this.staffInfoService.updateById(staffInfo);
        if (!update) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @DeleteMapping("/deleteById/{staffId}")
    public ResultVO deleteById(@PathVariable("staffId") String staffId) {
        boolean remove = this.staffInfoService.delete(staffId);
        if (!remove) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @GetMapping("/nonRegisteredStaff")
    public ResultVO nonRegisteredStaff() {
        QueryWrapper<StaffInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("registered", "N");
        List<StaffInfo> staffInfoList = this.staffInfoService.list(queryWrapper);
        return ResultVOUtil.success(staffInfoList);
    }

    @GetMapping("/staffInfoList")
    public ResultVO staffInfoList() {
        List<StaffInfo> staffInfoList = this.staffInfoService.list();
        return ResultVOUtil.success(staffInfoList);
    }
}

