package com.kaiyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaiyu.domain.vo.BuildingInfo;
import com.kaiyu.domain.vo.StaffInfo;
import com.kaiyu.mapper.BuildingMapper;
import com.kaiyu.mapper.StaffInfoMapper;
import com.kaiyu.service.IStaffInfoService;
import com.kaiyu.utils.UserHolder;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaiyu.common.domain.PageR;
import com.kaiyu.domain.dto.StaffListDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import com.kaiyu.domain.vo.UserInfo;
import com.kaiyu.domain.vo.DormLog;
import com.kaiyu.common.utils.IdUtils;
import com.kaiyu.service.IDormLogService;
import com.kaiyu.utils.Compare;

@Slf4j
@Service
public class StaffInfoServiceImpl extends ServiceImpl<StaffInfoMapper, StaffInfo>  implements IStaffInfoService {
  
  @Autowired
  private StaffInfoMapper StaffInfoMapper;

  @Autowired
  private IDormLogService IDormLogService;


    public PageR<StaffInfo> selectStaffInfoList(StaffListDTO dto) {
      // 构建查询条件
      LambdaQueryWrapper<StaffInfo> queryWrapper = new LambdaQueryWrapper<>();

      // 处理 query 字段的模糊匹配
      if (ObjectUtils.isNotEmpty(dto.getQuery())) {
          queryWrapper.and(wrapper ->
              wrapper.like(StaffInfo::getStaffNum, dto.getQuery().toUpperCase())
                  .or().like(StaffInfo::getStaffName, dto.getQuery())
                  .or().like(StaffInfo::getPhone, dto.getQuery().toUpperCase())
                  .or().like(StaffInfo::getPassportNo, dto.getQuery())
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
    

    public List<StaffInfo> selectStaffInfoAll() {
        List<StaffInfo> list = StaffInfoMapper.selectList(null);
        log.info("list: {}", list);
        return list;
    }

    public void saveStaffInfo(StaffInfo dto) {
        UserInfo loginUser = UserHolder.getLoginUser();
        dto.setUpdateBy(loginUser.getUserName());
        if(dto.getId() == null){
            this.StaffInfoMapper.insert(dto);
            DormLog log = new DormLog();
            log.setOperater(loginUser.getUserName());
            log.setOperateType("添加记录");
            StringBuffer sb = new StringBuffer();
            sb.append("添加记录：工号：").append(dto.getStaffNum()).append(" ->姓名： ")
                .append(dto.getStaffName()).append(" ->电话： ")
                .append(dto.getPhone());
            log.setRemark(sb.toString());
            this.IDormLogService.insertDormLog(log);
        }else{
            StaffInfo oldData = this.StaffInfoMapper.selectById(dto.getId()); // 获取修改前的数据
            this.StaffInfoMapper.updateById(dto);

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

    public void saveBatchStaffInfo(List<StaffInfo> dto) {
        UserInfo loginUser = UserHolder.getLoginUser();
        for (StaffInfo item : dto) {
            // 如果 staffName 为空，跳过此条数据
            if (ObjectUtils.isEmpty(item.getStaffName())) {
                continue;
            }
            boolean recordExists = false;
            if (ObjectUtils.isNotEmpty(item.getId())) {
                recordExists = lambdaQuery().eq(StaffInfo::getId, item.getId()).exists();
            } 
            log.info("recordExists: {},id: {}", recordExists,item.getId());
            item.setUpdateBy(loginUser.getUserName());
            if(recordExists){
                StaffInfo oldData = this.StaffInfoMapper.selectById(item.getId()); // 获取修改前的数据
                this.StaffInfoMapper.updateById(item);

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
            }else{
                item.setId(IdUtils.genLongId());
                this.StaffInfoMapper.insert(item);
                DormLog log = new DormLog();
                log.setOperateType("添加记录");
                log.setOperater(UserHolder.getLoginUser().getUserName());
                StringBuffer sb = new StringBuffer();
                sb.append("添加记录：").append(item.getStaffNum()).append(" -> ")
                .append(item.getStaffName()).append(" -> ")
                .append(item.getPhone());
                log.setRemark(sb.toString());
                this.IDormLogService.insertDormLog(log);
            }
        }
    }
    

    @Transactional
    public void deleteStaffInfoById(String id) {
        // 查询员工信息
        StaffInfo staffInfo = this.StaffInfoMapper.selectById(id);
        if (staffInfo == null) {
            // 如果员工信息不存在，抛出异常或记录日志，防止误删操作
            throw new RuntimeException("未找到ID为 " + id + " 的员工信息，无法执行删除操作");
        }

        // 删除员工信息
        this.StaffInfoMapper.deleteById(id);

        // 构建日志信息
        DormLog log = new DormLog();
        log.setOperateType("删除记录");
        log.setOperater(UserHolder.getLoginUser().getUserName());
        StringBuffer sb = new StringBuffer();
        sb.append("删除记录：").append("工号： ");
        sb.append(staffInfo.getStaffNum()).append(" -> 姓名：")
        .append(staffInfo.getStaffName()).append(" -> 手机号：")
        .append(staffInfo.getPhone());
        log.setRemark(sb.toString());

        // 插入日志记录
        this.IDormLogService.insertDormLog(log);
    }

}

