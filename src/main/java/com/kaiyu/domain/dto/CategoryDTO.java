package com.kaiyu.domain.dto;

import lombok.Getter;
import lombok.Setter;
import com.kaiyu.common.domain.PageQuery;



@Getter
@Setter
public class CategoryDTO extends PageQuery {

    private String name;
    private Integer sort;

}
