package com.kaiyu.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRoutes {
    public Map<String, Object> getPermissionRouter() {

    // Map<String, Object> buildingRouter = new HashMap<>();
    // buildingRouter.put("path", "/building");

    // Map<String, Object> buildingMeta = new HashMap<>();
    // buildingMeta.put("meta", Map.of(
    //     "title", "sidebar.building",
    //     "roles", List.of("admin", "common","super")
    // ));
    // buildingMeta.put("icon", "ep:home-filled");
    // buildingMeta.put("rank", 10);
    // buildingRouter.put("meta", buildingMeta);

    Map<String, Object> permissionRouter = new HashMap<>();
    permissionRouter.put("path", "/permission");
    
    
    Map<String, Object> meta = new HashMap<>();
    meta.put("title", "menus.purePermission");
    meta.put("icon", "ep:lollipop");
    meta.put("rank", 10);
    permissionRouter.put("meta", meta);

    
    List<Map<String, Object>> children = new ArrayList<>();
    
    // Page Permission
    Map<String, Object> pagePerm = new HashMap<>();
    pagePerm.put("path", "/permission/page");
    pagePerm.put("name", "PermissionPage");
    pagePerm.put("meta", Map.of(
        "title", "menus.purePermissionPage",
        "roles", List.of("admin", "common","super")
    ));
    children.add(pagePerm);
    
    // // Button Permission
    // Map<String, Object> buttonPerm = new HashMap<>();
    // buttonPerm.put("path", "/permission/button");
    // buttonPerm.put("meta", Map.of(
    //     "title", "按钮权限",
    //     "roles", List.of("admin", "common")
    // ));
    
    // List<Map<String, Object>> buttonChildren = new ArrayList<>();
    
    // Router Button Permission
    // Map<String, Object> routerBtn = new HashMap<>();
    // routerBtn.put("path", "/permission/button/router");
    // routerBtn.put("component", "permission/button/index");
    // routerBtn.put("name", "PermissionButtonRouter");
    // routerBtn.put("meta", Map.of(
    //     "title", "路由返回按钮权限",
    //     "auths", List.of(
    //         "permission:btn:add",
    //         "permission:btn:edit",
    //         "permission:btn:delete"
    //     )
    // ));
    // buttonChildren.add(routerBtn);
    
    // // Login Button Permission
    // Map<String, Object> loginBtn = new HashMap<>();
    // loginBtn.put("path", "/permission/button/login");
    // loginBtn.put("component", "permission/button/perms");
    // loginBtn.put("name", "PermissionButtonLogin");
    // loginBtn.put("meta", Map.of(
    //     "title", "登录接口返回按钮权限"
    // ));
    // buttonChildren.add(loginBtn);
    
    // buttonPerm.put("children", buttonChildren);
    // children.add(buttonPerm);
    
    permissionRouter.put("children", children);
    
    return permissionRouter;
}
}
