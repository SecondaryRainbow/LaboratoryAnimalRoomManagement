package com.larm.animalgroupingmod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.larm.animalgroupingmod.entity.AnimalGroupInfo;
import com.larm.animalgroupingmod.entity.AnimalInfo;
import com.larm.animalgroupingmod.entity.AnimalRoomInfo;
import com.larm.animalgroupingmod.form.SearchForm;
import com.larm.animalgroupingmod.mapper.AnimalGroupInfoMapper;
import com.larm.animalgroupingmod.mapper.AnimalInfoMapper;
import com.larm.animalgroupingmod.mapper.AnimalRoomInfoMapper;
import com.larm.animalgroupingmod.service.AnimalGroupInfoService;
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
public class AnimalGroupInfoServiceImpl extends ServiceImpl<AnimalGroupInfoMapper, AnimalGroupInfo> implements AnimalGroupInfoService {

    @Autowired(required = false)
    private AnimalGroupInfoMapper animalGroupInfoMapper;

    @Autowired(required = false)
    private AnimalRoomInfoMapper animalRoomInfoMapper;

    @Autowired(required = false)
    private AnimalInfoMapper animalInfoMapper;

    @Override
    public Boolean add(AnimalGroupInfo animalGroupInfo) {
        int insert = this.animalGroupInfoMapper.insert(animalGroupInfo);
        if (insert != 1) {
            return false;
        }
        return true;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<AnimalGroupInfo> animalGroupInfoPage = new Page<>(page, size);
        Page<AnimalGroupInfo> resultPage = this.animalGroupInfoMapper.selectPage(animalGroupInfoPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<AnimalGroupInfo> animalGroupInfoPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<AnimalGroupInfo> resultPage;
        if (searchForm.getValue().equals("")) {
            resultPage = this.animalGroupInfoMapper.selectPage(animalGroupInfoPage, null);
        } else {
            QueryWrapper<AnimalGroupInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = this.animalGroupInfoMapper.selectPage(animalGroupInfoPage, queryWrapper);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public boolean update(AnimalGroupInfo animalGroupInfo) {
        AnimalGroupInfo oldAnimalGroupInfo = this.animalGroupInfoMapper.selectById(animalGroupInfo.getAnimalGroupId());
        AnimalRoomInfo oldAnimalRoomInfo = this.animalRoomInfoMapper.selectById(oldAnimalGroupInfo.getAnimalRoomId());
        if (oldAnimalRoomInfo != null) {
            oldAnimalRoomInfo.setAnimalRoomCurrentCapacity(oldAnimalRoomInfo.getAnimalRoomCurrentCapacity() + animalGroupInfo.getAnimalNum());
            int update = this.animalRoomInfoMapper.updateById(oldAnimalRoomInfo);
            if (update != 1) {
                return false;
            }
        }

        AnimalRoomInfo newAnimalRoomInfo = this.animalRoomInfoMapper.selectById(animalGroupInfo.getAnimalRoomId());
        newAnimalRoomInfo.setAnimalRoomCurrentCapacity(newAnimalRoomInfo.getAnimalRoomCurrentCapacity() - animalGroupInfo.getAnimalNum());
        int update = this.animalRoomInfoMapper.updateById(newAnimalRoomInfo);
        if (update != 1) {
            return false;
        }

        update = this.animalGroupInfoMapper.updateById(animalGroupInfo);
        if (update != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String animalGroupId) {
        //清空animalInfo的groupId字段
        LambdaUpdateWrapper<AnimalInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(AnimalInfo::getAnimalGroupId, animalGroupId)
                .set(AnimalInfo::getAnimalGroupId, null);
        this.animalInfoMapper.update(null, lambdaUpdateWrapper);

        //修改animalRoom容量
        AnimalGroupInfo animalGroupInfo = this.animalGroupInfoMapper.selectById(animalGroupId);
        AnimalRoomInfo animalRoomInfo = this.animalRoomInfoMapper.selectById(animalGroupInfo.getAnimalRoomId());
        animalRoomInfo.setAnimalRoomCurrentCapacity(animalRoomInfo.getAnimalRoomCurrentCapacity() + animalGroupInfo.getAnimalNum());

        int update = this.animalRoomInfoMapper.updateById(animalRoomInfo);
        if (update != 1) {
            return false;
        }

        int delete = this.animalGroupInfoMapper.deleteById(animalGroupId);
        if (delete != 1) {
            return false;
        }

        return true;
    }
}
