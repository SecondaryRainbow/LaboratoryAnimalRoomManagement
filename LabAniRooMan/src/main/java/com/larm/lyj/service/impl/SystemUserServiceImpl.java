package com.larm.lyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.larm.lyj.entity.AnimalTransportPersonnel;
import com.larm.lyj.entity.SystemUser;
import com.larm.lyj.form.RuleForm;
import com.larm.lyj.mapper.SystemUserMapper;
import com.larm.lyj.service.SystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.larm.lyj.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-07-01
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Override
    public ResultVO login(RuleForm ruleForm) {
        //1、判断用户名是否存在
        QueryWrapper<SystemUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",ruleForm.getUsername());
        SystemUser systemUser = this.systemUserMapper.selectOne(queryWrapper);
        ResultVO resultVO=new ResultVO();
        if(systemUser==null){
            resultVO.setCode(-1);
        }else{
            //2、判断密码是否正确
            if(!systemUser.getPassword().equals(ruleForm.getPassword())){
                resultVO.setCode(-2);
            }else{
                resultVO.setCode(0);
                resultVO.setData(systemUser);
            }
        }
        return resultVO;
    }
}
