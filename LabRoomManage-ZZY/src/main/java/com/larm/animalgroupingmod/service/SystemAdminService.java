package com.larm.animalgroupingmod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.larm.animalgroupingmod.entity.SystemAdmin;
import com.larm.animalgroupingmod.form.RuleForm;
import com.larm.animalgroupingmod.vo.ResultVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author admin
 * @since 2022-07-05
 */
public interface SystemAdminService extends IService<SystemAdmin> {
    ResultVO<SystemAdmin> login(RuleForm ruleForm);
}
