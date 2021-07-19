package org.example.basic.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionExample {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        String setterName = String.format("set%s%02d", "MatterAmount", 7);
        System.out.println(setterName);
        System.out.println(String.format("%02d", 7));

        BudgetService budgetService = new BudgetService();
        budgetService.updateAmount(BigDecimal.valueOf(1000), 7, BusinessType.MATTER, true, "", "");
        budgetService.updateAmount(BigDecimal.valueOf(1000), 12, BusinessType.REIMBURSEMENT, true, "", "");
        //        Class<BudgetDetail> classz = BudgetDetail.class;
//
//
//        BigDecimal amount = BigDecimal.valueOf(1200);
//        String month = "07";
//        boolean isAdd = true;
//        BusinessType type = BusinessType.MATTER;
//
//        BudgetDetail budgetDetail = classz.newInstance();
//        budgetDetail.setMatterAmount07(BigDecimal.valueOf(1000));
//        budgetDetail.setOccurAmount07(BigDecimal.valueOf(1000));
//
//        // 获取 public 修饰的字段
//        for (Field field : classz.getFields()) {
////            System.out.println(field.getName());
//        }
//
//        // 获取所有字段（包括私有字段）
//        for (Field field : classz.getDeclaredFields()) {
////            System.out.println(field.getName());
//        }
//
//        for (Method method : classz.getMethods()) {
////            System.out.println(method.getName());
//        }
//
//        Map<BusinessType, List<String>> typeMap = new HashMap<>();
//        typeMap.put(BusinessType.MATTER, Arrays.asList("MatterAmount", "OccurAmount"));
//        typeMap.put(BusinessType.CONTRACT, Arrays.asList("ContractAmount", "OccurAmount"));
//        typeMap.put(BusinessType.SUPPLEMENT_CONTRACT, Arrays.asList("ContractAmount", "OccurAmount"));
//        typeMap.put(BusinessType.REIMBURSEMENT, Arrays.asList("ExpenseAmount", "FactAmount", "OccurAmount"));
//
//
//        if (type == BusinessType.MATTER) {
//            List<String> fields = typeMap.get(type);
//            for (int i = 0; i < fields.size(); i++) {
//                // 1. 构造字段的 setter
//                String field_ = fields.get(i);
//                String setterName = String.format("set%s%s", field_, month);
//                String getterName = String.format("get%s%s", field_, month);
//                Method getterMethod = classz.getMethod(getterName);
//                Method setterMethod = classz.getMethod(setterName, BigDecimal.class);
//
//                // 2. 调用 setter 累计赋值
//                BigDecimal originalAmount = (BigDecimal) getterMethod.invoke(budgetDetail);
//                originalAmount = originalAmount == null ? BigDecimal.valueOf(0) : originalAmount;
//                // 2.1 加或减
//                BigDecimal updatedAmount = isAdd ? originalAmount.add(amount) : originalAmount.subtract(amount);
//                setterMethod.invoke(budgetDetail, updatedAmount);
//            }
//            System.out.println(budgetDetail.getMatterAmount07());
//            System.out.println(budgetDetail.getOccurAmount07());
//        }
//
//        // 3. 以字段为起点递归更新其祖先节点
//

    }

//    public enum BusinessType {
//        MATTER, // 立项 MatterAmount、OccurAmount
//        CONTRACT, // 合同 ContractAmount、OccurAmount(非立项合同需更新)
//        SUPPLEMENT_CONTRACT, // 合同 ContractAmount、OccurAmount(非立项合同需更新)
//        REIMBURSEMENT // ExpenseAmount、FactAmount、OccurAmount((非立项合同需更新)
//    }
}
