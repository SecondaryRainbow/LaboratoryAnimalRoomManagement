package com.larm.animalgroupingmod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.larm.animalgroupingmod.entity.AnimalGroupInfo;
import com.larm.animalgroupingmod.entity.AnimalRoomInfo;
import com.larm.animalgroupingmod.form.SearchForm;
import com.larm.animalgroupingmod.mapper.AnimalGroupInfoMapper;
import com.larm.animalgroupingmod.mapper.AnimalRoomInfoMapper;
import com.larm.animalgroupingmod.service.AnimalRoomInfoService;
import com.larm.animalgroupingmod.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-07-05
 */
@Service
public class AnimalRoomInfoServiceImpl extends ServiceImpl<AnimalRoomInfoMapper, AnimalRoomInfo> implements AnimalRoomInfoService {

    @Autowired(required = false)
    private AnimalRoomInfoMapper animalRoomInfoMapper;

    @Autowired(required = false)
    private AnimalGroupInfoMapper animalGroupInfoMapper;

    @Override
    public Boolean add(AnimalRoomInfo animalRoomInfo) {
        int insert = this.animalRoomInfoMapper.insert(animalRoomInfo);
        if (insert != 1) {
            return false;
        }
        return true;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<AnimalRoomInfo> animalRoomInfoPage = new Page<>(page, size);
        Page<AnimalRoomInfo> resultPage = this.animalRoomInfoMapper.selectPage(animalRoomInfoPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<AnimalRoomInfo> animalRoomInfoPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<AnimalRoomInfo> resultPage;
        if (searchForm.getValue().equals("")) {
            resultPage = this.animalRoomInfoMapper.selectPage(animalRoomInfoPage, null);
        } else {
            QueryWrapper<AnimalRoomInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = this.animalRoomInfoMapper.selectPage(animalRoomInfoPage, queryWrapper);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public boolean delete(String animalRoomId) {
//        QueryWrapper<AnimalGroupInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("animal_room_id", animalRoomId);
//        List<AnimalGroupInfo> animalGroupInfoList = this.animalGroupInfoMapper.selectList(queryWrapper);

//        for (AnimalGroupInfo animalGroupInfo : animalGroupInfoList) {
//            animalGroupInfo.setAnimalNum(9);
//            animalGroupInfo.setAnimalRoomId("random");
//            try {
//                this.animalGroupInfoMapper.updateById(animalGroupInfo);
//            } catch (Exception e) {
//                return false;
//            }
//        }
        LambdaUpdateWrapper<AnimalGroupInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(AnimalGroupInfo::getAnimalRoomId, animalRoomId)
                .set(AnimalGroupInfo::getAnimalRoomId, null);
        this.animalGroupInfoMapper.update(null, lambdaUpdateWrapper);

        int delete = this.animalRoomInfoMapper.deleteById(animalRoomId);
        if (delete != 1) {
            return false;
        }

        return true;
    }

    @Override
    public boolean update(AnimalRoomInfo animalRoomInfo) {
        if (Objects.equals(animalRoomInfo.getAnimalRoomAvailability(), "Y")) {
            int update = this.animalRoomInfoMapper.updateById(animalRoomInfo);
            return update == 1;
        } else if (Objects.equals(animalRoomInfo.getAnimalRoomAvailability(), "N")) {
            LambdaUpdateWrapper<AnimalGroupInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(AnimalGroupInfo::getAnimalRoomId, animalRoomInfo.getAnimalRoomId())
                    .set(AnimalGroupInfo::getAnimalRoomId, null);
            try {
                this.animalGroupInfoMapper.update(null, lambdaUpdateWrapper);
                animalRoomInfo.setAnimalRoomCurrentCapacity(animalRoomInfo.getAnimalRoomTotalCapacity());
                this.animalRoomInfoMapper.updateById(animalRoomInfo);
            } catch (Exception e) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
