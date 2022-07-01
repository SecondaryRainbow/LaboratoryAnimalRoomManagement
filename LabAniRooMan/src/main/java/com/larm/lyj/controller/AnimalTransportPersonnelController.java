package com.larm.lyj.controller;


import com.larm.lyj.entity.AnimalTransportPersonnel;
import com.larm.lyj.form.RuleForm;
import com.larm.lyj.service.AnimalTransportPersonnelService;
import com.larm.lyj.util.ResultVOUtil;
import com.larm.lyj.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-07-01
 */
@RestController
@RequestMapping("/animalTransportPersonnel")
public class AnimalTransportPersonnelController {

    @Autowired
    private AnimalTransportPersonnelService animalTransportPersonnelService;

    @GetMapping("/login")
    public ResultVO login(RuleForm ruleForm){
        ResultVO resultVO = this.animalTransportPersonnelService.login(ruleForm);
        return resultVO;
    }


}

