package org.example.basic.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>创建时间: 2021/8/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class ComplexHeadData {
    @ExcelProperty({"主标题", "字符串标题"})
    private String string;
    @ExcelProperty({"主标题", "字符串标题", "三级表头1"})
    private String string1;
    @ExcelProperty({"主标题", "字符串标题", "三级表头1"})
    private String string2;
    @ExcelProperty({"主标题", "日期标题"})
    private Date date;
    // EasyExecl 不支持 LocalDateTime。
//    @ExcelProperty({"主标题", "日期标题2"})
//    private LocalDateTime localDateTime;
    @ExcelProperty({"主标题", "数字标题"})
    private Double doubleData;
}