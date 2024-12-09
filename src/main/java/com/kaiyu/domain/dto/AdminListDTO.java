package com.kaiyu.domain.dto;

import lombok.Getter;
import lombok.Setter;
import com.kaiyu.common.domain.PageQuery;

@Getter
@Setter
public class AdminListDTO extends PageQuery {

    private String userName;

    private Integer status;

}
