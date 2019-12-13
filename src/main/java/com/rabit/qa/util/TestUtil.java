package com.rabit.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rabit.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class TestUtil extends TestBase {
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	public static String TestSheetPath = System.getProperty("user.dir")
			+ "/src/main/java/com/rabit/qa/testdata/TestData2.xlsx";
	public static final String REPORT_PATH = System.getProperty("user.dir") + File.separator + "target" + File.separator
			+ "Reports";

	public static Object[][] getdata(String SheetName) {
		Object[][] data = null;
		int rowcount = -1;
		int colcount;
		try {
			FileInputStream fs = new FileInputStream(TestSheetPath);
			XSSFWorkbook wbook = new XSSFWorkbook(fs);
			XSSFSheet wsheet = wbook.getSheet(SheetName);
			Iterator<Row> rowiterator = wsheet.rowIterator();
			data = new Object[wsheet.getLastRowNum()][wsheet.getRow(0).getLastCellNum()];
			while (rowiterator.hasNext()) {
				Row row = rowiterator.next();
				if (row.getRowNum() != 0) {
					rowcount++;
					colcount = -1;
					Iterator<Cell> celliterator = row.cellIterator();
					while (celliterator.hasNext()) {
						Cell cell = celliterator.next();
						colcount++;
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							data[rowcount][colcount] = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							data[rowcount][colcount] = cell.getNumericCellValue();
							break;
						}

					}

				} else {
					continue;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

	public static Object[][] getdata2(String SheetName) {
		Object[][] data = null;
		// int i = 0;
		int rowcount = -1;
		int colcount;
		try {
			FileInputStream fs = new FileInputStream(TestSheetPath);
			XSSFWorkbook wbook = new XSSFWorkbook(fs);
			XSSFSheet wsheet = wbook.getSheet(SheetName);
			List<Integer> rc = findRow(wsheet, "Admin_MyAccount_001_AddMailExtentionsTest");

			data = new Object[rc.size()][wsheet.getRow(rc.get(0)).getLastCellNum()];
			// Iterator<Row> rowiterator = wsheet.rowIterator();
			for (int i = 0; i < rc.size(); i++) {
				Iterator<Row> rowiterator = wsheet.rowIterator();
				while (rowiterator.hasNext()) {
					Row row = rowiterator.next();
					if (row.getRowNum() == rc.get(i)) {
						rowcount++;
						colcount = -1;
						Iterator<Cell> celliterator = row.cellIterator();
						while (celliterator.hasNext()) {
							Cell cell = celliterator.next();
							colcount++;
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								data[rowcount][colcount] = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_NUMERIC:
								data[rowcount][colcount] = cell.getNumericCellValue();
								break;
							}

						}

					} else {
						continue;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

	public static Object[][] getdata3(String SheetName, List<String> ColumnDetails) {
		Object[][] data = null;
		// int i = 0;
		int rowcount = -1;
		int colcount;
		try {
			FileInputStream fs = new FileInputStream(TestSheetPath);
			XSSFWorkbook wbook = new XSSFWorkbook(fs);
			XSSFSheet wsheet = wbook.getSheet(SheetName);
			List<Integer> rc = findRow(wsheet, "Admin_MyAccount_001_AddMailExtentionsTest");
			List<Integer> cc = findColumns(wsheet, "Admin_MyAccount_001_AddMailExtentionsTest", ColumnDetails);

			data = new Object[rc.size()][ColumnDetails.size()];
			// Iterator<Row> rowiterator = wsheet.rowIterator();
			for (int i = 0; i < rc.size(); i++) {
				Iterator<Row> rowiterator = wsheet.rowIterator();
				while (rowiterator.hasNext()) {
					Row row = rowiterator.next();
					if (row.getRowNum() == rc.get(i)) {
						rowcount++;
						colcount = -1;
						for (int j = 0; j < cc.size(); j++) {
							Iterator<Cell> celliterator = row.cellIterator();
							while (celliterator.hasNext()) {
								Cell cell = celliterator.next();
								if (cell.getColumnIndex() == cc.get(j)) {
									colcount++;
									switch (cell.getCellType()) {
									case Cell.CELL_TYPE_STRING:
										data[rowcount][colcount] = cell.getStringCellValue();
										break;
									case Cell.CELL_TYPE_NUMERIC:
										data[rowcount][colcount] = cell.getNumericCellValue();
										break;
									}
								}
							}
						}

					} else {
						continue;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

	private static List<Integer> findRow(XSSFSheet sheet, String cellContent) {
		/*
		 * This is the method to find the row number
		 */

		List<Integer> rowNum = new ArrayList<Integer>();

		Iterator<Row> rowiterator = sheet.rowIterator();
		while (rowiterator.hasNext()) {
			Row row = rowiterator.next();
			Iterator<Cell> celliterator = row.cellIterator();
			while (celliterator.hasNext()) {
				Cell cell = celliterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					if (cell.getStringCellValue().contains(cellContent)) {
						rowNum.add(row.getRowNum());
					}
				}
			}
		}
		return rowNum;
	}

	private static List<Integer> findColumns(XSSFSheet sheet, String cellContent, List<String> ColumnDetails) {
		/*
		 * This is the method to find the row number
		 */

		List<Integer> ColsNum = new ArrayList<Integer>();

		Iterator<Row> rowiterator = sheet.rowIterator();
		while (rowiterator.hasNext()) {
			Row row = rowiterator.next();
			if (row.getRowNum() == 0) {
				for (int i = 0; i < ColumnDetails.size(); i++) {
					Iterator<Cell> celliterator = row.cellIterator();
					while (celliterator.hasNext()) {
						Cell cell = celliterator.next();
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							if (cell.getStringCellValue().contains(ColumnDetails.get(i))) {
								ColsNum.add(cell.getColumnIndex());
							}
						}
					}
				}
			}
		}
		return ColsNum;
	}

	public boolean verifyData(String data) {
		WebElement e = null;
		String actualText = "";
		String Object = ".//*[contains(text(),'" + data + "')]";
		// test.log(LogStatus.INFO, "Verifying the provided data value with actual
		// value");
		try {
			e = driver.findElement(By.xpath(Object));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(e));
			actualText = e.getText();
		} catch (Exception t) {
			// test.log(LogStatus.FAIL, "Following Web Element Not found :" + Object);

		}
		String actualText1 = actualText.toLowerCase();
		String data1 = data.toLowerCase();
		if (actualText1.contains(data1)) {
			return true;
		} else {
			return false;
		}
	}

	public void ClickonOKButton() {
		driver.findElement(By.xpath("//*[contains(text(),'OK')]")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void takeScreenshotAtEndOfTest() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + System.currentTimeMillis() + ".png";
		try {
			FileUtils.copyFile(src, new File(dest));
			test.log(LogStatus.FAIL, test.addScreenCapture(dest));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}