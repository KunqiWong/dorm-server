package com.kaiyu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kaiyu.annotation.PreAuthorize;
import com.kaiyu.common.domain.PageR;
import com.kaiyu.domain.dto.StaffListDTO;
import com.kaiyu.domain.vo.StaffInfo;
import com.kaiyu.service.IStaffInfoService;
import com.kaiyu.common.domain.R;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffInfoController {
     @Autowired
    private IStaffInfoService staffInfoService;

    @PreAuthorize({"super","admin"})
    @PostMapping("/list")
    public PageR<StaffInfo> getStaffInfoList(@RequestBody StaffListDTO dto) {
        return  staffInfoService.selectStaffInfoList(dto); 
    }

    @PreAuthorize({"super","admin"})
    @GetMapping("/all")
    public R<List<StaffInfo>> getStaffInfoAll() {
        return R.ok(staffInfoService.selectStaffInfoAll()); 
    }

    @PreAuthorize({"super","admin"})
    @PostMapping("/save")
    public R<Void> saveStaffInfo(@RequestBody StaffInfo dto) {
        staffInfoService.saveStaffInfo(dto); 
        return R.ok();
    }

    @PreAuthorize({"super","admin"})
    @PostMapping("/saveBatch")
    public R<Void> saveBatchStaffInfo(@RequestBody List<StaffInfo> dto) {
        staffInfoService.saveBatchStaffInfo(dto); 
        return R.ok();
    }

    @PreAuthorize({"super","admin"})
    @PostMapping({"/delete"})
    public R<Void> remove(@RequestBody String id) {
                
         this.staffInfoService.deleteStaffInfoById(id);
        return R.ok();
    }

}
