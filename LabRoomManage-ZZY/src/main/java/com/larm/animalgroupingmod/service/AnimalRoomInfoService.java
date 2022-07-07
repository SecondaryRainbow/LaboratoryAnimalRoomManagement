package com.larm.animalgroupingmod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.larm.animalgroupingmod.entity.AnimalRoomInfo;
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
public interface AnimalRoomInfoService extends IService<AnimalRoomInfo> {

    Boolean add(AnimalRoomInfo animalRoomInfo);

    PageVO list(Integer page, Integer size);

    PageVO search(SearchForm searchForm);

    boolean delete(String animalRoomId);

    boolean update(AnimalRoomInfo animalRoomInfo);
}
