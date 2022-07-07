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
public class StaffInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "staff_id")
    private String staffId;

    private String staffName;

    private String staffGender;

    private String staffPosition;

    private Integer staffTier;

    private String registered;
}
