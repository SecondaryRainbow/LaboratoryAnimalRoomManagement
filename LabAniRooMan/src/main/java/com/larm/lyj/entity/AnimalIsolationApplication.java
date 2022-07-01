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
    public class AnimalIsolationApplication implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(type = IdType.INPUT)
      private String isolationInformationNumber;

    private String animalNumber;

    private String animalSpecies;

    private String animalOrigin;

    private String isolationDetailedReason;

    private LocalDateTime isolationStartTime;

    private String plannedIsolationTime;

    private String isolationLocation;

    private String supportingMaterials;

    private String remark;


}
