package com.kaiyu.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class DormLog {

    private Long id;

    @TableField("operater")
    private String operater;

    @TableField("create_time")
    private String createTime;

    @TableField("operate_type")
    private String operateType;

    @TableField("remark")
    private String remark;

}

