package com.kaiyu.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import com.kaiyu.domain.vo.UserInfo;
import com.kaiyu.utils.JwtUtil;
import com.kaiyu.utils.TokenHolder;
import com.kaiyu.utils.UserHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "AuthorizeFilter",urlPatterns = "/*")
@Slf4j
@Order(value = 2) //存在多个filter时 指定filter执行顺序
public class AuthorizeFilter implements Filter {

    private List<String> whitePath;

    private static final String UN_AUTHORIZATION = "{\"code\": 401, \"msg\": \"未认证\", \"data\": null}";
    private static final String AUTHORIZATION_FAIL = "{\"code\": 100, \"msg\": \"认证失败\", \"data\": null}";
    private static final String LOGIN_AGAIN = "{\"code\": 1, \"msg\": \"重新登录\", \"data\": null}";

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("AuthorizeFilter initialization.......");
        whitePath = new ArrayList<>();
        whitePath.add("/login");
        whitePath.add("/v3/api-docs");
        whitePath.add("/doc.html");
        whitePath.add("webjars");
        whitePath.add("favicon");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String reqUrl = httpServletRequest.getRequestURL().toString();
//        log.info("AuthorizeFilter: 请求的sessionID为{},请求的url为{}",httpServletRequest.getSession().getId(),httpServletRequest.getRequestURL());
        log.info("请求的uri为{}",httpServletRequest.getRequestURI());
        for(String url : whitePath){
            if(reqUrl.contains(url)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }
        String token = httpServletRequest.getHeader("Authorization");
        // log.info("token : {}", token);
        if(StringUtils.isEmpty(token)){
            log.info("未认证");
            writeMessage(httpServletResponse, UN_AUTHORIZATION);
            return;
        }
        if (StringUtils.isNotEmpty(token) && token.startsWith("Bearer ")) {
            token = token.substring(7); // 去掉 "Bearer " 前缀
        }
        //解析Token
        try {
            Claims claimsBody = JwtUtil.getClaimsBody(token);
            //  -1：有效，0：有效，1：过期，2：过期
            int verifyToken = JwtUtil.verifyToken(claimsBody);
            log.info("verifyToken: {}", verifyToken);
            if(verifyToken > 0){
                log.info("Token过期");
                writeMessage(httpServletResponse, LOGIN_AGAIN);
                return;
            }
            //解析 分为两类用户 User  Admin
            UserInfo userInfo = new UserInfo();
            // @SuppressWarnings("all")
            // Long userId = claimsBody.get("userId", Long.class);
            // if(ObjectUtils.isNotEmpty(userId)){
            //     // 是User
            //     userInfo.setUserId(userId);
            //     userInfo.setUserName(claimsBody.get("userName", String.class));
            //     userInfo.setCardId(claimsBody.get("cardId", String.class));
            //     userInfo.setRole("user");
            //     log.info("user -- userId :{}",userId);

            //     TokenHolder.set(token);
            // }else {
                // 是Admin
                userInfo.setUserName(claimsBody.get("userName", String.class));
                userInfo.setRole(claimsBody.get("role", String.class));
                log.info("admin -- adminName : {}",userInfo.getUserName());
            // }
            //封装user对象存入线程变量中
            UserHolder.set(userInfo);
            filterChain.doFilter(servletRequest,servletResponse);
            //移除
            UserHolder.remove();
            TokenHolder.remove();
            log.info("请求结束 , 移除ThreadLocal中线程数据.....");
        } catch (Exception e) {
            log.error("token校验失败 : {}",e.getMessage());
            writeMessage(httpServletResponse, AUTHORIZATION_FAIL);
        }
    }

    private static void writeMessage(HttpServletResponse httpServletResponse, String errorMessage) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(errorMessage);
    }

    @Override
    public void destroy() {

    }

}
