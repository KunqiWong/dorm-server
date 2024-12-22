package com.kaiyu.domain.vo;

import lombok.Data;

@Data
public class FloorRest {
    private String building;
    private String floorInfo;
    private Integer totalCapacity;
    private Integer totalOccupied;
    private Integer totalVacancy;
    private Double occupancyRate;
}
