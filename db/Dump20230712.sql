-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: icommon
-- ------------------------------------------------------
-- Server version	5.6.47

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_user` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` varchar(12) DEFAULT '' COMMENT '租户',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2021-12-17 11:14:14','',NULL,'','0','蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2021-12-17 11:14:14','',NULL,'','0','初始化密码 123456'),(3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2021-12-17 11:14:14','',NULL,'','0','深色主题theme-dark，浅色主题theme-light'),(4,'账号自助-验证码开关','sys.account.captchaOnOff','true','Y','admin','2021-12-17 11:14:14','',NULL,'','0','是否开启验证码功能（true开启，false关闭）'),(5,'账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y','admin','2021-12-17 11:14:14','',NULL,'','0','是否开启注册用户功能（true开启，false关闭）');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS `sys_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_dict_data` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `create_user` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` varchar(12) DEFAULT '' COMMENT '租户',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_data`
--

LOCK TABLES `sys_dict_data` WRITE;
/*!40000 ALTER TABLE `sys_dict_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_dict_type` (
  `id` bigint(20) NOT NULL COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `create_user` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` varchar(12) DEFAULT '' COMMENT '租户',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_frame` char(1) DEFAULT '0' COMMENT '是否为外链（1是 0否）',
  `is_cache` char(1) DEFAULT '1' COMMENT '是否缓存（1缓存 0不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `is_visible` char(1) DEFAULT '1' COMMENT '菜单状态（1显示 0隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（1正常 0停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_user` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` varchar(12) DEFAULT '' COMMENT '租户',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,1,'system',NULL,'1','1','M','1','1','','system','admin','2021-12-17 11:14:12','',NULL,'','0','系统管理目录'),(2,'系统监控',0,2,'monitor',NULL,'1','1','M','1','1','','monitor','admin','2021-12-17 11:14:12','',NULL,'','0','系统监控目录'),(3,'系统工具',0,3,'tool',NULL,'1','1','M','1','1','','tool','admin','2021-12-17 11:14:12','',NULL,'','0','系统工具目录'),(100,'用户管理',1,1,'user','system/user/index','1','1','C','1','1','system:user:list','user','admin','2021-12-17 11:14:12','',NULL,'','0','用户管理菜单'),(101,'角色管理',1,2,'role','system/role/index','1','1','C','1','1','system:role:list','peoples','admin','2021-12-17 11:14:12','',NULL,'','0','角色管理菜单'),(102,'菜单管理',1,3,'menu','system/menu/index','1','1','C','1','1','system:menu:list','tree-table','admin','2021-12-17 11:14:12','',NULL,'','0','菜单管理菜单'),(103,'部门管理',1,4,'dept','system/dept/index','1','1','C','1','1','system:dept:list','tree','admin','2021-12-17 11:14:12','',NULL,'','0','部门管理菜单'),(104,'岗位管理',1,5,'post','system/post/index','1','1','C','1','1','system:post:list','post','admin','2021-12-17 11:14:12','',NULL,'','0','岗位管理菜单'),(105,'字典管理',1,6,'dict','system/dict/index','1','1','C','1','1','system:dict:list','dict','admin','2021-12-17 11:14:12','',NULL,'','0','字典管理菜单'),(106,'参数设置',1,7,'config','system/config/index','1','1','C','1','1','system:config:list','edit','admin','2021-12-17 11:14:12','',NULL,'','0','参数设置菜单'),(107,'通知公告',1,8,'notice','system/notice/index','1','1','C','1','1','system:notice:list','message','admin','2021-12-17 11:14:12','',NULL,'','0','通知公告菜单'),(108,'日志管理',1,9,'log','','1','1','M','1','1','','log','admin','2021-12-17 11:14:12','',NULL,'','0','日志管理菜单'),(109,'在线用户',2,1,'online','monitor/online/index','1','1','C','1','1','monitor:online:list','online','admin','2021-12-17 11:14:12','',NULL,'','0','在线用户菜单'),(110,'定时任务',2,2,'job','monitor/job/index','1','1','C','1','1','monitor:job:list','job','admin','2021-12-17 11:14:12','',NULL,'','0','定时任务菜单'),(111,'数据监控',2,3,'druid','monitor/druid/index','1','1','C','1','1','monitor:druid:list','druid','admin','2021-12-17 11:14:12','',NULL,'','0','数据监控菜单'),(112,'服务监控',2,4,'server','monitor/server/index','1','1','C','1','1','monitor:server:list','server','admin','2021-12-17 11:14:12','',NULL,'','0','服务监控菜单'),(113,'缓存监控',2,5,'cache','monitor/cache/index','1','1','C','1','1','monitor:cache:list','redis','admin','2021-12-17 11:14:12','',NULL,'','0','缓存监控菜单'),(114,'表单构建',3,1,'build','tool/build/index','1','1','C','1','1','tool:build:list','build','admin','2021-12-17 11:14:12','',NULL,'','0','表单构建菜单'),(115,'代码生成',3,2,'gen','tool/gen/index','1','1','C','1','1','tool:gen:list','code','admin','2021-12-17 11:14:12','',NULL,'','0','代码生成菜单'),(116,'系统接口',3,3,'swagger','tool/swagger/index','1','1','C','1','1','tool:swagger:list','swagger','admin','2021-12-17 11:14:12','',NULL,'','0','系统接口菜单'),(500,'操作日志',108,1,'operlog','monitor/operlog/index','1','1','C','1','1','monitor:operlog:list','form','admin','2021-12-17 11:14:12','',NULL,'','0','操作日志菜单'),(501,'登录日志',108,2,'logininfor','monitor/logininfor/index','1','1','C','1','1','monitor:logininfor:list','logininfor','admin','2021-12-17 11:14:12','',NULL,'','0','登录日志菜单'),(1001,'用户查询',100,1,'','','1','1','F','1','1','system:user:query','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1002,'用户新增',100,2,'','','1','1','F','1','1','system:user:add','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1003,'用户修改',100,3,'','','1','1','F','1','1','system:user:edit','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1004,'用户删除',100,4,'','','1','1','F','1','1','system:user:remove','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1005,'用户导出',100,5,'','','1','1','F','1','1','system:user:export','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1006,'用户导入',100,6,'','','1','1','F','1','1','system:user:import','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1007,'重置密码',100,7,'','','1','1','F','1','1','system:user:resetPwd','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1008,'角色查询',101,1,'','','1','1','F','1','1','system:role:query','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1009,'角色新增',101,2,'','','1','1','F','1','1','system:role:add','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1010,'角色修改',101,3,'','','1','1','F','1','1','system:role:edit','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1011,'角色删除',101,4,'','','1','1','F','1','1','system:role:remove','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1012,'角色导出',101,5,'','','1','1','F','1','1','system:role:export','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1013,'菜单查询',102,1,'','','1','1','F','1','1','system:menu:query','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1014,'菜单新增',102,2,'','','1','1','F','1','1','system:menu:add','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1015,'菜单修改',102,3,'','','1','1','F','1','1','system:menu:edit','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1016,'菜单删除',102,4,'','','1','1','F','1','1','system:menu:remove','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1017,'部门查询',103,1,'','','1','1','F','1','1','system:dept:query','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1018,'部门新增',103,2,'','','1','1','F','1','1','system:dept:add','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1019,'部门修改',103,3,'','','1','1','F','1','1','system:dept:edit','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1020,'部门删除',103,4,'','','1','1','F','1','1','system:dept:remove','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1021,'岗位查询',104,1,'','','1','1','F','1','1','system:post:query','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1022,'岗位新增',104,2,'','','1','1','F','1','1','system:post:add','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1023,'岗位修改',104,3,'','','1','1','F','1','1','system:post:edit','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1024,'岗位删除',104,4,'','','1','1','F','1','1','system:post:remove','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1025,'岗位导出',104,5,'','','1','1','F','1','1','system:post:export','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1026,'字典查询',105,1,'#','','1','1','F','1','1','system:dict:query','#','admin','2021-12-17 11:14:12','',NULL,'','0',''),(1027,'字典新增',105,2,'#','','1','1','F','1','1','system:dict:add','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1028,'字典修改',105,3,'#','','1','1','F','1','1','system:dict:edit','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1029,'字典删除',105,4,'#','','1','1','F','1','1','system:dict:remove','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1030,'字典导出',105,5,'#','','1','1','F','1','1','system:dict:export','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1031,'参数查询',106,1,'#','','1','1','F','1','1','system:config:query','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1032,'参数新增',106,2,'#','','1','1','F','1','1','system:config:add','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1033,'参数修改',106,3,'#','','1','1','F','1','1','system:config:edit','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1034,'参数删除',106,4,'#','','1','1','F','1','1','system:config:remove','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1035,'参数导出',106,5,'#','','1','1','F','1','1','system:config:export','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1036,'公告查询',107,1,'#','','1','1','F','1','1','system:notice:query','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1037,'公告新增',107,2,'#','','1','1','F','1','1','system:notice:add','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1038,'公告修改',107,3,'#','','1','1','F','1','1','system:notice:edit','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1039,'公告删除',107,4,'#','','1','1','F','1','1','system:notice:remove','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1040,'操作查询',500,1,'#','','1','1','F','1','1','monitor:operlog:query','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1041,'操作删除',500,2,'#','','1','1','F','1','1','monitor:operlog:remove','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1042,'日志导出',500,4,'#','','1','1','F','1','1','monitor:operlog:export','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1043,'登录查询',501,1,'#','','1','1','F','1','1','monitor:logininfor:query','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1044,'登录删除',501,2,'#','','1','1','F','1','1','monitor:logininfor:remove','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1045,'日志导出',501,3,'#','','1','1','F','1','1','monitor:logininfor:export','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1046,'在线查询',109,1,'#','','1','1','F','1','1','monitor:online:query','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1047,'批量强退',109,2,'#','','1','1','F','1','1','monitor:online:batchLogout','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1048,'单条强退',109,3,'#','','1','1','F','1','1','monitor:online:forceLogout','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1049,'任务查询',110,1,'#','','1','1','F','1','1','monitor:job:query','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1050,'任务新增',110,2,'#','','1','1','F','1','1','monitor:job:add','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1051,'任务修改',110,3,'#','','1','1','F','1','1','monitor:job:edit','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1052,'任务删除',110,4,'#','','1','1','F','1','1','monitor:job:remove','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1053,'状态修改',110,5,'#','','1','1','F','1','1','monitor:job:changeStatus','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1054,'任务导出',110,7,'#','','1','1','F','1','1','monitor:job:export','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1055,'生成查询',115,1,'#','','1','1','F','1','1','tool:gen:query','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1056,'生成修改',115,2,'#','','1','1','F','1','1','tool:gen:edit','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1057,'生成删除',115,3,'#','','1','1','F','1','1','tool:gen:remove','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1058,'导入代码',115,2,'#','','1','1','F','1','1','tool:gen:import','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1059,'预览代码',115,4,'#','','1','1','F','1','1','tool:gen:preview','#','admin','2021-12-17 11:14:13','',NULL,'','0',''),(1060,'生成代码',115,5,'#','','1','1','F','1','1','tool:gen:code','#','admin','2021-12-17 11:14:13','',NULL,'','0','');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '角色状态（1正常 0停用）',
  `create_user` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` varchar(12) DEFAULT '' COMMENT '租户',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','admin',1,'1','admin','2021-12-17 11:14:12','',NULL,'','0','超级管理员'),(2,'普通角色','common',2,'1','admin','2021-12-17 11:14:12','',NULL,'','0','普通角色');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (1,2,1),(2,2,2),(3,2,3),(4,2,4),(100,2,100),(101,2,101),(102,2,102),(103,2,103),(104,2,104),(105,2,105),(106,2,106),(107,2,107),(108,2,108),(109,2,109),(110,2,110),(111,2,111),(112,2,112),(113,2,113),(114,2,114),(115,2,115),(116,2,116),(500,2,500),(501,2,501),(1000,2,1000),(1001,2,1001),(1002,2,1002),(1003,2,1003),(1004,2,1004),(1005,2,1005),(1006,2,1006),(1007,2,1007),(1008,2,1008),(1009,2,1009),(1010,2,1010),(1011,2,1011),(1012,2,1012),(1013,2,1013),(1014,2,1014),(1015,2,1015),(1016,2,1016),(1017,2,1017),(1018,2,1018),(1019,2,1019),(1020,2,1020),(1021,2,1021),(1022,2,1022),(1023,2,1023),(1024,2,1024),(1025,2,1025),(1026,2,1026),(1027,2,1027),(1028,2,1028),(1029,2,1029),(1030,2,1030),(1031,2,1031),(1032,2,1032),(1033,2,1033),(1034,2,1034),(1035,2,1035),(1036,2,1036),(1037,2,1037),(1038,2,1038),(1039,2,1039),(1040,2,1040),(1041,2,1041),(1042,2,1042),(1043,2,1043),(1044,2,1044),(1045,2,1045),(1046,2,1046),(1047,2,1047),(1048,2,1048),(1049,2,1049),(1050,2,1050),(1051,2,1051),(1052,2,1052),(1053,2,1053),(1054,2,1054),(1055,2,1055),(1056,2,1056),(1057,2,1057),(1058,2,1058),(1059,2,1059),(1060,2,1060);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_code` varchar(30) NOT NULL COMMENT '用户账号',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phone_number` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（1正常 0停用）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_user` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` varchar(12) DEFAULT '' COMMENT '租户',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','若依','00','ry@163.com','15888888888','1','','$2a$10$keG41DOms6qogccAR0pRzODPjrpX0KWakQTCXV9E4HKWivGQAUenK','1','127.0.0.1','2021-12-17 11:14:12','admin','2021-12-17 11:14:12','',NULL,'','0','管理员'),(2,'ry','若依','00','ry@qq.com','15666666666','1','','$2a$10$keG41DOms6qogccAR0pRzODPjrpX0KWakQTCXV9E4HKWivGQAUenK','1','127.0.0.1','2021-12-17 11:14:12','admin','2021-12-17 11:14:12','',NULL,'','0','测试员');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1,1),(2,2,2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-12 23:38:42
--drop
DROP TABLE IF EXISTS `sys_config`;
DROP TABLE IF EXISTS `sys_menu`;
DROP TABLE IF EXISTS `sys_dict_data`;
DROP TABLE IF EXISTS `sys_dict_type`;
DROP TABLE IF EXISTS `sys_role`;
DROP TABLE IF EXISTS `sys_role_menu`;
DROP TABLE IF EXISTS `sys_user`;
DROP TABLE IF EXISTS `sys_user_role`;