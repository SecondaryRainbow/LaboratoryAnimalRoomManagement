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
    public class SystemUser implements Serializable {

    private static final long serialVersionUID=1L;

  @TableId(type = IdType.INPUT)
      private String jobId;

    private String name;

    private String sex;

    private String post;

    private Integer level;

    private String username;

    private String password;



}
