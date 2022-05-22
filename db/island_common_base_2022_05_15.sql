


DROP TABLE IF EXISTS `oauth_client_details`;
create table oauth_client_details
(
  client_id               varchar(48)   not null
    primary key,
  resource_ids            varchar(256)  null,
  client_secret           varchar(256)  null,
  scope                   varchar(256)  null,
  authorized_grant_types  varchar(256)  null,
  web_server_redirect_uri varchar(256)  null,
  authorities             varchar(256)  null,
  access_token_validity   int           null,
  refresh_token_validity  int           null,
  additional_information  varchar(4096) null,
  autoapprove             varchar(256)  null,
  create_user    varchar(64)  default ''  null comment '创建者',
  create_time  datetime                 null comment '创建时间',
  update_user    varchar(64)  default ''  null comment '更新者',
  update_time  datetime                 null comment '更新时间',
  tenant_id    varchar(12)  default ''  null comment '租户',
  del_flag     char(1) default '0' null comment '删除标识',
  remark       varchar(500)             null comment '备注'

)comment '认证信息表';

INSERT INTO `oauth_client_details` VALUES ('frontend','frontend','$2a$10$nptOhMcjg.1hC2eVJrJMBO9cnqTS/bGIIjtcMMR7mGoVjtlIFk/Ni','all','password,refresh_token',NULL,'admin',1800,86400,NULL,NULL, 'admin', '2021-12-17 11:14:14', 'admin', '2021-12-17 11:14:14', '', '0', NULL);

