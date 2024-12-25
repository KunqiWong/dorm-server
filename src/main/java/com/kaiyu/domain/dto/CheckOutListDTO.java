package com.kaiyu.domain.dto;

import java.util.Date;

import com.kaiyu.common.domain.PageQuery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckOutListDTO extends PageQuery {

    private String operator;

    private Date startTime;

    private Date endTime;

    private String staffName;

    private String passportNo;

}
