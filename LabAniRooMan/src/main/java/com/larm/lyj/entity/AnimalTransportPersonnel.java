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
    public class AnimalTransportPersonnel implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(type = IdType.INPUT)
      private String animalTransportPersonnelNumber;

    private String name;

    private String education;

    private String sex;

    private Integer entirePeriodOfActualOperation;

    private String stressProcessingSkills;

    private String username;

    private String password;


}
