package com.kaiyu.controller;

import com.kaiyu.domain.vo.BuildingInfo;
import com.kaiyu.domain.vo.DormLog;
import com.kaiyu.service.IBuildingInfoService;
// import com.kaiyu.project.dorm.service.IDormLogService;
import java.util.List;
// import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
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

import com.kaiyu.annotation.PreAuthorize;
import com.kaiyu.common.domain.PageR;
import com.kaiyu.common.domain.R;

import com.kaiyu.domain.dto.BuildingListDTO;
import com.kaiyu.domain.entity.Building;
import com.kaiyu.mapper.BuildingMapper;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/building")
@Slf4j
public class BuildingInfoController {

    @Autowired
    private IBuildingInfoService buildingInfoService;


    @Autowired
    private BuildingMapper buildingMapper;

    @PostMapping("/selectRoom")
    public R<BuildingInfo> selectBuildingInfoByBuildingFloorRoom(@RequestBody BuildingListDTO dto) {
        return R.ok(this.buildingMapper.selectBuildingInfoByBuildingFloorRoom(dto.getBuildingNum(), dto.getFloor(), dto.getRoomNum()));
    }

    @PreAuthorize({"super","admin"})
    @PostMapping("/list")
    public PageR<BuildingInfo> getBuildingInfoList(@RequestBody BuildingListDTO dto) {
        return  buildingInfoService.selectBuildingInfoList(dto); 
    }

    @PreAuthorize({"super","admin"})
    @GetMapping("/all")
    public R<List<BuildingInfo>> getBuildingInfoAll() {
        return R.ok(buildingInfoService.selectBuildingInfoAll()); 
    }

    @PreAuthorize({"super","admin"})
    @PostMapping("/save")
    public R<Void> saveBuildingInfo(@RequestBody BuildingInfo dto) {
        buildingInfoService.saveBuildingInfo(dto); 
        return R.ok();
    }

    @PreAuthorize({"super","admin"})
    @PostMapping("/saveBatch")
    public R<Void> saveBatchBuildingInfo(@RequestBody List<BuildingInfo> dto) {
        buildingInfoService.saveBatchBuildingInfo(dto); 
        return R.ok();
    }

    @PreAuthorize({"super","admin"})
    @PostMapping({"/delete"})
    public R<Void> remove(@RequestBody Long id) {
        buildingInfoService.deleteBuildingInfoById(id);
        return R.ok();
    }

}
