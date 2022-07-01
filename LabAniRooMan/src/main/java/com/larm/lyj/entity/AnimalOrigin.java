package com.larm.lyj.entity;

import java.time.LocalDateTime;
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
    public class AnimalOrigin implements Serializable {

    private static final long serialVersionUID=1L;

  @TableId(type = IdType.INPUT)
      private String animalOriginNumber;

    private String animalSpecies;

    private String animalOrigin;

    private Integer numberOfAnimals;

    private LocalDateTime transportTime;

    private String animalTransportPersonnelNumber;

    private String contactDetails;

    private String remark;


}
