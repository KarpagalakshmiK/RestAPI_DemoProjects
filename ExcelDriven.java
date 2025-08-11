package ExcelDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriven {
	
//	public static void main(String args[]) throws IOException {
//		ExcelDriven d=new ExcelDriven();
//		System.out.println(d.getData(testname));
//		
//	}
	
	public ArrayList<String> getData(String testname,String sheetname) throws IOException {
		ArrayList<String> list=new ArrayList<String>();
		FileInputStream fis=new FileInputStream("C:\\Users\\2273486\\Downloads\\Books.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		
		int sheets=wb.getNumberOfSheets();
		for(int i=0; i<sheets;i++) {
			if(wb.getSheetName(i).equalsIgnoreCase(sheetname)) {
				XSSFSheet sheet=wb.getSheetAt(i);
			
			
		
		Iterator<Row> row=sheet.rowIterator();
		Row firstrow=row.next();
		int k=0;
		int col=0;
		Iterator<Cell> cell=firstrow.cellIterator();
		while(cell.hasNext()) {
			Cell value=cell.next();
			if(value.getStringCellValue().equalsIgnoreCase("name")) {
				col=k;
			}
			k++;
		}
		
//		System.out.println(col);
		
		while(row.hasNext()) {
			Row r=row.next();
			if(r.getCell(col).getStringCellValue().equalsIgnoreCase(testname)) {
				Iterator<Cell> cv=r.cellIterator();
				while(cv.hasNext()) {
					Cell c=cv.next();
					if(c.getCellType()==CellType.STRING) {
						list.add(c.getStringCellValue());
					}
					else {
//						list.add(c.getNumericCellValue());
						list.add(NumberToTextConverter.toText(c.getNumericCellValue()));
						
						/*change array parameters to object or else keep it as string , and then use 
						NumberToTextConverter.toText which will change numeric to string but will print numbers only*/
					}
				
				
			}
		}
		}
			}
		}
		return list;
		
		
		
		
	}
	
	}



