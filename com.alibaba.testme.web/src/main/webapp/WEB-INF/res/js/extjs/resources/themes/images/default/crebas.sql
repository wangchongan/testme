/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/5/21 11:26:30                           */
/*==============================================================*/


drop table if exists testme.tm_system;

drop table if exists testme.tm_system_env;

drop table if exists testme.tm_system_env_detail;

drop table if exists testme.tm_system_require_prop;

drop table if exists testme.tm_testunit;

drop table if exists testme.tm_testunit_flow;

drop table if exists testme.tm_testunit_flow_case;

drop table if exists testme.tm_testunit_flow_case_detail;

drop table if exists testme.tm_testunit_flow_detail;

drop table if exists testme.tm_testunit_param;

drop table if exists testme.tm_testunit_param_ext;

drop table if exists testme.tm_user;

drop table if exists testme.tm_work_space;

/*==============================================================*/
/* Table: tm_system                                             */
/*==============================================================*/
create table tm_system
(
   id                   int(18) not null auto_increment comment 'id',
   gmt_create           datetime comment '创建时间',
   creator              varchar(128) comment '创建者',
   gmt_modified         datetime comment '修改时间',
   modifier             varchar(128) comment '修改者',
   name                 varchar(128) comment '系统名称',
   remark               varchar(4000) comment '备注',
   primary key (id)
);

alter table tm_system comment '系统定义表';

/*==============================================================*/
/* Table: tm_system_env                                         */
/*==============================================================*/
create table tm_system_env
(
   ID                   int(18) not null auto_increment comment 'ID',
   gmt_create           datetime not null comment '创建时间',
   creator              varchar(128) not null comment '创建者',
   gmt_modified         datetime not null comment '修改时间',
   modifier             varchar(128) not null comment '修改者',
   user_id              int(18) not null comment '用户ID',
   system_id            int(18) not null comment '系统ID',
   config_name          varchar(128) not null comment '配置名称',
   is_default           char(1) not null default 'Y' comment '是否默认使用',
   primary key (ID)
);

alter table tm_system_env comment '用户系统环境参数表';

/*==============================================================*/
/* Table: tm_system_env_detail                                  */
/*==============================================================*/
create table tm_system_env_detail
(
   id                   int(18) not null auto_increment comment 'id',
   gmt_create           datetime not null comment '创建时间',
   creator              varchar(128) not null comment '创建者',
   gmt_modified         datetime not null comment '修改时间',
   modifier             varchar(128) not null comment '修改者',
   system_id            int(18) not null comment '系统环境参数表ID',
   prop_key             varchar(128) not null comment '属性键',
   prop_value           varchar(128) not null comment '属性值',
   remark               varchar(128) not null comment '备注名称',
   config_type          varchar(128) not null comment '配置类型',
   primary key (id)
);

alter table tm_system_env_detail comment '用户系统环境参数详情表';

/*==============================================================*/
/* Table: tm_system_require_prop                                */
/*==============================================================*/
create table tm_system_require_prop
(
   ID                   int(18) not null auto_increment comment 'ID',
   gmt_create           datetime not null comment '创建时间',
   creator              varchar(128) not null comment '创建者',
   gmt_modified         datetime not null comment '修改时间',
   modifier             varchar(128) not null comment '修改者',
   system_id            int(18) not null comment '所属系统ID',
   prop_code            varchar(128) not null comment '参数英文名',
   prop_name            varchar(128) not null comment '参数中文名',
   default_value        varchar(128) not null comment '默认值',
   nullable             char(1) not null comment '是否可空',
   help                 varchar(128) comment '填写提示',
   primary key (ID)
);

alter table tm_system_require_prop comment '系统必要参数声明表';

/*==============================================================*/
/* Table: tm_testunit                                           */
/*==============================================================*/
create table tm_testunit
(
   id                   int(18) not null auto_increment comment 'id',
   gmt_create           datetime not null comment '创建时间2',
   creator              varchar(128) not null comment '创建者',
   gmt_modified         datetime not null comment '修改时间',
   modifier             varchar(128) not null comment '修改者',
   code                 varchar(128) not null comment '测试单元别名',
   name                 varchar(128) not null comment '测试单元中文名',
   work_space_id        int(18) not null comment '工作空间ID',
   class_qualified_name varchar(512) not null comment '类路径',
   version              varchar(128) not null comment 'bundle版本号',
   tag                  varchar(256) comment '标签',
   user_id              int(18) not null comment '用户ID',
   remark               varchar(4000) comment '备注说明',
   primary key (id)
);

alter table tm_testunit comment '测试单元定义表';

/*==============================================================*/
/* Table: tm_testunit_flow                                      */
/*==============================================================*/
create table tm_testunit_flow
(
   id                   int(18) not null auto_increment comment 'id',
   gmt_create           datetime not null comment '创建时间2',
   creator              varchar(128) not null comment '创建者',
   gmt_modified         datetime not null comment '修改时间2',
   modifier             varchar(128) not null comment '修改者',
   code                 varchar(128) not null comment '测试单元流程别名',
   name                 varchar(128) not null comment '测试单元流程中文名',
   system_id            varchar(128) not null comment '系统群组代码',
   tag                  varchar(256) comment '标签',
   user_id              int(18) not null comment '用户ID',
   pic_url              varchar(128) comment '图片',
   times                int(18) comment '调用次数',
   env_config_required  char(1) not null comment '是否需要系统配置参数',
   是否可用                 char(1) not null comment '是否可用',
   remark               varchar(4000) comment '备注说明',
   primary key (id)
);

alter table tm_testunit_flow comment '测试单元流程定义表';

