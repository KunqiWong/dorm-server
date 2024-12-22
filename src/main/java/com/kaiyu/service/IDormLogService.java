package com.kaiyu.service;

import com.kaiyu.domain.dto.DomLogDTO;
import com.kaiyu.domain.vo.DormLog;
import com.kaiyu.common.domain.PageR;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IDormLogService extends IService<DormLog> {
  DormLog selectDormLogById(Long paramLong);
  
  PageR<DormLog> selectDormLogList(DomLogDTO dto);

  List<DormLog> selectDormLogAll();
  
  void insertDormLog(DormLog paramDormLog);
  
  void updateDormLog(DormLog paramDormLog);
  
  void deleteDormLogByIds(Long[] paramArrayOfLong);
  
  void deleteDormLogById(Long paramLong);
}

