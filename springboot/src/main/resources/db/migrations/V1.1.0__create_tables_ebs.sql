/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.32-root2
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 192.168.1.32:3308
 Source Schema         : highzap_jerp_basic_app_test

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 03/12/2021 14:02:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ebs_accrued_expense
-- ----------------------------
DROP TABLE IF EXISTS ebs_accrued_expense;
CREATE TABLE ebs_accrued_expense (
  id varchar(36) NOT NULL COMMENT '主键',
  subject varchar(255) DEFAULT NULL COMMENT '主题',
  year int(11) DEFAULT NULL COMMENT '年份',
  accrued_amount decimal(18,6) DEFAULT '0.000000' COMMENT '预提金额',
  accrued_used_amount decimal(18,6) DEFAULT NULL COMMENT '预提使用金额',
  budget_department_id varchar(36) DEFAULT NULL COMMENT '预算部门ID',
  budget_department_name varchar(75) DEFAULT NULL COMMENT '预算部门',
  remark varchar(1500) DEFAULT NULL COMMENT '备注说明',
  modifier varchar(75) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人ID',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  approver varchar(75) DEFAULT NULL COMMENT '审核人',
  approver_id varchar(36) DEFAULT NULL COMMENT '审核人ID',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核、审核中、已审核）',
  creator varchar(75) NOT NULL COMMENT '创建人',
  creator_id varchar(36) NOT NULL COMMENT '创建人ID',
  created datetime NOT NULL COMMENT '创建时间',
  code varchar(75) NOT NULL COMMENT '编号',
  code_type varchar(75) NOT NULL COMMENT '编号类型',
  department varchar(255) NOT NULL COMMENT '申请部门',
  department_id varchar(36) NOT NULL COMMENT '申请部门ID',
  company_id varchar(36) NOT NULL COMMENT '公司ID',
  company_name varchar(75) NOT NULL COMMENT '公司名称',
  proposer varchar(75) NOT NULL COMMENT '申请人',
  proposer_id varchar(36) NOT NULL COMMENT '申请人ID',
  application_date datetime NOT NULL COMMENT '申请日期',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='预提费用';

-- ----------------------------
-- Table structure for ebs_accrued_expense_item
-- ----------------------------
DROP TABLE IF EXISTS ebs_accrued_expense_item;
CREATE TABLE ebs_accrued_expense_item (
  id varchar(36) NOT NULL COMMENT '主键',
  accrued_expense_id varchar(36) NOT NULL COMMENT '预提费用ID',
  name varchar(150) DEFAULT NULL COMMENT '事项名称',
  accrued_amount decimal(18,6) DEFAULT '0.000000' COMMENT '预提金额',
  remark varchar(300) DEFAULT NULL COMMENT '事项说明',
  sort bigint(20) DEFAULT NULL COMMENT '排序（雪花算法生成）',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='预提费用事项';

-- ----------------------------
-- Table structure for ebs_accrued_expense_item_subject
-- ----------------------------
DROP TABLE IF EXISTS ebs_accrued_expense_item_subject;
CREATE TABLE ebs_accrued_expense_item_subject (
  id varchar(36) NOT NULL COMMENT '主键',
  item_id varchar(36) NOT NULL COMMENT '预提费用事项ID',
  subject_id varchar(36) DEFAULT NULL COMMENT '预算科目ID',
  budget_id varchar(36) DEFAULT NULL COMMENT '预算ID',
  allocation_amount decimal(18,6) DEFAULT '0.000000' COMMENT '分摊金额（分摊到科目的预提金额）',
  used_amount decimal(18,6) DEFAULT '0.000000' COMMENT '已使用金额',
  year int(11) DEFAULT NULL COMMENT '年份（审核时更新）',
  month int(11) DEFAULT NULL COMMENT '月份（审核时更新）',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='预提费用事项科目';

-- ----------------------------
-- Table structure for ebs_accrued_expense_usage_detail
-- ----------------------------
DROP TABLE IF EXISTS ebs_accrued_expense_usage_detail;
CREATE TABLE ebs_accrued_expense_usage_detail (
  id varchar(36) NOT NULL COMMENT '主键',
  accrued_expense_id varchar(36) NOT NULL COMMENT '预提ID',
  accrued_expense_name varchar(75) NOT NULL COMMENT '预提名称',
  item_id varchar(36) NOT NULL COMMENT '预提事项ID',
  item_name varchar(75) NOT NULL COMMENT '预提事项名称',
  subject_id varchar(36) NOT NULL COMMENT '预提事项科目ID',
  business_id varchar(36) NOT NULL COMMENT '业务ID',
  business_type varchar(75) NOT NULL COMMENT '业务类型（费用报销）',
  accrued_amount decimal(18,6) DEFAULT '0.000000' COMMENT '预提金额',
  used_amount decimal(18,6) DEFAULT '0.000000' COMMENT '已使用金额',
  allocation_amount decimal(18,6) DEFAULT '0.000000' COMMENT '分摊金额',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='预提费用使用明细';

-- ----------------------------
-- Table structure for ebs_budget
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget;
CREATE TABLE ebs_budget (
  id varchar(36) NOT NULL COMMENT '主键',
  company_id varchar(36) NOT NULL COMMENT '公司ID',
  budget_owner_id varchar(36) NOT NULL COMMENT '预算所属者ID（部门ID或项目ID）',
  budget_owner varchar(255) NOT NULL COMMENT '预算所有者（部门名称或项目名称）',
  budget_type varchar(75) NOT NULL COMMENT '预算所属类型（1,部门；2,项目）',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核；审核中；已审核）',
  approver_id varchar(36) DEFAULT NULL COMMENT '审核人ID',
  approver varchar(75) DEFAULT NULL COMMENT '审核人',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  creator varchar(36) NOT NULL COMMENT '创建人',
  creator_id varchar(36) NOT NULL COMMENT '创建人ID',
  created datetime NOT NULL COMMENT '创建时间',
  is_closed tinyint(1) DEFAULT '0' COMMENT '是否预算关闭（1 关闭；0开启）',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='预算（预算部门、预算项目）';

-- ----------------------------
-- Table structure for ebs_budget_adjust
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget_adjust;
CREATE TABLE ebs_budget_adjust (
  id varchar(36) NOT NULL,
  company_id varchar(36) NOT NULL,
  title varchar(150) NOT NULL,
  department_id varchar(36) NOT NULL,
  department_name varchar(100) DEFAULT NULL,
  adjust_amount decimal(18,6) DEFAULT NULL COMMENT '调整总金额',
  budget_year int(11) NOT NULL COMMENT '年度',
  remark varchar(1500) DEFAULT NULL,
  adjuster varchar(75) NOT NULL,
  adjuster_id varchar(36) NOT NULL,
  adjusted datetime NOT NULL COMMENT '调整时间',
  approval_state varchar(75) DEFAULT NULL,
  approver_id varchar(36) DEFAULT NULL,
  approver varchar(75) DEFAULT NULL,
  approved datetime DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='预算调整';

-- ----------------------------
-- Table structure for ebs_budget_adjust_detail
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget_adjust_detail;
CREATE TABLE ebs_budget_adjust_detail (
  id varchar(36) NOT NULL,
  budget_adjust_id varchar(36) DEFAULT NULL,
  subject_id varchar(36) DEFAULT NULL,
  before_amount decimal(18,6) DEFAULT NULL COMMENT '调整前预算金额',
  adjust_amount decimal(18,6) DEFAULT NULL COMMENT '调整金额',
  after_amount decimal(18,6) DEFAULT NULL COMMENT '调整后预算金额',
  budget_year int(11) DEFAULT NULL COMMENT '预算年度',
  budget_month int(11) DEFAULT NULL COMMENT '预算月份',
  remark varchar(1500) DEFAULT NULL,
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='预算调整明细';

-- ----------------------------
-- Table structure for ebs_budget_contract_use_dtl
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget_contract_use_dtl;
CREATE TABLE ebs_budget_contract_use_dtl (
  id varchar(36) NOT NULL COMMENT '标识',
  business_id varchar(36) DEFAULT NULL COMMENT '业务ID',
  business_type varchar(75) DEFAULT NULL COMMENT '业务类型',
  budget_owner_id varchar(36) DEFAULT NULL COMMENT '预算所有者',
  budget_owner_type varchar(75) DEFAULT NULL COMMENT '预算所有者类型（部门、项目）',
  subject_id varchar(36) DEFAULT NULL COMMENT '科目ID',
  matter_id varchar(36) DEFAULT NULL COMMENT '立项ID',
  matter_detail_id varchar(36) DEFAULT NULL COMMENT '立项明细ID',
  contract_id varchar(36) DEFAULT NULL COMMENT '合同ID',
  budget_amount decimal(18,6) DEFAULT NULL COMMENT '科目预算金额',
  happend_amount decimal(18,6) DEFAULT NULL COMMENT '使用金额',
  used_amount decimal(18,6) DEFAULT NULL COMMENT '本次分摊金额',
  budget_year int(11) DEFAULT NULL COMMENT '年份(审核时更新)',
  budget_month int(11) DEFAULT NULL COMMENT '月份(审核时更新)',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核、审核中、已审核）',
  happen_date date DEFAULT NULL COMMENT '发生日期',
  summary varchar(75) DEFAULT NULL COMMENT '报销事项',
  budget_owner_name varchar(300) DEFAULT NULL COMMENT '预算所有者名称',
  subject_name varchar(200) DEFAULT NULL COMMENT '科目名称',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  sort bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='签约（发生）口径预算分摊信息';

-- ----------------------------
-- Table structure for ebs_budget_department
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget_department;
CREATE TABLE ebs_budget_department (
  id varchar(36) NOT NULL COMMENT '主键',
  company_id varchar(36) NOT NULL COMMENT '公司ID',
  company_name varchar(75) NOT NULL COMMENT '公司名称',
  department_code varchar(75) NOT NULL COMMENT '预算部门编号',
  department_name varchar(75) NOT NULL COMMENT '预算部门名称',
  sort bigint(20) NOT NULL COMMENT '排序',
  budget_year int(11) NOT NULL COMMENT '年度',
  owned_function varchar(75) DEFAULT NULL COMMENT '所属职能',
  is_control_expense tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否控制预算',
  month_number_01 int(11) DEFAULT NULL COMMENT '1月人数',
  month_number_02 int(11) DEFAULT NULL COMMENT '2月人数',
  month_number_03 int(11) DEFAULT NULL COMMENT '3月人数',
  month_number_04 int(11) DEFAULT NULL COMMENT '4月人数',
  month_number_05 int(11) DEFAULT NULL COMMENT '5月人数',
  month_number_06 int(11) DEFAULT NULL COMMENT '6月人数',
  month_number_07 int(11) DEFAULT NULL COMMENT '7月人数',
  month_number_08 int(11) DEFAULT NULL COMMENT '8月人数',
  month_number_09 int(11) DEFAULT NULL COMMENT '9月人数',
  month_number_10 int(11) DEFAULT NULL COMMENT '10月人数',
  month_number_11 int(11) DEFAULT NULL COMMENT '11月人数',
  month_number_12 int(11) DEFAULT NULL COMMENT '12月人数',
  remark varchar(1500) DEFAULT NULL COMMENT '备注说明',
  creator varchar(36) NOT NULL COMMENT '创建人',
  creator_id varchar(36) NOT NULL COMMENT '创建人ID',
  created datetime NOT NULL COMMENT '创建时间',
  modifier varchar(36) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人ID',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  is_cost_control tinyint(1) DEFAULT NULL COMMENT '是否成本管控',
  cost_project_id varchar(36) DEFAULT NULL COMMENT '成本项目id',
  cost_project_name varchar(75) DEFAULT NULL COMMENT '成本项目名称',
  is_deleted tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '是否删除',
  year_end_headcount_dec int(11) DEFAULT NULL COMMENT '年末12月人数',
  year_end_headcount_nov int(11) DEFAULT NULL COMMENT '年末11月人数',
  year_end_headcount_oct int(11) DEFAULT NULL COMMENT '年末10月人数',
  year_end_headcount_sept int(11) DEFAULT NULL COMMENT '年末9月人数',
  year_end_headcount_aug int(11) DEFAULT NULL COMMENT '年末8月人数',
  year_end_headcount_jul int(11) DEFAULT NULL COMMENT '年末7月人数',
  year_end_headcount_jun int(11) DEFAULT NULL COMMENT '年末6月人数',
  year_end_headcount_may int(11) DEFAULT NULL COMMENT '年末5月人数',
  year_end_headcount_apr int(11) DEFAULT NULL COMMENT '年末4月人数',
  year_end_headcount_mar int(11) DEFAULT NULL COMMENT '年末3月人数',
  year_end_headcount_fed int(11) DEFAULT NULL COMMENT '年末2月人数',
  year_end_headcount_jan int(11) DEFAULT NULL COMMENT '年末1月人数',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='预算部门';

-- ----------------------------
-- Table structure for ebs_budget_department_map
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget_department_map;
CREATE TABLE ebs_budget_department_map (
  id varchar(36) NOT NULL,
  current_department_id varchar(36) DEFAULT NULL,
  prev_department_id varchar(36) DEFAULT NULL,
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='预算部门年度对照信息';

-- ----------------------------
-- Table structure for ebs_budget_department_rights
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget_department_rights;
CREATE TABLE ebs_budget_department_rights (
  id varchar(36) NOT NULL COMMENT '主键',
  department_id varchar(36) NOT NULL COMMENT '预算部门ID',
  org_type varchar(36) NOT NULL COMMENT '组织类型（1,公司；2,部门；3,岗位；）',
  org_id varchar(36) NOT NULL COMMENT '组织ID',
  org_name varchar(75) NOT NULL COMMENT '组织名称',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='预算部门组织授权信息';

-- ----------------------------
-- Table structure for ebs_budget_department_station
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget_department_station;
CREATE TABLE ebs_budget_department_station (
  id varchar(36) NOT NULL COMMENT '主键',
  department_id varchar(36) NOT NULL COMMENT '预算部门ID',
  station_id varchar(36) NOT NULL COMMENT '岗位ID',
  station_name varchar(75) NOT NULL COMMENT '岗位名',
  station_type varchar(75) NOT NULL COMMENT '岗位类型',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='预算部门岗位关联信息';

-- ----------------------------
-- Table structure for ebs_budget_detail
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget_detail;
CREATE TABLE ebs_budget_detail (
  id varchar(36) NOT NULL COMMENT '主键',
  budget_id varchar(36) NOT NULL COMMENT '预算主表ID',
  department_id varchar(36) NOT NULL COMMENT '预算部门ID',
  subject_id varchar(36) NOT NULL COMMENT '预算科目ID',
  budget_year int(11) NOT NULL COMMENT '年度',
  plan_amount_01 decimal(18,6) DEFAULT '0.000000' COMMENT '预算1月',
  plan_amount_02 decimal(18,6) DEFAULT '0.000000' COMMENT '预算2月',
  plan_amount_03 decimal(18,6) DEFAULT '0.000000' COMMENT '预算3月',
  plan_amount_04 decimal(18,6) DEFAULT '0.000000' COMMENT '预算4月',
  plan_amount_05 decimal(18,6) DEFAULT '0.000000' COMMENT '预算5月',
  plan_amount_06 decimal(18,6) DEFAULT '0.000000' COMMENT '预算6月',
  plan_amount_07 decimal(18,6) DEFAULT '0.000000' COMMENT '预算7月',
  plan_amount_08 decimal(18,6) DEFAULT '0.000000' COMMENT '预算8月',
  plan_amount_09 decimal(18,6) DEFAULT '0.000000' COMMENT '预算9月',
  plan_amount_10 decimal(18,6) DEFAULT '0.000000' COMMENT '预算10月',
  plan_amount_11 decimal(18,6) DEFAULT '0.000000' COMMENT '预算11月',
  plan_amount_12 decimal(18,6) DEFAULT '0.000000' COMMENT '预算12月',
  adjust_amount_01 decimal(18,6) DEFAULT '0.000000' COMMENT '预算调整1月',
  adjust_amount_02 decimal(18,6) DEFAULT '0.000000' COMMENT '预算调整2月',
  adjust_amount_03 decimal(18,6) DEFAULT '0.000000' COMMENT '预算调整3月',
  adjust_amount_04 decimal(18,6) DEFAULT '0.000000' COMMENT '预算调整4月',
  adjust_amount_05 decimal(18,6) DEFAULT '0.000000' COMMENT '预算调整5月',
  adjust_amount_06 decimal(18,6) DEFAULT '0.000000' COMMENT '预算调整6月',
  adjust_amount_07 decimal(18,6) DEFAULT '0.000000' COMMENT '预算调整7月',
  adjust_amount_08 decimal(18,6) DEFAULT '0.000000' COMMENT '预算调整8月',
  adjust_amount_09 decimal(18,6) DEFAULT '0.000000' COMMENT '预算调整9月',
  adjust_amount_10 decimal(18,6) DEFAULT '0.000000' COMMENT '预算调整10月',
  adjust_amount_11 decimal(18,6) DEFAULT '0.000000' COMMENT '预算调整11月',
  adjust_amount_12 decimal(18,6) DEFAULT '0.000000' COMMENT '预算调整12月',
  matter_amount_01 decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额1月',
  matter_amount_02 decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额2月',
  matter_amount_03 decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额3月',
  matter_amount_04 decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额4月',
  matter_amount_05 decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额5月',
  matter_amount_06 decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额6月',
  matter_amount_07 decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额7月',
  matter_amount_08 decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额8月',
  matter_amount_09 decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额9月',
  matter_amount_10 decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额10月',
  matter_amount_11 decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额11月',
  matter_amount_12 decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额12月',
  contract_amount_01 decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额1月',
  contract_amount_02 decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额2月',
  contract_amount_03 decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额3月',
  contract_amount_04 decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额4月',
  contract_amount_05 decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额5月',
  contract_amount_06 decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额6月',
  contract_amount_07 decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额7月',
  contract_amount_08 decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额8月',
  contract_amount_09 decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额9月',
  contract_amount_10 decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额10月',
  contract_amount_11 decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额11月',
  contract_amount_12 decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额12月',
  expense_amount_01 decimal(18,6) DEFAULT '0.000000' COMMENT '报销金额1月',
  expense_amount_02 decimal(18,6) DEFAULT '0.000000' COMMENT '报销金额2月',
  expense_amount_03 decimal(18,6) DEFAULT '0.000000' COMMENT '报销金额3月',
  expense_amount_04 decimal(18,6) DEFAULT '0.000000' COMMENT '报销金额4月',
  expense_amount_05 decimal(18,6) DEFAULT '0.000000' COMMENT '报销金额5月',
  expense_amount_06 decimal(18,6) DEFAULT '0.000000' COMMENT '报销金额6月',
  expense_amount_07 decimal(18,6) DEFAULT '0.000000' COMMENT '报销金额7月',
  expense_amount_08 decimal(18,6) DEFAULT '0.000000' COMMENT '报销金额8月',
  expense_amount_09 decimal(18,6) DEFAULT '0.000000' COMMENT '报销金额9月',
  expense_amount_10 decimal(18,6) DEFAULT '0.000000' COMMENT '报销金额10月',
  expense_amount_11 decimal(18,6) DEFAULT '0.000000' COMMENT '报销金额11月',
  expense_amount_12 decimal(18,6) DEFAULT '0.000000' COMMENT '报销金额12月',
  fact_amount_01 decimal(18,6) DEFAULT '0.000000' COMMENT '应付金额1月',
  fact_amount_02 decimal(18,6) DEFAULT '0.000000' COMMENT '应付金额2月',
  fact_amount_03 decimal(18,6) DEFAULT '0.000000' COMMENT '应付金额3月',
  fact_amount_04 decimal(18,6) DEFAULT '0.000000' COMMENT '应付金额4月',
  fact_amount_05 decimal(18,6) DEFAULT '0.000000' COMMENT '应付金额5月',
  fact_amount_06 decimal(18,6) DEFAULT '0.000000' COMMENT '应付金额6月',
  fact_amount_07 decimal(18,6) DEFAULT '0.000000' COMMENT '应付金额7月',
  fact_amount_08 decimal(18,6) DEFAULT '0.000000' COMMENT '应付金额8月',
  fact_amount_09 decimal(18,6) DEFAULT '0.000000' COMMENT '应付金额9月',
  fact_amount_10 decimal(18,6) DEFAULT '0.000000' COMMENT '应付金额10月',
  fact_amount_11 decimal(18,6) DEFAULT '0.000000' COMMENT '应付金额11月',
  fact_amount_12 decimal(18,6) DEFAULT '0.000000' COMMENT '应付金额12月',
  pay_amount_01 decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额1月',
  pay_amount_02 decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额2月',
  pay_amount_03 decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额3月',
  pay_amount_04 decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额4月',
  pay_amount_05 decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额5月',
  pay_amount_06 decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额6月',
  pay_amount_07 decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额7月',
  pay_amount_08 decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额8月',
  pay_amount_09 decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额9月',
  pay_amount_10 decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额10月',
  pay_amount_11 decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额11月',
  pay_amount_12 decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额12月',
  occur_amount_01 decimal(18,6) DEFAULT '0.000000' COMMENT '立项+签约1月',
  occur_amount_02 decimal(18,6) DEFAULT '0.000000' COMMENT '立项+签约2月',
  occur_amount_03 decimal(18,6) DEFAULT '0.000000' COMMENT '立项+签约3月',
  occur_amount_04 decimal(18,6) DEFAULT '0.000000' COMMENT '立项+签约4月',
  occur_amount_05 decimal(18,6) DEFAULT '0.000000' COMMENT '立项+签约5月',
  occur_amount_06 decimal(18,6) DEFAULT '0.000000' COMMENT '立项+签约6月',
  occur_amount_07 decimal(18,6) DEFAULT '0.000000' COMMENT '立项+签约7月',
  occur_amount_08 decimal(18,6) DEFAULT '0.000000' COMMENT '立项+签约8月',
  occur_amount_09 decimal(18,6) DEFAULT '0.000000' COMMENT '立项+签约9月',
  occur_amount_10 decimal(18,6) DEFAULT '0.000000' COMMENT '立项+签约10月',
  occur_amount_11 decimal(18,6) DEFAULT '0.000000' COMMENT '立项+签约11月',
  occur_amount_12 decimal(18,6) DEFAULT '0.000000' COMMENT '立项+签约12月',
  again_apportioned_amount decimal(18,6) DEFAULT '0.000000' COMMENT '费用二次分摊',
  accrued_amount decimal(18,6) DEFAULT '0.000000' COMMENT '年度费用预提',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='预算明细';

-- ----------------------------
-- Table structure for ebs_budget_pay_apply_use_dtl
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget_pay_apply_use_dtl;
CREATE TABLE ebs_budget_pay_apply_use_dtl (
  id varchar(36) NOT NULL COMMENT '标识',
  business_id varchar(36) DEFAULT NULL COMMENT '业务ID',
  business_type varchar(75) DEFAULT NULL COMMENT '业务类型',
  budget_owner_id varchar(36) DEFAULT NULL COMMENT '预算所有者ID',
  budget_owner_type varchar(75) DEFAULT NULL COMMENT '预算所有者类型（部门、项目）',
  subject_id varchar(36) DEFAULT NULL COMMENT '科目ID',
  contract_allocation_id varchar(36) DEFAULT NULL COMMENT '签约口径分摊ID',
  contract_amount decimal(18,6) DEFAULT NULL COMMENT '科目预算金额',
  happend_amount decimal(18,6) DEFAULT NULL COMMENT '使用金额',
  used_amount decimal(18,6) DEFAULT NULL COMMENT '本次分摊金额',
  budget_year int(11) DEFAULT NULL COMMENT '年份',
  budget_month int(11) DEFAULT NULL COMMENT '月份',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核、审核中、已审核）',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  sort bigint(20) DEFAULT NULL COMMENT '排序',
  budget_owner_name varchar(300) DEFAULT NULL COMMENT '预算所有者名称',
  subject_name varchar(200) DEFAULT NULL COMMENT '科目名称',
  is_deleted tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='付款口径预算分摊信息';

-- ----------------------------
-- Table structure for ebs_budget_project
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget_project;
CREATE TABLE ebs_budget_project (
  id varchar(36) NOT NULL COMMENT '主键',
  name varchar(75) NOT NULL COMMENT '预算项目',
  code varchar(75) NOT NULL COMMENT '编号',
  person_in_charge varchar(2000) NOT NULL COMMENT '负责人',
  person_in_charge_id varchar(2000) NOT NULL COMMENT '负责人ID',
  start_date datetime NOT NULL COMMENT '计划开始日期',
  end_date datetime NOT NULL COMMENT '计划结束日期',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态',
  approver_id varchar(36) DEFAULT NULL COMMENT '审核人ID',
  approver varchar(75) DEFAULT NULL COMMENT '审核人',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  md_project varchar(75) DEFAULT NULL COMMENT '主项目',
  md_project_id varchar(36) DEFAULT NULL COMMENT '主项目ID',
  creator varchar(36) NOT NULL COMMENT '创建人',
  creator_id varchar(36) NOT NULL COMMENT '创建人ID',
  created datetime NOT NULL COMMENT '创建时间',
  modifier varchar(36) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人ID',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  remark varchar(1500) DEFAULT NULL COMMENT '备注说明',
  company_id varchar(36) DEFAULT NULL COMMENT '公司ID',
  company_name varchar(75) DEFAULT NULL COMMENT '公司名称',
  is_deleted tinyint(1) DEFAULT '0' COMMENT '是否删除，1-是；0-否',
  is_closed tinyint(1) DEFAULT '0' COMMENT '是否关闭，1-是；0-否',
  closed_date datetime DEFAULT NULL COMMENT '预算关闭日期',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='预算项目';

-- ----------------------------
-- Table structure for ebs_budget_recalculate
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget_recalculate;
CREATE TABLE ebs_budget_recalculate (
  id varchar(36) NOT NULL COMMENT '主键ID',
  recalculate_name varchar(200) DEFAULT NULL COMMENT '归集名称',
  business_process_type varchar(20) DEFAULT NULL COMMENT '业务流程类别:费用合同、费用报销',
  business_amount decimal(18,6) DEFAULT '0.000000' COMMENT '业务金额',
  business_id varchar(36) DEFAULT NULL COMMENT '业务id',
  state varchar(10) DEFAULT NULL COMMENT '状态（未归集、已归集）',
  creator varchar(50) DEFAULT NULL COMMENT '创建人',
  creator_id varchar(36) DEFAULT NULL COMMENT '创建人id',
  created datetime DEFAULT NULL COMMENT '创建时间',
  modifier varchar(50) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人id',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  recalculate_count int(11) DEFAULT '0' COMMENT '归集次数',
  is_deleted tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='费用归集表';

-- ----------------------------
-- Table structure for ebs_budget_recalculate_business
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget_recalculate_business;
CREATE TABLE ebs_budget_recalculate_business (
  id varchar(36) NOT NULL COMMENT '主键id',
  budget_recalculate_id varchar(200) DEFAULT NULL COMMENT '费用归集id',
  business_process_type varchar(20) DEFAULT NULL COMMENT '业务过程类别(费用合同，费用报销)',
  business_type varchar(20) DEFAULT NULL COMMENT '业务环节(合同、补充合同、合同变更、合同结算、合同请款、费用报销)',
  contract_id varchar(36) DEFAULT NULL COMMENT '合同id、报销id',
  business_id varchar(36) DEFAULT NULL COMMENT '业务单据id',
  business_name varchar(200) DEFAULT NULL COMMENT '业务单据名称',
  business_code varchar(200) DEFAULT NULL COMMENT '业务单据编码',
  business_date datetime DEFAULT NULL COMMENT '业务单据日期',
  business_approved datetime DEFAULT NULL COMMENT '业务单据审核日期',
  business_amount decimal(18,6) DEFAULT '0.000000' COMMENT '业务单据金额',
  state varchar(10) DEFAULT NULL COMMENT '归集状态(未归集/已归集)',
  recalculate_way varchar(10) DEFAULT NULL COMMENT '归集方式(自动归集/手动归集)',
  is_deleted tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='费用归集单据';

-- ----------------------------
-- Table structure for ebs_budget_recalculate_business_dtl
-- ----------------------------
DROP TABLE IF EXISTS ebs_budget_recalculate_business_dtl;
CREATE TABLE ebs_budget_recalculate_business_dtl (
  id varchar(36) NOT NULL COMMENT '主键id',
  buaget_recalculate_business_id varchar(200) DEFAULT NULL COMMENT '归集单据id',
  business_type varchar(20) DEFAULT NULL COMMENT '业务类别(合同、补充合同、合同变更、合同结算、请款申请)',
  business_id varchar(36) DEFAULT NULL COMMENT '业务单据id',
  budget_owner_id varchar(36) DEFAULT NULL COMMENT '预算所有者id',
  budget_owner_type varchar(75) DEFAULT NULL COMMENT '预算所有者类型(部门、项目)',
  budget_owner_name varchar(300) DEFAULT NULL COMMENT '预算所有者名称',
  subject_id varchar(36) DEFAULT NULL COMMENT '科目id',
  subject_name varchar(200) DEFAULT NULL COMMENT '科目名称',
  matter_id varchar(36) DEFAULT NULL COMMENT '立项id',
  matter_detail_id varchar(36) DEFAULT NULL COMMENT '立项明细id',
  contract_id varchar(36) DEFAULT NULL COMMENT '合同id',
  budget_amount decimal(18,6) DEFAULT '0.000000' COMMENT '科目预算金额',
  happend_amount decimal(18,6) DEFAULT '0.000000' COMMENT '使用金额',
  used_amount decimal(18,6) DEFAULT '0.000000' COMMENT '本次分摊金额',
  budget_year int(11) DEFAULT NULL COMMENT '年份',
  budget_month int(11) DEFAULT NULL COMMENT '月份',
  sort bigint(20) DEFAULT NULL COMMENT '排序',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态(已审核、审核中、未审核)',
  happen_date date DEFAULT NULL COMMENT '发生日期',
  summary varchar(75) DEFAULT NULL COMMENT '报销事项',
  is_history tinyint(1) DEFAULT NULL COMMENT '是否原分摊明细',
  contract_allocation_id varchar(36) DEFAULT NULL COMMENT '签约分摊ID',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='费用归集预算分摊明细';

-- ----------------------------
-- Table structure for ebs_contract_provider
-- ----------------------------
DROP TABLE IF EXISTS ebs_contract_provider;
CREATE TABLE ebs_contract_provider (
  id varchar(36) NOT NULL COMMENT '标识',
  provider varchar(75) NOT NULL COMMENT '供应商',
  provider_id varchar(36) NOT NULL COMMENT '供应商ID',
  invoice_type varchar(75) NOT NULL COMMENT '票据类型',
  invoice_type_id varchar(36) NOT NULL COMMENT '票据类型ID',
  amount decimal(18,6) DEFAULT NULL COMMENT '签约金额',
  tax_ratio decimal(18,6) DEFAULT NULL COMMENT '税率',
  amount_no_tax decimal(18,6) NOT NULL COMMENT '除税金额',
  input_tax decimal(18,6) DEFAULT NULL COMMENT '进项税额',
  contractor varchar(75) DEFAULT NULL COMMENT '签约人',
  contract_id varchar(36) DEFAULT NULL COMMENT '合同ID',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='合同供应商';

-- ----------------------------
-- Table structure for ebs_expense
-- ----------------------------
DROP TABLE IF EXISTS ebs_expense;
CREATE TABLE ebs_expense (
  id varchar(36) NOT NULL COMMENT '报销标识',
  expense_subject varchar(150) NOT NULL COMMENT '报销主题',
  expense_code varchar(75) NOT NULL COMMENT '报销编号',
  code_type varchar(75) NOT NULL COMMENT '报销编号类型',
  pay_unit_name varchar(75) DEFAULT NULL COMMENT '付款单位',
  pay_unit_id varchar(36) DEFAULT NULL COMMENT '付款单位ID',
  expense_type varchar(75) DEFAULT NULL COMMENT '报销类别',
  expense_type_id varchar(36) DEFAULT NULL COMMENT '报销类别ID',
  is_employee tinyint(4) DEFAULT '0' COMMENT '是否内部员工',
  is_bills_match tinyint(4) DEFAULT '0' COMMENT '是否票据相符',
  receive_unit_name varchar(75) DEFAULT NULL COMMENT '收款单位',
  receive_unit_id varchar(36) DEFAULT NULL COMMENT '收款单位ID',
  receive_bank_name varchar(75) DEFAULT NULL COMMENT '收款银行',
  receive_bank_account varchar(75) DEFAULT NULL COMMENT '收款账号',
  expense_amount decimal(18,6) DEFAULT NULL COMMENT '报销金额',
  balance_amount decimal(18,6) DEFAULT NULL COMMENT '冲账金额',
  pay_amount decimal(18,6) DEFAULT NULL COMMENT '应付金额',
  paid_amount decimal(18,6) DEFAULT NULL COMMENT '实付金额（付款登记更新）',
  paid_date datetime DEFAULT NULL COMMENT '支付日期（付款登记更新）',
  remark varchar(1500) DEFAULT NULL COMMENT '说明',
  approver varchar(75) DEFAULT NULL COMMENT '审核人',
  approver_id varchar(36) DEFAULT NULL COMMENT '审核人ID',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核、审核中、已审核）',
  company_id varchar(36) NOT NULL COMMENT '公司ID',
  company_name varchar(75) NOT NULL COMMENT '公司名称',
  department_id varchar(36) NOT NULL COMMENT '申请部门ID',
  department_name varchar(75) NOT NULL COMMENT '申请部门',
  operator varchar(75) NOT NULL COMMENT '申请人',
  operator_id varchar(36) NOT NULL COMMENT '申请人ID',
  apply_date datetime NOT NULL COMMENT '申请日期',
  modifier varchar(75) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人ID',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  creator varchar(75) NOT NULL COMMENT '创建人',
  creator_id varchar(36) NOT NULL COMMENT '创建人ID',
  created datetime DEFAULT NULL COMMENT '创建时间',
  is_deleted tinyint(4) NOT NULL COMMENT '是否删除',
  is_use_contingency_cost tinyint(1) DEFAULT NULL COMMENT '是否使用不可预见费',
  pay_state varchar(20) DEFAULT NULL,
  is_matter tinyint(1) DEFAULT NULL COMMENT '否关联立项',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='费用报销';

-- ----------------------------
-- Table structure for ebs_expense_detail
-- ----------------------------
DROP TABLE IF EXISTS ebs_expense_detail;
CREATE TABLE ebs_expense_detail (
  id varchar(36) NOT NULL COMMENT '标识',
  expense_id varchar(36) DEFAULT NULL COMMENT '报销ID',
  expense_detail_name varchar(75) DEFAULT NULL COMMENT '名称（替票、通用类别、自定义名称）',
  invoice_type varchar(75) DEFAULT NULL COMMENT '票据类型',
  occur_date datetime DEFAULT NULL COMMENT '发生日期',
  expense_detail_amount decimal(18,6) DEFAULT NULL COMMENT '金额',
  subject_id varchar(36) DEFAULT NULL COMMENT '科目ID（替票、通用类别科目ID）',
  summary varchar(1500) DEFAULT NULL COMMENT '摘要',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='费用报销明细';

-- ----------------------------
-- Table structure for ebs_expense_quota_standard
-- ----------------------------
DROP TABLE IF EXISTS ebs_expense_quota_standard;
CREATE TABLE ebs_expense_quota_standard (
  id varchar(36) NOT NULL,
  name varchar(75) NOT NULL,
  code varchar(75) NOT NULL,
  is_disabled tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  type varchar(75) NOT NULL,
  company varchar(75) NOT NULL,
  company_id varchar(36) NOT NULL,
  year int(11) NOT NULL COMMENT '年份',
  unit varchar(75) NOT NULL,
  value decimal(18,6) NOT NULL COMMENT '定额标准',
  remark varchar(1500) DEFAULT NULL,
  creator varchar(36) NOT NULL,
  creator_id varchar(36) NOT NULL,
  created datetime NOT NULL COMMENT '创建时间',
  modifier varchar(36) DEFAULT NULL,
  modifier_id varchar(36) DEFAULT NULL,
  modified datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='费用定额标准';

-- ----------------------------
-- Table structure for ebs_invoice
-- ----------------------------
DROP TABLE IF EXISTS ebs_invoice;
CREATE TABLE ebs_invoice (
  id varchar(36) NOT NULL COMMENT '票据标识',
  invoice_type varchar(75) NOT NULL COMMENT '票据类型',
  invoice_type_id varchar(36) NOT NULL COMMENT '票据类型ID',
  invoice_no varchar(75) NOT NULL COMMENT '票据编号(唯一)',
  business_id varchar(36) NOT NULL COMMENT '业务ID',
  business_type varchar(75) NOT NULL COMMENT '业务类型（合同付款申请、合同进度产值、合同结算、合同登记、日常报销、立项报销）',
  invoice_date datetime DEFAULT NULL COMMENT '开票日期',
  supplier varchar(75) NOT NULL COMMENT '供方单位',
  supplier_id varchar(36) NOT NULL COMMENT '供方单位ID',
  purchaser varchar(75) DEFAULT NULL COMMENT '购方单位',
  purchaser_id varchar(36) DEFAULT NULL COMMENT '购方单位ID',
  ticket_price decimal(18,6) DEFAULT NULL COMMENT '票价(A1,可抵扣)',
  additional_expense_one decimal(18,6) DEFAULT NULL COMMENT '附加费用一（A2,可抵扣）',
  additional_expense_two decimal(18,6) DEFAULT NULL COMMENT '附加费用二（A3,不可抵扣）',
  additional_expense_three decimal(18,6) DEFAULT NULL COMMENT '附加费用三（A4,不可抵扣）',
  price_including_tax decimal(18,6) NOT NULL COMMENT '价税合计(A=A1+A2+A3+A4)',
  tax_rate decimal(18,6) NOT NULL COMMENT '税率',
  price_excluding_tax decimal(18,6) DEFAULT NULL COMMENT '不含税金额',
  tax decimal(18,6) DEFAULT NULL COMMENT '税额',
  remark varchar(500) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='票据';

-- ----------------------------
-- Table structure for ebs_loan
-- ----------------------------
DROP TABLE IF EXISTS ebs_loan;
CREATE TABLE ebs_loan (
  id varchar(36) NOT NULL COMMENT '领借款标识',
  loan_subject varchar(150) NOT NULL COMMENT '领借款主题',
  loan_code varchar(75) NOT NULL COMMENT '领借款编号',
  code_type varchar(75) NOT NULL COMMENT '编号类型',
  company_id varchar(36) NOT NULL COMMENT '公司ID',
  company_name varchar(75) NOT NULL COMMENT '公司名称',
  department_name varchar(75) NOT NULL COMMENT '申请部门',
  department_id varchar(36) NOT NULL COMMENT '申请部门ID',
  operator varchar(75) NOT NULL COMMENT '申请人',
  operator_id varchar(36) NOT NULL COMMENT '申请人ID',
  apply_date datetime NOT NULL COMMENT '申请日期',
  loan_type varchar(75) NOT NULL COMMENT '领借款类型',
  loan_type_id varchar(36) NOT NULL COMMENT '领借款类型ID',
  loan_amount decimal(18,6) NOT NULL DEFAULT '0.000000' COMMENT '借款金额',
  paid_amount decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额（付款登记更新）',
  balance_amount decimal(18,6) DEFAULT '0.000000' COMMENT '借款抵销金额（冲账金额）',
  remain_amount decimal(18,6) DEFAULT '0.000000' COMMENT '借款抵销余额（借款金额-借款抵销金额）',
  is_employee tinyint(1) DEFAULT '0' COMMENT '是否内部员工（1 是，0否）',
  pay_unit_name varchar(75) DEFAULT NULL COMMENT '付款单位',
  pay_unit_id varchar(36) DEFAULT NULL COMMENT '付款单位ID',
  receive_unit_name varchar(75) DEFAULT NULL COMMENT '收款单位',
  receive_unit_id varchar(36) DEFAULT NULL COMMENT '收款单位ID',
  receive_bank_name varchar(75) DEFAULT NULL COMMENT '收款银行',
  receive_bank_account varchar(75) DEFAULT NULL COMMENT '收款账号',
  remark varchar(1500) DEFAULT NULL COMMENT '说明',
  approver varchar(75) DEFAULT NULL COMMENT '审核人',
  approver_id varchar(36) DEFAULT NULL COMMENT '审核人ID',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核、审核中、已审核）',
  payment_date datetime DEFAULT NULL COMMENT '支付日期',
  payment_state varchar(75) DEFAULT NULL COMMENT '支付状态（未支付、已支付）',
  modifier varchar(75) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人ID',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  creator varchar(75) DEFAULT NULL COMMENT '创建人',
  creator_id varchar(36) DEFAULT NULL COMMENT '创建人ID',
  created datetime DEFAULT NULL COMMENT '创建时间',
  is_deleted tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='领借款';

-- ----------------------------
-- Table structure for ebs_loan_balance_detail
-- ----------------------------
DROP TABLE IF EXISTS ebs_loan_balance_detail;
CREATE TABLE ebs_loan_balance_detail (
  id varchar(36) NOT NULL COMMENT '标识',
  loan_id varchar(36) DEFAULT NULL COMMENT '领借款ID',
  business_id varchar(36) DEFAULT NULL COMMENT '业务主键',
  business_type varchar(75) DEFAULT NULL COMMENT '业务类型（报销、合同付款申请、合同结算）',
  current_balance_amount decimal(18,6) DEFAULT NULL COMMENT '冲账金额',
  balance_date datetime DEFAULT NULL COMMENT '冲账日期',
  loan_amount decimal(18,6) DEFAULT NULL COMMENT '领借款金额',
  sum_balance_amount decimal(18,6) DEFAULT NULL COMMENT '已冲账金额',
  remain_amount decimal(18,6) DEFAULT NULL COMMENT '待冲账金额',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='领借款冲账明细';

-- ----------------------------
-- Table structure for ebs_loan_return
-- ----------------------------
DROP TABLE IF EXISTS ebs_loan_return;
CREATE TABLE ebs_loan_return (
  id varchar(36) NOT NULL COMMENT '主键',
  loan_id varchar(36) NOT NULL COMMENT '领借款ID',
  return_code varchar(75) NOT NULL COMMENT '还款编号',
  code_type varchar(75) NOT NULL COMMENT '编号类型',
  return_amount decimal(18,6) NOT NULL DEFAULT '0.000000' COMMENT '还款金额',
  loan_amount decimal(18,6) NOT NULL DEFAULT '0.000000' COMMENT '借款金额（快照）',
  repayer_id varchar(36) NOT NULL COMMENT '还款人ID',
  repayer_name varchar(75) NOT NULL COMMENT '还款人名称',
  repayment_date datetime NOT NULL COMMENT '还款日期',
  creator varchar(75) NOT NULL COMMENT '创建人',
  creator_id varchar(36) NOT NULL COMMENT '创建人ID',
  created datetime DEFAULT NULL COMMENT '创建时间',
  modifier varchar(75) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人ID',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  remark varchar(1500) DEFAULT NULL COMMENT '备注说明',
  balance_amount decimal(18,6) DEFAULT '0.000000' COMMENT '冲账金额（快照）',
  remain_amount decimal(18,6) DEFAULT '0.000000' COMMENT '冲账余额（快照）',
  loan_subject varchar(100) DEFAULT NULL COMMENT '领借款主题',
  loan_code varchar(100) DEFAULT NULL COMMENT '领借款编码',
  company_id varchar(36) DEFAULT NULL COMMENT '公司ID',
  company_name varchar(75) DEFAULT NULL COMMENT '公司名称',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='领借款直接还款';

-- ----------------------------
-- Table structure for ebs_matter
-- ----------------------------
DROP TABLE IF EXISTS ebs_matter;
CREATE TABLE ebs_matter (
  id varchar(36) NOT NULL COMMENT '立项标识',
  matter_subject varchar(150) NOT NULL COMMENT '立项主题',
  application_date datetime NOT NULL COMMENT '申请日期',
  matter_code varchar(75) NOT NULL COMMENT '立项编号',
  code_type varchar(75) NOT NULL COMMENT '编号类型',
  company_id varchar(36) NOT NULL COMMENT '公司ID',
  company_name varchar(75) NOT NULL COMMENT '公司名称',
  department varchar(75) NOT NULL COMMENT '申请部门',
  department_id varchar(36) NOT NULL COMMENT '申请部门ID',
  month varchar(25) NOT NULL COMMENT '所属月份（eg:2017-07）',
  proposer varchar(75) NOT NULL COMMENT '申请人',
  proposer_id varchar(36) NOT NULL COMMENT '申请人ID',
  matter_type varchar(75) NOT NULL COMMENT '立项类别',
  matter_type_id varchar(36) NOT NULL COMMENT '立项类别ID',
  cooperation_provider varchar(75) DEFAULT NULL COMMENT '合作单位',
  cooperation_provider_id varchar(36) DEFAULT NULL COMMENT '合作单位ID',
  matter_amount decimal(18,6) DEFAULT NULL COMMENT '立项金额',
  remark varchar(1500) DEFAULT NULL COMMENT '说明',
  business_personnel varchar(2000) DEFAULT NULL COMMENT '多个出差人员使用 ";" 分隔',
  business_personnel_id varchar(2000) DEFAULT NULL COMMENT '多个出差人员ID使用 ";" 分隔',
  business_reason varchar(500) DEFAULT NULL COMMENT '出差事由',
  start_end_location varchar(150) DEFAULT NULL COMMENT '起止地点',
  plan_start_date datetime DEFAULT NULL COMMENT '计划开始日期',
  plan_end_date datetime DEFAULT NULL COMMENT '计划结束日期',
  business_days int(11) DEFAULT NULL COMMENT '出差天数(=开始日期-结束日期+1)',
  creator varchar(75) DEFAULT NULL COMMENT '创建人',
  creator_id varchar(36) DEFAULT NULL COMMENT '创建人ID',
  created datetime DEFAULT NULL COMMENT '创建时间',
  modifier varchar(75) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人ID',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  approver varchar(75) DEFAULT NULL COMMENT '审核人',
  approver_id varchar(36) DEFAULT NULL COMMENT '审核人ID',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核、审核中、已审核）',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='立项';

-- ----------------------------
-- Table structure for ebs_matter_business
-- ----------------------------
DROP TABLE IF EXISTS ebs_matter_business;
CREATE TABLE ebs_matter_business (
  id varchar(36) NOT NULL COMMENT '标识',
  business_id varchar(36) NOT NULL COMMENT '业务ID',
  business_type varchar(75) NOT NULL COMMENT '业务类型（合同、合同变更、补充合同、报销）',
  matter_id varchar(36) NOT NULL COMMENT '立项ID',
  matter_name varchar(150) NOT NULL COMMENT '立项名称',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='立项关联';

-- ----------------------------
-- Table structure for ebs_matter_detail
-- ----------------------------
DROP TABLE IF EXISTS ebs_matter_detail;
CREATE TABLE ebs_matter_detail (
  id varchar(36) NOT NULL COMMENT '标识',
  matter_id varchar(36) NOT NULL COMMENT '立项ID',
  subject_id varchar(36) DEFAULT NULL COMMENT '预算科目ID',
  budget_owner_id varchar(36) DEFAULT NULL COMMENT '预算所有者ID',
  budget_owner_type varchar(75) DEFAULT NULL COMMENT '预算所有者类型（部门、项目）',
  used_amount decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额',
  happend_amount decimal(18,6) DEFAULT '0.000000' COMMENT '立项使用金额',
  budget_year int(11) DEFAULT NULL COMMENT '年份',
  budget_month int(11) DEFAULT NULL COMMENT '月份',
  budget_owner_name varchar(300) DEFAULT NULL COMMENT '预算所有者名称',
  subject_name varchar(200) DEFAULT NULL COMMENT '科目名称',
  sort bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='立项明细';

-- ----------------------------
-- Table structure for ebs_payment_voucher_budget_dtl
-- ----------------------------
DROP TABLE IF EXISTS ebs_payment_voucher_budget_dtl;
CREATE TABLE ebs_payment_voucher_budget_dtl (
  id varchar(36) CHARACTER SET utf8mb4 NOT NULL,
  payment_voucher_id varchar(36) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '付款登记ID',
  business_type varchar(75) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '业务类型（费用合同请款，立项报销，日常报销，领借款）',
  business_id varchar(36) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '业务id',
  budget_owner_id varchar(36) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '预算所有者ID',
  budget_owner_type varchar(75) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '预算所有者类型（部门、项目）',
  budget_owner_name varchar(300) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '预算所有者名称（费用预算：年度-公司-部门）',
  subject_id varchar(36) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '科目ID',
  subject_name varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '科目名称',
  subject_code varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '科目编码',
  used_amount decimal(18,6) DEFAULT '0.000000' COMMENT '本次分摊金额',
  budget_year int(11) DEFAULT NULL COMMENT '年份',
  budget_month int(11) DEFAULT NULL COMMENT '月份',
  approval_state varchar(75) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '审核状态（未审核、审核中、已审核）',
  sort bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实付款登记费用预算分摊明细';

-- ----------------------------
-- Table structure for ebs_project_budget_detail
-- ----------------------------
DROP TABLE IF EXISTS ebs_project_budget_detail;
CREATE TABLE ebs_project_budget_detail (
  id varchar(36) NOT NULL COMMENT '主键',
  subject_id varchar(36) NOT NULL COMMENT '科目ID',
  budget_project_id varchar(36) NOT NULL COMMENT '预算项目ID',
  plan_amount decimal(18,6) DEFAULT '0.000000' COMMENT '预算金额',
  matter_amount decimal(18,6) DEFAULT '0.000000' COMMENT '立项金额',
  contract_amount decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额',
  expense_amount decimal(18,6) DEFAULT '0.000000' COMMENT '报销金额',
  fact_amount decimal(18,6) DEFAULT '0.000000' COMMENT '应付金额',
  pay_amount decimal(18,6) DEFAULT '0.000000' COMMENT '实付金额',
  occur_amount decimal(18,6) DEFAULT '0.000000' COMMENT '发生金额',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='项目预算明细';

-- ----------------------------
-- Table structure for ebs_project_subject
-- ----------------------------
DROP TABLE IF EXISTS ebs_project_subject;
CREATE TABLE ebs_project_subject (
  id varchar(36) NOT NULL COMMENT '主键',
  template_id varchar(36) NOT NULL COMMENT '项目科目模板ID',
  subject_name varchar(75) NOT NULL COMMENT '科目名称',
  subject_code varchar(75) NOT NULL COMMENT '编号',
  short_code varchar(75) NOT NULL COMMENT '本级编号',
  parent_code varchar(75) NOT NULL COMMENT '父级编号',
  cms_subject_code varchar(75) DEFAULT NULL COMMENT '成本科目编号',
  remark varchar(500) DEFAULT NULL COMMENT '备注说明',
  modifier varchar(75) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人ID',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  creator varchar(75) NOT NULL COMMENT '创建人',
  creator_id varchar(36) NOT NULL COMMENT '创建人ID',
  created datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='项目科目';

-- ----------------------------
-- Table structure for ebs_project_subject_template
-- ----------------------------
DROP TABLE IF EXISTS ebs_project_subject_template;
CREATE TABLE ebs_project_subject_template (
  id varchar(36) NOT NULL COMMENT '主键',
  name varchar(75) NOT NULL COMMENT '模板名称',
  company_id varchar(36) NOT NULL COMMENT '公司ID',
  company_name varchar(75) NOT NULL COMMENT '公司名称',
  code varchar(75) NOT NULL COMMENT '模板编号',
  remark varchar(500) DEFAULT NULL COMMENT '备注说明',
  modifier varchar(75) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人ID',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  creator varchar(75) NOT NULL COMMENT '创建人',
  creator_id varchar(36) NOT NULL COMMENT '创建人ID',
  created datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='项目科目模板';

-- ----------------------------
-- Table structure for ebs_reallocation
-- ----------------------------
DROP TABLE IF EXISTS ebs_reallocation;
CREATE TABLE ebs_reallocation (
  id varchar(36) NOT NULL COMMENT '主键',
  subject varchar(150) NOT NULL COMMENT '费用分摊主题',
  proposer varchar(75) NOT NULL COMMENT '申请人',
  proposer_id varchar(36) NOT NULL COMMENT '申请人ID',
  department varchar(255) NOT NULL COMMENT '经办部门',
  department_id varchar(36) NOT NULL COMMENT '经办部门ID',
  company_id varchar(36) NOT NULL COMMENT '公司ID',
  company_name varchar(75) NOT NULL COMMENT '公司名称',
  code varchar(255) NOT NULL COMMENT '编号',
  code_type varchar(255) NOT NULL COMMENT '编号类型',
  modifier varchar(75) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人ID',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  creator varchar(75) NOT NULL COMMENT '创建人',
  creator_id varchar(36) NOT NULL COMMENT '创建人ID',
  created datetime NOT NULL COMMENT '创建时间',
  approver varchar(75) DEFAULT NULL COMMENT '审核人',
  approver_id varchar(36) DEFAULT NULL COMMENT '审核人ID',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核、审核中、已审核）',
  bill_id varchar(36) DEFAULT NULL COMMENT '单据ID',
  bill_name varchar(150) DEFAULT NULL COMMENT '单据名称',
  business_type varchar(75) DEFAULT NULL COMMENT '业务类型（费用合同、费用报销）',
  occur_amount decimal(18,6) DEFAULT NULL COMMENT '单据已发生金额',
  applied_amount decimal(18,6) DEFAULT NULL COMMENT '单据已申请金额',
  paid_amount decimal(18,6) DEFAULT NULL COMMENT '单据已支付金额',
  allcation_amount decimal(18,6) DEFAULT '0.000000' COMMENT '分摊金额',
  allocated_amount decimal(18,6) DEFAULT '0.000000' COMMENT '已分摊金额',
  application_date datetime NOT NULL COMMENT '申请日期',
  remark varchar(1500) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='重新分摊（二次分摊）';

-- ----------------------------
-- Table structure for ebs_reallocation_detail
-- ----------------------------
DROP TABLE IF EXISTS ebs_reallocation_detail;
CREATE TABLE ebs_reallocation_detail (
  id varchar(36) NOT NULL COMMENT '主键',
  business_id varchar(36) DEFAULT NULL COMMENT '业务ID',
  business_type varchar(75) DEFAULT NULL COMMENT '业务类型（二次分摊）',
  budget_owner_id varchar(36) DEFAULT NULL COMMENT '预算所有者ID',
  budget_owner_type varchar(75) DEFAULT NULL COMMENT '预算所有者类型（部门、项目）',
  subject_id varchar(36) DEFAULT NULL COMMENT '科目ID',
  contract_id varchar(36) DEFAULT NULL COMMENT '合同ID',
  allocation_amount decimal(18,6) DEFAULT NULL COMMENT '本次分摊金额',
  year int(11) DEFAULT NULL COMMENT '年份(审核时更新)',
  month int(11) DEFAULT NULL COMMENT '月份(审核时更新)',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='重新分摊明细';

-- ----------------------------
-- Table structure for ebs_year_subject
-- ----------------------------
DROP TABLE IF EXISTS ebs_year_subject;
CREATE TABLE ebs_year_subject (
  id varchar(36) NOT NULL COMMENT '主键',
  subject_name varchar(200) NOT NULL COMMENT '科目名',
  subject_code varchar(200) NOT NULL COMMENT '科目编码',
  short_code varchar(50) NOT NULL COMMENT '本级编码',
  parent_code varchar(100) NOT NULL COMMENT '父级编码',
  subject_kind varchar(75) NOT NULL COMMENT '科目类型（1,管理费用；2,财务费用；3,营销费用；0,其他）',
  subject_level tinyint(4) NOT NULL COMMENT '层级',
  budget_year int(11) NOT NULL COMMENT '年度',
  is_matter tinyint(1) DEFAULT '0' COMMENT '是否必须立项',
  budget_control_mode varchar(75) NOT NULL COMMENT '预算控制方式 （1,强控；2,预警；3,对照）',
  budget_control_node varchar(75) DEFAULT NULL COMMENT '预算控制节点（1,发生环节；2,付款环节；）多个值使用分号“;”分隔（eg:发生环节;付款环节）。',
  budget_control_cycle varchar(75) NOT NULL COMMENT '预算控制周期（1,月度；2,季度；3,半年；4,年度）',
  budget_balance_usage_mode varchar(75) NOT NULL COMMENT '预算余额使用方式（1,仅当期使用；2，滚动使用）',
  budget_input_mode varchar(75) NOT NULL COMMENT '预算编制方式（1,手动录入；2,智能计算；）',
  remark varchar(1500) DEFAULT NULL COMMENT '备注',
  creator varchar(36) NOT NULL COMMENT '创建人',
  creator_id varchar(36) NOT NULL COMMENT '创建人ID',
  created datetime NOT NULL COMMENT '创建时间',
  modifier varchar(36) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人ID',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  is_end tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否末级科目',
  is_company_modify tinyint(1) NOT NULL COMMENT '允许公司修改',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='费用科目';

-- ----------------------------
-- Table structure for ebs_year_subject_company
-- ----------------------------
DROP TABLE IF EXISTS ebs_year_subject_company;
CREATE TABLE ebs_year_subject_company (
  id varchar(36) NOT NULL COMMENT '主键',
  subject_id varchar(36) NOT NULL COMMENT '科目id',
  is_matter tinyint(1) NOT NULL COMMENT '是否立项',
  budget_control_mode varchar(75) NOT NULL COMMENT '预算控制方式（强控；预警；对照）',
  budget_control_node varchar(75) DEFAULT NULL COMMENT '预算控制节点（发生环节；付款环节）',
  budget_control_cycle varchar(75) NOT NULL COMMENT '预算控制周期（月；季度；半年；年）',
  budget_balance_usage_mode varchar(75) NOT NULL COMMENT '预算余额使用方式（仅档期使用；滚动使用）',
  budget_input_mode varchar(75) NOT NULL COMMENT '预算编制方式（手动录入；只能计算）',
  creator varchar(36) DEFAULT NULL COMMENT '创建人',
  creator_id varchar(36) DEFAULT NULL COMMENT '创建人id',
  created datetime DEFAULT NULL COMMENT '创建时间',
  modifier varchar(36) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人id',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  company_id varchar(36) NOT NULL COMMENT '公司id',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='公司年度科目表';

-- ----------------------------
-- Table structure for ebs_year_subject_mapping
-- ----------------------------
DROP TABLE IF EXISTS ebs_year_subject_mapping;
CREATE TABLE ebs_year_subject_mapping (
  id varchar(36) NOT NULL COMMENT '主键',
  current_subject_id varchar(36) NOT NULL COMMENT '本年科目ID',
  prev_subject_id varchar(36) NOT NULL COMMENT '上年科目ID',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='年度科目对照信息';

SET FOREIGN_KEY_CHECKS = 1;
