package com.kaiyu.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeStaffRoomDTO {
    private String selectedBuilding;
    private String selectedFloor;
    private String selectedRoom;
    private String selectedStaff;
    private String selectedRoomStandard;
    private String selectedBedNum;

    private String changeReason;

    private String staffName;
    private String buildingNum;
    private String floor;
    private String roomNum;
    private String roomStandard;
    private String bedNum;
    
    private String updateTime;
    private String updateBy;
}
