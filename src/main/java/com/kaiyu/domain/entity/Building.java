package com.kaiyu.domain.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class Building implements Serializable {

    @TableId
    private Long id;

    // private String roomInfo;

    private String buildingNum;

    private String floor;

    private String roomNum;

    private String capacity;

    private String capacityNum;

    private String roomStandard;

    private String roomType;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String createdBy;

    private String updateBy;

    private Integer seq;
}
