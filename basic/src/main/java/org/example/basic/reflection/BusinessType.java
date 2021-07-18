package org.example.basic.reflection;

public enum BusinessType {
    MATTER, // 立项 MatterAmount、OccurAmount
    CONTRACT, // 合同 ContractAmount、OccurAmount(非立项合同需更新)
    SUPPLEMENT_CONTRACT, // 合同 ContractAmount、OccurAmount(非立项合同需更新)
    REIMBURSEMENT // ExpenseAmount、FactAmount、OccurAmount((非立项合同需更新)
}