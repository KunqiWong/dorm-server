package com.kaiyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaiyu.common.domain.PageR;
import com.kaiyu.common.exceptions.CommonException;
import com.kaiyu.common.utils.IdUtils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.kaiyu.domain.vo.TreeSelect;
import com.kaiyu.domain.vo.BuildingInfo;
import com.kaiyu.domain.vo.DormLog;
import com.kaiyu.domain.vo.StaffInfo;
import com.kaiyu.domain.dto.BuildingListDTO;
import com.kaiyu.domain.entity.Building;
import com.kaiyu.mapper.BuildingMapper;
import com.kaiyu.service.IBuildingInfoService;
import com.kaiyu.utils.Compare;
import com.kaiyu.utils.UserHolder;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.kaiyu.domain.vo.UserInfo;
import com.kaiyu.service.IDormLogService;

import java.util.Date;

@Slf4j
@Service
public class BuildingInfoServiceImpl extends ServiceImpl<BuildingMapper, BuildingInfo> implements IBuildingInfoService {

    @Autowired
    private BuildingMapper BuildingMapper;

    @Autowired
    private IDormLogService IDormLogService;
    @Autowired
    private ObjectMapper objectMapper;

    private StringRedisTemplate stringRedisTemplate;

    @Override
    public BuildingInfo selectBuildingInfoById(Long id) {
        return this.BuildingMapper.selectBuildingInfoById(id);
    }

    @Override
    public PageR<BuildingInfo> selectBuildingInfoList(BuildingListDTO dto) {
        
        // 构建查询条件
        LambdaQueryWrapper<BuildingInfo> queryWrapper = new LambdaQueryWrapper<>();

        // 处理 query 字段的模糊匹配
        if (ObjectUtils.isNotEmpty(dto.getQuery())) {
            queryWrapper.and(wrapper ->
                wrapper.like(BuildingInfo::getBuildingNum, dto.getQuery().toUpperCase())
                    .or().like(BuildingInfo::getFloor, dto.getQuery())
                    .or().like(BuildingInfo::getRoomNum, dto.getQuery().toUpperCase())
                    .or().like(BuildingInfo::getRoomStandard, dto.getQuery())
                    .or().like(BuildingInfo::getRoomType, dto.getQuery().toUpperCase())
                    .or().like(BuildingInfo::getRemark, dto.getQuery())
            );
        }
        //  升序排序
        queryWrapper.orderByAsc(BuildingInfo::getBuildingNum, BuildingInfo::getFloor, BuildingInfo::getRoomNum);
        // 执行查询（分页 + 条件）
        Page<BuildingInfo> page = dto.toMpPage();
        // 执行分页查询
        Page<BuildingInfo> resultPage = this.baseMapper.selectPage(page, queryWrapper);

        log.info("resultPage:{}", resultPage);
        // 返回封装的分页结果
        return PageR.of(resultPage);
    }

    public List<BuildingInfo> selectBuildingInfoAll() {
        return this.BuildingMapper.selectBuildingInfoAll();
    }

    public void saveBuildingInfo(BuildingInfo dto) {
        UserInfo loginUser = UserHolder.getLoginUser();
        dto.setUpdateBy(loginUser.getUserName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dto.setUpdateTime(sdf.format(new Date()));
        if(dto.getId() == null){
            this.BuildingMapper.insertBuildingInfo(dto);
            DormLog log = new DormLog();
            log.setOperater(loginUser.getUserName());
            log.setOperateType("添加记录");
            StringBuffer sb = new StringBuffer();
            sb.append("添加记录：").append(dto.getBuildingNum()).append(" -> ")
                .append(dto.getFloor()).append(" -> ")
                .append(dto.getRoomNum());
            log.setRemark(sb.toString());
            this.IDormLogService.insertDormLog(log);
        }else{
            BuildingInfo oldData = this.BuildingMapper.selectById(dto.getId()); // 获取修改前的数据
            this.BuildingMapper.updateById(dto);

            // 3. 对比数据，获取修改的字段和内容
            String changes = Compare.compareAndGenerateLog(oldData, dto);

            // 4. 记录修改日志
            if (!changes.isEmpty()) { // 如果有修改内容再记录日志
                DormLog log = new DormLog();
                log.setOperateType("修改记录");
                log.setOperater(UserHolder.getLoginUser().getUserName());
                log.setRemark("修改记录："  + changes);

                this.IDormLogService.insertDormLog(log);
            }
        }
    }
    public void saveBatchBuildingInfo(List<BuildingInfo> dto) {
        UserInfo loginUser = UserHolder.getLoginUser();
        dto.forEach(item -> {
            // 检查是否有两个参数可以唯一标识记录
            boolean recordExists = lambdaQuery()
                .eq(ObjectUtils.isNotEmpty(item.getBuildingNum()), BuildingInfo::getBuildingNum, item.getBuildingNum()) 
                .eq(ObjectUtils.isNotEmpty(item.getRoomNum()), BuildingInfo::getRoomNum, item.getRoomNum())
                .exists(); // 检查记录是否存在
            item.setUpdateBy(loginUser.getUserName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            item.setUpdateTime(sdf.format(new Date()));
            if (recordExists) {
                BuildingInfo oldData = lambdaQuery()
                .eq(BuildingInfo::getBuildingNum, item.getBuildingNum()) 
                .eq(BuildingInfo::getRoomNum, item.getRoomNum()) // 获取修改前的数据
                .one();
                this.BuildingMapper.updateBuildingBatch(item);
                // 3. 对比数据，获取修改的字段和内容
                String changes = Compare.compareAndGenerateLog(oldData, item);

                // 4. 记录修改日志
                if (!changes.isEmpty()) { // 如果有修改内容再记录日志
                    DormLog log = new DormLog();
                    log.setOperateType("修改记录");
                    log.setOperater(UserHolder.getLoginUser().getUserName());
                    log.setRemark("修改记录："  + changes);

                    this.IDormLogService.insertDormLog(log);
                }
            } else {
                this.BuildingMapper.insertBuildingInfo(item);
                DormLog log = new DormLog();
                log.setOperater(loginUser.getUserName());
                log.setOperateType("添加记录");
                StringBuffer sb = new StringBuffer();
                sb.append("添加记录：").append(item.getBuildingNum()).append(" -> ")
                    .append(item.getFloor()).append(" -> ")
                    .append(item.getRoomNum());
                log.setRemark(sb.toString());
                this.IDormLogService.insertDormLog(log);
            }
        });
    }

    public void deleteBuildingInfoById(Long id) {
            // 先查询待删除的记录
            BuildingInfo buildingInfo = this.BuildingMapper.selectBuildingInfoById(id);
        
            // 删除记录
            this.BuildingMapper.deleteBuildingInfoById(id);
        
            // 创建日志
            DormLog log = new DormLog();
            log.setOperateType("删除记录");
        
            // 获取当前登录用户
            String userName = UserHolder.getLoginUser().getUserName();
            log.setOperater(userName);
        
            // 构建日志备注
            StringBuffer sb = new StringBuffer();
            sb.append("删除记录：");
            if (buildingInfo != null) {
                sb.append(buildingInfo.getBuildingNum())
                  .append(buildingInfo.getFloor())
                  .append(buildingInfo.getRoomNum());
            } else {
                sb.append("记录不存在");
            }
            log.setRemark(sb.toString());
        
            // 插入日志
            this.IDormLogService.insertDormLog(log);
        }
}
