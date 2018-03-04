package com.w2a.utilities;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestParameterization {

	@Test(dataProvider="getData")
	public void testData(Hashtable<String,String> data){
		System.out.println(data.get("Runmode")+"---"+data.get("customer")+"---"+data.get("currency"));
		//System.out.println(data.get("Runmode")+"---"+data.get("firstname")+"---"+data.get("lastname"));
		
	}
	@DataProvider
	public Object[][] getData(){
	ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\BankManagerSuite.xlsx");
	
	  int rows=excel.getRowCount(Constants.DATA_SHEET);
	  System.out.println("Total Rows are"+rows);
	  
	  String testName="OpenAccountTest";
	  //find the test case start
	  int testCaseRowNum=1;
	  for(testCaseRowNum=1;testCaseRowNum<=rows;testCaseRowNum++){
		  String testCasename=excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);
		  if(testCasename.equalsIgnoreCase(testName))
			  break;
	  }
	  System.out.println("Test case Starts From Row num:"+testCaseRowNum);
	  //Checking total Rows in test case
	  int dataStartRowNum=testCaseRowNum+2;
	  int testRows=0;
	  while(!excel.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum+testRows).equals("")){
		  testRows++;
	  }
	  
	  System.out.println("Total Rows of Data are "+testRows);
	  
	  //Checking total Cols in test case
	  int colStartColNum=testCaseRowNum+1;
	  int testCols=0;
	  while(!excel.getCellData(Constants.DATA_SHEET, testCols, colStartColNum).equals("")){
		  testCols++;
	  }
	  System.out.println("Total Cols are"+testCols);
	  //Printing Data
	  Object[][] data=new Object[testRows][1];
	  int i=0;
	  for(int rNum=dataStartRowNum;rNum<dataStartRowNum+testRows;rNum++){
		  Hashtable<String,String> table=new Hashtable<String, String>();
		  
		  for(int cNum=0;cNum<testCols;cNum++){
			  //System.out.println(excel.getCellData(Constants.DATA_SHEET, cNum, rNum));
			  String testData=excel.getCellData(Constants.DATA_SHEET, cNum, rNum);
			  String colname=excel.getCellData(Constants.DATA_SHEET, cNum, colStartColNum);
			  
			  table.put(colname, testData);

		  }
		  data[i][0]=table;
		  i++;
	  }
	  return data;
	}
}


