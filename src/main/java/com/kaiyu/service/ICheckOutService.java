package com.kaiyu.service;
// import com.kaiyu.common.domain.AjaxResult;
import com.kaiyu.domain.vo.TreeSelect;
import com.kaiyu.domain.vo.BuildingInfo;
import com.kaiyu.domain.vo.BuildingRest;
import com.kaiyu.domain.vo.BuildingInfoRest;
import com.kaiyu.domain.vo.FloorRest;
import com.kaiyu.domain.vo.RoomRest;
// import com.kaiyu.domain.LeaveInfo;
import com.kaiyu.common.domain.PageR;
import com.kaiyu.domain.dto.CheckOutListDTO;
import com.kaiyu.domain.vo.LeaveInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;


public interface ICheckOutService extends IService<LeaveInfo> {
  
  PageR<LeaveInfo> getCheckOutList(CheckOutListDTO dto);
  
  List<LeaveInfo> selectLeaveInfoAll();
}
