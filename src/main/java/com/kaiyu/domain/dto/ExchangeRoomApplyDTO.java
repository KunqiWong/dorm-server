package com.kaiyu.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRoomApplyDTO {
    private String selectedBuilding;
    private String selectedFloor;
    private String selectedRoom;

    private String staffName;
    
    private String buildingNum;
    private String floor;
    private String roomNum;
}
