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

 Date: 04/12/2021 15:03:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_contract
-- ----------------------------
DROP TABLE IF EXISTS cms_contract;
CREATE TABLE cms_contract (
  id varchar(36) NOT NULL,
  contract_name varchar(300) DEFAULT NULL COMMENT '合同名称',
  contract_code varchar(1000) DEFAULT NULL COMMENT '合同编码',
  code_type varchar(150) DEFAULT NULL COMMENT '编码规则',
  custom_code varchar(150) DEFAULT NULL COMMENT '自定义编码',
  contract_property varchar(10) DEFAULT NULL COMMENT '合同属性(成本、费用)',
  operator varchar(75) DEFAULT NULL COMMENT '经办人',
  operator_id varchar(36) DEFAULT NULL COMMENT '经办人ID',
  department_id varchar(36) DEFAULT NULL COMMENT '经办部门ID',
  department_name varchar(75) DEFAULT NULL COMMENT '经办部门',
  company_id varchar(36) DEFAULT NULL COMMENT '经办部门所属公司ID',
  contract_type_id varchar(36) DEFAULT NULL COMMENT '合同类别ID',
  contract_type_name varchar(150) DEFAULT NULL COMMENT '合同类别',
  own_unit_id varchar(36) DEFAULT NULL COMMENT '我当单位ID',
  own_unit_name varchar(75) DEFAULT NULL COMMENT '我方单位',
  own_unit_siger varchar(75) DEFAULT NULL COMMENT '我方单位-签字人',
  contract_party varchar(10) DEFAULT NULL COMMENT '我方单位合同方(甲方、乙方)',
  other_unit_name varchar(1000) DEFAULT NULL COMMENT '对方单位名称(多个对方单位字符拼接)',
  sign_date datetime DEFAULT NULL COMMENT '签约日期',
  total_amount decimal(18,6) DEFAULT '0.000000' COMMENT '合同金额',
  debar_amount decimal(18,6) DEFAULT '0.000000' COMMENT '不计成本金额',
  contract_amount decimal(18,6) DEFAULT '0.000000' COMMENT '有效签约金额',
  contract_tax_amount decimal(18,6) DEFAULT '0.000000' COMMENT '合同税额',
  contract_no_tax_amount decimal(18,6) DEFAULT '0.000000' COMMENT '不含税合同金额',
  provisional_amount decimal(18,6) DEFAULT '0.000000' COMMENT '暂列金额',
  provisional_remain_amount decimal(18,6) DEFAULT '0.000000' COMMENT '暂列金余额',
  confirm_type varchar(75) DEFAULT NULL COMMENT '形成方式',
  cost_property varchar(75) DEFAULT NULL COMMENT '造价性质',
  is_foreign_currency tinyint(4) DEFAULT NULL COMMENT '是否外币合同',
  currency_type varchar(50) DEFAULT NULL COMMENT '币种',
  currency_amount decimal(18,6) DEFAULT '0.000000' COMMENT '原货币金额',
  exchange_rate decimal(18,6) DEFAULT '0.000000' COMMENT '汇率',
  project_id varchar(500) DEFAULT NULL COMMENT '所属项目ID（多个，由分隔符隔开）',
  project_name varchar(500) DEFAULT NULL COMMENT '所属项目（多个，由分隔符隔开）',
  contract_scope text COMMENT '合同范围',
  payment_agreement text COMMENT '付款范围',
  is_use_contingency_cost tinyint(1) DEFAULT NULL COMMENT '是否使用不可预见费',
  creator varchar(36) NOT NULL COMMENT '新增人',
  creator_id varchar(36) DEFAULT NULL COMMENT '新增人id',
  created datetime DEFAULT NULL COMMENT '新增时间',
  modifier varchar(36) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人id',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核；审核中；已审核）',
  approver_id varchar(36) DEFAULT NULL COMMENT '审核人ID',
  approver varchar(75) DEFAULT NULL COMMENT '审核人',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  settlement_state varchar(75) DEFAULT NULL COMMENT '结算状态（未结算；已结算）',
  contract_alter_amount decimal(18,6) DEFAULT '0.000000' COMMENT '合同变更金额=设计变更+工程签证',
  supplement_contract_amount decimal(18,6) DEFAULT '0.000000' COMMENT '补充合同金额',
  settlement_adjust_amount decimal(18,6) DEFAULT '0.000000' COMMENT '结算调整金额',
  sum_report_value_amount decimal(18,6) DEFAULT '0.000000' COMMENT '累计审定产值金额',
  sum_schedule_amount decimal(18,6) DEFAULT '0.000000' COMMENT '累计应付进度款',
  sum_apply_amount decimal(18,6) DEFAULT '0.000000' COMMENT '累计申请金额',
  sum_paid_amount decimal(18,6) DEFAULT '0.000000' COMMENT '累计支付金额',
  is_deleted tinyint(1) NOT NULL COMMENT '是否删除',
  approve_price_amount decimal(18,6) DEFAULT '0.000000' COMMENT '合同批价金额',
  design_alter_amount decimal(18,6) DEFAULT '0.000000' COMMENT '设计变更金额',
  no_design_alter_amount decimal(18,6) DEFAULT '0.000000' COMMENT '工程签证金额（除设计变更外的狭义变更金额',
  cont_settlement_alter_amount decimal(18,6) DEFAULT '0.000000' COMMENT '合同+已结算变更金额',
  predict_settlement_amount decimal(18,6) DEFAULT '0.000000' COMMENT '预计结算金额',
  contract_dynamic_amount decimal(18,6) DEFAULT '0.000000' COMMENT '合同动态金额',
  is_matter tinyint(4) DEFAULT NULL COMMENT '是否立项',
  cost_effect_type varchar(10) DEFAULT NULL COMMENT '费效分摊（单次合同、按月分摊）',
  cost_effect_start_date date DEFAULT NULL COMMENT '费效分摊开始日期',
  cost_effect_month tinyint(4) DEFAULT NULL COMMENT '费效分摊周期（月）',
  is_enable_provisional_amount tinyint(1) DEFAULT '0' COMMENT '是否启用暂列金管理',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='成本合同主表';

-- ----------------------------
-- Table structure for cms_contract_alter
-- ----------------------------
DROP TABLE IF EXISTS cms_contract_alter;
CREATE TABLE cms_contract_alter (
  id varchar(36) NOT NULL,
  alter_name varchar(300) DEFAULT NULL COMMENT '变更名称',
  alter_code varchar(1000) DEFAULT NULL COMMENT '变更编码',
  code_type varchar(150) DEFAULT NULL COMMENT '编码规则',
  customer_code varchar(150) DEFAULT NULL COMMENT '自定义编码',
  is_design_alter tinyint(1) DEFAULT NULL COMMENT '是否设计变更',
  alter_type varchar(75) DEFAULT NULL COMMENT '变更类型',
  alter_reason varchar(75) DEFAULT NULL COMMENT '变更原因',
  alter_reason_id varchar(36) DEFAULT NULL COMMENT '变更原因ID（业务类别项ID）',
  operator varchar(255) DEFAULT NULL COMMENT '经办人',
  operator_id varchar(36) DEFAULT NULL COMMENT '经办人ID',
  department_name varchar(75) DEFAULT NULL COMMENT '经办部门',
  department_id varchar(36) DEFAULT NULL COMMENT '经办部门ID',
  company_id varchar(75) DEFAULT NULL COMMENT '经办部门所属公司ID',
  involve_specialty varchar(300) DEFAULT NULL COMMENT '涉及专业',
  alter_level varchar(75) DEFAULT NULL COMMENT '变更登记',
  propose_stage varchar(75) DEFAULT NULL COMMENT '提出阶段',
  alter_proposer varchar(75) DEFAULT NULL COMMENT '变更提出方',
  is_optimized tinyint(1) DEFAULT NULL COMMENT '是否设计优化',
  is_ineffective_cost tinyint(1) DEFAULT NULL COMMENT '是否无效成本',
  propose_date datetime DEFAULT NULL COMMENT '提出日期',
  predict_complete_date datetime DEFAULT NULL COMMENT '预计完工日期',
  regist_date datetime DEFAULT NULL COMMENT '登记日期',
  remark varchar(4000) DEFAULT NULL,
  alter_amount decimal(18,6) DEFAULT NULL COMMENT '变更审定金额(A)',
  alter_apply_amount decimal(18,6) DEFAULT NULL COMMENT '变更申报金额',
  tax_rate decimal(18,6) DEFAULT NULL COMMENT '进项税率',
  alter_no_tax_amount decimal(18,6) DEFAULT NULL COMMENT '不含税变更金额',
  alter_tax_amount decimal(18,6) DEFAULT NULL COMMENT '进项税额',
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
  is_deleted tinyint(1) NOT NULL COMMENT '是否删除',
  is_use_contingency_cost tinyint(1) DEFAULT NULL COMMENT '是否使用不可预见费',
  is_enable_provisional_amount tinyint(1) DEFAULT '0' COMMENT '是否启用暂列金管理',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='合同变更表';

-- ----------------------------
-- Table structure for cms_contract_pay_apply
-- ----------------------------
DROP TABLE IF EXISTS cms_contract_pay_apply;
CREATE TABLE cms_contract_pay_apply (
  id varchar(36) NOT NULL,
  contract_id varchar(36) DEFAULT NULL COMMENT '合同id',
  company_id varchar(36) DEFAULT NULL COMMENT '公司id',
  plan_pay_date date NOT NULL COMMENT '计划付款日期',
  pay_apply_date date DEFAULT NULL COMMENT '付款申请日期',
  pay_apply_name varchar(256) DEFAULT NULL COMMENT '申请主题',
  pay_apply_code varchar(1000) DEFAULT NULL COMMENT '合同请款申请编码',
  code_type varchar(50) DEFAULT NULL COMMENT '编号类型',
  operator varchar(75) NOT NULL COMMENT '申报人',
  operator_id varchar(36) NOT NULL COMMENT '申报人ID',
  department_id varchar(36) DEFAULT NULL COMMENT '关联申请部门id',
  department_name varchar(50) DEFAULT NULL COMMENT '申请人部门',
  pay_unit_id varchar(36) DEFAULT NULL COMMENT '付款单位id',
  pay_unit_name varchar(256) DEFAULT NULL COMMENT '付款单位名称',
  receive_unit_id varchar(36) DEFAULT NULL COMMENT '收款单位id',
  receive_unit_name varchar(256) DEFAULT NULL COMMENT '收款单位名称',
  fund_type varchar(36) DEFAULT NULL COMMENT '款项类型',
  fund_name varchar(36) DEFAULT NULL COMMENT '款项名称',
  pay_apply_type_id varchar(36) DEFAULT NULL COMMENT '付款审批类型id',
  pay_apply_type varchar(36) DEFAULT NULL COMMENT '付款审批类型',
  receive_bank_name varchar(256) DEFAULT NULL COMMENT '收款银行',
  receive_bank_account varchar(30) DEFAULT NULL COMMENT '收款账号',
  pay_apply_amount decimal(18,6) DEFAULT NULL COMMENT '申请金额',
  tax_rate decimal(6,6) DEFAULT NULL COMMENT '税率',
  pay_apply_no_tax_amount decimal(18,6) DEFAULT NULL COMMENT '不含税申请金额',
  pay_apply_tax_amount decimal(18,6) DEFAULT NULL COMMENT '付款申请税额',
  balance_amount decimal(18,6) DEFAULT NULL COMMENT '冲账金额',
  repay_amount decimal(18,6) DEFAULT NULL COMMENT '代付代扣金额',
  pay_amount decimal(18,6) DEFAULT NULL COMMENT '应付金额（申请金额-冲账金额-代付金额）',
  pay_apply_remark varchar(6000) DEFAULT NULL COMMENT '付款说明',
  pay_state varchar(36) DEFAULT '未支付' COMMENT '默认为未支付',
  paid_amount decimal(18,6) DEFAULT '0.000000' COMMENT '付款金额',
  contract_alter_amount decimal(18,6) DEFAULT NULL COMMENT '签约+变更金额',
  sum_contract_value_amount decimal(18,6) DEFAULT NULL COMMENT '累计审定产值',
  sum_progress_amount decimal(18,6) DEFAULT NULL COMMENT '累计应付进度款',
  cont_settlement_alter_amount decimal(18,6) DEFAULT NULL COMMENT '合同+已结算变更金额',
  sum_apply_amount decimal(18,6) DEFAULT NULL COMMENT '累计已申请金额',
  sum_paid_amount decimal(18,6) DEFAULT NULL COMMENT '累计实付金额',
  creator varchar(36) DEFAULT NULL COMMENT '创建人',
  creator_id varchar(36) DEFAULT NULL COMMENT '创建人id',
  created datetime DEFAULT NULL COMMENT '创建时间',
  modifier varchar(36) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人ID',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核、审核中、已审核）',
  approval_id varchar(36) DEFAULT NULL COMMENT '审核人ID',
  approver varchar(75) DEFAULT NULL COMMENT '审核人',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  is_deleted tinyint(1) DEFAULT '0' COMMENT '是否删除',
  award_punish_amount decimal(18,6) DEFAULT NULL COMMENT '奖罚金额',
  contract_dynamic_amount decimal(18,6) DEFAULT '0.000000' COMMENT '合同动态金额',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='合同付款申请';

-- ----------------------------
-- Table structure for cms_contract_settlement
-- ----------------------------
DROP TABLE IF EXISTS cms_contract_settlement;
CREATE TABLE cms_contract_settlement (
  id varchar(36) NOT NULL,
  settlement_name varchar(150) DEFAULT NULL COMMENT '结算主题',
  settlement_number int(10) unsigned zerofill DEFAULT NULL COMMENT '结算次数',
  settlement_type varchar(75) DEFAULT NULL COMMENT '结算类型（1,阶段结算；2,最终结算）',
  settlement_code varchar(1000) DEFAULT NULL COMMENT '结算编号',
  code_type varchar(150) DEFAULT NULL COMMENT '编码规则',
  contract_id varchar(36) DEFAULT NULL COMMENT '合同ID',
  settlement_date datetime DEFAULT NULL COMMENT '结算日期',
  operator varchar(75) DEFAULT NULL COMMENT '经办人',
  operator_id varchar(36) DEFAULT NULL COMMENT '经办人ID',
  department_name varchar(75) DEFAULT NULL COMMENT '经办部门',
  department_id varchar(36) DEFAULT NULL COMMENT '经办部门ID',
  company_id varchar(36) DEFAULT NULL COMMENT '经办部门所属公司ID',
  settlement_amount decimal(18,6) DEFAULT NULL COMMENT '结算金额（C）',
  settlement_adjust_amount decimal(18,6) DEFAULT NULL COMMENT '结算调整金额（D=C-(A+B)）',
  out_approve_amount decimal(18,6) DEFAULT NULL COMMENT '外围评审金额',
  tax_rate decimal(18,6) DEFAULT NULL COMMENT '进项税率',
  settlement_no_tax_amount decimal(18,6) DEFAULT NULL COMMENT '结算金额（不含税）',
  settlement_tax_amount decimal(18,6) DEFAULT NULL COMMENT '进项税额',
  warranty_amount decimal(18,6) DEFAULT NULL COMMENT '保修金额（E）',
  warranty_rate decimal(18,6) DEFAULT NULL COMMENT '保修比例（E/C*100）',
  contract_amount decimal(18,6) DEFAULT NULL COMMENT '有效签约金额（A）',
  sum_alter_amount decimal(18,6) DEFAULT NULL COMMENT '广义变更小计（B）',
  warranty_date date DEFAULT NULL COMMENT '质保到期日期',
  is_use_contingency_cost tinyint(1) NOT NULL COMMENT '是否使用不可预见费',
  creator varchar(36) NOT NULL COMMENT '新增人',
  creator_id varchar(36) NOT NULL COMMENT '新增人id',
  created datetime NOT NULL COMMENT '新增时间',
  modifier varchar(36) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人id',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核；审核中；已审核）',
  approver_id varchar(36) DEFAULT NULL COMMENT '审核人ID',
  approver varchar(75) DEFAULT NULL COMMENT '审核人',
  approve_start_date datetime DEFAULT NULL COMMENT '结算提交日期',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  is_deleted tinyint(1) NOT NULL COMMENT '是否删除',
  remark varchar(500) DEFAULT NULL COMMENT '结算说明',
  predict_settlement_amount decimal(18,6) DEFAULT '0.000000' COMMENT '预计结算金额',
  contract_dynamic_amount decimal(18,6) DEFAULT '0.000000' COMMENT '合同动态金额',
  is_final_settlement tinyint(1) DEFAULT '0' COMMENT '是否最后一次最终结算',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='合同结算';

-- ----------------------------
-- Table structure for cms_contract_to_provider
-- ----------------------------
DROP TABLE IF EXISTS cms_contract_to_provider;
CREATE TABLE cms_contract_to_provider (
  id varchar(36) NOT NULL,
  contract_id varchar(36) DEFAULT NULL COMMENT '合同ID（合同id和无合同id）',
  contract_name varchar(300) DEFAULT NULL COMMENT '合同名称',
  contract_class varchar(75) DEFAULT NULL COMMENT '合同类型(合同or补充合同)',
  provider_id varchar(36) DEFAULT NULL COMMENT '供应商ID',
  provider_name varchar(200) DEFAULT NULL COMMENT '供应商名称',
  contract_party varchar(10) DEFAULT NULL COMMENT '合同方(乙方、丙方、丁方等)',
  invoice_type varchar(75) DEFAULT NULL COMMENT '票据类型',
  contract_amount decimal(18,6) DEFAULT '0.000000' COMMENT '签约金额',
  contract_no_tax_amount decimal(18,6) DEFAULT '0.000000' COMMENT '除税金额',
  tax_rate decimal(18,6) DEFAULT '0.000000' COMMENT '税率',
  contract_tax_amount decimal(18,6) DEFAULT '0.000000' COMMENT '进项税额',
  signer varchar(75) DEFAULT NULL COMMENT '签字人',
  is_tax_deduction tinyint(1) DEFAULT '0' COMMENT '是否进项抵扣',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='合同供应商关联表';

-- ----------------------------
-- Table structure for cms_contract_user_relation
-- ----------------------------
DROP TABLE IF EXISTS cms_contract_user_relation;
CREATE TABLE cms_contract_user_relation (
  id varchar(36) NOT NULL,
  user_id varchar(36) DEFAULT NULL COMMENT '用户id',
  contract_id varchar(36) DEFAULT NULL COMMENT '合同id',
  is_delete tinyint(1) DEFAULT NULL COMMENT '是否删除',
  modified datetime DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户合同收藏表';

-- ----------------------------
-- Table structure for cms_contract_value
-- ----------------------------
DROP TABLE IF EXISTS cms_contract_value;
CREATE TABLE cms_contract_value (
  id varchar(36) NOT NULL COMMENT '进度产值标识',
  value_name varchar(400) DEFAULT NULL COMMENT '进度产值主题',
  value_code varchar(1000) DEFAULT NULL COMMENT '变更编码',
  code_type varchar(150) NOT NULL COMMENT '编码规则',
  operator varchar(75) NOT NULL COMMENT '申报人',
  operator_id varchar(36) NOT NULL COMMENT '申报人ID',
  contract_id varchar(36) NOT NULL COMMENT '合同ID',
  company_id varchar(36) NOT NULL COMMENT '公司ID',
  department_name varchar(75) NOT NULL COMMENT '申报部门',
  department_id varchar(36) NOT NULL COMMENT '申报部门ID',
  apply_date datetime NOT NULL COMMENT '申报日期',
  apply_unit_id varchar(36) NOT NULL COMMENT '申报单位ID',
  apply_unit_name varchar(75) NOT NULL COMMENT '申报单位名称',
  report_value_amount decimal(18,6) NOT NULL COMMENT '本次上报产值',
  contract_value_amount decimal(18,6) NOT NULL COMMENT '本次审定产值',
  contract_value_no_tax_amount decimal(18,6) NOT NULL COMMENT '本次审定产值不含税',
  report_value_rate decimal(18,6) DEFAULT NULL COMMENT '本次上报比例',
  tax_rate decimal(18,6) DEFAULT NULL COMMENT '进项税率',
  contract_value_tax_amount decimal(18,6) DEFAULT NULL COMMENT '进项税额',
  contract_alter_amount decimal(18,6) DEFAULT NULL COMMENT '签约+广义变更（A）',
  cont_settlement_alter_amount decimal(18,6) DEFAULT NULL COMMENT '签约+已结算变更金额',
  sum_progress_amount decimal(18,6) DEFAULT NULL COMMENT '累计应付进度款含税（F）',
  sum_apply_pay_amount decimal(18,6) DEFAULT NULL COMMENT '累计申请付款金额（G）',
  sum_paid_amount decimal(18,6) DEFAULT NULL COMMENT '累计实付金额（H）',
  sum_contract_value_amount decimal(18,6) DEFAULT NULL COMMENT '累计审定产值含税（C）',
  progress_amount decimal(18,6) DEFAULT NULL COMMENT '本次应付进度款',
  progress_no_tax_amount decimal(18,6) DEFAULT NULL COMMENT '本次应付进度款不含税',
  progress_rate decimal(18,6) DEFAULT NULL COMMENT '本次应付进度款比例',
  after_sum_value_amount decimal(18,6) DEFAULT NULL COMMENT '本次后累计审定产值含税（B+G）',
  value_custom_code varchar(75) DEFAULT NULL COMMENT '实际产值单号',
  performance_remark varchar(2000) DEFAULT NULL COMMENT '履约情况',
  approver varchar(75) DEFAULT NULL COMMENT '审核人',
  approver_id varchar(36) DEFAULT NULL COMMENT '审核人ID',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核、审核中、已审核）',
  modifier varchar(75) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人ID',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  creator varchar(36) NOT NULL COMMENT '新增人',
  creator_id varchar(36) NOT NULL COMMENT '新增人id',
  created datetime NOT NULL COMMENT '新增时间',
  is_deleted tinyint(1) NOT NULL COMMENT '是否删除',
  contract_dynamic_amount decimal(18,6) DEFAULT '0.000000' COMMENT '合同动态金额',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='合同进度产值';

-- ----------------------------
-- Table structure for cms_supplement_contract
-- ----------------------------
DROP TABLE IF EXISTS cms_supplement_contract;
CREATE TABLE cms_supplement_contract (
  id varchar(36) NOT NULL,
  contract_id varchar(36) DEFAULT NULL COMMENT '合同ID',
  supplement_contract_name varchar(300) DEFAULT NULL COMMENT '主题',
  supplement_code varchar(1000) DEFAULT NULL COMMENT '补充合同编码',
  is_use_contingency_cost tinyint(1) DEFAULT NULL COMMENT '是否使用不可预见费',
  supplement_custom_code varchar(150) DEFAULT NULL COMMENT '自定义编号',
  code_type varchar(150) DEFAULT NULL COMMENT '编码规则',
  supplement_type varchar(75) DEFAULT NULL COMMENT '调整类型',
  supplement_reason varchar(75) DEFAULT NULL COMMENT '调整原因',
  sign_date date DEFAULT NULL COMMENT '经办日期',
  operator varchar(75) DEFAULT NULL COMMENT '经办人',
  operator_id varchar(36) DEFAULT NULL COMMENT '经办人ID',
  department_name varchar(75) DEFAULT NULL COMMENT '经办部门名称',
  department_id varchar(36) DEFAULT NULL COMMENT '经办部门ID',
  company_id varchar(36) DEFAULT NULL COMMENT '经办部门所属公司ID',
  remark varchar(1500) DEFAULT NULL COMMENT '调整范围说明',
  supplement_total_amount decimal(18,6) DEFAULT '0.000000' COMMENT '补充合同金额（A）',
  supplement_amount decimal(18,6) DEFAULT '0.000000' COMMENT '调整金额（B=A-C）',
  supplement_alter_amount decimal(18,6) DEFAULT '0.000000' COMMENT '已变更金额（C）',
  tax_rate decimal(18,6) DEFAULT NULL COMMENT '进项税率',
  supplement_tax_amount decimal(18,6) DEFAULT '0.000000' COMMENT '进项税额',
  supplement_no_tax_amount decimal(18,6) DEFAULT '0.000000' COMMENT '不含税补充合同金额',
  sum_supplement_amount decimal(18,6) DEFAULT '0.000000' COMMENT '累计补充合同金额',
  sum_alter_amount decimal(18,6) DEFAULT '0.000000' COMMENT '累计广义变更金额',
  creator varchar(36) NOT NULL COMMENT '新增人',
  creator_id varchar(36) NOT NULL COMMENT '新增人id',
  created datetime NOT NULL COMMENT '新增时间',
  modifier varchar(36) DEFAULT NULL COMMENT '修改人',
  modifier_id varchar(36) DEFAULT NULL COMMENT '修改人id',
  approved datetime DEFAULT NULL COMMENT '审核时间',
  approval_state varchar(75) DEFAULT NULL COMMENT '审核状态（未审核；审核中；已审核）',
  approver_id varchar(36) DEFAULT NULL COMMENT '审核人ID',
  approver varchar(75) DEFAULT NULL COMMENT '审核人',
  modified datetime DEFAULT NULL COMMENT '修改时间',
  is_deleted tinyint(1) NOT NULL COMMENT '是否删除',
  is_matter tinyint(4) DEFAULT NULL COMMENT '是否立项',
  cost_effect_type varchar(10) DEFAULT NULL COMMENT '费效分摊（单次合同、按月分摊）',
  cost_effect_start_date date DEFAULT NULL COMMENT '费效分摊开始日期',
  cost_effect_month tinyint(1) DEFAULT NULL COMMENT '费效分摊周期（月）',
  sum_supplement_contract_amount decimal(18,6) DEFAULT '0.000000' COMMENT '累计补充合同金额',
  contract_dynamic_amount decimal(18,6) DEFAULT '0.000000' COMMENT '合同动态金额',
  PRIMARY KEY (id) USING BTREE,
  KEY supplement_contract_index (contract_id,approval_state)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='补充合同主表';

SET FOREIGN_KEY_CHECKS = 1;
