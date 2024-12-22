package com.kaiyu.service;

// import com.kaiyu.domain.TreeSelect;
import com.kaiyu.domain.vo.StaffInfo;
import com.kaiyu.common.domain.PageR;
import com.kaiyu.domain.dto.StaffListDTO;


import java.util.List;

public interface IStaffInfoService {
  
  
  PageR<StaffInfo> selectStaffInfoList(StaffListDTO dto);

  List<StaffInfo> selectStaffInfoAll();
  
  

  void saveStaffInfo(StaffInfo dto);

  void saveBatchStaffInfo(List<StaffInfo> dto);
  
  
  void deleteStaffInfoById(String id);
  
}
