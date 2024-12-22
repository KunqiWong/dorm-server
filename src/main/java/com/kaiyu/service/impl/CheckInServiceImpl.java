package com.kaiyu.service.impl;

import com.kaiyu.common.domain.PageR;
import com.kaiyu.common.exceptions.CommonException;
import com.kaiyu.common.utils.IdUtils;
import com.kaiyu.service.ICheckInService;


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
import com.kaiyu.service.ICheckInService;
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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.data.redis.core.StringRedisTemplate;

import com.kaiyu.domain.dto.BuildingListDTO;
import com.kaiyu.domain.dto.StaffListDTO;
import com.kaiyu.domain.dto.CheckOutListDTO;
import com.kaiyu.domain.dto.ExchangeStaffRoomDTO;
import com.kaiyu.domain.vo.LeaveInfo;
import com.kaiyu.domain.vo.BuildingRest;
import com.kaiyu.domain.vo.BuildingInfoRest;
import com.kaiyu.domain.vo.FloorRest;
import com.kaiyu.domain.vo.RoomRest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaiyu.service.IDormLogService;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

@Service
@Slf4j
public class CheckInServiceImpl extends ServiceImpl<StaffInfoMapper, StaffInfo> implements ICheckInService {
    @Autowired
    private BuildingMapper BuildingMapper;    

    @Autowired
    private StaffInfoMapper StaffInfoMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;  

    @Autowired
    private IDormLogService dormLogService;
    
    @Override
    public BuildingRest getBuildingRest(BuildingListDTO dto) {
        //避免了 split() 等操作时遇到空值或数组越界的错误。
        if (dto == null || dto.getQuery() == null) {
            log.error("BuildingListDTO 或 query 为空");
            return null;
        }

        List<String> queryList = Arrays.asList(dto.getQuery().split("\\*"));
        if (queryList.size() < 1) {
            log.error("queryList 长度不足: {}", queryList);
            return null;
        }

        BuildingRest buildingRest = this.BuildingMapper.getBuildingRest(queryList.get(0));
        log.info("getBuildingRest 返回值: {}", buildingRest);
        return buildingRest;
    }


    @Override
    public BuildingInfoRest getBuildingInfoRest(BuildingListDTO dto) {
        //避免了 split() 等操作时遇到空值或数组越界的错误。
        if (dto == null || dto.getQuery() == null) {
            log.error("BuildingListDTO 或 query 为空");
            return null;
        }

        List<String> queryList = Arrays.asList(dto.getQuery().split("\\*"));
        if (queryList.size() < 1) {
            log.error("queryList 长度不足: {}", queryList);
            return null;
        }

        BuildingInfoRest buildingInfoRest = this.BuildingMapper.getBuildingInfoRest(queryList.get(0));
        log.info("getBuildingInfoRest 返回值: {}", buildingInfoRest);
        return buildingInfoRest;
    }

    @Override
    public FloorRest getFloorRest(BuildingListDTO dto) {
        //避免了 split() 等操作时遇到空值或数组越界的错误。
        if (dto == null || dto.getQuery() == null) {
            log.error("BuildingListDTO 或 query 为空");
            return null;
        }

        List<String> queryList = Arrays.asList(dto.getQuery().split("\\*"));
        if (queryList.size() < 1) {
            log.error("queryList 长度不足: {}", queryList);
            return null;
        }

        FloorRest floorRest = this.BuildingMapper.getFloorRest(queryList.get(0), queryList.get(1));
        log.info("getFloorRest 返回值: {}", floorRest);
        return floorRest;
    }

