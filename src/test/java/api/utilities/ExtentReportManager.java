package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
//public class ExtentReportsClass {
//public WebDriver driver;

//public ExtentReports extent;
//public ExtentTest logger;

public class ExtentReportManager implements ITestListener{

	
	//public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public ExtentSparkReporter spark;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
    // Create an object of Extent Reports
         extent = new ExtentReports();
         repName="Test-Report";

          spark = new ExtentSparkReporter(".\\reports\\"+repName); //Specify the location of the report
           extent.attachReporter(spark);
           extent.setSystemInfo("Application", "Pest store Users API");
            extent.setSystemInfo("Environment", "QA");
           extent.setSystemInfo("User", "Bhuvanya");
           extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("User Name",System.getProperty("user.name"));
            spark.config().setDocumentTitle("Title of the Report Comes here ");
                // Name of the report
            spark.config().setReportName("Name of the Report Comes here ");
                // Dark Theme
             spark.config().setTheme(Theme.STANDARD);
}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}
