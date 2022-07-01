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
    public class ExperimentTransportApplication implements Serializable {

    private static final long serialVersionUID=1L;

  @TableId(type = IdType.INPUT)
      private String transportInformationNumber;

    private String experimentNumber;

    private String animalNumber;

    private String animalSpecies;

    private String experimentalApprovalResults;

    private LocalDateTime experimentStartTime;

    private String plannedExperimentTime;

    private String experimentalLocation;

    private String animalTransportPersonnelNumber;

    private String remark;


}
