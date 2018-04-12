package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ExcelUtility {
	private static ExcelUtility singletonObj;
	 
	   
	//public static String filename = System.getProperty("user.dir")+"\\src\\config\\testcases\\TestData.xls";
	public  String Path;
	public  FileInputStream RFIS = null;
	public  FileOutputStream RFOS =null;
	
	public  CellStyle style;
	public  FileInputStream FIS = null;
	public  FileOutputStream FOS =null;
	public Workbook  WorkBook ;
	public Sheet  Sheet;
	public Row row;
	public Cell cell;

	public DataFormatter dfTestData;
	
	 public  String ResultExlFilepath;
	
	 
	 public  HashMap<String, String> loadTestData(String sheetName,String testScriptName)  {
		HashMap<String,String> dict=new HashMap<String,String>();
		 Row testDataRow = null;
			Row headerRow;
			try {

				List<String> colNames=new ArrayList<String>();
				Sheet = WorkBook.getSheet(sheetName);
				dfTestData = new DataFormatter();
				String headerExcelVal;
				Cell hederCellVal;
				String testDataExcelVal;
				Cell testDataCellVal;
				for (int rowIndex = 0; rowIndex <= Sheet.getLastRowNum(); rowIndex++) {
					  row = Sheet.getRow(rowIndex);
					  try {
						if(dfTestData.formatCellValue(row.getCell(0)).trim().equals(testScriptName)){
							  testDataRow=row;
							  break;
						  }
					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						System.out.println("There is no value in the cell, sheetname:"+sheetName+",row:"+rowIndex+", column="+0);
						continue;
					}
					  
				}
				
				if(testDataRow==null){
				return null;	
				}
				headerRow=Sheet.getRow(0);
				  if (headerRow == null){
						return null;	
					}
				  
				 int colCount= headerRow.getPhysicalNumberOfCells();
			    for (int colIndex = 0; colIndex < colCount; colIndex++) {
			    	try {
						hederCellVal = headerRow.getCell(colIndex);
						headerExcelVal=dfTestData.formatCellValue(hederCellVal).trim();
					} catch (NullPointerException e) {
						System.out.println("There is no value in the cell, sheetname:"+sheetName+",row:"+headerRow.getRowNum()+", column="+colIndex);
						continue;
					}
			    	try {
						testDataCellVal = testDataRow.getCell(colIndex);
						testDataExcelVal=dfTestData.formatCellValue(testDataCellVal).trim();
						dict.put(headerExcelVal, testDataExcelVal);
					} catch (NullPointerException e) {
						System.out.println("There is no value in the cell, sheetname:"+sheetName+",row:"+testDataRow.getRowNum()+", column="+colIndex);
						dict.put(headerExcelVal, "");
					}
			    	
			    }
					    
					  
					
			 return dict;
			 
			} 
			
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return null;
			}
			
		}
	 public ExcelUtility(String path){
		 this.Path=path;
	    	
	    	
			try {
				
					FIS = new FileInputStream(path);
					WorkBook = WorkbookFactory.create(FIS);
					
					Sheet = WorkBook.getSheetAt(0);
					row=Sheet.getRow(0);
					FIS.close();
				
					
			} catch (Exception e) {
				System.out.println("Exception in creating excelutility");
			} 
	    	
	 }
	 
	
	 /**
	     * Create private constructor
	     */
	  /*  private ExcelUtility(String path){
	    	this.Path=path;
	    	
	    	
			try {
				
					FIS = new FileInputStream(path);
					WorkBook = WorkbookFactory.create(FIS);
					
					Sheet = WorkBook.getSheetAt(0);
					row=Sheet.getRow(0);
					FIS.close();
				
					
			} catch (Exception e) {
				System.out.println("Exception in creating excelutility");
			} 
	    	
	    	
	    }
	    *//**
	     * Create a static method to get instance.
	     *//*
	    public static ExcelUtility getInstance(String path){
	        if(singletonObj == null){
	        	singletonObj = new ExcelUtility(path);
	        }
	        return singletonObj;
	    }*/
	    
	    /**
	     * Create a static method to get instance.
	     * @throws IOException 
	     */
	    public  ExcelUtility closeInstance(String path) throws IOException{
	        if(singletonObj != null){
	        	WorkBook.close();
	        	singletonObj=null;
	        	return null;
	        }
	        return null;
	    }
	// returns the row count in a sheet
	public int getRowCount(String sheetName){
		int index = WorkBook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
		Sheet = WorkBook.getSheetAt(index);
		int number=Sheet.getLastRowNum()+1;
		return number;
		}
		
	}
	
