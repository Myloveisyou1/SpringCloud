/*
package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

import model.ExcelVO;


*/
/**
 * Created by pudding on 2017-2-6.
 * Excel读取 工具
 *//*

public class ExcelReadUtil {

    private static final String EXTENSION_XLS = "xls";
    private static final String EXTENSION_XLSX = "xlsx";


    */
/***
     * <pre>
     * 取得Workbook对象(xls和xlsx对象不同,不过都是Workbook的实现类)
     *   xls:HSSFWorkbook
     *   xlsx：XSSFWorkbook
     * @param filePath
     * @return
     * @throws IOException
     * </pre>
     *//*

    private static Workbook getWorkbook(String filePath) throws IOException {
        Workbook workbook = null;
        InputStream is = new FileInputStream(filePath);
        if (filePath.endsWith(EXTENSION_XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (filePath.endsWith(EXTENSION_XLSX)) {
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    */
/**
     * 文件检查
     *
     * @param filePath
     * @throws FileNotFoundException
     * @throws FileFormatException
     *//*

    private static void preReadCheck(String filePath) throws FileNotFoundException, FileFormatException {
        // 常规检查
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("传入的文件不存在：" + filePath);
        }
        if (!(filePath.endsWith(EXTENSION_XLS) || filePath.endsWith(EXTENSION_XLSX))) {
            throw new FileFormatException("传入的文件不是excel");
        }
    }


    */
/**
     * 取单元格的值
     *
     * @param cell       单元格对象
     * @param treatAsStr 为true时，当做文本来取值 (取到的是文本，不会把“1”取成“1.0”)
     * @return
     *//*

    private static String getCellValue(Cell cell, boolean treatAsStr) {
        if (cell == null) {
            return "";
        }
        if (treatAsStr) {
            // 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
            // 加上下面这句，临时把它当做文本来读取
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }


    */
/**
     * 读取excel文件内容
     *ExcelVO    将要读取的excel的值
     * @param filePath
     * @throws FileNotFoundException
     * @throws FileFormatException
     *//*

    public static List<ExcelVO> readExcel(String filePath) throws FileNotFoundException, FileFormatException {
        List<ExcelVO> vos = new ArrayList<ExcelVO>();
        // 检查
        preReadCheck(filePath);
        // 获取workbook对象
        Workbook workbook = null;
        try {
            workbook = getWorkbook(filePath);
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workbook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }

                int firstRowIndex = sheet.getFirstRowNum();//首行
                int lastRowIndex = sheet.getLastRowNum();//末行


                // 读取数据行 +1 是不需要标题  因为excel的第一行就是0
                for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
                    ExcelVO excelVO = new ExcelVO();
                    Row currentRow = sheet.getRow(rowIndex);// 当前行
                    int firstColumnIndex = currentRow.getFirstCellNum(); // 首列
                    int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
                    for (int columnIndex = firstColumnIndex; columnIndex < lastColumnIndex; columnIndex++) {
                        Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
                        String currentCellValue = getCellValue(currentCell, true);// 当前单元格的值
                        //System.out.println(currentCellValue);//0.0
                        if (columnIndex == 0) {
                            excelVO.setOaNo(currentCellValue);
                        } else if (columnIndex == 1) {
                            excelVO.setRechargeType((int)Double.parseDouble(currentCellValue));
                        } else if (columnIndex == 2) {
                            excelVO.setUserKey(currentCellValue);
                        } else if (columnIndex == 3) {
                            excelVO.setMoney(Double.parseDouble(currentCellValue));
                        } else if (columnIndex == 4) {
                            excelVO.setWithDrawls((int)(Double.parseDouble(currentCellValue)));
                        } else if (columnIndex == 5) {
                            excelVO.setRemark(currentCellValue);
                        }
                        if(null == excelVO.getOaNo() && excelVO.getUserName()== null){
                            break;
                        }
                    }
                    vos.add(excelVO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook = null;
            }
        }
        return vos;
    }
    */
/**
     * 写入excel
     * @param path
     * @param wb
     *//*

    public static void excelWrite(String path, HSSFWorkbook wb){
        try{
            FileOutputStream fout1 = new FileOutputStream(path);
            wb.write(fout1);
            fout1.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws FileNotFoundException, FileFormatException {

        List<ExcelVO> vos = readExcel("C:/Users/pudding/Desktop/2016092717473911.xlsx");
        System.out.println(vos);


    }


}
*/
