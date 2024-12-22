create table `dorm`.`admin`
(
    user_name  varchar(50)                           not null
        primary key,
    password   varchar(255)                          not null,
    role       varchar(20) default 'admin'           not null,
    status     tinyint     default 0                 null comment '0 正常 1 禁用',
    created_at datetime    default CURRENT_TIMESTAMP null
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='后台管理用户表';

CREATE TABLE IF NOT EXISTS `dorm`.`building_info`(
    `id` bigint NOT NULL AUTO_INCREMENT,
    -- `room_info` varchar(100) NOT NULL DEFAULT '' COMMENT '房间信息',
    `building_num` varchar(50) NOT NULL DEFAULT '' COMMENT '楼栋',
    `floor` varchar(50) NOT NULL DEFAULT '' COMMENT '楼层',
    `room_num` varchar(50) NOT NULL COMMENT '房间号',
    `capacity` char(4) NULL COMMENT '可住人数',
    `capacity_num` char(4) NULL COMMENT '已住人数',
    `room_type` varchar(50) NULL COMMENT '房间属性',
    `room_standard` varchar(20) NULL COMMENT '房间标准',
    `remark` text NULL COMMENT '备注',
    `create_time` datetime DEFAULT (CURRENT_TIMESTAMP),
    `update_time` datetime DEFAULT (CURRENT_TIMESTAMP),
    `create_by` varchar(32) NULL DEFAULT '',
    `update_by` varchar(32) NULL DEFAULT '',
    `seq` int NULL DEFAULT '100',
    PRIMARY KEY  (`id` ),
    KEY `idx_building_num` (`building_num` ),
    KEY `idx_floor` (`floor` ),
    KEY `idex_room_num` (`room_num` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='宿舍楼栋信息';

CREATE TABLE IF NOT EXISTS `dorm`.`dorm_dept`(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `dept` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
    PRIMARY KEY  (`id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='宿舍部门';
00_ai_ci  COMMENT '房间申请记录表';

CREATE TABLE IF NOT EXISTS `dorm`.`staff_info`(
    `id` varchar(20) NOT NULL AUTO_INCREMENT,
    -- `room_info` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '房间信息',
    `building_num` varchar(20) NOT NULL DEFAULT '' COMMENT '楼栋',
    `floor` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '楼层',
    `room_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '房间号',
    `room_standard` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '房间标准（收费）',
    `staff_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '工号',
    `staff_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
    `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '性别',
    `contry` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '国籍',
    `company` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '公司',
    `dept` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '部门',
    `post` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '职务',
    `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '电话',
    `visa_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '签证类型',
    `passport_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '护照号',
    `bed_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '床位',
    `key_flag` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '钥匙',
    `checkin_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '入住时间',
    `deposit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '入住押金 ',
    `remark` text NULL COMMENT '备注',
    `create_time` datetime NULL,
    `update_time` datetime DEFAULT (CURRENT_TIMESTAMP),
    `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
    `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
    `del_flag` char(8) NULL DEFAULT '0',
    `person_attribute` varchar(255) NULL,
    `dept_id` bigint NULL,
    `node` bigint NULL COMMENT '统计节点 部门id',
    PRIMARY KEY  (`id` ),
    KEY `idx_building` (`building_num` ),
    KEY `idx_floor` (`floor` ),
    KEY `idx_room` (`room_num` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='员工信息';

CREATE TABLE IF NOT EXISTS `dorm`.`dorm_log`(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `create_time` datetime DEFAULT (CURRENT_TIMESTAMP) COMMENT '时间',
    `operater` varchar(50) NOT NULL DEFAULT '' COMMENT '操作人',
    `remark` text NOT NULL COMMENT '记录',
    `operate_type` varchar(255) NULL DEFAULT '',
    PRIMARY KEY  (`id` ),
    KEY `idx_operator` (`operater` ),
    KEY `idx_type` (`operate_type` ),
    KEY `idx_date` (`create_time` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci  COMMENT '操作记录';

CREATE TABLE IF NOT EXISTS `dorm`.`dorm_room_record`(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `staff_name` varchar(255) NULL,
    `staff_num` varchar(20) NULL,
    `dept` varchar(255) NULL,
    `create_time` datetime DEFAULT (CURRENT_TIMESTAMP),
    `building_num` varchar(255) NULL,
    `floor` varchar(255) NULL,
    `room_num` varchar(20) NULL,
    PRIMARY KEY  (`id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_09

CREATE TABLE IF NOT EXISTS `dorm`.`leave_info`(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `operator` varchar(50) NOT NULL DEFAULT '' COMMENT '登记人',
    `room_info` varchar(50) NULL DEFAULT '',
    `staff_name` varchar(50) NOT NULL DEFAULT '',
    `sex` varchar(50) NOT NULL DEFAULT '',
    `dept` varchar(100) NOT NULL DEFAULT '',
    `checkin_date` varchar(50) NOT NULL DEFAULT '',
    `checkout_date` varchar(50) NOT NULL DEFAULT '',
    `leave_date` varchar(50) NOT NULL DEFAULT '' COMMENT '出矿日期',
    `key_flag` varchar(50) NOT NULL DEFAULT '' COMMENT '钥匙',
    `card_flag` varchar(50) NOT NULL DEFAULT '' COMMENT '饭卡',
    `bedding_flag` varchar(50) NOT NULL DEFAULT '' COMMENT '床上用品',
    `pillow_flag` varchar(50) NOT NULL DEFAULT '' COMMENT '枕头',
    `basin` varchar(50) NOT NULL DEFAULT '' COMMENT '脸盆',
    `deposit` varchar(50) NOT NULL DEFAULT '' COMMENT '押金',
    `leave_reason` varchar(254) NULL DEFAULT '' COMMENT '退宿理由',
    `remark` text NULL,
    `create_time` datetime DEFAULT (CURRENT_TIMESTAMP),
    `update_time` datetime DEFAULT (CURRENT_TIMESTAMP),
    `create_by` varchar(32) NULL DEFAULT '',
    `update_by` varchar(32) NULL DEFAULT '',
    `company` varchar(255) NULL DEFAULT '',
    `passport_no` varchar(255) NULL DEFAULT '',
    PRIMARY KEY  (`id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='退宿信息';


USE dorm;
DELIMITER $$

-- 插入员工时，更新宿舍人数
CREATE TRIGGER after_insert_staff
AFTER INSERT ON dorm.staff_info
FOR EACH ROW
BEGIN
  UPDATE dorm.building_info
  SET capacity_num = capacity_num + 1
  WHERE room_num = NEW.room_num;
END$$

-- 删除员工时，更新宿舍人数
CREATE TRIGGER after_delete_staff
AFTER DELETE ON dorm.staff_info
FOR EACH ROW
BEGIN
  -- 减少对应宿舍人数
  UPDATE dorm.building_info
  SET capacity_num = capacity_num - 1
  WHERE room_num = OLD.room_num;
END$$


-- 更新员工宿舍时，更新人数（迁移宿舍）
CREATE TRIGGER after_update_staff
AFTER UPDATE ON dorm.staff_info
FOR EACH ROW
BEGIN
  -- 减少旧宿舍人数
  UPDATE dorm.building_info
  SET capacity_num = capacity_num - 1
  WHERE room_num = OLD.room_num;

  -- 增加新宿舍人数
  UPDATE dorm.building_info
  SET capacity_num = capacity_num + 1
  WHERE room_num = NEW.room_num;
END$$


DELIMITER ;