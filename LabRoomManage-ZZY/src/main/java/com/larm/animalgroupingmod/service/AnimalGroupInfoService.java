package com.larm.animalgroupingmod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.larm.animalgroupingmod.entity.AnimalGroupInfo;
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
public interface AnimalGroupInfoService extends IService<AnimalGroupInfo> {

    Boolean add(AnimalGroupInfo animalGroupInfo);

    PageVO list(Integer page, Integer size);

    PageVO search(SearchForm searchForm);

    boolean update(AnimalGroupInfo animalGroupInfo);

    boolean delete(String animalGroupId);
}
