package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {

Workbook wb;
// create constructor for reading the excel path 
public ExcelFileUtil(String ExcelPath)throws Throwable 
{
	FileInputStream fi = new FileInputStream(ExcelPath);
	wb = WorkbookFactory.create(fi);
}
// method for count no of rows 
public int rowCount(String SheetName)
{
	return wb.getSheet(SheetName).getLastRowNum();
}
// method to count no of columns or cells in a row
public int cellCount(String SheetName)
{
	return wb.getSheet(SheetName).getRow(0).getLastCellNum();
}
//method for reading cell data
public String getCellData(String SheetName, int row, int column)
{
	String data = "";
	if(wb.getSheet(SheetName).getRow(row).getCell(column).getCellType()== Cell.CELL_TYPE_NUMERIC)
{	
	int cellData = (int) wb.getSheet(SheetName).getRow(row).getCell(column).getNumericCellValue();
	data = String.valueOf(cellData);
}	
	else
{
	data = wb.getSheet(SheetName).getRow(row).getCell(column).getStringCellValue();
}
	return data;
}
// method for setCellData
public void setCellData(String SheetName, int row, int column, String status,String WriteExcel)throws Throwable
{
	//get sheet from wb
	Sheet ws = wb.getSheet(SheetName);
	//get row from sheet
	Row rowNum = ws.getRow(row);
	// create cell
	Cell cell = rowNum.createCell(column);
	// write cell status
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("Pass"))
	{
		CellStyle style = wb.createCellStyle();
		//color green for pass
		Font font = wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		style.setFont(font);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Fail"))
	{CellStyle style = wb.createCellStyle();
	//color red for fail
	Font font = wb.createFont();
	font.setColor(IndexedColors.RED.getIndex());
	font.setBold(true);
	style.setFont(font);
	font.setBoldweight(Font.BOLDWEIGHT_BOLD);
	ws.getRow(row).getCell(column).setCellStyle(style);
		
	}
	else if(status.equalsIgnoreCase("Blocked"))
	{
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		//colour blue for blocked
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		style.setFont(font);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}
	//to get output
	FileOutputStream fo = new FileOutputStream(WriteExcel);
	wb.write(fo);
}
}

