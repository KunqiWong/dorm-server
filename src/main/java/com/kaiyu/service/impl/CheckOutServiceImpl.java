package com.kaiyu.service.impl;

import com.kaiyu.common.domain.PageR;
import com.kaiyu.common.exceptions.CommonException;
import com.kaiyu.common.utils.IdUtils;
import com.kaiyu.service.ICheckOutService;


// import com.kaiyu.common.utils.SecurityUtils;
// import com.kaiyu.common.utils.StringUtils;
// import com.kaiyu.common.domain.AjaxResult;
import com.kaiyu.domain.vo.TreeSelect;
import com.kaiyu.domain.vo.BuildingInfo;
import com.kaiyu.domain.vo.DormLog;
// import com.kaiyu.domain.vo.LeaveInfo;
// import com.kaiyu.domain.vo.RoomRecord;
import com.kaiyu.domain.vo.StaffInfo;
import com.kaiyu.domain.vo.BuildingDateVo;
import com.kaiyu.mapper.BuildingMapper;
// import com.kaiyu.mapper.RoomRecordMapper;
import com.kaiyu.mapper.StaffInfoMapper;
import com.kaiyu.service.IBuildingInfoService;
// import com.kaiyu.service.IDormLogService;
// import com.kaiyu.service.ILeaveInfoService;
import com.kaiyu.service.IStaffInfoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaiyu.mapper.CheckOutMapper;
import com.kaiyu.mapper.StaffInfoMapper;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.kaiyu.domain.dto.BuildingListDTO;
import com.kaiyu.domain.dto.StaffListDTO;
import com.kaiyu.domain.dto.CheckOutListDTO;
import com.kaiyu.domain.dto.CheckOutDTO;
import com.kaiyu.domain.vo.LeaveInfo;
import com.kaiyu.domain.vo.BuildingRest;
import com.kaiyu.domain.vo.BuildingInfoRest;
import com.kaiyu.domain.vo.FloorRest;
import com.kaiyu.domain.vo.RoomRest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.Map;

import com.kaiyu.utils.UserHolder;


@Service
@Slf4j
public class CheckOutServiceImpl extends ServiceImpl<CheckOutMapper, LeaveInfo> implements ICheckOutService {

    @Autowired
    private StaffInfoMapper staffInfoMapper;
    

    // 退宿记录查询
    @Override
    public PageR<LeaveInfo> getCheckOutList(CheckOutListDTO dto) {
        // 构建查询条件
        // 构建 QueryWrapper
        QueryWrapper<LeaveInfo> queryWrapper = new QueryWrapper<>();
        // 根据 DTO 中的条件动态添加查询条件
        queryWrapper
          .eq(ObjectUtils.isNotEmpty(dto.getOperator()), "operator", dto.getOperator())
          .eq(ObjectUtils.isNotEmpty(dto.getStaffName()), "staff_name", dto.getStaffName())
          .eq(ObjectUtils.isNotEmpty(dto.getPassportNo()), "passport_no", dto.getPassportNo())
          .eq(ObjectUtils.isNotEmpty(dto.getStaffNum()), "staff_num", dto.getStaffNum())
          .ge(ObjectUtils.isNotEmpty(dto.getStartTime()), "DATE(create_time)", dto.getStartTime())
          .le(ObjectUtils.isNotEmpty(dto.getEndTime()), "DATE(create_time)", dto.getEndTime());
        // 执行分页查询
        Page<LeaveInfo> resultPage = this.baseMapper.selectPage(dto.toMpPage(), queryWrapper);
        log.info("resultPage: {}", resultPage);
        // 返回封装的分页结果
        return PageR.of(resultPage);
    }

    @Override
    public List<LeaveInfo> selectLeaveInfoAll() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public void checkOutStaff(CheckOutDTO dto) {
        // this.baseMapper.checkOutStaff(dto);
        this.staffInfoMapper.deleteById(dto.getStaffId());
        LeaveInfo leaveInfo = new LeaveInfo();
        BeanUtils.copyProperties(dto, leaveInfo);
        leaveInfo.setOperator(UserHolder.getLoginUser().getUserName());
        log.info("dto: {}", dto.getStaffId());
        this.baseMapper.insert(leaveInfo);
    }
}


