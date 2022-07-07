package com.larm.animalgroupingmod.entity;

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
public class SystemAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "username")
    private String username;

    private String email;

    private String password;

    private LocalDateTime createTime;

    private String staffId;


}
