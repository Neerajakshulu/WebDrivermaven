package com.w2a.utilities;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;

public class DataUtil {
	public static void checkExecution(String testSuiteName,String testCaseName,String dataRunMode,ExcelReader excel){
		if(!isSuiteRunnable(testSuiteName)){
			throw new SkipException("Skipping the test:"+testCaseName+"as the Runmode of test Suite:"+testSuiteName+"is NO");
			
		}
		if(!isTestRunnable(testSuiteName,excel)){
			throw new SkipException("Skipping the test:"+testCaseName+"as the Runmode of test Case:"+testCaseName+"is NO");
			
		}
		if(dataRunMode.equalsIgnoreCase(Constants.RUNMODE_NO)){
			throw new SkipException("Skipping the test:"+testCaseName+"as the Run Mode to data set is NO");
		}
	}
 public static boolean isSuiteRunnable(String suiteName){
	 ExcelReader excel=new ExcelReader(Constants.SUITE_XL_PATH);
	 int rows=excel.getRowCount(Constants.SUITE_SHEET);
	 for(int rowNum=2;rowNum<=rows;rowNum++){
		String data=excel.getCellData(Constants.SUITE_SHEET, Constants.SUITENAME_COL, rowNum);
		if(data.equals(suiteName)){
			String runmode=excel.getCellData(Constants.SUITE_SHEET, Constants.RUNMODE_COL, rowNum);
			if(runmode.equals(Constants.RUNMODE_YES))
				return true;
			else
				return false;
		}
	 }
	return false;
 }
 public static boolean isTestRunnable(String testCaseName,ExcelReader excel){
	 int rows=excel.getRowCount(Constants.TESTCASE_SHEET);
	 for(int rowNum=2;rowNum<=rows;rowNum++){
		String data=excel.getCellData(Constants.TESTCASE_SHEET, Constants.TESTCASE_COL, rowNum);
		if(data.equals(testCaseName)){
			String runmode=excel.getCellData(Constants.TESTCASE_SHEET, Constants.RUNMODE_COL, rowNum);
			if(runmode.equals(Constants.RUNMODE_YES))
				return true;
			else
				return false;
		}
	 }
	return false;
 }
 @DataProvider
	public static Object[][] getData(String testCase,ExcelReader excel){
	//ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\BankManagerSuite.xlsx");
	
	  int rows=excel.getRowCount(Constants.DATA_SHEET);
	  System.out.println("Total Rows are"+rows);
	  
	  String testName="AddCustomerTest";
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




