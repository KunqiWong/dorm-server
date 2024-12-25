package com.kaiyu.domain.dto;

import java.util.Date;

import com.kaiyu.common.domain.PageQuery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckOutDTO extends PageQuery {

    private String staffId;
    private String roomInfo;
    private String staffName;
    private String sex;
    private String dept;
    private String checkInDate;
    private String checkOutDate;
    private String leaveDate;
    private String keyFlag;
    private String beddingFlag;
    private String pillowFlag;
    private String basin;
    private String deposit;
    private String leaveReason;
    private String remark;

}
