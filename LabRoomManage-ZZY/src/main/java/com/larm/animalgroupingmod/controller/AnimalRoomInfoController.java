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
@RequestMapping("/animalRoomInfo")
public class AnimalRoomInfoController {
    @Autowired
    private AnimalRoomInfoService animalRoomInfoService;

    @Autowired
    private AnimalGroupInfoService animalGroupInfoService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody AnimalRoomInfo animalRoomInfo) {
        Boolean add = this.animalRoomInfoService.add(animalRoomInfo);
        if (!add) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO pageVO = this.animalRoomInfoService.list(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm) {
        PageVO pageVO = this.animalRoomInfoService.search(searchForm);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/findById/{animalRoomId}")
    public ResultVO findById(@PathVariable("animalRoomId") String animalRoomId) {
        AnimalRoomInfo animalRoomInfo = this.animalRoomInfoService.getById(animalRoomId);
        return ResultVOUtil.success(animalRoomInfo);
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody AnimalRoomInfo animalRoomInfo) {
        boolean update = this.animalRoomInfoService.update(animalRoomInfo);
        if (!update) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @DeleteMapping("/deleteById/{animalRoomId}")
    public ResultVO deleteById(@PathVariable("animalRoomId") String animalRoomId) {
        boolean remove = this.animalRoomInfoService.delete(animalRoomId);
        if (!remove) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @GetMapping("/roomList")
    public ResultVO roomList() {
        QueryWrapper<AnimalRoomInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("animal_room_availability", "Y");
        queryWrapper.gt("animal_room_current_capacity", 0);
        List<AnimalRoomInfo> animalRoomInfoList = this.animalRoomInfoService.list(queryWrapper);
        return ResultVOUtil.success(animalRoomInfoList);
    }

    @GetMapping("/availableRoom/{animalGroupId}")
    public ResultVO availableRoom(@PathVariable("animalGroupId") String animalGroupId) {
        AnimalGroupInfo animalGroupInfo = this.animalGroupInfoService.getById(animalGroupId);

        QueryWrapper<AnimalRoomInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("animal_room_availability", "Y");
        queryWrapper.ge("animal_room_current_capacity", animalGroupInfo.getAnimalNum());
        List<AnimalRoomInfo> animalRoomInfoList = this.animalRoomInfoService.list(queryWrapper);
        return ResultVOUtil.success(animalRoomInfoList);
    }
}

