package com.kaiyu.domain.vo;

import java.util.List;

import lombok.Data;

@Data
public class TreeSelect {
    private Long id;
    private String label;
    private List<TreeSelect> children;
    private String parentLabel;
}
