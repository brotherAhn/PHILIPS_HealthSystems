package com.philips.healthSystems.util;


import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service("excelView")
public class ExcelView {
    
    public void createExcel(
            Map<String, Object> model, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	XSSFWorkbook workbook = new XSSFWorkbook();

        String fileName = (String)model.get("fileName");
        String sheetName = (String)model.get("sheetName");
        List<String> listColumn = (List<String>) model.get("listColumn");
        List<List<Object>> listData = (List<List<Object>>) model.get("listData");

        

        CellStyle numberCellStyle = workbook.createCellStyle();
        DataFormat numberDataFormat = workbook.createDataFormat();
        numberCellStyle.setAlignment(HorizontalAlignment.CENTER);
        numberCellStyle.setBorderBottom(BorderStyle.THIN);
        numberCellStyle.setBorderTop(BorderStyle.THIN);
        numberCellStyle.setBorderLeft(BorderStyle.THIN);
        numberCellStyle.setBorderRight(BorderStyle.THIN);
        numberCellStyle.setDataFormat(numberDataFormat.getFormat("#,##0"));
        //Sheet sheet = workbook.createSheet(sheetName);

        CellStyle CellValueStyle = workbook.createCellStyle();
        CellValueStyle.setAlignment(HorizontalAlignment.CENTER);
        CellValueStyle.setBorderBottom(BorderStyle.THIN);
        CellValueStyle.setBorderTop(BorderStyle.THIN);
        CellValueStyle.setBorderLeft(BorderStyle.THIN);
        CellValueStyle.setBorderRight(BorderStyle.THIN);

        CellStyle ColumnStyle = workbook.createCellStyle();
        ColumnStyle.setAlignment(HorizontalAlignment.CENTER);
        ColumnStyle.setBorderBottom(BorderStyle.THIN);
        ColumnStyle.setBorderTop(BorderStyle.THIN);
        ColumnStyle.setBorderLeft(BorderStyle.THIN);
        ColumnStyle.setBorderRight(BorderStyle.THIN);
        ColumnStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        ColumnStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);

        Font font = workbook.createFont();
        font.setColor(HSSFColor.WHITE.index);
        font.setBold(true);
        ColumnStyle.setFont(font);

        int sheetCount = 1000000;
        int sheetSize = listData.size()/sheetCount;
        if(listData.size()%sheetCount!=0){
            sheetSize++;
        }
        if(sheetSize == 0) sheetSize = 1;

        if(sheetSize == 0) sheetSize = 1;


        Sheet[] sheet = new Sheet[sheetSize];
        for(int j = 0 ; j < sheetSize ; j++){


            sheet[j] = workbook.createSheet(sheetName+""+(j+1));

            int RowCount = 0;
            int ColumnCount = 0;
            Row ColumnRow = sheet[j].createRow(RowCount);
            for (String entry : listColumn) {
                Cell cell = ColumnRow.createCell(ColumnCount);
                cell.setCellValue(entry);
                cell.setCellStyle(ColumnStyle);
                ColumnCount++;
            }

            RowCount++;

            for (int h = sheetCount*(j); h < sheetCount*(j+1) ; h++ ) {
                if(listData.size()<=h){
                    break;
                }
                List<Object> Data = listData.get(h);
                Row row = sheet[j].createRow(RowCount);
                ColumnCount = 0;
                for ( int i = 0; i < Data.size(); i++ ) {
                    Object obj = Data.get(i);
                    Cell cell = row.createCell(ColumnCount);
                    if ( obj == null ) {
                        cell.setCellStyle(CellValueStyle);
                        cell.setCellValue("   ");
                    } else if ( obj instanceof String ) {
                        cell.setCellValue(StringUtil.nvl((String)obj, ""));
                        cell.setCellStyle(CellValueStyle);
                    } else if ( obj instanceof Integer ) {
                        cell.setCellType(CellType.NUMERIC);
                        cell.setCellStyle(numberCellStyle);
                        cell.setCellValue((Integer)obj);
                    } else if ( obj instanceof Long ) {
                        cell.setCellType(CellType.NUMERIC);
                        cell.setCellStyle(numberCellStyle);
                        cell.setCellValue((Long)obj);
                    } else if ( obj instanceof Double ) {
                        cell.setCellType(CellType.NUMERIC);
                        cell.setCellStyle(numberCellStyle);
                        cell.setCellValue((Double)obj);
                    } else if ( obj instanceof Float ) {
                        cell.setCellType(CellType.NUMERIC);
                        cell.setCellStyle(numberCellStyle);
                        cell.setCellValue((Float)obj);
                    } else if ( obj instanceof BigDecimal ) {
                        cell.setCellType(CellType.NUMERIC);
                        cell.setCellStyle(numberCellStyle);
                        cell.setCellValue(obj.toString());
                    } else {
                        cell.setCellStyle(CellValueStyle);
                        cell.setCellValue(obj.toString());
                    }
                    ColumnCount++;
                }
                RowCount++;
            }
        }
        
        
        
        fileName = createFileName(fileName);
        setFileNameToResponse(request, response, fileName , workbook);
        
        
    }

    private void setFileNameToResponse(HttpServletRequest request, HttpServletResponse response, String fileName , XSSFWorkbook workbook) {
    	
    	
        String fileNameEnc = fileName;
        try {
            fileNameEnc = new String(fileName.getBytes("euc-kr"), "8859_1");
        } catch (UnsupportedEncodingException e) {
        }

        String userAgent = request.getHeader("User-Agent");
        if (userAgent.indexOf("MSIE 5.5") >= 0) {
            response.setContentType("doesn/matter");
            response.setHeader("Content-Disposition", "filename=\""+fileNameEnc+"\"");
        } else {
            response.setHeader("Content-Disposition", "attachment; filename=\""+fileNameEnc+"\"");
        }
        

        response.setContentType("Application/Msexcel");


        OutputStream fileOut;
		try {
			fileOut = response.getOutputStream();
			
	        workbook.write(fileOut);
	        fileOut.close();

	        response.getOutputStream().flush();
	        response.getOutputStream().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    private String createFileName(String fileName) {
        return new StringBuilder( fileName ).append(".xlsx").toString();
    }

}
