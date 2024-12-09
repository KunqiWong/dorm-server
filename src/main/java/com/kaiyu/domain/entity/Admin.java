package com.kaiyu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 *
 * 后台用户表
 */

@Data
public class Admin implements Serializable {

    /**
     * 用户名
     */
    @TableId
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private String role;

    /**
     * 状态
     * 0-正常
     * 1-拉黑
     */
    private Integer status;


    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

}