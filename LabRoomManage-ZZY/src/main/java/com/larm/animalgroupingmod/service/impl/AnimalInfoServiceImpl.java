package com.larm.animalgroupingmod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.larm.animalgroupingmod.entity.AnimalGroupInfo;
import com.larm.animalgroupingmod.entity.AnimalInfo;
import com.larm.animalgroupingmod.entity.AnimalRoomInfo;
import com.larm.animalgroupingmod.form.SearchForm;
import com.larm.animalgroupingmod.mapper.AnimalGroupInfoMapper;
import com.larm.animalgroupingmod.mapper.AnimalInfoMapper;
import com.larm.animalgroupingmod.mapper.AnimalRoomInfoMapper;
import com.larm.animalgroupingmod.service.AnimalInfoService;
import com.larm.animalgroupingmod.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-07-05
 */
@Service
public class AnimalInfoServiceImpl extends ServiceImpl<AnimalInfoMapper, AnimalInfo> implements AnimalInfoService {

    @Autowired(required = false)
    private AnimalInfoMapper animalInfoMapper;

    @Autowired(required = false)
    private AnimalGroupInfoMapper animalGroupInfoMapper;

    @Autowired(required = false)
    private AnimalRoomInfoMapper animalRoomInfoMapper;

    @Override
    public Boolean add(AnimalInfo animalInfo) {
        int insert = this.animalInfoMapper.insert(animalInfo);
        if (insert != 1) {
            return false;
        }

        AnimalGroupInfo animalGroupInfo = this.animalGroupInfoMapper.selectById(animalInfo.getAnimalGroupId());
        animalGroupInfo.setAnimalNum(animalGroupInfo.getAnimalNum() + 1);
        int update = this.animalGroupInfoMapper.updateById(animalGroupInfo);
        if (update != 1) {
            return false;
        }

        AnimalRoomInfo animalRoomInfo = this.animalRoomInfoMapper.selectById(animalGroupInfo.getAnimalRoomId());
        if (animalRoomInfo != null) {
            animalRoomInfo.setAnimalRoomCurrentCapacity(animalRoomInfo.getAnimalRoomCurrentCapacity() - 1);
            update = this.animalRoomInfoMapper.updateById(animalRoomInfo);
            if (update != 1) {
                return false;
            }
        }

        return true;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<AnimalInfo> animalInfoPage = new Page<>(page, size);
        Page<AnimalInfo> resultPage = this.animalInfoMapper.selectPage(animalInfoPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<AnimalInfo> animalInfoPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<AnimalInfo> resultPage;
        if (searchForm.getValue().equals("")) {
            resultPage = this.animalInfoMapper.selectPage(animalInfoPage, null);
        } else {
            QueryWrapper<AnimalInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = this.animalInfoMapper.selectPage(animalInfoPage, queryWrapper);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public boolean delete(String animalId) {
        AnimalInfo animalInfo = this.animalInfoMapper.selectById(animalId);

        AnimalGroupInfo animalGroupInfo = this.animalGroupInfoMapper.selectById(animalInfo.getAnimalGroupId());
        animalGroupInfo.setAnimalNum(animalGroupInfo.getAnimalNum() - 1);
        int update = this.animalGroupInfoMapper.updateById(animalGroupInfo);
        if (update != 1) {
            return false;
        }

        AnimalRoomInfo animalRoomInfo = this.animalRoomInfoMapper.selectById(animalGroupInfo.getAnimalRoomId());
        animalRoomInfo.setAnimalRoomCurrentCapacity(animalRoomInfo.getAnimalRoomCurrentCapacity() + 1);
        update = this.animalRoomInfoMapper.updateById(animalRoomInfo);
        if (update != 1) {
            return false;
        }

        int delete = this.animalInfoMapper.deleteById(animalId);

        return true;
    }

    @Override
    public boolean update(AnimalInfo animalInfo) {
        AnimalInfo oldAnimalInfo = this.animalInfoMapper.selectById(animalInfo.getAnimalId());
        AnimalGroupInfo oldAnimalGroupInfo = this.animalGroupInfoMapper.selectById(oldAnimalInfo.getAnimalGroupId());
        if (oldAnimalGroupInfo != null) {
            oldAnimalGroupInfo.setAnimalNum(oldAnimalGroupInfo.getAnimalNum() - 1);
            int update = this.animalGroupInfoMapper.updateById(oldAnimalGroupInfo);
            if (update != 1) {
                return false;
            }

            AnimalRoomInfo oldAnimalRoomInfo = this.animalRoomInfoMapper.selectById(oldAnimalGroupInfo.getAnimalRoomId());
            if (oldAnimalRoomInfo != null) {
                oldAnimalRoomInfo.setAnimalRoomCurrentCapacity(oldAnimalRoomInfo.getAnimalRoomCurrentCapacity() + 1);
                update = this.animalRoomInfoMapper.updateById(oldAnimalRoomInfo);
                if (update != 1) {
                    return false;
                }
            }
        }


        AnimalGroupInfo newAnimalGroupInfo = this.animalGroupInfoMapper.selectById(animalInfo.getAnimalGroupId());
        newAnimalGroupInfo.setAnimalNum(newAnimalGroupInfo.getAnimalNum() + 1);
        int update = this.animalGroupInfoMapper.updateById(newAnimalGroupInfo);
        if (update != 1) {
            return false;
        }

        AnimalRoomInfo newAnimalRoomInfo = this.animalRoomInfoMapper.selectById(newAnimalGroupInfo.getAnimalRoomId());
        if (newAnimalRoomInfo != null) {
            newAnimalRoomInfo.setAnimalRoomCurrentCapacity(newAnimalRoomInfo.getAnimalRoomCurrentCapacity() - 1);
            update = this.animalRoomInfoMapper.updateById(newAnimalRoomInfo);
            if (update != 1) {
                return false;
            }
        }

        update = this.animalInfoMapper.updateById(animalInfo);
        if (update != 1) {
            return false;
        }

        return true;
    }
}
