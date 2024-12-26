package com.kaiyu.domain.vo;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class BuildingInfo {
    private Long id;

    // 
    // @JsonAlias("房间信息")
    // private String roomInfo;
    // // 
    @JsonAlias("楼栋")
    private String buildingNum;
    // 
    @JsonAlias("楼层")
    private String floor;
    // 
    @JsonAlias("房间号")
    private String roomNum;
    // 
    @JsonAlias("可住人数")
    private String capacity;
    // 
    @JsonAlias("已住人数")
    private String capacityNum;
    // 
    @JsonAlias("房间标准")
    private String roomStandard;
    // 
    @JsonAlias("房间属性")
    private String roomType;
    // 
    @JsonAlias("备注")
    private String remark;

    private String updateBy;

    private String updateTime;

    private String createTime;

}