    @Override
    public RoomRest getRoomRest(BuildingListDTO dto) {
        //避免了 split() 等操作时遇到空值或数组越界的错误。
        if (dto == null || dto.getQuery() == null) {
            log.error("BuildingListDTO 或 query 为空");
            return null;
        }

        List<String> queryList = Arrays.asList(dto.getQuery().split("\\*"));
        if (queryList.size() < 1) {
            log.error("queryList 长度不足: {}", queryList);
            return null;
        }

        RoomRest roomRest = this.BuildingMapper.getRoomRest(queryList.get(0), queryList.get(1), queryList.get(2));
        log.info("getRoomRest 返回值: {}", roomRest);
        return roomRest;
    }

  
    public List<TreeSelect> getBuildingTreeFromRedis() throws IOException {
        // Fetch the JSON string from Redis
        String buildingTreeJson = stringRedisTemplate.opsForValue().get("buildingTree");

        // Use Jackson to deserialize the JSON string into List<TreeSelect>
        if (buildingTreeJson != null) {
            return objectMapper.readValue(buildingTreeJson, 
                objectMapper.getTypeFactory().constructCollectionType(List.class, TreeSelect.class));
        }
        return null;
    }
    public List<TreeSelect> buildBuildingInfoTreeSelectWithCache() {
        // List<TreeSelect> buildingTree = null;
        // try {
        //     buildingTree = getBuildingTreeFromRedis();
        // } catch (IOException e) {
        //     buildingTree = null;
        // }

        // if (buildingTree != null) {
        //     return buildingTree;
        // }

        CopyOnWriteArraySet<TreeSelect> treeList = new CopyOnWriteArraySet<>(Collections.singleton(new TreeSelect()));
        String[] floors = { "1楼", "2楼", "3楼", "4楼", "5楼"};
        List<String> buildingName = this.BuildingMapper.getBuildingInfoName();

        List<BuildingInfo> roomNumList = this.BuildingMapper.getRoomNumList();

        for (String building : buildingName) {
            TreeSelect treeSelect = new TreeSelect();
            treeSelect.setId(Long.valueOf(IdUtils.genLongId()));
            treeSelect.setLabel(building);
            CopyOnWriteArrayList<TreeSelect> listFloor = new CopyOnWriteArrayList<>();

            try {
                for (String floor : floors) {
                    TreeSelect floorVo = new TreeSelect();
                    floorVo.setId(Long.valueOf(IdUtils.genLongId()));
                    floorVo.setLabel(floor);
                    floorVo.setParentLabel(building);

                    CopyOnWriteArrayList<TreeSelect> listRoom = new CopyOnWriteArrayList<>();

                    for (BuildingInfo room : roomNumList) {
                        if (room.getBuildingNum().equals(building) && room.getFloor().equals(floor)) {
                            TreeSelect roomVo = new TreeSelect();
                            roomVo.setId(room.getId());
                            roomVo.setLabel(room.getRoomNum());
                            roomVo.setParentLabel(building+"*"+floor);
                            listRoom.add(roomVo);
                        }
                    }
                    if (!CollectionUtils.isEmpty(listRoom)) {
                        floorVo.setChildren(listRoom);
                        listFloor.add(floorVo);
                    }
                }
                treeSelect.setChildren(listFloor);
            } catch (Exception e) {
                e.printStackTrace();
            }
            treeList.add(treeSelect);
        }
        List<TreeSelect> resultList = (new ArrayList<>(treeList)).subList(1, treeList.size());
        // try {
        //     this.stringRedisTemplate.opsForValue().set("buildingTree", objectMapper.writeValueAsString(resultList));
        // } catch (Exception e) {
        //     throw new CommonException("保存楼栋树结构失败");
        // }
        return resultList;
    }

    @Override
    public PageR<StaffInfo> employeeListByBuilding(StaffListDTO dto) {

      List<String> queryList = Arrays.asList(dto.getQuery().split("\\*"));
        // 构建查询条件
      LambdaQueryWrapper<StaffInfo> queryWrapper = new LambdaQueryWrapper<>();

      if (queryList.size() == 1) {
        queryWrapper.and(wrapper ->
            wrapper.eq(StaffInfo::getBuildingNum, queryList.get(0).toUpperCase())
        );
      }
      if (queryList.size() == 2) {
        queryWrapper.and(wrapper ->
            wrapper.eq(StaffInfo::getBuildingNum, queryList.get(0).toUpperCase())
                   .eq(StaffInfo::getFloor, queryList.get(1))
        );
      }
      if (queryList.size() == 3) {
          queryWrapper.and(wrapper ->
              wrapper.eq(StaffInfo::getBuildingNum, queryList.get(0).toUpperCase())
                     .eq(StaffInfo::getFloor, queryList.get(1))
                     .eq(StaffInfo::getRoomNum, queryList.get(2).toUpperCase())
          );
      }

        //  升序排序
        queryWrapper.orderByAsc(StaffInfo::getBuildingNum, StaffInfo::getFloor, StaffInfo::getRoomNum);
      // 执行查询（分页 + 条件）
        Page<StaffInfo> page = dto.toMpPage();
        // 执行分页查询
        Page<StaffInfo> resultPage = this.baseMapper.selectPage(page, queryWrapper);
        log.info("resultPage: {}", resultPage);
        // 返回封装的分页结果
        return PageR.of(resultPage);

    }

    @Override
    public void exchangeStaffRoom(ExchangeStaffRoomDTO dto) {
        DormLog dormLog = new DormLog();
        dormLog.setOperateType("调换记录");

        if(dto.getSelectedStaff().equals("调入房间") ){
            String selectedRoomStandard = this.StaffInfoMapper.selectedRoomInfo(dto);
            dto.setSelectedRoomStandard(selectedRoomStandard);
            this.StaffInfoMapper.updateRoom(dto);
            // 记录日志
            dormLog.setRemark("调换记录：'"+dto.getStaffName()+"'从"+dto.getRoomNum()+"调入"+dto.getSelectedRoom()+", 原因："+dto.getChangeReason());
        }
        StaffInfo selectedInfo = this.StaffInfoMapper.selectedInfo(dto);
        StaffInfo info = this.StaffInfoMapper.Info(dto);
        dto.setSelectedRoomStandard(selectedInfo.getRoomStandard());
        dto.setSelectedBedNum(selectedInfo.getBedNum());
        dto.setRoomStandard(info.getRoomStandard());
        dto.setBedNum(info.getBedNum());
        this.StaffInfoMapper.exchangeStaffRoom(dto);
        this.StaffInfoMapper.exchangeSelectedRoom(dto);
        // 记录日志
        dormLog.setRemark("调换记录：'"+dto.getStaffName()+"'从"+dto.getRoomNum()+"调入"+dto.getSelectedRoom()+","+dto.getSelectedStaff()+"从"+dto.getSelectedRoom()+"调入"+dto.getRoomNum()+", 原因："+dto.getChangeReason());

        this.dormLogService.insertDormLog(dormLog);
    }

}

