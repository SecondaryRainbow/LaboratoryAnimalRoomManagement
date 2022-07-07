package com.larm.animalgroupingmod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.larm.animalgroupingmod.entity.SystemUser;
import com.larm.animalgroupingmod.form.RuleForm;
import com.larm.animalgroupingmod.form.SearchForm;
import com.larm.animalgroupingmod.vo.PageVO;
import com.larm.animalgroupingmod.vo.ResultVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author admin
 * @since 2022-07-05
 */
public interface SystemUserService extends IService<SystemUser> {

    ResultVO<SystemUser> login(RuleForm ruleForm);

    Boolean add(SystemUser systemUser);

    PageVO list(Integer page, Integer size);

    PageVO search(SearchForm searchForm);

    Boolean delete(String username);

}
