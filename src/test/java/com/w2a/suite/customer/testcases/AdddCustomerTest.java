package com.w2a.suite.customer.testcases;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.utilities.Constants;
import com.w2a.utilities.DataProviders;
import com.w2a.utilities.DataUtil;
import com.w2a.utilities.ExcelReader;

public class AdddCustomerTest {
	@Test(dataProviderClass=DataProviders.class,dataProvider="bankManagerDP")
	public void addCustomerTest(Hashtable<String,String> data){
		
		
	}
	
}