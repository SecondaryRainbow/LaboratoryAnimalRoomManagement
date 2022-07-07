package com.larm.animalgroupingmod.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class AnimalIoRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "operation_id", type = IdType.AUTO)
    private Integer operationId;

    private String animalId;

    private String staffId;

    private String animalIoBehavior;

    private LocalDateTime animalIoTime;


}
