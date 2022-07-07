package com.larm.animalgroupingmod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.larm.animalgroupingmod.entity.AnimalIoRecord;
import com.larm.animalgroupingmod.form.SearchForm;
import com.larm.animalgroupingmod.mapper.AnimalIoRecordMapper;
import com.larm.animalgroupingmod.service.AnimalIoRecordService;
import com.larm.animalgroupingmod.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-07-05
 */
@Service
public class AnimalIoRecordServiceImpl extends ServiceImpl<AnimalIoRecordMapper, AnimalIoRecord> implements AnimalIoRecordService {

    @Autowired(required = false)
    private AnimalIoRecordMapper animalIoRecordMapper;

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<AnimalIoRecord> animalIoRecordPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<AnimalIoRecord> resultPage;
        if (searchForm.getValue().equals("")) {
            resultPage = this.animalIoRecordMapper.selectPage(animalIoRecordPage, null);
        } else {
            QueryWrapper<AnimalIoRecord> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = this.animalIoRecordMapper.selectPage(animalIoRecordPage, queryWrapper);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<AnimalIoRecord> animalIoRecordPage = new Page<>(page, size);
        Page<AnimalIoRecord> resultPage = this.animalIoRecordMapper.selectPage(animalIoRecordPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }
}
