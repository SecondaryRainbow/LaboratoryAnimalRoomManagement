package com.larm.animalgroupingmod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.larm.animalgroupingmod.entity.StaffInfo;
import com.larm.animalgroupingmod.form.SearchForm;
import com.larm.animalgroupingmod.vo.PageVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author admin
 * @since 2022-07-05
 */
public interface StaffInfoService extends IService<StaffInfo> {
    PageVO list(Integer page, Integer size);

    PageVO search(SearchForm searchForm);

    boolean delete(String staffId);
}
