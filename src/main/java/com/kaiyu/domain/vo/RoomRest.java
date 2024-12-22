package com.kaiyu.domain.vo;

import lombok.Data;

@Data
public class RoomRest {
    private String building;
    private String floor;
    private String room;
    private Integer totalCapacity;
    private Integer totalOccupied;
    private Integer totalVacancy;
    private Double occupancyRate;
}
