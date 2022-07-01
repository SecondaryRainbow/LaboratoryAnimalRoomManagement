package com.larm.lyj.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2022-07-01
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class SituationOfAnimalEntering implements Serializable {

    private static final long serialVersionUID=1L;

  @TableId(type = IdType.INPUT)
      private String animalNumber;

    private String animalSpecies;

    private String animalOrigin;

    private String animalHealth;

    private Integer roomNumber;

    private Integer fieldNumber;

    private String occupancy;

    private String animalTransportPersonnelNumber;


}
