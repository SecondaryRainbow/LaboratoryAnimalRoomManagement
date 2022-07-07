package com.larm.animalgroupingmod.util;

import com.larm.animalgroupingmod.vo.ResultVO;

public class ResultVOUtil {
    public static ResultVO fail() {
        ResultVO resultVO = new ResultVO<>();
        resultVO.setCode(-1);
        return resultVO;
    }

    public static ResultVO success(Object obj) {
        ResultVO resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setData(obj);
        return resultVO;
    }
}
