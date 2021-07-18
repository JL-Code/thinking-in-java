package org.example.basic.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetService {

    Map<BusinessType, List<String>> typeFieldMapping = new HashMap<>();

    public BudgetService() {
        typeFieldMapping.put(BusinessType.MATTER, Arrays.asList("MatterAmount", "OccurAmount"));
        typeFieldMapping.put(BusinessType.CONTRACT, Arrays.asList("ContractAmount", "OccurAmount"));
        typeFieldMapping.put(BusinessType.SUPPLEMENT_CONTRACT, Arrays.asList("ContractAmount", "OccurAmount"));
        typeFieldMapping.put(BusinessType.REIMBURSEMENT, Arrays.asList("ExpenseAmount", "FactAmount", "OccurAmount"));
    }

    public void updateAmount(BigDecimal amount, Integer month, BusinessType type, boolean isAdd, String budgetId, String subjectId) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<BudgetDetail> classz = BudgetDetail.class;

        BudgetDetail budgetDetail = classz.newInstance();

        List<String> fields = typeFieldMapping.get(type);

        for (int i = 0; i < fields.size(); i++) {
            // 1. 构造字段的 setter
            String field_ = fields.get(i);
            String setterName = String.format("set%s%02d", field_, month);
            String getterName = String.format("get%s%02d", field_, month);
            Method getterMethod = classz.getMethod(getterName);
            Method setterMethod = classz.getMethod(setterName, BigDecimal.class);

            // 2. 调用 setter 累计赋值
            BigDecimal originalAmount = (BigDecimal) getterMethod.invoke(budgetDetail);
            originalAmount = originalAmount == null ? BigDecimal.valueOf(0) : originalAmount;
            // 2.1 加或减
            BigDecimal updatedAmount = isAdd ? originalAmount.add(amount) : originalAmount.subtract(amount);
            setterMethod.invoke(budgetDetail, updatedAmount);

            System.out.println(getterMethod.invoke(budgetDetail));
        }
        // 3. 以字段为起点递归更新其祖先节点
        System.out.println(budgetDetail);
    }
}
