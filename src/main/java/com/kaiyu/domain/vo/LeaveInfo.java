package com.kaiyu.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaveInfo {
    @TableField("operator")
    private String operator;       // 登记人
    @TableField("room_info")
    private String roomInfo;       // 房间信息
    @TableField("staff_name")
    private String staffName;      // 员工姓名
    @TableField("staff_num")
    private String staffNum;       // 工号
    @TableField("passport_no")
    private String passportNo;     // 护照号
    @TableField("sex")
    private String sex;            // 性别
    @TableField("dept")
    private String dept;           // 部门
    @TableField("company")
    private String company;        // 公司
    @TableField("checkin_date")
    private String checkinDate;    // 入住日期
    @TableField("checkout_date")
    private String checkoutDate;   // 退宿日期
    @TableField("leave_date")
    private String leaveDate;      // 出矿日期
    @TableField("key_flag")
    private String keyFlag;        // 钥匙
    @TableField("card_flag")
    private String cardFlag;       // 饭卡
    @TableField("bedding_flag")
    private String beddingFlag;    // 床上用品
    @TableField("pillow_flag")
    private String pillowFlag;     // 枕头
    @TableField("basin")
    private String basin;          // 脸盆
    @TableField("deposit")
    private String deposit;        // 押金
    @TableField("leave_reason")
    private String leaveReason;    // 退宿理由
    @TableField("remark")
    private String remark;         // 备注
    @TableField("create_by")
    private String createBy;     // 创建人
    @TableField("create_time")
    private String createTime;     // 创建时间
}