DROP TABLE IF EXISTS `sys_config`;
create table sys_config
(
  config_id    int(5) auto_increment comment '参数主键'
    primary key,
  config_name  varchar(100) default ''  null comment '参数名称',
  config_key   varchar(100) default ''  null comment '参数键名',
  config_value varchar(500) default ''  null comment '参数键值',
  config_type  char         default 'N' null comment '系统内置（Y是 N否）',
  create_user    varchar(64)  default ''  null comment '创建者',
  create_time  datetime                 null comment '创建时间',
  update_user    varchar(64)  default ''  null comment '更新者',
  update_time  datetime                 null comment '更新时间',
  tenant_id    varchar(12)  default ''  null comment '租户',
    del_flag     char(1) default '0' null comment '删除标识',
  remark       varchar(500)             null comment '备注'
)
  comment '参数配置表';

  INSERT INTO `sys_config` VALUES
  (1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2021-12-17 11:14:14','',NULL, '', '0', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),
  (2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2021-12-17 11:14:14','',NULL, '', '0', '初始化密码 123456'),
  (3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2021-12-17 11:14:14','',NULL, '', '0', '深色主题theme-dark，浅色主题theme-light'),
  (4,'账号自助-验证码开关','sys.account.captchaOnOff','true','Y','admin','2021-12-17 11:14:14','',NULL, '', '0', '是否开启验证码功能（true开启，false关闭）'),
  (5,'账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y','admin','2021-12-17 11:14:14','',NULL, '', '0', '是否开启注册用户功能（true开启，false关闭）');
DROP TABLE IF EXISTS `sys_menu`;
create table sys_menu
(
  menu_id     bigint auto_increment comment '菜单ID'
    primary key,
  menu_name   varchar(50)              not null comment '菜单名称',
  parent_id   bigint       default 0   null comment '父菜单ID',
  order_num   int(4)       default 0   null comment '显示顺序',
  path        varchar(200) default ''  null comment '路由地址',
  component   varchar(255)             null comment '组件路径',
  is_frame    int(1)       default 1   null comment '是否为外链（0是 1否）',
  is_cache    int(1)       default 0   null comment '是否缓存（0缓存 1不缓存）',
  menu_type   char         default ''  null comment '菜单类型（M目录 C菜单 F按钮）',
  visible     char         default '0' null comment '菜单状态（0显示 1隐藏）',
  status      char         default '0' null comment '菜单状态（0正常 1停用）',
  perms       varchar(100)             null comment '权限标识',
  icon        varchar(100) default '#' null comment '菜单图标',
  create_user   varchar(64)  default ''  null comment '创建者',
  create_time datetime                 null comment '创建时间',
  update_user   varchar(64)  default ''  null comment '更新者',
  update_time datetime                 null comment '更新时间',
  tenant_id       varchar(12)  default ''  null comment '租户',
  del_flag     char(1) default '0' null comment '删除标识',
  remark      varchar(500) default ''  null comment '备注'
)
  comment '菜单权限表';

  INSERT INTO `sys_menu`
  (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_user, create_time, update_user, update_time, remark)
  VALUES
  (1,'系统管理',0,1,'system',NULL,1,0,'M','0','0','','system','admin','2021-12-17 11:14:12','',NULL,'系统管理目录'),
  (2,'系统监控',0,2,'monitor',NULL,1,0,'M','0','0','','monitor','admin','2021-12-17 11:14:12','',NULL,'系统监控目录'),
  (3,'系统工具',0,3,'tool',NULL,1,0,'M','0','0','','tool','admin','2021-12-17 11:14:12','',NULL,'系统工具目录'),
  (4,'若依官网',0,4,'http://ruoyi.vip',NULL,0,0,'M','0','0','','guide','admin','2021-12-17 11:14:12','',NULL,'若依官网地址'),
  (100,'用户管理',1,1,'user','system/user/index',1,0,'C','0','0','system:user:list','user','admin','2021-12-17 11:14:12','',NULL,'用户管理菜单'),
  (101,'角色管理',1,2,'role','system/role/index',1,0,'C','0','0','system:role:list','peoples','admin','2021-12-17 11:14:12','',NULL,'角色管理菜单'),
  (102,'菜单管理',1,3,'menu','system/menu/index',1,0,'C','0','0','system:menu:list','tree-table','admin','2021-12-17 11:14:12','',NULL,'菜单管理菜单'),
  (103,'部门管理',1,4,'dept','system/dept/index',1,0,'C','0','0','system:dept:list','tree','admin','2021-12-17 11:14:12','',NULL,'部门管理菜单'),
  (104,'岗位管理',1,5,'post','system/post/index',1,0,'C','0','0','system:post:list','post','admin','2021-12-17 11:14:12','',NULL,'岗位管理菜单'),
  (105,'字典管理',1,6,'dict','system/dict/index',1,0,'C','0','0','system:dict:list','dict','admin','2021-12-17 11:14:12','',NULL,'字典管理菜单'),
  (106,'参数设置',1,7,'config','system/config/index',1,0,'C','0','0','system:config:list','edit','admin','2021-12-17 11:14:12','',NULL,'参数设置菜单'),
  (107,'通知公告',1,8,'notice','system/notice/index',1,0,'C','0','0','system:notice:list','message','admin','2021-12-17 11:14:12','',NULL,'通知公告菜单'),
  (108,'日志管理',1,9,'log','',1,0,'M','0','0','','log','admin','2021-12-17 11:14:12','',NULL,'日志管理菜单'),
  (109,'在线用户',2,1,'online','monitor/online/index',1,0,'C','0','0','monitor:online:list','online','admin','2021-12-17 11:14:12','',NULL,'在线用户菜单'),
  (110,'定时任务',2,2,'job','monitor/job/index',1,0,'C','0','0','monitor:job:list','job','admin','2021-12-17 11:14:12','',NULL,'定时任务菜单'),
  (111,'数据监控',2,3,'druid','monitor/druid/index',1,0,'C','0','0','monitor:druid:list','druid','admin','2021-12-17 11:14:12','',NULL,'数据监控菜单'),
  (112,'服务监控',2,4,'server','monitor/server/index',1,0,'C','0','0','monitor:server:list','server','admin','2021-12-17 11:14:12','',NULL,'服务监控菜单'),
  (113,'缓存监控',2,5,'cache','monitor/cache/index',1,0,'C','0','0','monitor:cache:list','redis','admin','2021-12-17 11:14:12','',NULL,'缓存监控菜单'),
  (114,'表单构建',3,1,'build','tool/build/index',1,0,'C','0','0','tool:build:list','build','admin','2021-12-17 11:14:12','',NULL,'表单构建菜单'),
  (115,'代码生成',3,2,'gen','tool/gen/index',1,0,'C','0','0','tool:gen:list','code','admin','2021-12-17 11:14:12','',NULL,'代码生成菜单'),
  (116,'系统接口',3,3,'swagger','tool/swagger/index',1,0,'C','0','0','tool:swagger:list','swagger','admin','2021-12-17 11:14:12','',NULL,'系统接口菜单'),
  (500,'操作日志',108,1,'operlog','monitor/operlog/index',1,0,'C','0','0','monitor:operlog:list','form','admin','2021-12-17 11:14:12','',NULL,'操作日志菜单'),
  (501,'登录日志',108,2,'logininfor','monitor/logininfor/index',1,0,'C','0','0','monitor:logininfor:list','logininfor','admin','2021-12-17 11:14:12','',NULL,'登录日志菜单'),
  (1001,'用户查询',100,1,'','',1,0,'F','0','0','system:user:query','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1002,'用户新增',100,2,'','',1,0,'F','0','0','system:user:add','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1003,'用户修改',100,3,'','',1,0,'F','0','0','system:user:edit','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1004,'用户删除',100,4,'','',1,0,'F','0','0','system:user:remove','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1005,'用户导出',100,5,'','',1,0,'F','0','0','system:user:export','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1006,'用户导入',100,6,'','',1,0,'F','0','0','system:user:import','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1007,'重置密码',100,7,'','',1,0,'F','0','0','system:user:resetPwd','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1008,'角色查询',101,1,'','',1,0,'F','0','0','system:role:query','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1009,'角色新增',101,2,'','',1,0,'F','0','0','system:role:add','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1010,'角色修改',101,3,'','',1,0,'F','0','0','system:role:edit','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1011,'角色删除',101,4,'','',1,0,'F','0','0','system:role:remove','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1012,'角色导出',101,5,'','',1,0,'F','0','0','system:role:export','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1013,'菜单查询',102,1,'','',1,0,'F','0','0','system:menu:query','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1014,'菜单新增',102,2,'','',1,0,'F','0','0','system:menu:add','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1015,'菜单修改',102,3,'','',1,0,'F','0','0','system:menu:edit','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1016,'菜单删除',102,4,'','',1,0,'F','0','0','system:menu:remove','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1017,'部门查询',103,1,'','',1,0,'F','0','0','system:dept:query','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1018,'部门新增',103,2,'','',1,0,'F','0','0','system:dept:add','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1019,'部门修改',103,3,'','',1,0,'F','0','0','system:dept:edit','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1020,'部门删除',103,4,'','',1,0,'F','0','0','system:dept:remove','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1021,'岗位查询',104,1,'','',1,0,'F','0','0','system:post:query','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1022,'岗位新增',104,2,'','',1,0,'F','0','0','system:post:add','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1023,'岗位修改',104,3,'','',1,0,'F','0','0','system:post:edit','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1024,'岗位删除',104,4,'','',1,0,'F','0','0','system:post:remove','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1025,'岗位导出',104,5,'','',1,0,'F','0','0','system:post:export','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1026,'字典查询',105,1,'#','',1,0,'F','0','0','system:dict:query','#','admin','2021-12-17 11:14:12','',NULL,''),
  (1027,'字典新增',105,2,'#','',1,0,'F','0','0','system:dict:add','#','admin','2021-12-17 11:14:13','',NULL,''),
  (1028,'字典修改',105,3,'#','',1,0,'F','0','0','system:dict:edit','#','admin','2021-12-17 11:14:13','',NULL,''),
  (1029,'字典删除',105,4,'#','',1,0,'F','0','0','system:dict:remove','#','admin','2021-12-17 11:14:13','',NULL,''),
  (1030,'字典导出',105,5,'#','',1,0,'F','0','0','system:dict:export','#','admin','2021-12-17 11:14:13','',NULL,''),
  (1031,'参数查询',106,1,'#','',1,0,'F','0','0','system:config:query','#','admin','2021-12-17 11:14:13','',NULL,''),
  (1032,'参数新增',106,2,'#','',1,0,'F','0','0','system:config:add','#','admin','2021-12-17 11:14:13','',NULL,''),
  (1033,'参数修改',106,3,'#','',1,0,'F','0','0','system:config:edit','#','admin','2021-12-17 11:14:13','',NULL,''),
  (1034,'参数删除',106,4,'#','',1,0,'F','0','0','system:config:remove','#','admin','2021-12-17 11:14:13','',NULL,''),
  (1035,'参数导出',106,5,'#','',1,0,'F','0','0','system:config:export','#','admin','2021-12-17 11:14:13','',NULL,''),
  (1036,'公告查询',107,1,'#','',1,0,'F','0','0','system:notice:query','#','admin','2021-12-17 11:14:13','',NULL,''),
  (1037,'公告新增',107,2,'#','',1,0,'F','0','0','system:notice:add','#','admin','2021-12-17 11:14:13','',NULL,''),(1038,'公告修改',107,3,'#','',1,0,'F','0','0','system:notice:edit','#','admin','2021-12-17 11:14:13','',NULL,''),(1039,'公告删除',107,4,'#','',1,0,'F','0','0','system:notice:remove','#','admin','2021-12-17 11:14:13','',NULL,''),(1040,'操作查询',500,1,'#','',1,0,'F','0','0','monitor:operlog:query','#','admin','2021-12-17 11:14:13','',NULL,''),(1041,'操作删除',500,2,'#','',1,0,'F','0','0','monitor:operlog:remove','#','admin','2021-12-17 11:14:13','',NULL,''),(1042,'日志导出',500,4,'#','',1,0,'F','0','0','monitor:operlog:export','#','admin','2021-12-17 11:14:13','',NULL,''),(1043,'登录查询',501,1,'#','',1,0,'F','0','0','monitor:logininfor:query','#','admin','2021-12-17 11:14:13','',NULL,''),(1044,'登录删除',501,2,'#','',1,0,'F','0','0','monitor:logininfor:remove','#','admin','2021-12-17 11:14:13','',NULL,''),(1045,'日志导出',501,3,'#','',1,0,'F','0','0','monitor:logininfor:export','#','admin','2021-12-17 11:14:13','',NULL,''),(1046,'在线查询',109,1,'#','',1,0,'F','0','0','monitor:online:query','#','admin','2021-12-17 11:14:13','',NULL,''),(1047,'批量强退',109,2,'#','',1,0,'F','0','0','monitor:online:batchLogout','#','admin','2021-12-17 11:14:13','',NULL,''),(1048,'单条强退',109,3,'#','',1,0,'F','0','0','monitor:online:forceLogout','#','admin','2021-12-17 11:14:13','',NULL,''),(1049,'任务查询',110,1,'#','',1,0,'F','0','0','monitor:job:query','#','admin','2021-12-17 11:14:13','',NULL,''),(1050,'任务新增',110,2,'#','',1,0,'F','0','0','monitor:job:add','#','admin','2021-12-17 11:14:13','',NULL,''),(1051,'任务修改',110,3,'#','',1,0,'F','0','0','monitor:job:edit','#','admin','2021-12-17 11:14:13','',NULL,''),(1052,'任务删除',110,4,'#','',1,0,'F','0','0','monitor:job:remove','#','admin','2021-12-17 11:14:13','',NULL,''),(1053,'状态修改',110,5,'#','',1,0,'F','0','0','monitor:job:changeStatus','#','admin','2021-12-17 11:14:13','',NULL,''),(1054,'任务导出',110,7,'#','',1,0,'F','0','0','monitor:job:export','#','admin','2021-12-17 11:14:13','',NULL,''),(1055,'生成查询',115,1,'#','',1,0,'F','0','0','tool:gen:query','#','admin','2021-12-17 11:14:13','',NULL,''),(1056,'生成修改',115,2,'#','',1,0,'F','0','0','tool:gen:edit','#','admin','2021-12-17 11:14:13','',NULL,''),(1057,'生成删除',115,3,'#','',1,0,'F','0','0','tool:gen:remove','#','admin','2021-12-17 11:14:13','',NULL,''),(1058,'导入代码',115,2,'#','',1,0,'F','0','0','tool:gen:import','#','admin','2021-12-17 11:14:13','',NULL,''),(1059,'预览代码',115,4,'#','',1,0,'F','0','0','tool:gen:preview','#','admin','2021-12-17 11:14:13','',NULL,''),(1060,'生成代码',115,5,'#','',1,0,'F','0','0','tool:gen:code','#','admin','2021-12-17 11:14:13','',NULL,'');


DROP TABLE IF EXISTS `sys_dept`;
create table sys_dept
(
  dept_id     bigint auto_increment comment '部门id'
    primary key,
  parent_id   bigint      default 0   null comment '父部门id',
  ancestors   varchar(50) default ''  null comment '祖级列表',
  dept_name   varchar(30) default ''  null comment '部门名称',
  order_num   int(4)      default 0   null comment '显示顺序',
  leader      varchar(20)             null comment '负责人',
  phone       varchar(11)             null comment '联系电话',
  email       varchar(50)             null comment '邮箱',
  status      char        default '0' null comment '部门状态（0正常 1停用）',
  del_flag    char        default '0' null comment '删除标志',
  create_user   varchar(64) default ''  null comment '创建者',
  create_time datetime                null comment '创建时间',
  update_user   varchar(64) default ''  null comment '更新者',
  update_time datetime                null comment '更新时间',
  tenant_id    varchar(12)  default ''  null comment '租户'
)
  comment '部门表';

  DROP TABLE IF EXISTS `sys_dict_data`;
create table sys_dict_data
(
  dict_code   bigint auto_increment comment '字典编码'
    primary key,
  dict_sort   int(4)       default 0   null comment '字典排序',
  dict_label  varchar(100) default ''  null comment '字典标签',
  dict_value  varchar(100) default ''  null comment '字典键值',
  dict_type   varchar(100) default ''  null comment '字典类型',
  css_class   varchar(100)             null comment '样式属性（其他样式扩展）',
  list_class  varchar(100)             null comment '表格回显样式',
  is_default  char         default 'N' null comment '是否默认（Y是 N否）',
  status      char         default '0' null comment '状态（0正常 1停用）',
  create_user   varchar(64)  default ''  null comment '创建者',
  create_time datetime                 null comment '创建时间',
  update_user   varchar(64)  default ''  null comment '更新者',
  update_time datetime                 null comment '更新时间',
  tenant_id    varchar(12)  default ''  null comment '租户',
  del_flag     char(1) default '0' null comment '删除标识',
  remark      varchar(500)             null comment '备注'
)
  comment '字典数据表';

  DROP TABLE IF EXISTS `sys_dict_type`;
create table sys_dict_type
(
  dict_id     bigint auto_increment comment '字典主键'
    primary key,
  dict_name   varchar(100) default ''  null comment '字典名称',
  dict_type   varchar(100) default ''  null comment '字典类型',
  status      char         default '0' null comment '状态（0正常 1停用）',
  create_user   varchar(64)  default ''  null comment '创建者',
  create_time datetime                 null comment '创建时间',
  update_user   varchar(64)  default ''  null comment '更新者',
  update_time datetime                 null comment '更新时间',
  tenant_id    varchar(12)  default ''  null comment '租户',
  del_flag     char(1) default '0' null comment '删除标识',
  remark      varchar(500)             null comment '备注',
  constraint dict_type
    unique (dict_type)
)
  comment '字典类型表';

  DROP TABLE IF EXISTS `sys_post`;
create table sys_post
(
  post_id     bigint auto_increment comment '岗位ID'
    primary key,
  post_code   varchar(64)            not null comment '岗位编码',
  post_name   varchar(50)            not null comment '岗位名称',
  post_sort   int(4)                 not null comment '显示顺序',
  status      char                   not null comment '状态（0正常 1停用）',
  create_user   varchar(64) default '' null comment '创建者',
  create_time datetime               null comment '创建时间',
  update_user   varchar(64) default '' null comment '更新者',
  update_time datetime               null comment '更新时间',
  tenant_id       varchar(12)  default ''  null comment '租户',
  del_flag     char(1) default '0' null comment '删除标识',
  remark      varchar(500)           null comment '备注'
)
  comment '岗位信息表';

  DROP TABLE IF EXISTS `sys_role`;
create table sys_role
(
  role_id             bigint auto_increment comment '角色ID'
    primary key,
  role_name           varchar(30)             not null comment '角色名称',
  role_key            varchar(100)            not null comment '角色权限字符串',
  role_sort           int(4)                  not null comment '显示顺序',
  data_scope          char        default '1' null comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  menu_check_strictly tinyint(1)  default 1   null comment '菜单树选择项是否关联显示',
  dept_check_strictly tinyint(1)  default 1   null comment '部门树选择项是否关联显示',
  status              char                    not null comment '角色状态（0正常 1停用）',
  create_user           varchar(64) default ''  null comment '创建者',
  create_time         datetime                null comment '创建时间',
  update_user           varchar(64) default ''  null comment '更新者',
  update_time         datetime                null comment '更新时间',
  tenant_id       varchar(12)  default ''  null comment '租户',
  del_flag     char(1) default '0' null comment '删除标识',
  remark              varchar(500)            null comment '备注'
)
  comment '角色信息表';

  INSERT INTO `sys_role` VALUES
  (1,'超级管理员','admin',1,'1',1,1,'0','admin','2021-12-17 11:14:12','',NULL, '', '0', '超级管理员'),
  (2,'普通角色','common',2,'2',1,1,'0','admin','2021-12-17 11:14:12','',NULL, '', '0', '普通角色');


DROP TABLE IF EXISTS `sys_role_dept`;
create table sys_role_dept
(
  role_id bigint not null comment '角色ID',
  dept_id bigint not null comment '部门ID',
  tenant_id       varchar(12)  default ''  null comment '租户',
  primary key (role_id, dept_id)
)
  comment '角色和部门关联表';

  DROP TABLE IF EXISTS `sys_role_menu`;
create table sys_role_menu
(
  role_id bigint not null comment '角色ID',
  menu_id bigint not null comment '菜单ID',
          tenant_id       varchar(12)  default ''  null comment '租户',
  primary key (role_id, menu_id)
)
  comment '角色和菜单关联表';

INSERT INTO `sys_role_menu` (role_id, menu_id) VALUES (2,1),(2,2),(2,3),(2,4),(2,100),(2,101),(2,102),(2,103),(2,104),(2,105),(2,106),(2,107),(2,108),(2,109),(2,110),(2,111),(2,112),(2,113),(2,114),(2,115),(2,116),(2,500),(2,501),(2,1000),(2,1001),(2,1002),(2,1003),(2,1004),(2,1005),(2,1006),(2,1007),(2,1008),(2,1009),(2,1010),(2,1011),(2,1012),(2,1013),(2,1014),(2,1015),(2,1016),(2,1017),(2,1018),(2,1019),(2,1020),(2,1021),(2,1022),(2,1023),(2,1024),(2,1025),(2,1026),(2,1027),(2,1028),(2,1029),(2,1030),(2,1031),(2,1032),(2,1033),(2,1034),(2,1035),(2,1036),(2,1037),(2,1038),(2,1039),(2,1040),(2,1041),(2,1042),(2,1043),(2,1044),(2,1045),(2,1046),(2,1047),(2,1048),(2,1049),(2,1050),(2,1051),(2,1052),(2,1053),(2,1054),(2,1055),(2,1056),(2,1057),(2,1058),(2,1059),(2,1060);

DROP TABLE IF EXISTS `sys_user`;
create table sys_user
(
  user_id     bigint auto_increment comment '用户ID'
    primary key,
  dept_id     bigint                    null comment '部门ID',
  user_name   varchar(30)               not null comment '用户账号',
  nick_name   varchar(30)               not null comment '用户昵称',
  user_type   varchar(2)   default '00' null comment '用户类型（00系统用户）',
  email       varchar(50)  default ''   null comment '用户邮箱',
  phonenumber varchar(11)  default ''   null comment '手机号码',
  sex         char         default '0'  null comment '用户性别（0男 1女 2未知）',
  avatar      varchar(100) default ''   null comment '头像地址',
  password    varchar(100) default ''   null comment '密码',
  status      char         default '0'  null comment '帐号状态（0正常 1停用）',
  login_ip    varchar(128) default ''   null comment '最后登录IP',
  login_date  datetime                  null comment '最后登录时间',
  create_user   varchar(64)  default ''   null comment '创建者',
  create_time datetime                  null comment '创建时间',
  update_user   varchar(64)  default ''   null comment '更新者',
  update_time datetime                  null comment '更新时间',
  tenant_id       varchar(12)  default ''  null comment '租户',
  del_flag     char(1) default '0' null comment '删除标识',
  remark      varchar(500)              null comment '备注'
)
  comment '用户信息表';

INSERT INTO `sys_user` VALUES
(1,103,'admin','若依','00','ry@163.com','15888888888','1','','$2a$10$keG41DOms6qogccAR0pRzODPjrpX0KWakQTCXV9E4HKWivGQAUenK','0','127.0.0.1','2021-12-17 11:14:12','admin','2021-12-17 11:14:12','',NULL, '', '0', '管理员'),
(2,105,'ry','若依','00','ry@qq.com','15666666666','1','','$2a$10$keG41DOms6qogccAR0pRzODPjrpX0KWakQTCXV9E4HKWivGQAUenK','0','127.0.0.1','2021-12-17 11:14:12','admin','2021-12-17 11:14:12','',NULL, '', '0','测试员');


DROP TABLE IF EXISTS `sys_user_post`;
create table sys_user_post
(
  user_id bigint not null comment '用户ID',
  post_id bigint not null comment '岗位ID',
  tenant_id       varchar(12)  default ''  null comment '租户',
  primary key (user_id, post_id)
)
  comment '用户与岗位关联表';


DROP TABLE IF EXISTS `sys_user_role`;
create table sys_user_role
(
  user_id bigint not null comment '用户ID',
  role_id bigint not null comment '角色ID',
  tenant_id       varchar(12)  default ''  null comment '租户',
  primary key (user_id, role_id)
)
  comment '用户和角色关联表';

INSERT INTO `sys_user_role` (user_id, role_id) VALUES (1,1),(2,2);
