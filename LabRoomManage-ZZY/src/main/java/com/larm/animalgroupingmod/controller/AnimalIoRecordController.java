package com.larm.animalgroupingmod.controller;


import com.larm.animalgroupingmod.entity.AnimalIoRecord;
import com.larm.animalgroupingmod.form.SearchForm;
import com.larm.animalgroupingmod.service.AnimalIoRecordService;
import com.larm.animalgroupingmod.util.CommonUtil;
import com.larm.animalgroupingmod.util.ResultVOUtil;
import com.larm.animalgroupingmod.vo.PageVO;
import com.larm.animalgroupingmod.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-07-05
 */
@RestController
@RequestMapping("/animalIoRecord")
public class AnimalIoRecordController {
    @Autowired
    private AnimalIoRecordService animalIoRecordService;

    @PostMapping("/add")
    public ResultVO add(@RequestBody AnimalIoRecord animalIoRecord) {
        animalIoRecord.setAnimalIoTime(CommonUtil.getTime());
        boolean add = this.animalIoRecordService.save(animalIoRecord);
        if (!add) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO pageVO = this.animalIoRecordService.list(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm) {
        PageVO pageVO = this.animalIoRecordService.search(searchForm);
        return ResultVOUtil.success(pageVO);
    }
}

