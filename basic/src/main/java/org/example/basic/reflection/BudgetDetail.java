package org.example.basic.reflection;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 预算明细表(BudgetDetail)实体类
 *
 * @author 蒋勇
 * @since 2021-06-22 09:16:54
 */
@Data
public class BudgetDetail implements Serializable {
    public String className = "BudgetDetail";
    private static final long serialVersionUID = 718930420730091325L;
    private String id;
    /**
     * 预算主表ID
     */
    private String budgetId;
    /**
     * 预算科目ID
     */
    private String subjectId;
    /**
     * 年度
     */
    private Integer year;
    /**
     * 预算1月
     */

    private BigDecimal planAmount01;
    /**
     * 预算2月
     */

    private BigDecimal planAmount02;
    /**
     * 预算3月
     */

    private BigDecimal planAmount03;
    /**
     * 预算4月
     */

    private BigDecimal planAmount04;
    /**
     * 预算5月
     */

    private BigDecimal planAmount05;
    /**
     * 预算6月
     */

    private BigDecimal planAmount06;
    /**
     * 预算7月
     */

    private BigDecimal planAmount07;
    /**
     * 预算8月
     */

    private BigDecimal planAmount08;
    /**
     * 预算9月
     */

    private BigDecimal planAmount09;
    /**
     * 预算10月
     */

    private BigDecimal planAmount10;
    /**
     * 预算11月
     */

    private BigDecimal planAmount11;
    /**
     * 预算12月
     */

    private BigDecimal planAmount12;
    /**
     * 预算调整1月
     */

    private BigDecimal adjustAmount01;
    /**
     * 预算调整2月
     */

    private BigDecimal adjustAmount02;
    /**
     * 预算调整3月
     */

    private BigDecimal adjustAmount03;
    /**
     * 预算调整4月
     */

    private BigDecimal adjustAmount04;
    /**
     * 预算调整5月
     */

    private BigDecimal adjustAmount05;
    /**
     * 预算调整6月
     */

    private BigDecimal adjustAmount06;
    /**
     * 预算调整7月
     */

    private BigDecimal adjustAmount07;
    /**
     * 预算调整8月
     */

    private BigDecimal adjustAmount08;
    /**
     * 预算调整9月
     */

    private BigDecimal adjustAmount09;
    /**
     * 预算调整10月
     */

    private BigDecimal adjustAmount10;
    /**
     * 预算调整11月
     */

    private BigDecimal adjustAmount11;
    /**
     * 预算调整12月
     */

    private BigDecimal adjustAmount12;
    /**
     * 立项金额1月
     */

    private BigDecimal matterAmount01;
    /**
     * 立项金额2月
     */

    private BigDecimal matterAmount02;
    /**
     * 立项金额3月
     */

    private BigDecimal matterAmount03;
    /**
     * 立项金额4月
     */

    private BigDecimal matterAmount04;
    /**
     * 立项金额5月
     */

    private BigDecimal matterAmount05;
    /**
     * 立项金额6月
     */

    private BigDecimal matterAmount06;
    /**
     * 立项金额7月
     */

    private BigDecimal matterAmount07;
    /**
     * 立项金额8月
     */

    private BigDecimal matterAmount08;
    /**
     * 立项金额9月
     */

    private BigDecimal matterAmount09;
    /**
     * 立项金额10月
     */

    private BigDecimal matterAmount10;
    /**
     * 立项金额11月
     */

    private BigDecimal matterAmount11;
    /**
     * 立项金额12月
     */

    private BigDecimal matterAmount12;
    /**
     * 合同金额1月
     */

    private BigDecimal contractAmount01;
    /**
     * 合同金额2月
     */

    private BigDecimal contractAmount02;
    /**
     * 合同金额3月
     */

    private BigDecimal contractAmount03;
    /**
     * 合同金额4月
     */

    private BigDecimal contractAmount04;
    /**
     * 合同金额5月
     */

    private BigDecimal contractAmount05;
    /**
     * 合同金额6月
     */

    private BigDecimal contractAmount06;
    /**
     * 合同金额7月
     */

    private BigDecimal contractAmount07;
    /**
     * 合同金额8月
     */

    private BigDecimal contractAmount08;
    /**
     * 合同金额9月
     */