public List<String> getSheetNames(){
	
	int sheetNumber=WorkBook.getNumberOfSheets();
	String[] sheetNames = new String[sheetNumber];
	for(int i=0;i<sheetNumber;i++){
		sheetNames[i]=WorkBook.getSheetName(i);
	}
	
	return Arrays.asList(sheetNames);
}
	

public  String getCellVal(String sheetName,int rowNum,int colNum)  {
	
	try {
		
	Sheet = WorkBook.getSheet(sheetName);
	dfTestData = new DataFormatter();
	 row = Sheet.getRow(rowNum);
	 Cell cellVal = row.getCell(colNum);
	 String excelVal = dfTestData.formatCellValue(cellVal).trim();
	 return excelVal;
	 
	} 
	catch(NullPointerException e){
		System.out.println("There is no value in the cell, sheetname:"+sheetName+",row:"+rowNum+", column="+colNum);
		return "";
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
		return null;
	}
	
}

public  List<String> getColNames(String sheetName)  {
	
	try {
	List<String> colNames=new ArrayList<String>();
	Sheet = WorkBook.getSheet(sheetName);
	dfTestData = new DataFormatter();
	 String excelVal;
	Cell cellVal;
	for (int rowIndex = 0; rowIndex <= Sheet.getLastRowNum(); rowIndex++) {
		  row = Sheet.getRow(rowIndex);
		  if (row != null) {
			 int colCount= row.getPhysicalNumberOfCells();
		    for (int colIndex = 0; colIndex < colCount; colIndex++) {
		    	cellVal = row.getCell(colIndex);
		    	excelVal=dfTestData.formatCellValue(cellVal).trim();
		    	colNames.add(excelVal);
		    }
		    break;
		  }
		}
	return colNames;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	
	}
	
	}
public  List<String> getColNames()  {
	
	try {
		Sheet=WorkBook.getSheetAt(0);
		List<String> colList=getColNames(Sheet.getSheetName());
		return colList;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	
	}
	
	}



public int getRowCount(){
	Sheet=WorkBook.getSheetAt(0);
	int rowCount=getRowCount(Sheet.getSheetName());
	return rowCount;
}

