package com.larm.animalgroupingmod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.larm.animalgroupingmod.entity.AnimalRoomInfo;
import com.larm.animalgroupingmod.entity.StaffInfo;
import com.larm.animalgroupingmod.entity.SystemUser;
import com.larm.animalgroupingmod.form.SearchForm;
import com.larm.animalgroupingmod.mapper.AnimalRoomInfoMapper;
import com.larm.animalgroupingmod.mapper.StaffInfoMapper;
import com.larm.animalgroupingmod.mapper.SystemUserMapper;
import com.larm.animalgroupingmod.service.StaffInfoService;
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
public class StaffInfoServiceImpl extends ServiceImpl<StaffInfoMapper, StaffInfo> implements StaffInfoService {

    @Autowired(required = false)
    private StaffInfoMapper staffInfoMapper;

    @Autowired(required = false)
    private AnimalRoomInfoMapper animalRoomInfoMapper;

    @Autowired(required = false)
    private SystemUserMapper systemUserMapper;

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<StaffInfo> staffInfoPage = new Page<>(page, size);
        Page<StaffInfo> resultPage = this.staffInfoMapper.selectPage(staffInfoPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<StaffInfo> staffInfoPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<StaffInfo> resultPage;
        if (searchForm.getValue().equals("")) {
            resultPage = this.staffInfoMapper.selectPage(staffInfoPage, null);
        } else {
            QueryWrapper<StaffInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = this.staffInfoMapper.selectPage(staffInfoPage, queryWrapper);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public boolean delete(String staffId) {
        LambdaUpdateWrapper<AnimalRoomInfo> animalRoomInfoLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        animalRoomInfoLambdaUpdateWrapper.eq(AnimalRoomInfo::getStaffId, staffId)
                .set(AnimalRoomInfo::getStaffId, null);
        animalRoomInfoMapper.update(null, animalRoomInfoLambdaUpdateWrapper);

        QueryWrapper<SystemUser> systemUserQueryWrapper = new QueryWrapper<>();
        systemUserQueryWrapper.eq("staff_id", staffId);
        systemUserMapper.delete(systemUserQueryWrapper);

        int delete = this.staffInfoMapper.deleteById(staffId);
        if (delete != 1) {
            return false;
        }

        return true;
    }
}
