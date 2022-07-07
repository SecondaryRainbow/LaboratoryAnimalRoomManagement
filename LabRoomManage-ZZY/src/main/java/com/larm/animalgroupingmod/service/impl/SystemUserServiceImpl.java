package com.larm.animalgroupingmod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.larm.animalgroupingmod.entity.StaffInfo;
import com.larm.animalgroupingmod.entity.SystemUser;
import com.larm.animalgroupingmod.form.RuleForm;
import com.larm.animalgroupingmod.form.SearchForm;
import com.larm.animalgroupingmod.mapper.StaffInfoMapper;
import com.larm.animalgroupingmod.mapper.SystemUserMapper;
import com.larm.animalgroupingmod.service.SystemUserService;
import com.larm.animalgroupingmod.util.CommonUtil;
import com.larm.animalgroupingmod.vo.PageVO;
import com.larm.animalgroupingmod.vo.ResultVO;
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
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

    @Autowired(required = false)
    private SystemUserMapper systemUserMapper;

    @Autowired(required = false)
    private StaffInfoMapper staffInfoMapper;

    @Override
    public ResultVO<SystemUser> login(RuleForm ruleForm) {
        //1. check username
        QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", ruleForm.getUsername());
        SystemUser systemUser = this.systemUserMapper.selectOne(queryWrapper);
        ResultVO<SystemUser> resultVO = new ResultVO<>();
        if (systemUser == null) {
            resultVO.setCode(-1);
        } else {
            //2. check password
            if (!systemUser.getPassword().equals(ruleForm.getPassword())) {
                resultVO.setCode(-2);
            } else {
                resultVO.setCode(0);
                resultVO.setData(systemUser);
            }
        }
        return resultVO;
    }

    @Override
    public Boolean add(SystemUser systemUser) {
        systemUser.setCreateTime(CommonUtil.getTime());
        int insert = this.systemUserMapper.insert(systemUser);
        if (insert != 1) {
            return false;
        }

        StaffInfo staffInfo = this.staffInfoMapper.selectById(systemUser.getStaffId());
        staffInfo.setRegistered("Y");
        int update = this.staffInfoMapper.updateById(staffInfo);
        if (update != 1) {
            return false;
        }

        return true;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<SystemUser> systemUserPage = new Page<>(page, size);
        Page<SystemUser> resultPage = this.systemUserMapper.selectPage(systemUserPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<SystemUser> systemUserPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<SystemUser> resultPage;
        if (searchForm.getValue().equals("")) {
            resultPage = this.systemUserMapper.selectPage(systemUserPage, null);
        } else {
            QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = this.systemUserMapper.selectPage(systemUserPage, queryWrapper);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public Boolean delete(String username) {
        SystemUser systemUser = this.systemUserMapper.selectById(username);
        StaffInfo staffInfo = this.staffInfoMapper.selectById(systemUser.getStaffId());
        staffInfo.setRegistered("N");
        int update = this.staffInfoMapper.updateById(staffInfo);
        if (update != 1) {
            return false;
        }

        int delete = this.systemUserMapper.deleteById(systemUser.getUsername());
        if (delete != 1) {
            return false;
        }

        return true;
    }
}
