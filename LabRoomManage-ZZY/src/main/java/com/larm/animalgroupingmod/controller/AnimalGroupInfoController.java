package com.larm.animalgroupingmod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.larm.animalgroupingmod.entity.AnimalGroupInfo;
import com.larm.animalgroupingmod.entity.AnimalRoomInfo;
import com.larm.animalgroupingmod.form.SearchForm;
import com.larm.animalgroupingmod.service.AnimalGroupInfoService;
import com.larm.animalgroupingmod.service.AnimalRoomInfoService;
import com.larm.animalgroupingmod.util.ResultVOUtil;
import com.larm.animalgroupingmod.vo.PageVO;
import com.larm.animalgroupingmod.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/animalGroupInfo")
public class AnimalGroupInfoController {
    @Autowired
    private AnimalGroupInfoService animalGroupInfoService;

    @Autowired
    private AnimalRoomInfoService animalRoomInfoService;

    @GetMapping("/animalGroupList")
    public ResultVO animalGroupList() {
        QueryWrapper<AnimalRoomInfo> animalRoomInfoQueryWrapper = new QueryWrapper<>();
        animalRoomInfoQueryWrapper.ge("animal_room_current_capacity", 1);
        List<AnimalRoomInfo> animalRoomInfoList = this.animalRoomInfoService.list(animalRoomInfoQueryWrapper);

        List<String> animalRoomIds = new ArrayList<>();
        for (AnimalRoomInfo animalRoomInfo : animalRoomInfoList) {
            animalRoomIds.add(animalRoomInfo.getAnimalRoomId());
        }

        QueryWrapper<AnimalGroupInfo> animalGroupInfoQueryWrapper = new QueryWrapper<>();
        animalGroupInfoQueryWrapper.isNull("animal_room_id").or().in("animal_room_id", animalRoomIds);
        List<AnimalGroupInfo> animalGroupInfoList = this.animalGroupInfoService.list(animalGroupInfoQueryWrapper);

        return ResultVOUtil.success(animalGroupInfoList);
    }

    @PostMapping("/add")
    public ResultVO add(@RequestBody AnimalGroupInfo animalGroupInfo) {
        Boolean add = this.animalGroupInfoService.add(animalGroupInfo);
        if (!add) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO pageVO = this.animalGroupInfoService.list(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm) {
        PageVO pageVO = this.animalGroupInfoService.search(searchForm);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/findById/{animalGroupId}")
    public ResultVO findById(@PathVariable("animalGroupId") String animalRoomId) {
        AnimalGroupInfo animalGroupInfo = this.animalGroupInfoService.getById(animalRoomId);
        return ResultVOUtil.success(animalGroupInfo);
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody AnimalGroupInfo animalGroupInfo) {
        boolean update = this.animalGroupInfoService.update(animalGroupInfo);
        if (!update) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @DeleteMapping("/deleteById/{animalGroupId}")
    public ResultVO deleteById(@PathVariable("animalGroupId") String animalGroupId) {
        boolean remove = this.animalGroupInfoService.delete(animalGroupId);
        if (!remove) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }
}

