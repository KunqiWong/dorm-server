package com.kaiyu.domain.vo;

import lombok.Data;

@Data
public class BuildingInfoRest {
    private String building;
    private Integer totalCapacity;
    private Integer totalOccupied;
    private Integer totalVacancy;
    private Double occupancyRate;
}
