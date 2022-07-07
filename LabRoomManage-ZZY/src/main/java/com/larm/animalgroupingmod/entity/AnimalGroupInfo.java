package com.larm.animalgroupingmod.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @since 2022-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnimalGroupInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "animal_group_id")
    private String animalGroupId;

    private Integer animalNum;

    private String animalRoomId;


}
