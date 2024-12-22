package com.kaiyu.domain.dto;

import com.kaiyu.common.domain.PageQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffListDTO  extends PageQuery  {
    
    // 查询条件
    private String query;
    
    private String buildingNum;

    private String floor;

    private String roomNum;

    private String roomStandard;

    private String staffNum;

    private String staffName;

    private String sex;

    private String contry;

    private String company;

    private String dept;

    private String post;

    private String phone;

    private String visaType;

    private String passportNo;

    private String bedNum;

    private String keyFlag;

    private String checkinDate;

    private String deposit;

    private String remark;
}
