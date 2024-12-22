package com.kaiyu.domain.vo;

import lombok.Data;

@Data
public class BuildingRest {
    private String lifeArea;
    private Integer totalCapacity;
    private Integer totalOccupied;
    private Integer totalVacancy;
    private Double occupancyRate;

    // @Override
    // public String toString() {
    //     return "BuildingRest{" +
    //             "lifeArea:" + lifeArea +
    //             ", totalCapacity:" + totalCapacity +
    //             ", totalOccupied:" + totalOccupied +
    //             ", totalVacancy:" + totalVacancy +
    //             ", occupancyRate:" + occupancyRate +
    //             "}";
    // }
}
