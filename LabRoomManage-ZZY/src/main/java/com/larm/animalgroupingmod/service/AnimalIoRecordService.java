package com.larm.animalgroupingmod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.larm.animalgroupingmod.entity.AnimalIoRecord;
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
public interface AnimalIoRecordService extends IService<AnimalIoRecord> {

    PageVO search(SearchForm searchForm);

    PageVO list(Integer page, Integer size);
}
