// package com.kaiyu.interceptor;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.AllArgsConstructor;
// import lombok.NoArgsConstructor;
// import org.springframework.data.redis.core.StringRedisTemplate;
// import org.springframework.web.servlet.HandlerInterceptor;
// import com.kaiyu.common.exceptions.CommonException;
// import com.kaiyu.common.exceptions.impl.BadRequestException;
// import org.apache.commons.lang3.ObjectUtils;
// import org.apache.commons.lang3.StringUtils;
// import com.kaiyu.domain.vo.UserInfo;
// import com.kaiyu.utils.TokenHolder;
// import com.kaiyu.utils.UserHolder;
// import lombok.extern.slf4j.Slf4j;

// import static com.kaiyu.common.constants.Constant.ONE_LOGIN;


// @NoArgsConstructor
// @AllArgsConstructor
// @Slf4j
// public class OneLoginInterceptor implements HandlerInterceptor {


//     private StringRedisTemplate stringRedisTemplate;

//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//         UserInfo loginUser = UserHolder.getLoginUser();
//         log.info("loginUser:{}",loginUser);
//         //只拦截用户
//         if (ObjectUtils.isNotEmpty(loginUser.getUserName())) {
//             String token = stringRedisTemplate.opsForValue().get(ONE_LOGIN + loginUser.getUserName());
//             log.info("token:{}",token);
//             if(StringUtils.isBlank(token)){
//                 throw new CommonException(1, "重新登录");
//             }
//             if(!TokenHolder.getToken().equals(token)){
//                 throw new BadRequestException(701,"多设备登录 , 拒绝访问");
//             }
//         }
//         return true;
//     }
// }
