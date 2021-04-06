package resource;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader extends dataUtil{
	
	public String path;
	public FileInputStream fis = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private InputStream inputStream = null;
	private XSSFCell cell = null;
	private XSSFRow row = null;
	
	public ExcelReader(FileInputStream fis) {
	
		try {
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(testDataSheet);
			fis.close();
		} catch (Exception e) {
			System.out.println("Got an exception while reading the Excel file. " + path + "\nException is ==> " + e.getMessage());
			try {
				fis.close();
			} catch (Exception ex) {
				System.out.println("Got an exception while closing the file input stream of the Excel file. " + path + "\nException is ==> " + e.getMessage());
			}
		}
	}
	public ExcelReader(InputStream stream) {
		inputStream = stream;
		try {
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheet(testDataSheet);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}

	}
	public int getColumnCount(String sheetName) {
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if (row == null)
			return -1;

		return row.getLastCellNum();

	}
	public int getRowsInColumn(String sheetName, int col) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int rowCount = 1;
			while (!getCellData(sheetName, col, rowCount + 1).equals("")) {
				rowCount++;
				if (rowCount > sheet.getLastRowNum() + 1) {
					// throw ERROR
					return 0;

				}

			}
			rowCount = rowCount - 1;
			return rowCount;
		}

	}
	
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";

			if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getCellType() == Cell.CELL_TYPE_FORMULA)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

				String cellText = String.valueOf(cell.getNumericCellValue());

				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}
	
	public boolean isSheetExist(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}
	
	
}
