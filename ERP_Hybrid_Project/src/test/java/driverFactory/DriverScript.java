package driverFactory;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript extends FunctionLibrary{
	String inputpath = "./FileInput/Controller.xlsx";
	String outputpath = "./FileOutput/HybridResults.xlsx";
	String TestCases = "MasterTestCases";
	String TCModule = "";
	public void startTest() throws Throwable
	{
		String Module_Status="";
		// create object for excel file util class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//iterate all test cases in TestCases sheet
		for(int i=1;i<=xl.rowCount(TestCases);i++)
		{
			String Exe_status = xl.getCellData(TestCases, i, 2);
			if(Exe_status.equalsIgnoreCase("Y"))
			{
				TCModule = xl.getCellData(TestCases, i, 1);
				// iterate all over testcases
				for(int j=0; j<=xl.rowCount(TCModule); j++)
				{
					String Description = xl.getCellData(TCModule, j, 0);
					String Function_Name = xl.getCellData(TCModule, j, 1);
					String Locator_Type = xl.getCellData(TCModule, j, 2);
					String Locator_Value = xl.getCellData(TCModule, j, 3);
					String TestData =xl.getCellData(TCModule, j, 4);
					try
					{
						if(Function_Name.equalsIgnoreCase("startBrowser"))
						{
							driver = FunctionLibrary.startBrowser();
						}
						else if(Function_Name.equalsIgnoreCase("openUrl"))
						{
							 FunctionLibrary.openUrl(driver);
						}
						else if(Function_Name.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value,TestData); 

						}
						else if (Function_Name.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, TestData);
						}
						else if (Function_Name.equalsIgnoreCase("validateTitle"))
						{
							FunctionLibrary.validateTitle(driver, TestData);
						}
						else if(Function_Name.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
						}
						else if (Function_Name.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser(driver);
						}
						else if (Function_Name.equalsIgnoreCase("mouseClick"))
						{
							FunctionLibrary.mouseClick(driver);
						}
						else if(Function_Name.equalsIgnoreCase("categoryTable"))
						{
							FunctionLibrary.categoryTable(driver, TestData);
						}
						else if(Function_Name.equalsIgnoreCase("dropDownAction"))
						{
							FunctionLibrary.dropDownAction(driver, Locator_Type, Locator_Value, TestData);
						}
						else if(Function_Name.equalsIgnoreCase("captureData"))
						{
							FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
						}
						else if(Function_Name.equalsIgnoreCase("stockTable"))
						{
							FunctionLibrary.stockTable(driver);
						}
						else if(Function_Name.equalsIgnoreCase("supplierTable"))
						{
							FunctionLibrary.supplierTable(driver);
						}
						//write as pass in status cell
						xl.setCellData(TCModule, j, 5, "Pass", outputpath);
						Module_Status = "True";
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
						// write as fail in status cell
						xl.setCellData(TCModule, j, 5, "Fail", outputpath);
						Module_Status = "False";
					}
				}
				if(Module_Status.equalsIgnoreCase("True"))
				{
					xl.setCellData(TestCases, i, 3, "Pass", outputpath);
				}
				if(Module_Status.equalsIgnoreCase("Fail"))
				{
					xl.setCellData(TestCases, i, 3, "Fail", outputpath);
				}
				
			}
			else
			{
				// write as blocked into Testcases sheet for text case flag to N
				xl.setCellData(TestCases, i, 3, "Blocked", outputpath);
			}
					
		}
	}
}
