package com.kaiyu.domain.dto;

import lombok.Getter;
import lombok.Setter;
import com.kaiyu.common.domain.PageQuery;

@Getter
@Setter
public class BuildingListDTO  extends PageQuery {

    // 查询条件
    private String query;
    // 房间信息
    // private String roomInfo;
    // 楼栋
    private String buildingNum;
    // 楼层
    private String floor;
    // 房间号
    private String roomNum;
    // 可住人数
    private String capacity;
    // 已住人数
    private String capacityNum;
    // 房间标准
    private String roomStandard;
    // 房间属性
    private String roomType;
    // 备注
    private String remark;

}
