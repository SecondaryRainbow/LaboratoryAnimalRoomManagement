package com.larm.animalgroupingmod.controller;


import com.larm.animalgroupingmod.entity.SystemAdmin;
import com.larm.animalgroupingmod.form.RuleForm;
import com.larm.animalgroupingmod.service.SystemAdminService;
import com.larm.animalgroupingmod.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-07-05
 */
@RestController
@RequestMapping("/systemAdmin")
public class SystemAdminController {

    @Autowired
    private SystemAdminService systemAdminService;

    @GetMapping("/login")
    public ResultVO<SystemAdmin> login(RuleForm ruleForm) {
        return this.systemAdminService.login(ruleForm);
    }
}

