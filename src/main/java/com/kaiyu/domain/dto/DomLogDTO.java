package com.kaiyu.domain.dto;

import java.util.Date;

import com.kaiyu.common.domain.PageQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomLogDTO extends PageQuery  {

    private String operater;

    private Date startTime;

    private Date endTime;

    private String operateType;

    private String remark;
}
