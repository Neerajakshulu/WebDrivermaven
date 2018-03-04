package rough;

import com.w2a.utilities.Constants;
import com.w2a.utilities.ExcelReader;

public class ReadingExcelData {
	public static void main(String args[]){
		ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\BankManagerSuite.xlsx");
	
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
	  for(int rNum=dataStartRowNum;rNum<dataStartRowNum+testRows;rNum++){
		  for(int cNum=0;cNum<testCols;cNum++){
			  System.out.println(excel.getCellData(Constants.DATA_SHEET, cNum, rNum));
		  }
	  }
	}

}
