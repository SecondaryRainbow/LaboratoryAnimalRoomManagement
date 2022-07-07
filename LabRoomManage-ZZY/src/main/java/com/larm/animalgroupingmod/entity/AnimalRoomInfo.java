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
public class AnimalRoomInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "animal_room_id")
    private String animalRoomId;

    private Integer animalRoomTotalCapacity;

    private Integer animalRoomCurrentCapacity;

    private String animalRoomAvailability;

    private String staffId;


}
