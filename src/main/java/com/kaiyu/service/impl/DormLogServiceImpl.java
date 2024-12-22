package com.kaiyu.service.impl;

import com.kaiyu.domain.vo.BuildingInfo;
import com.kaiyu.domain.vo.DormLog;
import com.kaiyu.mapper.DormLogMapper;
import com.kaiyu.service.IDormLogService;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Date;
import com.kaiyu.common.domain.PageR;
import com.kaiyu.domain.dto.DomLogDTO;
import org.apache.commons.lang3.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import com.kaiyu.utils.UserHolder;

@Slf4j
@Service
public class DormLogServiceImpl extends ServiceImpl<DormLogMapper, DormLog> implements IDormLogService {

  
  public DormLog selectDormLogById(Long id) {
    return getById(id);
  }


  @Override
  public List<DormLog> selectDormLogAll() {
    return list();
  }

  @Override
  public PageR<DormLog> selectDormLogList(DomLogDTO dto) {
        // 构建 QueryWrapper
        QueryWrapper<DormLog> queryWrapper = new QueryWrapper<>();
        // 根据 DTO 中的条件动态添加查询条件
        queryWrapper
        .eq(ObjectUtils.isNotEmpty(dto.getOperater()), "operater", dto.getOperater())
        .eq(ObjectUtils.isNotEmpty(dto.getOperateType()), "operate_type", dto.getOperateType())
        .ge(ObjectUtils.isNotEmpty(dto.getStartTime()), "DATE(create_time)", dto.getStartTime())
        .le(ObjectUtils.isNotEmpty(dto.getEndTime()), "DATE(create_time)", dto.getEndTime());
        
        // 执行分页查询
        Page<DormLog> resultPage = this.baseMapper.selectPage(dto.toMpPage(), queryWrapper);

        // 返回封装的分页结果
        return PageR.of(resultPage);
  }

  
  @Override
  public void insertDormLog(DormLog dormLog) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    dormLog.setCreateTime(sdf.format(new Date()));
    dormLog.setOperater(UserHolder.getLoginUser().getUserName());
    log.info("dormLog:{}",sdf.format(new Date()));
    save(dormLog);
  }


  
  @Override
  public void updateDormLog(DormLog dormLog) {
    updateById(dormLog);
  }

  
  @Override
  public void deleteDormLogByIds(Long[] ids) {
    removeBatchByIds(Arrays.asList(ids));
  }


  @Override
  public void deleteDormLogById(Long id) {
    removeById(id);
  }
}

