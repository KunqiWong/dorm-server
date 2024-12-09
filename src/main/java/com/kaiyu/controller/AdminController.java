package com.kaiyu.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kaiyu.annotation.Log;
import com.kaiyu.annotation.PreAuthorize;
import com.kaiyu.common.domain.PageR;
import com.kaiyu.common.domain.R;
import com.kaiyu.domain.dto.AdminListDTO;
import com.kaiyu.domain.dto.AdminLoginDTO;
import com.kaiyu.domain.dto.ModifyPasswordDTO;
import com.kaiyu.domain.entity.Admin;
import com.kaiyu.domain.vo.UserInfo;
import com.kaiyu.service.IAdminService;
import com.kaiyu.annotation.RepeatSubmit;

// import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/login")
    public R<String> login(@RequestBody AdminLoginDTO dto){
        String token = adminService.login(dto);
        return R.ok(token);
    }
    
    @GetMapping("/get-async-routes")
    public R<String> getAsyncRoutes(){
        String res = adminService.getRoutes();
        return R.ok(res);
    }

    @Log
    @RepeatSubmit
    @PreAuthorize({"super","admin"})
    @PostMapping("/create")
    public R<Void> create(@RequestBody Admin dto){
        adminService.create(dto);
        return R.ok();
    }


    /**
     * 返回管理员账号信息 editor
     */
    @PreAuthorize({"super","admin"})
    @PostMapping("/list")
    public PageR<Admin> list(@RequestBody AdminListDTO dto){
        return adminService.list(dto);
    }

    /**
     * 重置该用户密码 123456
     */
    @Log
    @GetMapping("/reset/{userName}")
    public R<Void> reset(@PathVariable String userName){
        adminService.reset(userName);
        return R.ok();
    }


    /**
     * 禁用启用账号
     */
    @Log
    @GetMapping("/change")
    public R<Void> change(String userName, Integer status){
        adminService.change(userName,status);
        return R.ok();
    }

    @PostMapping("/logout")
    public R<Void> logout(){
        return R.ok();
    }

    @GetMapping("/info")
    public R<UserInfo> info(){
        UserInfo userInfo = adminService.getUserInfo();
        return R.ok(userInfo);
    }

    @Log
    @PostMapping("/modifyPassword")
    public R<Void> modifyPassword(@RequestBody ModifyPasswordDTO dto){
        adminService.modifyPassword(dto);
        return R.ok();
    }


}
