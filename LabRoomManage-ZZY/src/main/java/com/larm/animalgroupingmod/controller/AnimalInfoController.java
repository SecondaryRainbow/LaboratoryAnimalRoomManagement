package com.larm.animalgroupingmod.controller;


import com.larm.animalgroupingmod.entity.AnimalInfo;
import com.larm.animalgroupingmod.form.SearchForm;
import com.larm.animalgroupingmod.service.AnimalInfoService;
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
@RequestMapping("/animalInfo")
public class AnimalInfoController {

    @Autowired
    private AnimalInfoService animalInfoService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody AnimalInfo animalInfo) {
        Boolean add = this.animalInfoService.add(animalInfo);
        if (!add) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO pageVO = this.animalInfoService.list(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm) {
        PageVO pageVO = this.animalInfoService.search(searchForm);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/findById/{animalId}")
    public ResultVO findById(@PathVariable("animalId") String animalId) {
        AnimalInfo animalInfo = this.animalInfoService.getById(animalId);
        return ResultVOUtil.success(animalInfo);
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody AnimalInfo animalInfo) {
        boolean update = this.animalInfoService.update(animalInfo);
        if (!update) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @DeleteMapping("/deleteById/{animalId}")
    public ResultVO deleteById(@PathVariable("animalId") String animalId) {
        boolean remove = this.animalInfoService.delete(animalId);
        if (!remove) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @GetMapping("/animalInfoList")
    public ResultVO animalInfoList() {
        List<AnimalInfo> animalInfoList = this.animalInfoService.list();
        return ResultVOUtil.success(animalInfoList);
    }
}

