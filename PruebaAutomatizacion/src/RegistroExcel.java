
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class RegistroExcel {
	
	private String fecha;
	private String doc;
	private String nombre;
	private String descrip;
	private String peso;
	private String precio;
	private String alic;
	
	public RegistroExcel() {
		
	}
	
	
	
	public ArrayList<RegistroExcel> readExcelAndSaveInList(String filepath, String sheetname) throws IOException{
		
		File file = new File (filepath);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook newWorkBook = new XSSFWorkbook(inputStream);
		XSSFSheet newSheet = newWorkBook.getSheet(sheetname);
		int rowCount = newSheet.getLastRowNum() - newSheet.getFirstRowNum();
		ArrayList<RegistroExcel> list = new ArrayList<RegistroExcel>();
		for (int i = 1; i <= rowCount; i++) {
			XSSFRow row = newSheet.getRow(i);
			RegistroExcel regis = new RegistroExcel();
			System.out.print("<"+i+">"+" || ");
			for (int j = 0; j < 6; j++) {
				
				
				DataFormatter formatter = new DataFormatter();
				
				switch(j) {
					
				case 0: 
						regis.fecha= formatter.formatCellValue(row.getCell(j));
						System.out.print("<"+regis.fecha+">"+" || ");
						break;
				case 1: 
						regis.descrip = "Varios (Ticket ".concat(formatter.formatCellValue(row.getCell(j))).concat(" ").concat(formatter.formatCellValue(row.getCell(++j))).concat(")");
						
						System.out.print("<"+regis.descrip+">"+" || ");
	
						break;
				case 3: 
						 
						regis.precio=formatter.formatCellValue(row.getCell(j));
						System.out.print("<"+regis.precio+">"+" || ");
						break;
				case 4: 
						
						regis.doc=formatter.formatCellValue(row.getCell(j));
						System.out.print("<"+regis.doc+"> || ");	
						break;
				case 5: 
					
						regis.alic=formatter.formatCellValue(row.getCell(j));
						System.out.println("<"+regis.alic+">\n");
						break;
				default: 	
						System.out.println("Error al cargar lista desde excel.\n");
						break;			
				}
				
			}
			if(regis.fecha.isEmpty())
				i=rowCount;
			else {
				
				regis.precio=regis.precio.replace(',', '.');
				list.add(regis);
				//System.out.println(regis.fecha + " || " + regis.doc + " || " + regis.nombre + " || " + regis.descrip + " || " + regis.precio);
			}
			//System.out.println();
		}
		newWorkBook.close();
		return list;
	}
	
//	public void writeCellExcel(String filepath, String sheetname, String inform, int rownum) throws IOException {
//		
//		File file = new File (filepath);
//		FileInputStream inputStream = new FileInputStream(file);
//		XSSFWorkbook newWorkBook = new XSSFWorkbook(inputStream);
//		XSSFSheet newSheet = newWorkBook.getSheet(sheetname);
//		XSSFCell cell = newSheet.getRow(rownum).createCell(5);
//		cell.setCellValue(inform);
//		
//		
//		inputStream.close();
//		FileOutputStream outpustream = new FileOutputStream(file);
//		newWorkBook.write(outpustream);
//		outpustream.close();
//		newWorkBook.close();
//	}
	
	public void writeInformsInExcel(String filepath, String sheetname, ArrayList<String> list ) throws IOException {
		
		File file = new File (filepath);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook newWorkBook = new XSSFWorkbook(inputStream);
		XSSFSheet newSheet = newWorkBook.getSheet(sheetname);
		
		for (int i = 0; i < list.size(); i++) {
			
			XSSFCell cell= newSheet.getRow(i+1).createCell(6);
			cell.setCellValue(list.get(i));
		}
		
		inputStream.close();
		FileOutputStream outpustream = new FileOutputStream(file);
		newWorkBook.write(outpustream);
		outpustream.close();
		newWorkBook.close();
		
	}
	
	
	
//	public void readExcel( String filepath, String sheetName) throws IOException {
//		
//		
//		
//		File file = new File(filepath);
//		
//		FileInputStream inputStream = new FileInputStream(file);
//		
//		XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
//		
//		XSSFSheet newSheet = newWorkbook.getSheet(sheetName);
//		
//		int rowCount = newSheet.getLastRowNum() - newSheet.getFirstRowNum();
//		
//		
//		for (int i = 0; i <= rowCount; i++) {
//			XSSFRow row = newSheet.getRow(i);
//			
//			for (int j = 0; j < 6; j++) {
//				
//				DataFormatter formatter = new DataFormatter();
//				String s= formatter.formatCellValue(row.getCell(j));
//				System.out.print("<"+s+">"+" || ");
//			}
//			System.out.println();
//		}
//		newWorkbook.close();
//	
//	}
//	
	public void setFecha(String s) {
		fecha=s;
	}
	public String getFecha() {
		return fecha;
	}
	
	
	public void setDoc(String s) {
		doc=s;
	}
	public String getDoc() {
		return doc;
	}
	
	
	public void setNombre(String s) {
		nombre=s;
	}
	public String getNombre() {
		return nombre;
	}
	
	
	public void setDescrip(String s) {
		descrip=s;
	}
	public String getDescrip() {
		return descrip;
	}
	
	
	public void setPeso(String s) {
		peso=s;
	}
	public String getPeso() {
		return peso;
	}
	
	
	public void setPrecio(String s) {
		precio=s;
	}
	public String getPrecio() {
		return precio;
	}
	
	public void setAlic(String s) {
		alic=s;
	}
	public String getAlic() {
		return alic;
	}
	
	public boolean precioMasDeMill(){
		
		Float pre = Float.valueOf(precio);
		if(pre>=1000)
			return true;
		else
			return false;
	}
	
	public boolean es21Porc(){
		
		return alic.equals("s");
			
	}
	
	public void mostrar() {
		System.out.println(fecha + " || "+ descrip +  " || " + precio + " || " + doc + " || <" + alic + ">");
	}


}