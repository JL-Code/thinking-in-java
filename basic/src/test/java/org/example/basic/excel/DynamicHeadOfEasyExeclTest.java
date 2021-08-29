package org.example.basic.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>创建时间: 2021/8/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class DynamicHeadOfEasyExeclTest {

    /**
     * 动态头，实时生成头写入
     * <p>
     * 思路是这样子的，先创建List<String>头格式的sheet仅仅写入头,然后通过table 不写入头的方式 去写入数据
     *
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 然后写入table即可
     */
    @Test
    public void dynamicHeadWrite() {
        String fileName = TestFileUtil.getPath() + "dynamicHeadWrite.xlsx";
        EasyExcel.write(fileName)
                // 这里放入动态头
                .head(head())
                .sheet("模板")
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(data());
    }

    @Test
    public void dynamicHeadOfInfiniteWrite() throws JsonProcessingException {

        String fileName = TestFileUtil.getPath() + "dynamicHeadOfInfiniteWrite.xlsx";

        Integer degree = 2;
        Integer depth = 4;

        HeadTree<HeadTreeNode> headTree = ExcelUtils.buildTree(degree, depth);

        List<List<String>> head = ExcelUtils.treeToEasyExcelHead(headTree);

        EasyExcel.write(fileName)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                // 这里放入动态头
                .head(head)
                .sheet("模板")
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(data());

//        Assertions.assertEquals(Math.pow(degree, depth), head.size());
    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<>();

        List<String> head0 = new ArrayList<>();
        head0.add("字符串" + System.currentTimeMillis());

        List<String> head1 = new ArrayList<>();
        head1.add("数字" + System.currentTimeMillis());

        List<String> head2 = new ArrayList<>();
        head2.add("日期" + System.currentTimeMillis());

        list.add(head0);
        list.add(head1);
        list.add(head2);

        return list;
    }

    @Test
    void head3Test() throws JsonProcessingException {

        head3();

    }


    public List<List<String>> head3() throws JsonProcessingException {

        List<List<String>> header = new ArrayList<>();

        List<String> cellContain1 = new ArrayList<>();
        cellContain1.add("大连");
        cellContain1.add("中山区");
//        cellContain1.add("中山广场");
        header.add(cellContain1);

        List<String> cellContain2 = new ArrayList<>();
        cellContain2.add("大连");
        cellContain2.add("沙河口区");
//        cellContain2.add("中山广场");
        header.add(cellContain2);

        List<String> cellContain3 = new ArrayList<>();
        cellContain3.add("成都");
        cellContain3.add("锦江区");
//        cellContain3.add("中山广场");
        header.add(cellContain3);

        List<String> cellContain4 = new ArrayList<>();
        cellContain4.add("成都");
        cellContain4.add("青羊区");
        cellContain4.add("万达广场1");
        header.add(cellContain4);

        List<String> cellContain5 = new ArrayList<>();
        cellContain5.add("成都");
        cellContain5.add("青羊区");
        cellContain5.add("万达广场2");
        header.add(cellContain5);

        List<String> cellContain6 = new ArrayList<>();
        cellContain6.add("大连");
        cellContain6.add("甘井子区");
        header.add(cellContain6);

        List<String> cellContain7 = new ArrayList<>();
        cellContain7.add("重庆");
        header.add(cellContain7);

        System.out.println(new ObjectMapper().writeValueAsString(header));

        return header;
    }

    private List data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            DemoData item = new DemoData();
            item.setDate(new Date());
            item.setString("动态表头测试" + i);
            item.setDoubleData(1000000000.0);
            list.add(item);
        }
        return list;
    }
}
