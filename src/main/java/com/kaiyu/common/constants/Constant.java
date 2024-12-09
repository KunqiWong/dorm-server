package com.kaiyu.common.constants;

public interface Constant {
    String REPEAT_SUBMIT_KEY = "repeat_submit:";
    String CATEGORY_KEY = "category:";
    String VALUE_LABEL = "value_label";
    String ALL = "ALL";
    String VARIABLES = "variables:";
    String TAKE = "Take:";
    String VEGE_TAKE = "vege_take:";
    String PRE_ORDER_BEGIN_TIME = "preOrderBeginTime";
    String PRE_ORDER_END_TIME = "preOrderEndTime";
    String CUR_ORDER_BEGIN_TIME = "CurOrderBeginTime";
    String CUR_ORDER_END_TIME = "CurOrderEndTime";
    String DEADLINE = "deadline";
    String VISIT = "visit:";
    String ONE_LOGIN = "one_login:";

    String FOOD = "food";
    String KTV = "ktv";
    String KTV_RENT = "ktvRent";
    String HOTSPOT = "hotspot";
    String PACK = "pack";
    String PACKAGE_CHARGE = "packageCharge";
    String CAFE = "cafe";
    String ORDER = "order";
    String VEGE_ORDER = "vege_order";
    String VEGE_SURE = "vege_sure:";
    String VEGE_ON = "vege_on";
    String VEGE_WHITE_LIST = "vege_white_list";

    String REQUEST_ID_HEADER = "requestId";
    String REQUEST_FROM_HEADER = "x-request-from";


    // 数据字段 - id
    String DATA_FIELD_NAME_ID = "id";

    // 数据字段 - create_time
    String DATA_FIELD_NAME_CREATE_TIME = "create_at";
    String DATA_FIELD_NAME_CREATE_TIME_CAMEL = "createTime";

    // 数据字段 - update_time
    String DATA_FIELD_NAME_UPDATE_TIME = "update_time";
    String DATA_FIELD_NAME_UPDATE_TIME_CAMEL = "updateTime";

    // 数据字段 - liked_times
    String DATA_FIELD_NAME_LIKED_TIME = "liked_times";
    String DATA_FIELD_NAME_LIKED_TIME_CAMEL = "likedTimes";

    // 数据字段 - creator
    String DATA_FIELD_NAME_CREATOR = "creator";

    // 数据字段 - updater
    String DATA_FIELD_NAME_UPDATER = "updater";

    // 数据已经删除标识值
    boolean DATA_DELETE = true;
    // 数据未删除标识值
    boolean DATA_NOT_DELETE = false;
    // 响应结果是否被R标记过
    String BODY_PROCESSED_MARK_HEADER = "IS_BODY_PROCESSED";


    String UTF8 = "UTF-8";



}
