package com.kaiyu.controller;

import com.kaiyu.domain.vo.TreeSelect;
import com.kaiyu.domain.vo.StaffInfo;
import com.kaiyu.domain.dto.BuildingListDTO;
import com.kaiyu.domain.dto.StaffListDTO;
import com.kaiyu.domain.dto.CheckOutListDTO;
import com.kaiyu.domain.dto.ExchangeStaffRoomDTO;
import com.kaiyu.domain.vo.LeaveInfo;
import com.kaiyu.service.ICheckInService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kaiyu.common.domain.PageR;
import com.kaiyu.common.domain.R;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
@RequestMapping({"/checkin"})
public class CheckInController {

  
  
  @Autowired
  private ICheckInService IcheckInService;

  
  @GetMapping({"/treeselect"})
  public R<List<TreeSelect>> treeselect() {
    return R.ok(this.IcheckInService.buildBuildingInfoTreeSelectWithCache());
  }

  @PostMapping({"/employeeListByBuilding"})
  public PageR<StaffInfo> employeeListByBuilding(@RequestBody StaffListDTO staffListDTO) {
    return this.IcheckInService.employeeListByBuilding(staffListDTO);
  }

  @PostMapping({"/infoRest"})
  public R<List<Object>> infoRest(@RequestBody BuildingListDTO dto) {
      List<Object> resultList = new ArrayList<>();
      
      try {
          resultList.add(this.IcheckInService.getBuildingRest(dto));
          resultList.add(this.IcheckInService.getBuildingInfoRest(dto));
          resultList.add(this.IcheckInService.getFloorRest(dto));
          resultList.add(this.IcheckInService.getRoomRest(dto));
      } catch (Exception e) {
          log.error("服务调用异常", e);
      }

      log.info("返回的 List 内容: {}", resultList);
      return R.ok(resultList);
  }

  @PostMapping({"/exchangeStaffRoom"})
  public R<Void> exchangeStaffRoom(@RequestBody ExchangeStaffRoomDTO dto) {
    this.IcheckInService.exchangeStaffRoom(dto);
    return R.ok();
  }
}