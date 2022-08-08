package com.rest.webservices.restfullwebservices.user.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.rest.webservices.restfullwebservices.user.User;
@Component
public class ExcelImport {
//creation of excel Sheet if it is not there;
//	private int rowId = 1;
//	private static List<Object> header = new ArrayList<>();

//	static {
//		try {
//			FileOutputStream out = new FileOutputStream(filePath);
//			header.add(new Object[] { "Sno", "Name", "Date Of Birth" });
//			Row row;
//			row = workSheet.createRow(0);
//			int cellId = 0;
//			Cell cell = row.createCell(cellId);
//			cell.setCellValue((String) header.get(cellId++));
//			workBook.write(out);
//			out.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	@GetMapping("/data")
	@SuppressWarnings("null")
	public List<User> excelConvertor() throws ParseException, IOException {
		String filePath = System.getProperty("user.dir") + "./data/Data.xlsx";
		FileInputStream out = new FileInputStream(filePath);
		@SuppressWarnings("resource")
		XSSFWorkbook workBook = new XSSFWorkbook(out);
		
		XSSFSheet workSheet = workBook.getSheet("Sheet1");
//		System.out.println(workSheet);
		List<User> user = new ArrayList<>();
		DataFormatter format = new DataFormatter();
		Iterator<Row> row = workSheet.iterator();
//		System.out.println(row);
		Row row1=row.next();
		while (row.hasNext()) {
			User u = new User();
			int c = 0;
			row1 = row.next();
			Iterator<Cell> column = row1.iterator();
			while (column.hasNext()) {
				Cell value = column.next();
//				System.out.println("VAlue+ -"+format.formatCellValue(value));
				switch (c++) {
				case 0:
					u.setId(Integer.parseInt(format.formatCellValue(value)));
					break;
				case 1:
					u.setName(format.formatCellValue(value));
					break;
				case 2:
					Date date = new SimpleDateFormat("dd/MM/yyyy").parse(format.formatCellValue(value));
					u.setDate(date);
					break;
				}

			}
//			System.out.println("To String Method +--"+u.toString());
			user.add(u);
		}
		out.close();

		return user;
	}
}
