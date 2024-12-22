package com.kaiyu.controller;

import com.kaiyu.domain.vo.TreeSelect;
import com.kaiyu.domain.vo.StaffInfo;
import com.kaiyu.domain.dto.BuildingListDTO;
import com.kaiyu.domain.dto.StaffListDTO;
import com.kaiyu.domain.dto.CheckOutListDTO;
import com.kaiyu.domain.vo.DormLog;
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

import com.kaiyu.service.ICheckOutService;
import com.kaiyu.common.domain.PageR;
import com.kaiyu.common.domain.R;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
@RequestMapping({"/checkout"})
public class CheckOutController {
  
  @Autowired
  private ICheckOutService IcheckOutService;

  @PostMapping({"/getCheckOutList"})
  public PageR<LeaveInfo> getCheckOutList(@RequestBody CheckOutListDTO dto) {
    return IcheckOutService.getCheckOutList(dto);
  }
  @GetMapping("/all")
  public R<List<LeaveInfo>> getAll() {
    return R.ok(IcheckOutService.selectLeaveInfoAll());
  }
  

}