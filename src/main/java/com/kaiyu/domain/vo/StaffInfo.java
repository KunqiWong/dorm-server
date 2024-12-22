package com.kaiyu.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class StaffInfo {
    @JsonAlias("ID")
    @TableId
    private String id;

    @JsonAlias("楼栋")
    private String buildingNum;

    @JsonAlias("楼层")
    private String floor;

    @JsonAlias("房间号")
    private String roomNum;

    @JsonAlias("房间类型")
    private String roomStandard;

    @JsonAlias("工号")
    private String staffNum;

    @JsonAlias("姓名")
    private String staffName;

    @JsonAlias("性别")
    private String sex;

    @JsonAlias("国籍")
    private String contry;

    @JsonAlias("公司")
    private String company;

    @JsonAlias("部门")
    private String dept;

    @JsonAlias("职务")
    private String post;

    @JsonAlias("电话")
    private String phone;

    @JsonAlias("签证类型")
    private String visaType;

    @JsonAlias("护照号")
    private String passportNo;

    @JsonAlias("床位")
    private String bedNum;

    @JsonAlias("钥匙")
    private String keyFlag;

    @JsonAlias("入住日期")
    private String checkinDate;

    @JsonAlias("入住押金")
    private String deposit;

    @JsonAlias("备注")
    private String remark;

    private String updateBy;

    @JsonAlias("添加时间")
    private String createTime;

}
