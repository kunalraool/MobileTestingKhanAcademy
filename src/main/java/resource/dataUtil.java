package resource;

import resource.ExcelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

public class dataUtil {


	public static final String testDataSheet = "credentials";
	static FileInputStream fis;

	@SuppressWarnings("null")
	@DataProvider
	public static Object[][] getData() {
		int testStartRowNum = 0;
		int dataStartRowNum = testStartRowNum + 1;
		ExcelReader excelReader;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Testdata.xlsx");
			excelReader = new ExcelReader(fis);
			int rows = excelReader.getRowCount(testDataSheet);
			int cols = excelReader.getColumnCount(testDataSheet);
			int dataRow = 0;
			Object[][] data = new Object[rows][1];
			
			Hashtable<String, String> table = null;
			for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + rows); rNum++) {
				for (int cNum = 0; cNum < cols; cNum++) {
					String key = excelReader.getCellData(testDataSheet, cNum, testStartRowNum);
					String value = excelReader.getCellData(testDataSheet, cNum, rNum);
					table.put(key, value);
				}
				data[dataRow][0] = table;
				dataRow++;
			}
			return data;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
