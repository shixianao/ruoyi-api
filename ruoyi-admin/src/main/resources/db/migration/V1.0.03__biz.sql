-- 创建班级表
CREATE TABLE `biz_classes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '班级ID',
  `class_name` varchar(50) NOT NULL COMMENT '班级名称',
  `description` varchar(255) DEFAULT NULL COMMENT '班级描述',
  `head_teacher` varchar(50) DEFAULT NULL COMMENT '班主任',
  `student_count` int(11) DEFAULT '0' COMMENT '学生人数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级管理表';

-- 创建学生表
CREATE TABLE `biz_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '学生ID',
  `student_name` varchar(50) NOT NULL COMMENT '学生姓名',
  `gender` char(1) DEFAULT NULL COMMENT '性别',
  `class_id` bigint(20) DEFAULT NULL COMMENT '班级ID',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) DEFAULT NULL COMMENT '家庭地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `biz_student_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `biz_classes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生管理表';