/*==============================================================*/
/* Table: tm_testunit_flow_case                                 */
/*==============================================================*/
create table tm_testunit_flow_case
(
   id                   int(18) not null auto_increment comment 'id',
   gmt_create           datetime not null comment '创建时间',
   creator              varchar(128) not null comment '创建者',
   gmt_modified         datetime not null comment '修改时间',
   modifier             varchar(128) not null comment '修改者',
   testunit_flow_id     varchar(128) not null comment 'bundle流程ID',
   status               varchar(128) not null comment '任务执行状态',
   gmt_start            datetime comment '开始执行时间',
   gmt_end              datetime comment '任务结束时间',
   user_id              varchar(128) not null comment '用户ID',
   system_env_id        int(18) not null comment '环境参数配置ID',
   gmt_next_retry       datetime comment '下次重试时间',
   primary key (id)
);

alter table tm_testunit_flow_case comment '测试流程实例表';

/*==============================================================*/
/* Table: tm_testunit_flow_case_detail                          */
/*==============================================================*/
create table tm_testunit_flow_case_detail
(
   id                   int(18) not null auto_increment comment 'id',
   gmt_create           datetime not null comment '创建时间',
   creator              varchar(128) not null comment '创建者',
   gmt_modified         datetime not null comment '修改时间',
   modifier             varchar(128) not null comment '修改者',
   testunit_flow_case_id int(18) not null comment '任务实例ID',
   testunit_flow_detail_id int(18) not null comment '测试单元链路ID',
   gmt_start            datetime comment '执行开始时间',
   gmt_end              datetime comment '执行结束时间',
   gmt_last_run         datetime comment '最新执行时间',
   status               varchar(128) not null comment '执行状态',
   in_param             varchar(4000) comment '测试单元入参值',
   out_param            varchar(4000) comment '测试单元出参值',
   msg                  varchar(4000) comment '执行结果信息',
   primary key (id)
);

alter table tm_testunit_flow_case_detail comment '测试任务实例详情表';

/*==============================================================*/
/* Table: tm_testunit_flow_detail                               */
/*==============================================================*/
create table tm_testunit_flow_detail
(
   id                   int(18) not null auto_increment comment 'id',
   gmt_create           datetime not null comment '创建时间',
   creator              varchar(128) not null comment '创建者',
   gmt_modified         datetime not null comment '修改时间',
   modifier             varchar(128) not null comment '修改者',
   testunit_flow_id     int(18) not null comment '测试单元流程定义ID',
   testunit_id          int(18) not null comment '测试单元ID',
   pre_testunit_id      int(18) comment '上一个测试单元ID',
   next_testunit_id     int(18) comment '下一个测试单元 ID',
   primary key (id)
);

alter table tm_testunit_flow_detail comment '测试单元流程详情表';

/*==============================================================*/
/* Table: tm_testunit_param                                     */
/*==============================================================*/
create table tm_testunit_param
(
   id                   int(18) not null auto_increment comment 'id',
   gmt_create           datetime not null comment '创建时间',
   creator              varchar(128) not null comment '创建者',
   gmt_modified         datetime not null comment '修改时间',
   modifier             varchar(128) not null comment '修改者',
   label_name           varchar(128) not null comment '控件显示名',
   param_name           varchar(128) not null comment '参数名称',
   form_ctrl_type       varchar(128) not null comment '表单控件类型',
   testunit_id          varchar(128) not null comment '测试单元 ID',
   default_value        varchar(128) comment '缺省值',
   rank                 int(4) not null comment '位置序号',
   is_required          char(1) not null comment '是否必填',
   help                 varchar(128) comment '填写提示',
   primary key (id)
);

alter table tm_testunit_param comment '测试单元参数定义表';

/*==============================================================*/
/* Table: tm_testunit_param_ext                                 */
/*==============================================================*/
create table tm_testunit_param_ext
(
   id                   int(18) not null auto_increment comment 'id',
   gmt_create           datetime comment '创建时间',
   creator              varchar(128) comment '创建者',
   gmt_modified         datetime comment '修改时间',
   modifier             varchar(128) comment '修改者',
   value_name           varchar(128) comment '控件值显示名称',
   value                varchar(128) comment '控件值',
   testunit_param_id    int(18) comment '测试单元参数定义id',
   primary key (id)
);

alter table tm_testunit_param_ext comment '测试单元参数定义扩展表';

/*==============================================================*/
/* Table: tm_user                                               */
/*==============================================================*/
create table tm_user
(
   ID                   int(18) not null auto_increment comment 'ID',
   gmt_create           datetime not null comment '创建时间',
   creator              varchar(128) not null comment '创建者',
   gmt_modified         datetime not null comment '修改时间',
   modifier             varchar(128) not null comment '修改者',
   user_name            varchar(128) not null comment '用户名',
   password             varchar(128) not null comment '用户密码',
   wangwang             varchar(128) comment '用户旺旺',
   primary key (ID)
);

alter table tm_user comment '用户表';

/*==============================================================*/
/* Table: tm_work_space                                         */
/*==============================================================*/
create table tm_work_space
(
   ID                   int(18) not null auto_increment comment 'ID',
   gmt_create           datetime not null comment '创建时间',
   creator              varchar(128) not null comment '创建者',
   gmt_modified         datetime not null comment '修改时间',
   modifier             varchar(128) not null comment '修改者',
   name                 varchar(128) not null comment '工作空间名称',
   user_id              varchar(128) not null comment '所属用户ID',
   system_id            int(18) not null comment '所属系统',
   primary key (ID)
);

alter table tm_work_space comment '测试单元工作空间';

