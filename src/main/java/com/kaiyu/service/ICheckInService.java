package com.kaiyu.service;
// import com.kaiyu.common.domain.AjaxResult;
import com.kaiyu.domain.vo.TreeSelect;
import com.kaiyu.domain.vo.BuildingInfo;
import com.kaiyu.domain.vo.BuildingRest;
import com.kaiyu.domain.vo.BuildingInfoRest;
import com.kaiyu.domain.vo.FloorRest;
import com.kaiyu.domain.vo.RoomRest;
// import com.kaiyu.domain.LeaveInfo;
// import com.kaiyu.domain.RoomRecord;
import com.kaiyu.domain.vo.StaffInfo;
import com.kaiyu.common.domain.PageR;
import com.kaiyu.domain.dto.BuildingListDTO;
import com.kaiyu.domain.dto.StaffListDTO;
import com.kaiyu.domain.dto.CheckOutListDTO;
import com.kaiyu.domain.dto.ExchangeStaffRoomDTO;
import com.kaiyu.domain.dto.ExchangeRoomApplyDTO;
import com.kaiyu.domain.vo.LeaveInfo;
import java.util.List;
import java.util.Map;


public interface ICheckInService {
  
  List<TreeSelect> buildBuildingInfoTreeSelectWithCache();

  PageR<StaffInfo> employeeListByBuilding(StaffListDTO staffListDTO);

  BuildingRest getBuildingRest(BuildingListDTO dto);

  BuildingInfoRest getBuildingInfoRest(BuildingListDTO dto);

  FloorRest getFloorRest(BuildingListDTO dto);

  RoomRest getRoomRest(BuildingListDTO dto);

  void exchangeStaffRoom(ExchangeStaffRoomDTO dto);

  void exchangeRoomApply(ExchangeRoomApplyDTO dto);
}
