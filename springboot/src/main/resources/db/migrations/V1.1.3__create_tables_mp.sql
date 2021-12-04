/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.32-root2
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 192.168.1.32:3308
 Source Schema         : highzap_jerp_basic_app_integrated

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 04/12/2021 14:14:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mp_cascade_condition
-- ----------------------------
DROP TABLE IF EXISTS mp_cascade_condition;
CREATE TABLE mp_cascade_condition (
  id varchar(36) COLLATE utf8mb4_bin NOT NULL,
  search_condition_id varchar(36) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '搜索条件主键',
  sort int(11) DEFAULT NULL COMMENT '排序',
  current_name varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '本级名称',
  current_condition varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '本级内容展示条件',
  next_connection_filed varchar(36) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '下级关联字段',
  is_recom tinyint(4) DEFAULT NULL COMMENT '是否开启推荐 1是、0否',
  current_recom_condition varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '本级推荐内容展示条件',
  cascade_condition_id varchar(36) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '本级展示的推荐内容',
  current_recom_display_name varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '推荐本级内容名称',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='级联条件';

-- ----------------------------
-- Table structure for mp_component_button
-- ----------------------------
DROP TABLE IF EXISTS mp_component_button;
CREATE TABLE mp_component_button (
  id varchar(36) NOT NULL,
  fk_id varchar(36) NOT NULL COMMENT '外键',
  permission_code varchar(100) NOT NULL COMMENT '功能权限编码',
  permission_name varchar(100) NOT NULL COMMENT '功能权限名称',
  button_name varchar(20) NOT NULL COMMENT '按钮名称',
  button_type tinyint(4) NOT NULL COMMENT '按钮类型（0：表格按钮，1：表格行按钮）',
  parent_id varchar(36) DEFAULT NULL COMMENT '按钮分组主键',
  button_action tinyint(4) NOT NULL COMMENT '	- 按钮行为(0:弹窗，1：页面跳转，2：接口请求，3：执行存储过程，4：到处，5：打印)视图行为（弹窗、页面跳转）、服务端行为（接口请求、执行存储过程、导出、打印），使用选择器分组展示；',
  selected_row tinyint(4) DEFAULT NULL COMMENT '是否选中行（0：否，1：是）',
  open_strategy tinyint(4) DEFAULT NULL COMMENT '打开方式（0:对话框，1：窗口，2：选项卡）如果按钮行为选择弹窗，则需要配置打开方式',
  confirm_text varchar(100) DEFAULT NULL COMMENT '文本框确认文字',
  preprocessing_url varchar(1000) DEFAULT NULL COMMENT '前置URL',
  processing_url varchar(1000) DEFAULT NULL COMMENT '操作URL',
  window_title varchar(50) DEFAULT NULL COMMENT '窗口标题',
  window_width varchar(10) DEFAULT '' COMMENT '窗口宽度',
  window_height varchar(10) DEFAULT '' COMMENT '窗口高度',
  sql_script varchar(1000) DEFAULT NULL COMMENT 'sql语句',
  button_tips varchar(100) DEFAULT NULL COMMENT '按钮tips文字',
  sort bigint(20) NOT NULL COMMENT '排序',
  hierarchy tinyint(4) NOT NULL COMMENT '层级',
  is_button_group tinyint(4) NOT NULL COMMENT '是否按钮组合（0：否，1：是）',
  display_condition varchar(500) DEFAULT NULL COMMENT '展示按钮的条件',
  main tinyint(3) unsigned DEFAULT NULL COMMENT '是否主按钮',
  preprocessing_url_method varchar(25) DEFAULT NULL,
  processing_url_method varchar(25) DEFAULT NULL,
  processing_url_body varchar(5000) DEFAULT NULL,
  preprocessing_url_body varchar(5000) DEFAULT NULL,
  rear_behavior tinyint(3) unsigned DEFAULT NULL COMMENT '后置行为：0-刷新当前行，1-整表刷新，2-删除当前行',
  skip_by_condition tinyint(4) DEFAULT NULL COMMENT '按条件打开',
  json_expression varchar(2000) DEFAULT NULL COMMENT 'json格式的表达式',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='按钮';

-- ----------------------------
-- Table structure for mp_component_search
-- ----------------------------
DROP TABLE IF EXISTS mp_component_search;
CREATE TABLE mp_component_search (
  id varchar(36) NOT NULL,
  fk_id varchar(36) DEFAULT NULL COMMENT '外键',
  expanded tinyint(4) NOT NULL DEFAULT '0' COMMENT '高级搜索条件是否展开（默认不展开）（0：否，1：是）',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='搜索组件';

-- ----------------------------
-- Table structure for mp_component_table
-- ----------------------------
DROP TABLE IF EXISTS mp_component_table;
CREATE TABLE mp_component_table (
  id varchar(36) NOT NULL,
  fk_id varchar(36) NOT NULL COMMENT '外键（视图）',
  table_title varchar(50) NOT NULL COMMENT '表格名称',
  is_pagination tinyint(1) NOT NULL COMMENT '是否分页（0：否，1：是），树形表格不存在分页设置',
  table_type tinyint(4) NOT NULL COMMENT '表格类型（0：表格，1：树形表格）',
  is_multi_choose tinyint(1) DEFAULT NULL COMMENT '是否复选（0：否，1：是）树表格不存在此设置',
  level_label varchar(50) DEFAULT NULL COMMENT '层级标识字段',
  parent_label varchar(50) DEFAULT NULL COMMENT '父级标识字段',
  child_label varchar(50) DEFAULT NULL COMMENT '子级标识字段',
  end_label varchar(50) DEFAULT NULL COMMENT '末级标识字段',
  is_open_layer tinyint(1) DEFAULT NULL COMMENT '是否开启分层（0：否，1：是）树表格默认开启',
  title_align_strategy tinyint(4) NOT NULL DEFAULT '0' COMMENT '表头对齐策略（0：自动，1：左对齐，2：居中，3：右对齐）',
  is_open_search_box tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用搜索框（0：否，1：是）',
  is_open_title_filter tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用表头筛选（0：否，1：是）',
  sql_script varchar(5000) NOT NULL COMMENT 'SQL语句(基础搜索获取表格数据)',
  advanced_search_sql_script varchar(1000) DEFAULT NULL COMMENT '高级搜索条件所使用的sql脚本',
  order_column_script varchar(300) DEFAULT NULL COMMENT '排序字段',
  sum_column_script varchar(100) DEFAULT NULL COMMENT '合计字段',
  level_display_label varchar(50) DEFAULT NULL COMMENT '层级展示字段(用于在生成树形表格时作为展开/收折的字段)',
  explain_text varchar(2000) DEFAULT NULL COMMENT '说明文本',
  expand_level tinyint(3) unsigned DEFAULT NULL COMMENT '指定层级数',
  expand_strategy tinyint(3) unsigned DEFAULT NULL COMMENT '默认展开：0-默认，1-全部，2-指定层级',
  option_source tinyint(4) DEFAULT NULL COMMENT '数据来源（0-SQL 2-接口请求）',
  url varchar(1000) DEFAULT NULL COMMENT '接口请求URL',
  url_request_body varchar(5000) DEFAULT NULL COMMENT '接口请求体',
  url_request_method varchar(25) DEFAULT NULL COMMENT '接口请求方式',
  is_lazy_load tinyint(3) unsigned DEFAULT NULL COMMENT '是否启用懒加载，1是 0否',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='树列表/列表';

-- ----------------------------
-- Table structure for mp_component_table_column
-- ----------------------------
DROP TABLE IF EXISTS mp_component_table_column;
CREATE TABLE mp_component_table_column (
  id varchar(36) NOT NULL,
  component_table_id varchar(36) NOT NULL COMMENT '表格组件id',
  filed_name varchar(100) NOT NULL COMMENT '字段名称',
  column_name varchar(50) NOT NULL COMMENT '列名称',
  filter_group_id varchar(36) DEFAULT NULL COMMENT '表头筛选分组id',
  align_strategy tinyint(4) NOT NULL COMMENT '对齐方式(1:左对齐，2：居中，3：右对齐)',
  display_type tinyint(4) NOT NULL COMMENT '显示类型（0：文本，1：数字。2：日期，3：时间）用于数据格式化展示',
  number_precision tinyint(4) DEFAULT NULL COMMENT '数字精度',
  column_width int(11) DEFAULT NULL COMMENT '列宽(px)',
  font_color varchar(10) DEFAULT NULL COMMENT '字体颜色',
  composite_header_id varchar(36) DEFAULT NULL COMMENT '所属复合表头分组',
  sort bigint(20) NOT NULL COMMENT '排序',
  hierarchy tinyint(4) DEFAULT NULL COMMENT '层级',
  is_font_bold tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否字体加粗(0:否，1：是)',
  is_freeze_column tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否冻结列（0：否，1：是）',
  is_add_to_search_box tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否加入搜索框查询（0：否，1：是）',
  search_strategy tinyint(4) NOT NULL COMMENT '查询策略(0:模糊匹配，1：精确匹配)',
  is_allow_order tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否允许表头排序(0:否，1：是)',
  is_hidden tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏（0：否，1：是）',
  is_open_hyperlink tinyint(1) DEFAULT '0' COMMENT '是否启用超链接（0：否，1：是）',
  open_strategy tinyint(4) DEFAULT NULL COMMENT '打开方式（0：对话框，1：窗口，2：选项卡）',
  page_url varchar(1000) DEFAULT NULL COMMENT '页面地址',
  page_title varchar(50) DEFAULT NULL COMMENT '页面标题',
  page_width varchar(10) DEFAULT '' COMMENT '页面宽度',
  page_height varchar(10) DEFAULT '' COMMENT '页面高度',
  column_type tinyint(4) DEFAULT NULL COMMENT '表格列类型（0：字段列，1：复合表头）用于前端判断当前选中行数据是否是复合表头，只有复合表头能增加子级符合表头',
  parent_id varchar(36) DEFAULT NULL COMMENT '父级id',
  hide_condition varchar(500) DEFAULT '' COMMENT '隐藏条件',
  skip_by_condition tinyint(4) DEFAULT NULL COMMENT '按条件打开',
  json_expression varchar(2000) DEFAULT NULL COMMENT 'json格式的表达式',
  column_remark varchar(500) DEFAULT NULL COMMENT '列说明',
  is_font_color_set_by_condition tinyint(4) DEFAULT NULL COMMENT '数据源字体颜色按条件打开',
  font_color_json_expression varchar(2000) DEFAULT NULL COMMENT '数据源字体颜色json格式的表达式',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='列表列';

-- ----------------------------
-- Table structure for mp_component_tree
-- ----------------------------
DROP TABLE IF EXISTS mp_component_tree;
CREATE TABLE mp_component_tree (
  id varchar(36) NOT NULL,
  fk_id varchar(36) NOT NULL COMMENT '外键关联（组件可被视图、布局等使用）',
  tree_name varchar(50) NOT NULL COMMENT '树形组件名称',
  code varchar(100) NOT NULL COMMENT '编码',
  parent_label varchar(50) NOT NULL COMMENT '父级标识字段',
  child_label varchar(50) NOT NULL COMMENT '子级标识字段',
  end_label varchar(50) NOT NULL COMMENT '末级标识字段',
  choose_type tinyint(4) NOT NULL COMMENT '选择类型（0：单选，1：单选末级）',
  is_open_layer tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否开启快速分层(0:否，1：是)',
  sql_script varchar(5000) DEFAULT NULL COMMENT 'SQL语句(基础搜索获取表格数据)',
  order_column_script varchar(300) DEFAULT NULL COMMENT '排序字段脚本',
  level_label varchar(50) DEFAULT NULL COMMENT '层级标识字段',
  label_filed varchar(25) DEFAULT NULL COMMENT '显示字段',
  value_filed varchar(25) DEFAULT NULL COMMENT '实际字段',
  expand_strategy tinyint(4) DEFAULT NULL COMMENT '展开策略',
  expand_level int(11) DEFAULT NULL COMMENT '指定展开层级数',
  option_source tinyint(3) unsigned DEFAULT NULL COMMENT '选项来源，0 SQL，2 api',
  url varchar(1000) DEFAULT NULL COMMENT '接口请求URL',
  url_request_body varchar(5000) DEFAULT NULL COMMENT '接口请求体',
  url_request_method varchar(25) DEFAULT NULL COMMENT '接口请求方式',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='树';

-- ----------------------------
-- Table structure for mp_date_range_config
-- ----------------------------
DROP TABLE IF EXISTS mp_date_range_config;
CREATE TABLE mp_date_range_config (
  id varchar(36) NOT NULL,
  search_condition_id varchar(36) DEFAULT NULL COMMENT '搜索条件主键\n搜索条件主键\n',
  text varchar(30) DEFAULT NULL COMMENT '选项文本\n',
  config_method tinyint(3) unsigned DEFAULT NULL COMMENT '配置方式（0：自定义，1：公式）\n',
  start_date datetime DEFAULT NULL COMMENT '起始日期\n',
  end_date datetime DEFAULT NULL COMMENT '截止日期\n',
  formula_setting varchar(200) DEFAULT NULL COMMENT '公式设置\n',
  is_default tinyint(3) unsigned DEFAULT NULL COMMENT '是否默认\n',
  sort int(11) DEFAULT NULL COMMENT '序号，用于排序\n',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mp_db_table
-- ----------------------------
DROP TABLE IF EXISTS mp_db_table;
CREATE TABLE mp_db_table (
  id varchar(36) NOT NULL,
  table_name varchar(80) DEFAULT NULL COMMENT '数据库表名称',
  primary_key_column varchar(50) DEFAULT NULL COMMENT '主键字段标识',
  remark varchar(200) DEFAULT NULL COMMENT '表说明文本',
  foreign_key_column varchar(50) DEFAULT NULL COMMENT '外键字段',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户开发平台，表单扩展字段，数据库表注册';

-- ----------------------------
-- Table structure for mp_extend_form
-- ----------------------------
DROP TABLE IF EXISTS mp_extend_form;
CREATE TABLE mp_extend_form (
  id varchar(36) NOT NULL,
  extend_form_name varchar(50) DEFAULT NULL COMMENT '扩展表单名称',
  extend_form_code varchar(50) DEFAULT NULL COMMENT '扩展表单编码',
  creator varchar(50) DEFAULT NULL,
  creator_id varchar(36) DEFAULT NULL,
  created datetime DEFAULT NULL,
  modifier varchar(50) DEFAULT NULL,
  modifier_id varchar(36) DEFAULT NULL,
  modified datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='扩展表单表';

-- ----------------------------
-- Table structure for mp_extend_form_field_register
-- ----------------------------
DROP TABLE IF EXISTS mp_extend_form_field_register;
CREATE TABLE mp_extend_form_field_register (
  id varchar(36) COLLATE utf8_bin NOT NULL,
  form_widget_id varchar(36) COLLATE utf8_bin DEFAULT NULL COMMENT '表单控件id',
  db_table_id varchar(36) COLLATE utf8_bin DEFAULT NULL,
  return_value_name varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '返回值名称',
  default_value varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '默认值（支持表达式）',
  bind_with_widget tinyint(4) DEFAULT NULL COMMENT '是否与控件绑定，即这个字段是否通过v-model与控件关联',
  column_name varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '表格列名称',
  validate_unique tinyint(4) DEFAULT NULL COMMENT '是否验证唯一',
  validate_required tinyint(4) DEFAULT NULL COMMENT '是否必需',
  validate_required_by_condition varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '按条件校验必填，支持关键字，与validate_required 二选一',
  extend_form_id varchar(36) COLLATE utf8_bin DEFAULT NULL COMMENT '扩展表单ID',
  form_group_id varchar(36) COLLATE utf8_bin DEFAULT NULL COMMENT '分组名称',
  validate_required_rule_sql varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '唯一性校验规则SQL',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='扩展表单字段注册';

-- ----------------------------
-- Table structure for mp_filter_group
-- ----------------------------
DROP TABLE IF EXISTS mp_filter_group;
CREATE TABLE mp_filter_group (
  id varchar(36) NOT NULL,
  component_table_id varchar(36) NOT NULL COMMENT '表格id',
  group_name varchar(50) NOT NULL COMMENT '分组名称',
  sort bigint(20) NOT NULL COMMENT '排序',
  remark varchar(200) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='表头筛选分组';

-- ----------------------------
-- Table structure for mp_fixed_val_config
-- ----------------------------
DROP TABLE IF EXISTS mp_fixed_val_config;
CREATE TABLE mp_fixed_val_config (
  id varchar(36) NOT NULL,
  search_condition_id varchar(36) DEFAULT NULL COMMENT '搜索条件主键\n',
  text varchar(100) DEFAULT NULL COMMENT '显示名称',
  value varchar(100) DEFAULT NULL COMMENT '实际值',
  is_default tinyint(3) unsigned DEFAULT NULL COMMENT '是否默认',
  sort int(11) DEFAULT NULL COMMENT '序号，用于排序',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mp_form_group
-- ----------------------------
DROP TABLE IF EXISTS mp_form_group;
CREATE TABLE mp_form_group (
  id varchar(36) NOT NULL,
  group_name varchar(50) DEFAULT NULL COMMENT '分组名称',
  group_code varchar(50) DEFAULT NULL COMMENT '分组编码',
  show_group_name tinyint(4) DEFAULT NULL COMMENT '是否显示分组名称（0：否，1：是）',
  widget_number int(11) DEFAULT NULL COMMENT '每行展示控件数量',
  sort bigint(20) DEFAULT NULL COMMENT '排序字段',
  remark varchar(200) DEFAULT NULL COMMENT '分组说明',
  extend_form_id varchar(36) DEFAULT NULL COMMENT '所属扩展表单',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='表单扩展字段，表单分组';

-- ----------------------------
-- Table structure for mp_form_table_relation
-- ----------------------------
DROP TABLE IF EXISTS mp_form_table_relation;
CREATE TABLE mp_form_table_relation (
  id varchar(36) NOT NULL,
  fk_id varchar(36) DEFAULT NULL COMMENT '扩展表单id、分组id，数据库表的关联可以在表单上，也可在分组上',
  db_table_id varchar(36) DEFAULT NULL COMMENT '数据库表id',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='扩展表单与数据库表关联表（此表也可以与分组进行关联，不同分组中的字段来自不同的表）';

-- ----------------------------
-- Table structure for mp_form_widget
-- ----------------------------
DROP TABLE IF EXISTS mp_form_widget;
CREATE TABLE mp_form_widget (
  id varchar(36) NOT NULL,
  widget_show_name varchar(50) DEFAULT NULL COMMENT '控件展示名称',
  whole_line_display tinyint(4) DEFAULT NULL COMMENT '是否整行显示（0:是，1：否）',
  insert_line_break tinyint(4) DEFAULT NULL COMMENT '是否插入换行',
  explanatory_text varchar(100) DEFAULT NULL COMMENT '说明文本',
  form_group_id varchar(36) DEFAULT NULL COMMENT '所属分组id',
  widget_type int(11) DEFAULT NULL COMMENT '控件类型:\n	0.文本框\n	1.日期框\n	2.日期+时间\n	3.选择器\n	4.树形选择器\n	5.数字框\n	6.复选框\n	7.单选框\n	8.附件上传\n	9.自动编码',
  choose_type int(11) DEFAULT NULL COMMENT '选择类型（0：单选；1：复选；2：单选末级）',
  placeholder_text varchar(50) DEFAULT NULL COMMENT '控件水印',
  child_label varchar(30) DEFAULT NULL COMMENT '子级标识',
  parent_label varchar(30) DEFAULT NULL COMMENT '父级标识',
  end_label varchar(30) DEFAULT NULL COMMENT '末级标识',
  text_label varchar(30) DEFAULT NULL COMMENT '显示值字段',
  value_label varchar(30) DEFAULT NULL COMMENT '实际值字段',
  number_unit varchar(10) DEFAULT NULL COMMENT '数值单位',
  number_precision int(11) DEFAULT NULL COMMENT '数字精度',
  widget_readonly tinyint(4) DEFAULT NULL COMMENT '控件是否只读',
  reference_addr varchar(500) DEFAULT NULL COMMENT '参照页地址',
  datasource_query_sql varchar(2000) DEFAULT NULL COMMENT '数据源查询sql',
  option_source int(11) DEFAULT NULL COMMENT '选项来源（0：sql, 1:固定值,2:接口请求）',
  url varchar(1000) DEFAULT NULL COMMENT '请求地址',
  url_request_body varchar(5000) DEFAULT NULL COMMENT '请求体',
  url_request_method varchar(25) DEFAULT NULL COMMENT 'http请求方法',
  show_by_condition varchar(200) DEFAULT NULL COMMENT '按条件显示隐藏',
  value_length int(11) DEFAULT NULL COMMENT '字段值长度',
  sort bigint(20) DEFAULT NULL COMMENT '排序字段',
  number_max decimal(13,4) DEFAULT NULL,
  number_min decimal(13,4) DEFAULT NULL COMMENT '数字最小值，大于最小值',
  auto_code_params varchar(200) DEFAULT NULL COMMENT '生成自动编码需要的参数（companyId=[company_id],deptId=[dept_id]）',
  auto_code_rule varchar(50) DEFAULT NULL COMMENT '编码规则Code',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='扩展表单控件';

-- ----------------------------
-- Table structure for mp_function_module
-- ----------------------------
DROP TABLE IF EXISTS mp_function_module;
CREATE TABLE mp_function_module (
  id varchar(36) NOT NULL COMMENT '主键（与功能定义表中的主键值保持一致）',
  function_name varchar(100) NOT NULL COMMENT '模块名称',
  parent_id varchar(36) DEFAULT NULL COMMENT '父级模块id(对应功能定义的group_id)',
  order_code int(11) NOT NULL COMMENT '排序字段',
  disabled tinyint(4) NOT NULL COMMENT '是否禁用(0:否，1：是)',
  application_id varchar(50) NOT NULL COMMENT '系统id',
  remark varchar(200) DEFAULT NULL COMMENT '说明',
  hierarchy tinyint(4) NOT NULL COMMENT '层级(对应功能定义的level)',
  type tinyint(4) NOT NULL COMMENT '类型（1：分组，2：模块）',
  mode tinyint(4) DEFAULT NULL COMMENT '功能模式（1：定制开发，2：用户开发平台）',
  is_enable_search_condition tinyint(1) DEFAULT NULL COMMENT '是否启用模块级搜索条件（1：启用，0：禁用）',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='功能模块表';

-- ----------------------------
-- Table structure for mp_hierarchy_icon
-- ----------------------------
DROP TABLE IF EXISTS mp_hierarchy_icon;
CREATE TABLE mp_hierarchy_icon (
  id varchar(36) NOT NULL,
  fk_id varchar(36) NOT NULL COMMENT '外键id(树形组件或者树表格组件)',
  hierarchy tinyint(4) NOT NULL COMMENT '层级',
  icon_code varchar(50) NOT NULL COMMENT '图标名称',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='层级图标';

-- ----------------------------
-- Table structure for mp_indicator_item
-- ----------------------------
DROP TABLE IF EXISTS mp_indicator_item;
CREATE TABLE mp_indicator_item (
  id varchar(36) NOT NULL COMMENT '主键',
  fk_id varchar(36) NOT NULL COMMENT '外键id',
  item_name varchar(100) NOT NULL COMMENT '指标项',
  item_bold tinyint(3) unsigned NOT NULL COMMENT '指标项_是否加粗',
  item_font_size tinyint(3) unsigned NOT NULL COMMENT '指标项_字体大小',
  item_font_color varchar(7) DEFAULT NULL COMMENT '指标项_字体颜色',
  item_remark varchar(255) DEFAULT NULL COMMENT '指标项_说明',
  item_value_sql varchar(5000) NOT NULL COMMENT '指标值_sql脚本',
  item_value_bold tinyint(3) unsigned NOT NULL COMMENT '指标值_是否加粗',
  item_value_font_size tinyint(3) unsigned NOT NULL COMMENT '指标值_字体大小',
  item_value_font_color varchar(7) DEFAULT NULL COMMENT '指标值_字体颜色',
  icon varchar(100) DEFAULT NULL COMMENT '图标',
  icon_color varchar(30) DEFAULT NULL COMMENT '图标颜色',
  sort bigint(20) unsigned NOT NULL COMMENT '排序',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mp_layout_template
-- ----------------------------
DROP TABLE IF EXISTS mp_layout_template;
CREATE TABLE mp_layout_template (
  id varchar(36) NOT NULL,
  template_name varchar(50) NOT NULL COMMENT '模板名称',
  sample_picture_url varchar(200) DEFAULT NULL COMMENT '示例图片地址',
  remark varchar(200) DEFAULT NULL COMMENT '说明',
  template_code varchar(50) NOT NULL COMMENT '模板编码（唯一的）',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='布局模板';

-- ----------------------------
-- Table structure for mp_numerical_val_config
-- ----------------------------
DROP TABLE IF EXISTS mp_numerical_val_config;
CREATE TABLE mp_numerical_val_config (
  id varchar(36) NOT NULL,
  search_condition_id varchar(36) DEFAULT NULL COMMENT '搜索条件主键\n',
  start_val decimal(16,6) DEFAULT NULL COMMENT '起始值（六位小数）\n',
  end_val decimal(16,6) DEFAULT NULL COMMENT '截止值（六位小数）\n',
  text varchar(30) DEFAULT NULL COMMENT '选项文本\n',
  is_default tinyint(3) unsigned DEFAULT NULL COMMENT '是否默认 0否 1是',
  sort int(11) DEFAULT NULL COMMENT '序号，用于排序\n',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mp_search_condition
-- ----------------------------
DROP TABLE IF EXISTS mp_search_condition;
CREATE TABLE mp_search_condition (
  id varchar(36) NOT NULL,
  show_name varchar(30) DEFAULT NULL COMMENT '显示名称',
  code varchar(30) DEFAULT NULL COMMENT '编码',
  component_type tinyint(3) unsigned DEFAULT NULL COMMENT '组件类型（选择器：0、树形选择器：1、文本框：2、日期范围：3、单选框：4、复选框：5、数值框：6、数值范围：7）',
  choose_type tinyint(3) unsigned DEFAULT NULL COMMENT '选择类型(单选：0，复选：1，单选末级：2)\n',
  child_label varchar(30) DEFAULT NULL COMMENT '子级标识',
  parent_label varchar(30) DEFAULT NULL COMMENT '父级标识',
  end_label varchar(30) DEFAULT NULL COMMENT '末级标识',
  is_allow_search tinyint(3) unsigned DEFAULT '0' COMMENT '是否开启搜索',
  option_source tinyint(3) unsigned DEFAULT NULL COMMENT '选项来源(SQL:0,固定值：1)\n',
  query_sql varchar(5000) DEFAULT NULL COMMENT 'SQL语句(基础搜索获取表格数据)',
  grade tinyint(3) unsigned DEFAULT NULL COMMENT '级别（系统级：0；用户级：1）\n',
  unit varchar(20) DEFAULT NULL COMMENT '单位',
  placeholder varchar(30) DEFAULT NULL COMMENT '默认文本（占位符）\n',
  remark varchar(200) DEFAULT NULL COMMENT '说明\n',
  create_user_id char(36) DEFAULT NULL COMMENT '创建人ID',
  create_user_name varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_user_id char(36) DEFAULT NULL COMMENT '修改人ID',
  update_user_name varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  update_time datetime DEFAULT NULL COMMENT '修改时间',
  is_delete tinyint(3) unsigned DEFAULT '0' COMMENT '0-否 1-是',
  url varchar(1000) DEFAULT '' COMMENT '请求地址',
  url_request_body varchar(5000) DEFAULT '' COMMENT '请求体',
  url_request_method varchar(25) DEFAULT '' COMMENT '请求方式',
  expand_level int(10) unsigned DEFAULT NULL COMMENT '指定层级数',
  expand_strategy int(10) unsigned DEFAULT NULL COMMENT '默认展开',
  label_filed varchar(25) DEFAULT NULL COMMENT '显示字段',
  value_filed varchar(25) DEFAULT NULL COMMENT '实际字段',
  order_column_script varchar(300) DEFAULT NULL COMMENT '排序字段',
  search_placeholder varchar(50) DEFAULT NULL COMMENT '搜索框水印',
  direct_select_condition varchar(50) DEFAULT NULL COMMENT '全选/单选查询条件',
  select_filed varchar(100) DEFAULT NULL COMMENT '选中被加入查询搜索的列',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mp_search_relation
-- ----------------------------
DROP TABLE IF EXISTS mp_search_relation;
CREATE TABLE mp_search_relation (
  id varchar(36) NOT NULL,
  search_condition_id varchar(36) NOT NULL COMMENT '搜索条件主键',
  component_search_id varchar(36) NOT NULL COMMENT '搜索条件组件主键',
  code varchar(30) DEFAULT NULL COMMENT '编码',
  condition_type tinyint(4) NOT NULL COMMENT '搜索条件类型（0：基础搜索条件，1：高级搜索条件）',
  sort bigint(20) DEFAULT NULL COMMENT '排序',
  show_name varchar(30) NOT NULL COMMENT '显示名称',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='搜索条件与组件关系表';

-- ----------------------------
-- Table structure for mp_test_contract
-- ----------------------------
DROP TABLE IF EXISTS mp_test_contract;
CREATE TABLE mp_test_contract (
  id varchar(36) NOT NULL,
  name varchar(50) DEFAULT NULL,
  code varchar(50) DEFAULT NULL,
  is_matter tinyint(1) unsigned DEFAULT NULL,
  operator varchar(75) DEFAULT NULL COMMENT '经办人',
  project_id varchar(500) DEFAULT NULL COMMENT '所属项目ID（多个，由分隔符隔开）',
  project_name varchar(500) DEFAULT NULL COMMENT '所属项目（多个，由分隔符隔开）',
  create_time datetime DEFAULT NULL,
  modify_date date DEFAULT NULL,
  approval_state tinyint(3) unsigned DEFAULT NULL COMMENT '审核状态',
  total_amount decimal(18,6) DEFAULT NULL COMMENT '合同金额',
  contract_property tinyint(3) unsigned DEFAULT NULL COMMENT '合同属性(成本、费用)',
  remarks varchar(500) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mp_unit_configuration
-- ----------------------------
DROP TABLE IF EXISTS mp_unit_configuration;
CREATE TABLE mp_unit_configuration (
  id varchar(36) NOT NULL COMMENT '主键',
  unit_name varchar(25) NOT NULL COMMENT '单位名称',
  unit varchar(25) NOT NULL COMMENT '单位',
  number_precision tinyint(1) DEFAULT NULL COMMENT '数字精度',
  sort bigint(20) DEFAULT NULL COMMENT '排序',
  is_default tinyint(3) unsigned NOT NULL COMMENT '是否默认',
  component_table_id varchar(36) NOT NULL COMMENT '表格id',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mp_unit_rate_relation
-- ----------------------------
DROP TABLE IF EXISTS mp_unit_rate_relation;
CREATE TABLE mp_unit_rate_relation (
  id varchar(36) NOT NULL COMMENT '主键',
  unit_configuration_id varchar(36) NOT NULL COMMENT '单位切换配置id(外键)',
  table_column_id varchar(36) DEFAULT NULL COMMENT '表格列id(外键)',
  formula varchar(100) DEFAULT NULL COMMENT '公式',
  sort bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mp_view
-- ----------------------------
DROP TABLE IF EXISTS mp_view;
CREATE TABLE mp_view (
  id varchar(36) NOT NULL,
  view_name varchar(50) NOT NULL COMMENT '视图名称',
  disabled tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否禁用（0：否，1：是）',
  layout_template_code varchar(50) NOT NULL COMMENT '布局模板编码',
  defaulted tinyint(4) DEFAULT '0' COMMENT '是否默认（0：否，1：是）',
  deleted tinyint(4) DEFAULT '0' COMMENT '是否删除（0：否，1：是）',
  function_module_id varchar(36) NOT NULL COMMENT '功能模块主键',
  creator varchar(36) DEFAULT NULL COMMENT '创建人id',
  creator_id varchar(36) NOT NULL COMMENT '创建人姓名',
  created datetime NOT NULL COMMENT '创建时间',
  modifier varchar(36) DEFAULT NULL COMMENT '修改人id',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人姓名',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  permission_name varchar(50) DEFAULT NULL COMMENT '权限名称',
  permission_code varchar(50) DEFAULT NULL COMMENT '权限编码',
  sort bigint(20) NOT NULL COMMENT '排序',
  dynamic_sql varchar(5000) DEFAULT NULL COMMENT 'SQL语句（动态视图数据源）',
  dynamic_key_filed varchar(36) DEFAULT NULL COMMENT '主键标识字段',
  dynamic_title_filed varchar(36) DEFAULT NULL COMMENT '标题展示标识字段',
  enable_dynamic tinyint(4) DEFAULT NULL COMMENT '启用动态视图（默认禁用）',
  view_purpose tinyint(3) unsigned DEFAULT NULL COMMENT '视图用途（0：主页视图，1-表单视图）',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视图';

SET FOREIGN_KEY_CHECKS = 1;
