package com.larm.lyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.larm.lyj.entity.AnimalTransportPersonnel;
import com.larm.lyj.form.RuleForm;
import com.larm.lyj.mapper.AnimalTransportPersonnelMapper;
import com.larm.lyj.service.AnimalTransportPersonnelService;
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
public class AnimalTransportPersonnelServiceImpl extends ServiceImpl<AnimalTransportPersonnelMapper, AnimalTransportPersonnel> implements AnimalTransportPersonnelService {

    @Autowired
    private AnimalTransportPersonnelMapper animalTransportPersonnelMapper;

    @Override
    public ResultVO login(RuleForm ruleForm) {
        //1、判断用户名是否存在
        QueryWrapper<AnimalTransportPersonnel> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",ruleForm.getUsername());
        AnimalTransportPersonnel animalTransportPersonnel = this.animalTransportPersonnelMapper.selectOne(queryWrapper);
        ResultVO resultVO=new ResultVO();
        if(animalTransportPersonnel==null){
            resultVO.setCode(-1);
        }else{
            //2、判断密码是否正确
            if(!animalTransportPersonnel.getPassword().equals(ruleForm.getPassword())){
                resultVO.setCode(-2);
            }else{
                resultVO.setCode(0);
                resultVO.setData(animalTransportPersonnel);
            }
        }
        return resultVO;
    }
}