public  List<String> getRowValues(String sheetName,int rowNum)  {
	
	try {
	List<String> colNames=new ArrayList<String>();
	Sheet = WorkBook.getSheet(sheetName);
	dfTestData = new DataFormatter();
	 String excelVal;
	Cell cellVal;

	  row = Sheet.getRow(rowNum);
	  if (row != null) {
		 int colCount= row.getPhysicalNumberOfCells();
	    for (int colIndex = 0; colIndex < colCount; colIndex++) {
	    	cellVal = row.getCell(colIndex);
	    	excelVal=dfTestData.formatCellValue(cellVal).trim();
	    	colNames.add(excelVal);
	    }
	    
	  }
		
	return colNames;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	
	}
	
	}	
	public boolean deleteFile(String path){
		File file = new File(path);

		if(file.delete()){
			System.out.println(file.getName() + " is deleted!");
			return true;
		}else{
			System.out.println("Delete operation is failed.");
			return false;
		}
		
	}
	
	public boolean deleteFile(){
		return deleteFile(this.Path);
	}

	public  List<String> getColumnValues(String sheetName,String columnName,int columnHeaderRow){
		ArrayList<String> colValues=new ArrayList<String>();
		Sheet = WorkBook.getSheet(sheetName);
		colValues=(ArrayList<String>) getColumnValues(sheetName,columnName,columnHeaderRow,Sheet.getLastRowNum());
		return colValues;
	}
	
	public  List<String> getColumnValues(String columnName){
		ArrayList<String> colValues=new ArrayList<String>();
		Sheet = WorkBook.getSheetAt(0);
		colValues=(ArrayList<String>) getColumnValues(Sheet.getSheetName(),columnName,0,Sheet.getLastRowNum());
		return colValues;
	}
	
	public  List<String> getColumnValues(String sheetName,String columnName){
		ArrayList<String> colValues=new ArrayList<String>();
		Sheet = WorkBook.getSheet(sheetName);
		colValues=(ArrayList<String>) getColumnValues(sheetName,columnName,0,Sheet.getLastRowNum());
		return colValues;
	}
	
	public  List<String> getColumnValues(String sheetName,String columnName,int columnHeaderRow, int columnEndIndex)  {
		ArrayList<String> colValues=new ArrayList<String>();
		try {
			boolean flagColFound=false;
			int colFoundIndex=0;
		Sheet = WorkBook.getSheet(sheetName);
		dfTestData = new DataFormatter();
		 row = Sheet.getRow(columnHeaderRow);
		 String excelVal;
			Cell cellVal;
		  if (row == null){
			  System.out.println("There is no data present at row num:"+columnHeaderRow);
			  return colValues;
		  }
			  
			 int colCount= row.getPhysicalNumberOfCells();
		    for (int colIndex = 0; colIndex < colCount; colIndex++) {
		    	cellVal = row.getCell(colIndex);
		    	excelVal=dfTestData.formatCellValue(cellVal).trim();
		    	if(excelVal.replaceAll(" ", "").equalsIgnoreCase(columnName.replaceAll(" ", ""))){
		    		colFoundIndex=colIndex;
		    		flagColFound=true;
		    		break;
		    	}
		    }
		    
		    if(flagColFound==false){
		    	System.out.println("no column exist in sheet:"+sheetName +"with the given column name:"+columnName+" at rowIndex:"+columnHeaderRow );
		    	return colValues;
		    }
		    
		  
		  
		  for(int rowItr=columnHeaderRow+1;rowItr<=columnEndIndex;rowItr++){
			try{
			  row = Sheet.getRow(rowItr);
			  cellVal = row.getCell(colFoundIndex);
			  excelVal = dfTestData.formatCellValue(cellVal).trim();
			  colValues.add(excelVal);
			  }catch(NullPointerException e){
				  colValues.add("");
			  }
		  }
		 
		  return colValues;
		 
		} 
		catch(NullPointerException e){
			System.out.println("There is no value in the cell");
			return colValues;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return colValues;
		}
		
	}
	
	public  String getColumnValue(String sheetName,String columnName,int cellrowNum){
		Sheet = WorkBook.getSheet(sheetName);
		String colValue=getColumnValue(sheetName,columnName,cellrowNum,0);
		return colValue;
	}
	public  String getColumnValue(String sheetName,String columnName,int cellrowNum,int columnHeaderRow)  {
		String colValue="";
		try {
			boolean flagColFound=false;
			int colFoundIndex=0;
		Sheet = WorkBook.getSheet(sheetName);
		dfTestData = new DataFormatter();
		 row = Sheet.getRow(columnHeaderRow);
		 String excelVal;
			Cell cellVal;
		  if (row == null){
			  System.out.println("There is no data present at row num:"+columnHeaderRow);
			  return colValue;
		  }
			  
			 int colCount= row.getPhysicalNumberOfCells();
		    for (int colIndex = 0; colIndex < colCount; colIndex++) {
		    	cellVal = row.getCell(colIndex);
		    	excelVal=dfTestData.formatCellValue(cellVal).trim();
		    	if(excelVal.replaceAll(" ", "").equalsIgnoreCase(columnName.replaceAll(" ", ""))){
		    		colFoundIndex=colIndex;
		    		flagColFound=true;
		    		break;
		    	}
		    }
		    
		    if(flagColFound==false){
		    	System.out.println("no column exist in sheet:"+sheetName +"with the given column name:"+columnName+" at rowIndex:"+columnHeaderRow );
		    	return colValue;
		    }
		    
		  if(columnHeaderRow>=cellrowNum){
			  System.out.println("The row number provided for cell value is greater than header row number");
			  return null;
		  }
		  
		  row = Sheet.getRow(cellrowNum);
		  cellVal = row.getCell(colFoundIndex);
		  colValue = dfTestData.formatCellValue(cellVal).trim();
		 
		  return colValue;
		 
		} 
		catch(NullPointerException e){
			System.out.println("There is no value in the cell");
			return colValue;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return colValue;
		}
		
	}
}

