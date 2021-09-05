package org.example.basic.excel.style.strategy;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.StyleUtil;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

public class HeadStyleStrategy extends AbstractCellStyleStrategy {

    private WriteCellStyle headWriteCellStyle;
    private CellStyle headCellStyle;
    private XSSFColor headXSSFColor;

    public HeadStyleStrategy(WriteCellStyle headWriteCellStyle, XSSFColor headXSSFColor) {
        this.headWriteCellStyle = headWriteCellStyle;
        this.headXSSFColor = headXSSFColor;
    }

    /**
     * Initialization cell style
     *
     * @param workbook
     */
    @Override
    protected void initCellStyle(Workbook workbook) {
        if (headWriteCellStyle != null) {
            headCellStyle = StyleUtil.buildHeadCellStyle(workbook, headWriteCellStyle);
        }
    }

    /**
     * Sets the cell style of header
     *
     * @param cell
     * @param head
     * @param relativeRowIndex
     */
    @Override
    protected void setHeadCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        if (headCellStyle == null) {
            return;
        }
        if (headXSSFColor != null) {
            XSSFCellStyle xssfCellStyle = (XSSFCellStyle) headCellStyle;
            xssfCellStyle.setFillForegroundColor(headXSSFColor);
//            xssfCellStyle.setTopBorderColor(headXSSFColor);
//            xssfCellStyle.setRightBorderColor(headXSSFColor);
//            xssfCellStyle.setBottomBorderColor(headXSSFColor);
//            xssfCellStyle.setLeftBorderColor(headXSSFColor);
        }
        cell.setCellStyle(headCellStyle);
    }

    /**
     * Sets the cell style of content
     *
     * @param cell
     * @param head
     * @param relativeRowIndex
     */
    @Override
    protected void setContentCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        // 不实现
    }
}
