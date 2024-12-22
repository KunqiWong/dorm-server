package com.kaiyu.service;

import com.kaiyu.domain.vo.BuildingDateVo;
import com.kaiyu.domain.vo.BuildingInfo;
import com.kaiyu.common.domain.PageR;
import com.kaiyu.domain.dto.BuildingListDTO;
import com.kaiyu.domain.vo.TreeSelect;


import java.util.List;

public interface IBuildingInfoService {
  
  
  PageR<BuildingInfo> selectBuildingInfoList(BuildingListDTO dto);

  List<BuildingInfo> selectBuildingInfoAll();

  BuildingInfo selectBuildingInfoById(Long id);
  
  

  void saveBuildingInfo(BuildingInfo dto);

  void saveBatchBuildingInfo(List<BuildingInfo> dto);
  
  
  void deleteBuildingInfoById(Long id);
  
}
