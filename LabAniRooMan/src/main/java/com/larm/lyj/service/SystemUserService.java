package com.larm.lyj.service;

import com.larm.lyj.entity.SystemUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.larm.lyj.form.RuleForm;
import com.larm.lyj.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-07-01
 */
public interface SystemUserService extends IService<SystemUser> {
    public ResultVO login(RuleForm ruleForm);
}
