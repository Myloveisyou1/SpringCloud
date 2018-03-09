package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.Region;

/**
 * 导出excel工具类
 * @author Administrator
 *
 */
public class ExcelUtils {
	
    private String url = "E://";//测试上传地址
	/**
	 * 导出excel公共接口
	 * @param param      查询条件
	 * @param title      excel表格的标题，将要显示的字段
	 * @param excelName  excel表格的名称
	 * @param content    每一个表格中的值
	 * @return
	 */
	public String excel(Object param,String[] title,String excelName,String[][] content){
		
		//第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
    //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(excelName+"表");
    //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        //1.设置第一行的样式
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFFont font1 = wb.createFont();
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  // 字体加粗
        font1.setFontHeightInPoints((short)18);   //--->设置字体大小
        style1.setFont(font1);
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直局中
        //设置第二行样式
        HSSFCellStyle style2 = wb.createCellStyle();
        HSSFFont font2 = wb.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  // 字体加粗
        font2.setFontHeightInPoints((short)10);   //--->设置字体大小
        style2.setFont(font2);
        style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 创建一个右对齐格式
        //设置第三行样式
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font3 = wb.createFont();
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  // 字体加粗
        font3.setFontHeightInPoints((short)10);   //--->设置字体大小
        style.setFont(font3);
        
        HSSFRow row1 = sheet.createRow(0);//第一行      设置标题
        HSSFCell cell1 = row1.createCell((short) 0);
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));//合并单元格，开始行，开始列，结束行，结束列  
        cell1.setCellValue(excelName);
        cell1.setCellStyle(style1);
        
        HSSFRow row2 = sheet.createRow(1);//第二行      设置时间
        HSSFCell cell2 = row2.createCell((short) 0);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 10));//合并单元格，开始行，开始列，结束行，结束列
        //cell2.setCellValue("统计时间："+param.getStartTime()+"-"+param.getEndTime());
        cell2.setCellStyle(style2);
        
        HSSFRow row = sheet.createRow((int) 2);//第三行   设置标题
        
   // 第四步，创建单元格
        for(int i=0;i<title.length;i++){
        	HSSFCell cell = row.createCell((short) i);
        	cell.setCellValue(title[i]);
            cell.setCellStyle(style);
            sheet.setColumnWidth(i,20 * 256);//设置单元格的宽度
        }
   //赋值
        for(int m=0;m<content.length;m++){
        	String arr[] = content[m];
        	row = sheet.createRow( m+3 );//因为有标题，所以这里+2

        	for(int n=0;n<arr.length;n++){
            	row.createCell((short) n).setCellValue(arr[n]);
        	}
        } 
        
  //保存文件
        String path=url+excelName+new SimpleDateFormat("yyyyMMddHHmmsssss").format(new Date())+".xls";
        //ExcelReadUtil.excelWrite(path,wb);
        return path;
	}
}
