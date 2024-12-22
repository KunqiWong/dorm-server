package com.kaiyu.utils;

import java.util.HashMap;
import java.util.Map;

public class Transform {
    private static final Map<String, String> FIELD_MAPPING = new HashMap<>();

    static {
        FIELD_MAPPING.put("buildingNum", "楼栋");
        FIELD_MAPPING.put("floor", "楼层");
        FIELD_MAPPING.put("roomNum", "房间号");
        FIELD_MAPPING.put("capacity", "可住人数");
        FIELD_MAPPING.put("capacityNum", "已住人数");
        FIELD_MAPPING.put("roomType", "房间属性");
        FIELD_MAPPING.put("roomStandard", "房间标准");
        FIELD_MAPPING.put("remark", "备注");
        FIELD_MAPPING.put("createTime", "创建时间");
        FIELD_MAPPING.put("updateTime", "更新时间");
        FIELD_MAPPING.put("createBy", "创建人");
        FIELD_MAPPING.put("updateBy", "更新人");
        FIELD_MAPPING.put("seq", "序号");
        FIELD_MAPPING.put("dept", "部门");
        FIELD_MAPPING.put("staffNum", "工号");
        FIELD_MAPPING.put("staffName", "姓名");
        FIELD_MAPPING.put("sex", "性别");
        FIELD_MAPPING.put("contry", "国籍");
        FIELD_MAPPING.put("company", "公司");
        FIELD_MAPPING.put("post", "职务");
        FIELD_MAPPING.put("phone", "电话");
        FIELD_MAPPING.put("visaType", "签证类型");
        FIELD_MAPPING.put("passportNo", "护照号");
        FIELD_MAPPING.put("bedNum", "床位");
        FIELD_MAPPING.put("keyFlag", "钥匙");
        FIELD_MAPPING.put("checkinDate", "入住日期");
        FIELD_MAPPING.put("deposit", "入住押金");
    }

    // 获取字段对应的中文名
    public static String getFieldName(String fieldName) {
        return FIELD_MAPPING.getOrDefault(fieldName, fieldName); // 如果没有映射，返回原字段名
    }
}


