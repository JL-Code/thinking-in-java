/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.32-root2
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 192.168.1.32:3308
 Source Schema         : ecs

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 04/12/2021 14:20:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pay_invoice
-- ----------------------------
DROP TABLE IF EXISTS pay_invoice;
CREATE TABLE pay_invoice (
  id varchar(36) NOT NULL COMMENT '票据标识',
  invoice_type varchar(75) NOT NULL COMMENT '票据类型',
  invoice_type_id varchar(36) NOT NULL COMMENT '票据类型ID',
  invoice_no varchar(75) NOT NULL COMMENT '票据编号(唯一)',
  business_id varchar(36) NOT NULL COMMENT '业务ID',
  business_type varchar(75) NOT NULL COMMENT '业务类型（合同付款申请、合同进度产值、合同结算、合同登记、日常报销、立项报销）',
  operator varchar(75) DEFAULT NULL COMMENT '申请人',
  operator_id varchar(36) DEFAULT NULL COMMENT '申请人id',
  department_name varchar(75) DEFAULT NULL COMMENT '申请部门',
  department_id varchar(36) DEFAULT NULL COMMENT '申请部门id',
  invoice_date datetime DEFAULT NULL COMMENT '开票日期',
  supplier varchar(75) NOT NULL COMMENT '供方单位',
  supplier_id varchar(36) NOT NULL COMMENT '供方单位ID',
  purchaser varchar(75) DEFAULT NULL COMMENT '购方单位',
  purchaser_id varchar(36) DEFAULT NULL COMMENT '购方单位ID',
  ticket_amount decimal(18,6) DEFAULT NULL COMMENT '票价(A1,可抵扣)',
  additional_expense_one decimal(18,6) DEFAULT NULL COMMENT '附加费用一（A2,可抵扣）',
  additional_expense_two decimal(18,6) DEFAULT NULL COMMENT '附加费用二（A3,不可抵扣）',
  additional_expense_three decimal(18,6) DEFAULT NULL COMMENT '附加费用三（A4,不可抵扣）',
  total_amount decimal(18,6) NOT NULL COMMENT '价税合计(A=A1+A2+A3+A4)',
  tax_rate decimal(18,6) NOT NULL COMMENT '税率',
  no_tax_amount decimal(18,6) DEFAULT NULL COMMENT '不含税金额',
  tax_amount decimal(18,6) DEFAULT NULL COMMENT '税额',
  remark varchar(500) DEFAULT NULL COMMENT '说明',
  is_deleted tinyint(1) DEFAULT '0',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='票据';

-- ----------------------------
-- Table structure for pay_payment_voucher
-- ----------------------------
DROP TABLE IF EXISTS pay_payment_voucher;
CREATE TABLE pay_payment_voucher (
  id varchar(36) NOT NULL,
  business_type varchar(75) DEFAULT NULL COMMENT '申请类型（代付代扣，成本付款申请，费用付款申请，无合同付款申请，保证金退款，报销，领借款）',
  business_id varchar(36) DEFAULT NULL COMMENT '申请业务ID',
  department_name varchar(75) DEFAULT NULL COMMENT '申请部门（业务单据的申请部门）',
  department_id varchar(36) DEFAULT NULL COMMENT '申请部门ID',
  company_id varchar(36) DEFAULT NULL COMMENT '申请部门所属公司ID',
  apply_code varchar(150) DEFAULT NULL COMMENT '申请编码',
  apply_subject varchar(300) DEFAULT NULL COMMENT '申请主题',
  operator varchar(75) DEFAULT NULL COMMENT '实付经办人',
  operator_id varchar(36) DEFAULT NULL COMMENT '实付经办人ID',
  operation_date datetime DEFAULT NULL COMMENT '实付经办时间',
  pay_unit_id varchar(36) DEFAULT NULL COMMENT '付款单位ID',
  pay_unit_name varchar(75) DEFAULT NULL COMMENT '付款单位',
  receive_unit_id varchar(36) DEFAULT NULL COMMENT '收款单位ID',
  receive_unit_name varchar(75) DEFAULT NULL COMMENT '收款单位',
  receive_bank_name varchar(75) DEFAULT NULL COMMENT '收款银行',
  receive_bank_account varchar(75) DEFAULT NULL COMMENT '收款账号',
  pay_amount decimal(18,6) DEFAULT NULL COMMENT '实付款金额',
  not_payment_reason varchar(300) DEFAULT NULL COMMENT '未完成付款原因',
  creator varchar(36) NOT NULL COMMENT '新增人',
  creator_id varchar(36) NOT NULL COMMENT '新增人id',
  created datetime NOT NULL COMMENT '新增时间',
  modifier varchar(36) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人id',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核；审核中；已审核）',
  approver_id varchar(36) DEFAULT NULL COMMENT '审核人ID',
  approver varchar(75) DEFAULT NULL COMMENT '审核人',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  is_deleted tinyint(3) unsigned DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='实付款登记';

-- ----------------------------
-- Table structure for pay_payment_voucher_detail
-- ----------------------------
DROP TABLE IF EXISTS pay_payment_voucher_detail;
CREATE TABLE pay_payment_voucher_detail (
  id varchar(36) NOT NULL,
  payment_voucher_id varchar(36) DEFAULT NULL COMMENT '付款登记主表ID',
  sort bigint(20) DEFAULT NULL COMMENT '排序字段',
  pay_mode varchar(75) DEFAULT NULL COMMENT '付款类型',
  pay_amount decimal(18,6) DEFAULT NULL COMMENT '付款金额',
  pay_date datetime DEFAULT NULL COMMENT '付款日期',
  remark varchar(500) DEFAULT NULL COMMENT '付款说明',
  offset_type varchar(75) DEFAULT NULL COMMENT '冲抵类型（领借款、代付代扣、合同奖罚）',
  offset_id varchar(36) DEFAULT NULL COMMENT '冲抵业务单据ID',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='付款登记明细';

SET FOREIGN_KEY_CHECKS = 1;
