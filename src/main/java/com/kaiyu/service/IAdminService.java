package com.kaiyu.service;

import com.kaiyu.common.domain.PageR;
import com.kaiyu.domain.dto.AdminListDTO;
import com.kaiyu.domain.dto.AdminLoginDTO;
import com.kaiyu.domain.dto.ModifyPasswordDTO;
import com.kaiyu.domain.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
// import com.kaiyu.domain.entity.Category;
import com.kaiyu.domain.vo.UserInfo;

// import java.util.List;


public interface IAdminService extends IService<Admin> {

    String login(AdminLoginDTO dto);

    String getRoutes();

    UserInfo getUserInfo();

    PageR<Admin> list(AdminListDTO dto);

    void reset(String userName);

    void change(String userName, Integer status);

    void create(Admin dto);

    void modifyPassword(ModifyPasswordDTO dto);

    void logout();

}
