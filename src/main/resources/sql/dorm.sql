create table admin
(
    user_name  varchar(50)                           not null
        primary key,
    password   varchar(255)                          not null,
    role       varchar(20) default 'admin'           not null,
    status     tinyint     default 0                 null comment '0 正常 1 禁用',
    created_at datetime    default CURRENT_TIMESTAMP null
)
    comment '后台管理用户表' row_format = COMPACT;

-- 楼栋房间表
CREATE TABLE building_room (
    id INT PRIMARY KEY AUTO_INCREMENT,
    building_name VARCHAR(50) NOT NULL COMMENT '楼栋名称',
    room_number VARCHAR(20) NOT NULL COMMENT '房间号',
    max_capacity INT NOT NULL COMMENT '可住人数',
    current_capacity INT DEFAULT 0 COMMENT '已住人数',
    room_type TINYINT COMMENT '房间属性(0单人/1双人/2四人/3六人)',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY `uk_building_room` (building_name, room_number)
) COMMENT '楼栋房间信息表';

-- 员工住宿表
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    emp_no VARCHAR(50) NOT NULL COMMENT '工号',
    name VARCHAR(100) NOT NULL COMMENT '员工姓名',
    gender VARCHAR(10) COMMENT '性别',
    nationality VARCHAR(50) COMMENT '国籍',
    company VARCHAR(100) COMMENT '公司',
    department VARCHAR(100) COMMENT '部门',
    position VARCHAR(100) COMMENT '职务',
    phone VARCHAR(20) COMMENT '电话',
    visa_type VARCHAR(50) COMMENT '签证类型',
    passport_no VARCHAR(50) COMMENT '护照号',
    check_in_time DATETIME COMMENT '入住时间',
    emp_type VARCHAR(50) COMMENT '人员属性',
    building_name VARCHAR(50) COMMENT '楼栋',
    room_number VARCHAR(20) COMMENT '房间号',
    status TINYINT DEFAULT 0 COMMENT '状态(0:在住 1:已退宿)',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY `uk_emp_no` (emp_no),
    UNIQUE KEY `uk_passport` (passport_no)
) COMMENT '员工住宿信息表';

-- 退宿记录表
CREATE TABLE check_out_record (
    id INT PRIMARY KEY AUTO_INCREMENT,
    recorder VARCHAR(50) NOT NULL COMMENT '登记人',
    building_name VARCHAR(50) NOT NULL COMMENT '楼栋',
    room_number VARCHAR(20) NOT NULL COMMENT '房间号',
    emp_name VARCHAR(100) NOT NULL COMMENT '姓名',
    passport_no VARCHAR(50) NOT NULL COMMENT '护照号',
    check_out_time DATETIME NOT NULL COMMENT '退宿时间',
    check_in_time DATETIME COMMENT '入住时间',
    key_returned TINYINT DEFAULT 0 COMMENT '钥匙是否归还(0:未归还 1:已归还)',
    bedding_returned TINYINT DEFAULT 0 COMMENT '床上用品是否归还',
    pillow_returned TINYINT DEFAULT 0 COMMENT '枕头是否归还',
    basin_returned TINYINT DEFAULT 0 COMMENT '脸盆是否归还',
    reason TEXT COMMENT '退宿理由',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) COMMENT '退宿记录表';

-- 巡查记录表
CREATE TABLE patrol (
    id INT PRIMARY KEY AUTO_INCREMENT,
    patrol_person VARCHAR(50) NOT NULL COMMENT '巡查人',
    building_name VARCHAR(50) NOT NULL COMMENT '巡查楼栋',
    room_number VARCHAR(20)  COMMENT '房间号',
    patrol_time DATETIME NOT NULL COMMENT '巡查时间',
    abnormal_situation TEXT COMMENT '异常情况',
    processing_result TEXT COMMENT '处理结果',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) COMMENT '巡查记录表';

-- 房间维修记录表
CREATE TABLE room_maintenance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    building_name VARCHAR(50) NOT NULL COMMENT '楼栋名称',
    room_number VARCHAR(20) NOT NULL COMMENT '房间号',
    description TEXT NOT NULL COMMENT '维修描述',
    processor VARCHAR(50) NOT NULL COMMENT '维修人',
    status TINYINT DEFAULT 0 COMMENT '状态(0:待处理 1:处理中 2:已完成)'
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) COMMENT '房间维修记录表';

-- 报修申请表
CREATE TABLE maintenance_request (
    id INT PRIMARY KEY AUTO_INCREMENT,
    building_name VARCHAR(50) NOT NULL COMMENT '楼栋名称',
    room_number VARCHAR(20) NOT NULL COMMENT '房间号',
    emp_no VARCHAR(50) NOT NULL COMMENT '报修人工号',
    emp_name VARCHAR(100) NOT NULL COMMENT '报修人姓名',
    phone VARCHAR(20) NOT NULL COMMENT '联系电话',
    description TEXT NOT NULL COMMENT '问题描述',
    images VARCHAR(500) COMMENT '问题图片(多个图片用逗号分隔)',
    status TINYINT DEFAULT 0 COMMENT '状态(0:待处理 1:处理中 2:已完成 3:已取消)',
    submit_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX `idx_building_room` (building_name, room_number),
    INDEX `idx_emp_no` (emp_no)
) COMMENT '报修申请表';