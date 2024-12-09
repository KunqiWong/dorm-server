package com.kaiyu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.kaiyu.common.domain.PageR;
import com.kaiyu.common.exceptions.impl.BadRequestException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import com.kaiyu.domain.dto.AdminListDTO;
import com.kaiyu.domain.dto.AdminLoginDTO;
import com.kaiyu.domain.dto.ModifyPasswordDTO;
import com.kaiyu.domain.entity.Admin;
import com.kaiyu.domain.vo.UserInfo;
import com.kaiyu.mapper.AdminMapper;
import com.kaiyu.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import com.kaiyu.utils.GetRoutes;
import com.kaiyu.utils.JwtUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.kaiyu.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;

// import java.util.List;
import java.util.Map;
// import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import static com.kaiyu.common.constants.Constant.ONE_LOGIN;
// import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private ObjectMapper mapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String login(AdminLoginDTO dto) {
        // System.out.println(dto);
        if(ObjectUtils.isEmpty(dto.getUsername()) || StringUtils.isEmpty(dto.getPassword())){
            throw new BadRequestException("用户名或密码不能为空");
        }
        Admin admin = getById(dto.getUsername());

        if(admin == null || !passwordEncoder.matches(dto.getPassword(),admin.getPassword())){
            throw new BadRequestException("用户名或密码错误 User atau Password salah");
        }
        if(admin.getStatus() == 1){throw new BadRequestException("账号已禁用,请联系管理员 Akun Telah Dinonaktifan");}

        String username = admin.getUserName();
        String role = admin.getRole();
            
        String token = JwtUtil.getToken(Map.of(
                "userName",username,
                "role",role
        ));
        // stringRedisTemplate.opsForValue().set(ONE_LOGIN + username, token,8L, TimeUnit.DAYS);

        ObjectMapper objectMapper = new ObjectMapper();
        // 获取当前日期加七天后的日期
        LocalDateTime expiresDateTime = LocalDateTime.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String expires = expiresDateTime.format(formatter);
        try {
            return objectMapper.writeValueAsString( Map.of(
                "username", username,
                "nickname", role,
                "roles", List.of(role),
                "permissions", List.of("*:*:*"),
                "accessToken", token,
                "expires", expires
            ));
        } catch (JsonProcessingException e) {
            log.error("Error processing JSON", e);
            throw new BadRequestException("Error generating token response");
        }

            // System.out.println(res);
        // //颁发Token
        // return JwtUtil.getToken(Map.of(
        //         "userName",admin.getUserName(),
        //         "role",admin.getRole()
        // ));
        
    }

    @Override
    public String getRoutes() {
        try {
                GetRoutes getRouter = new GetRoutes();
                String permissionRouter = '[' + mapper.writeValueAsString(getRouter.getPermissionRouter()) + ']';
                return permissionRouter;
            
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserInfo getUserInfo() {
        return UserHolder.getLoginUser();
    }

    @Override
    public PageR<Admin> list(AdminListDTO dto) {
        
        Page<Admin> page = lambdaQuery()
                .like(ObjectUtils.isNotEmpty(dto.getUserName()), Admin::getUserName, dto.getUserName())
                .eq(ObjectUtils.isNotEmpty(dto.getStatus()),Admin::getStatus, dto.getStatus())
                .eq(Admin::getRole,"common")
                .page(dto.toMpPage());
        List<Admin> list = page.getRecords().stream().peek(a -> a.setPassword(null)).toList();
        page.setRecords(list);
        return PageR.of(page);
    }

    @Override
    public void reset(String userName) {
        Admin admin = getById(userName);
        if(admin == null){
            throw new BadRequestException("用户不存在");
        }
        admin.setPassword(passwordEncoder.encode("123456"));
        updateById(admin);
    }

    @Override
    public void change(String userName, Integer status) {
        Admin admin = getById(userName);
        if(admin == null){
            throw new BadRequestException("用户不存在");
        }
        admin.setStatus(status);
        updateById(admin);
    }

    @Override
    public void create(Admin dto) {
        Admin admin = getById(dto.getUserName());
        if(admin != null){
            throw new BadRequestException("用户已存在 Akun Telah Ada");
        }
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        dto.setRole("common");
        save(dto);
    }

    @Override
    public void modifyPassword(ModifyPasswordDTO dto) {
        UserInfo loginUser = UserHolder.getLoginUser();
        Admin admin = getById(loginUser.getUserName());
        if(!passwordEncoder.matches(dto.getRawPassword(),admin.getPassword())){
            throw new BadRequestException(506,"原密码错误 Password saat ini salah");
        }
        admin.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        updateById(admin);
    }

    @Override
    public void logout() {
        UserInfo loginUser = UserHolder.getLoginUser();
        stringRedisTemplate.delete(ONE_LOGIN + loginUser.getUserName());
    }


}
