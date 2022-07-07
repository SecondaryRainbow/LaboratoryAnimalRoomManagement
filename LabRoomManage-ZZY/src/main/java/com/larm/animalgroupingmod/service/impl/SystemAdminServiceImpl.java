package com.larm.animalgroupingmod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.larm.animalgroupingmod.entity.SystemAdmin;
import com.larm.animalgroupingmod.form.RuleForm;
import com.larm.animalgroupingmod.mapper.SystemAdminMapper;
import com.larm.animalgroupingmod.service.SystemAdminService;
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
public class SystemAdminServiceImpl extends ServiceImpl<SystemAdminMapper, SystemAdmin> implements SystemAdminService {

    @Autowired(required = false)
    private SystemAdminMapper systemAdminMapper;

    @Override
    public ResultVO<SystemAdmin> login(RuleForm ruleForm) {
        //1. check username
        QueryWrapper<SystemAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", ruleForm.getUsername());
        SystemAdmin systemAdmin = this.systemAdminMapper.selectOne(queryWrapper);
        ResultVO<SystemAdmin> resultVO = new ResultVO<>();
        if (systemAdmin == null) {
            resultVO.setCode(-1);
        } else {
            //2. check password
            if (!systemAdmin.getPassword().equals(ruleForm.getPassword())) {
                resultVO.setCode(-2);
            } else {
                resultVO.setCode(0);
                resultVO.setData(systemAdmin);
            }
        }
        return resultVO;
    }
}
