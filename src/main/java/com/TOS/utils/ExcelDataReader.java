package com.TOS.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataReader {

	private static XSSFSheet excelSheet;
	private static XSSFWorkbook excelWorkbook;
	private static XSSFCell cell;
	private static XSSFRow row;
	public static String sheetName;

	private static void setExcelFile() throws IOException {
		FileInputStream excelFile = new FileInputStream(
				new File("src/test/resources/TestData/TestData.xlsx").getAbsolutePath());
		excelWorkbook = new XSSFWorkbook(excelFile);
		excelSheet = excelWorkbook.getSheet(sheetName);
	}

	private static int getDataRow(String dataKey, int dataColumn) {
		int rowCount = excelSheet.getLastRowNum();
		for (int row = 0; row <= rowCount; row++) {
			if (ExcelDataReader.getCellData(row, dataColumn).equalsIgnoreCase(dataKey)) {
				return row;
			}
		}
		return 0;

	}

	private static String getCellData(int rowNumb, int colNumb) {
		cell = excelSheet.getRow(rowNumb).getCell(colNumb);
		if (cell.getCellType() == CellType.NUMERIC) {
			cell.setCellType(CellType.STRING);
		} else {
			cell.setCellType(CellType.STRING);
		}
		String cellData = cell.getStringCellValue();
		return cellData;
	}

	public static Map<String, String> getData(String dataKey) throws Exception {
		Map<String, String> dataMap = new HashMap<String, String>();
		setExcelFile();
		int dataRow = getDataRow(dataKey.trim(), 1);
		if (dataRow == 0) {
			throw new Exception("NO DATA FOUND for dataKey: " + dataKey);
		}
		int columnCount = excelSheet.getRow(dataRow).getLastCellNum();
		for (int i = 0; i < columnCount; i++) {
			cell = excelSheet.getRow(dataRow).getCell(i);
			String cellData = null;
			if (cell != null) {
				if (cell.getCellType() != CellType.STRING) {
					cell.setCellType(CellType.STRING);
				}
				cellData = cell.getStringCellValue();
			}
			// System.out.println("map
			// key"+excelSheet.getRow(0).getCell(i).getStringCellValue());
			if (cellData != null) {
				if (!(cellData.isEmpty() || cellData.isBlank())) {
					dataMap.put(excelSheet.getRow(0).getCell(i).getStringCellValue(), cellData);
				}
			}
		}
		return dataMap;
	}

	public static String getColumData(String rowName, String ColName, String sName) throws Exception {
		String colData = null;
		sheetName = sName;
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap = getData(rowName);
		for (Map.Entry<String, String> data : dataMap.entrySet()) {
			if (data.getKey().equalsIgnoreCase(ColName)) {
				colData = data.getValue();
				break;
			}
		}
		// System.out.println("ColData"+colData);
		return colData;
	}

	public static String getColumData(String rowName, String ColName) throws Exception {
		String colData = null;
		// sheetName = sName;
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap = getData(rowName);
		for (Map.Entry<String, String> data : dataMap.entrySet()) {
			if (data.getKey().equalsIgnoreCase(ColName)) {
				colData = data.getValue();
				break;
			}
		}
		// System.out.println("ColData" + colData);
		return colData;
	}

	public static void main(String[] args) throws Exception {
		sheetName = "DocumentRulesets";
		System.out.println(getData("TC2"));
	}

}
