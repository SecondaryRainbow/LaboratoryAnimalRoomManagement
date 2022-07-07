package com.larm.animalgroupingmod.controller;


import com.larm.animalgroupingmod.entity.SystemUser;
import com.larm.animalgroupingmod.form.RuleForm;
import com.larm.animalgroupingmod.form.SearchForm;
import com.larm.animalgroupingmod.service.SystemUserService;
import com.larm.animalgroupingmod.util.ResultVOUtil;
import com.larm.animalgroupingmod.vo.PageVO;
import com.larm.animalgroupingmod.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-07-05
 */
@RestController
@RequestMapping("/systemUser")
public class SystemUserController {
    @Autowired
    private SystemUserService systemUserService;

    @GetMapping("/login")
    public ResultVO<SystemUser> login(RuleForm ruleForm) {
        return this.systemUserService.login(ruleForm);
    }

    @PostMapping("/add")
    public ResultVO add(@RequestBody SystemUser systemUser) {
        Boolean add = this.systemUserService.add(systemUser);
        if (!add) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageVO pageVO = this.systemUserService.list(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm) {
        PageVO pageVO = this.systemUserService.search(searchForm);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/findById/{username}")
    public ResultVO findById(@PathVariable("username") String username) {
        SystemUser systemUser = this.systemUserService.getById(username);
        return ResultVOUtil.success(systemUser);
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody SystemUser systemUser) {
        boolean update = this.systemUserService.updateById(systemUser);
        if (!update) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @DeleteMapping("/deleteById/{username}")
    public ResultVO deleteById(@PathVariable("username") String username) {
        boolean remove = this.systemUserService.delete(username);
        if (!remove) {
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

}

