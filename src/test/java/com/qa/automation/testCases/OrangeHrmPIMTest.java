package com.qa.automation.testCases;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.automation.base.TestBase;
import com.qa.automation.pages.OrangeHrmPIMPage;
import com.qa.automation.util.UtilFunctions;

public class OrangeHrmPIMTest extends TestBase {
	
	@Test
	public void pimFunctionality() throws Exception {
		test = extent.createTest("Orange-HRM PIM functionality");
		test.assignCategory("Regression Test");
		
		OrangeHrmPIMPage pim = new OrangeHrmPIMPage();
		
		pim.PIMFunctionality();
	}
	
	@AfterMethod
	public void checkResult(ITestResult result) throws IOException {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getName() + "Test Failed");
			test.log(Status.FAIL, "Test case Failed is" + result.getThrowable());
			test.fail(MarkupHelper.createLabel(result.getName()+ "Test case Failed", ExtentColor.RED));
			
			String screenshotPath = UtilFunctions.getScreenshot(result.getName());
			test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			test.addScreenCaptureFromPath(screenshotPath);
		
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip(MarkupHelper.createLabel(result.getName() +"Test case skipped", ExtentColor.YELLOW));
			test.skip(result.getThrowable());
			
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass(MarkupHelper.createLabel("Test passed", ExtentColor.GREEN));
		}
	}
}