    private BigDecimal contractAmount09;
    /**
     * 合同金额10月
     */

    private BigDecimal contractAmount10;
    /**
     * 合同金额11月
     */

    private BigDecimal contractAmount11;
    /**
     * 合同金额12月
     */

    private BigDecimal contractAmount12;
    /**
     * 报销金额1月
     */

    private BigDecimal expenseAmount01;
    /**
     * 报销金额2月
     */

    private BigDecimal expenseAmount02;
    /**
     * 报销金额3月
     */

    private BigDecimal expenseAmount03;
    /**
     * 报销金额4月
     */

    private BigDecimal expenseAmount04;
    /**
     * 报销金额5月
     */

    private BigDecimal expenseAmount05;
    /**
     * 报销金额6月
     */

    private BigDecimal expenseAmount06;
    /**
     * 报销金额7月
     */

    private BigDecimal expenseAmount07;
    /**
     * 报销金额8月
     */

    private BigDecimal expenseAmount08;
    /**
     * 报销金额9月
     */

    private BigDecimal expenseAmount09;
    /**
     * 报销金额10月
     */

    private BigDecimal expenseAmount10;
    /**
     * 报销金额11月
     */

    private BigDecimal expenseAmount11;
    /**
     * 报销金额12月
     */

    private BigDecimal expenseAmount12;
    /**
     * 应付金额1月
     */

    private BigDecimal factAmount01;
    /**
     * 应付金额2月
     */

    private BigDecimal factAmount02;
    /**
     * 应付金额3月
     */

    private BigDecimal factAmount03;
    /**
     * 应付金额4月
     */

    private BigDecimal factAmount04;
    /**
     * 应付金额5月
     */

    private BigDecimal factAmount05;
    /**
     * 应付金额6月
     */

    private BigDecimal factAmount06;
    /**
     * 应付金额7月
     */

    private BigDecimal factAmount07;
    /**
     * 应付金额8月
     */

    private BigDecimal factAmount08;
    /**
     * 应付金额9月
     */

    private BigDecimal factAmount09;
    /**
     * 应付金额10月
     */

    private BigDecimal factAmount10;
    /**
     * 应付金额11月
     */

    private BigDecimal factAmount11;
    /**
     * 应付金额12月
     */

    private BigDecimal factAmount12;
    /**
     * 实付金额1月
     */

    private BigDecimal payAmount01;
    /**
     * 实付金额2月
     */

    private BigDecimal payAmount02;
    /**
     * 实付金额3月
     */

    private BigDecimal payAmount03;
    /**
     * 实付金额4月
     */

    private BigDecimal payAmount04;
    /**
     * 实付金额5月
     */

    private BigDecimal payAmount05;
    /**
     * 实付金额6月
     */

    private BigDecimal payAmount06;
    /**
     * 实付金额7月
     */

    private BigDecimal payAmount07;
    /**
     * 实付金额8月
     */

    private BigDecimal payAmount08;
    /**
     * 实付金额9月
     */

    private BigDecimal payAmount09;
    /**
     * 实付金额10月
     */

    private BigDecimal payAmount10;
    /**
     * 实付金额11月
     */

    private BigDecimal payAmount11;
    /**
     * 实付金额12月
     */

    private BigDecimal payAmount12;
    /**
     * 立项+签约1月
     */

    private BigDecimal occurAmount01;
    /**
     * 立项+签约2月
     */

    private BigDecimal occurAmount02;
    /**
     * 立项+签约3月
     */

    private BigDecimal occurAmount03;
    /**
     * 立项+签约4月
     */

    private BigDecimal occurAmount04;
    /**
     * 立项+签约5月
     */

    private BigDecimal occurAmount05;
    /**
     * 立项+签约6月
     */

    private BigDecimal occurAmount06;
    /**
     * 立项+签约7月
     */

    private BigDecimal occurAmount07;
    /**
     * 立项+签约8月
     */

    private BigDecimal occurAmount08;
    /**
     * 立项+签约9月
     */

    private BigDecimal occurAmount09;
    /**
     * 立项+签约10月
     */

    private BigDecimal occurAmount10;
    /**
     * 立项+签约11月
     */

    private BigDecimal occurAmount11;
    /**
     * 立项+签约12月
     */

    private BigDecimal occurAmount12;
}
