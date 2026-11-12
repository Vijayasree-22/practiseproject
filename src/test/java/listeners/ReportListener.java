
package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import reporting.ExtentManager;
import reporting.ExtentTestManager;

public class ReportListener implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();

    // Runs when TestNG context starts
    @Override
    public void onStart(ITestContext context) {

        System.out.println("\n");
        System.out.println("==================================================");
        System.out.println("TEST SUITE EXECUTION STARTED");
        System.out.println("Context Name : " + context.getName());
        System.out.println("==================================================");
    }

    // Runs before each test method
    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = extent.createTest(result.getName());

        ExtentTestManager.setTest(test);

        String description = getDescription(result);

        test.info("Test execution started");
        test.info("Description: " + description);

        System.out.println("\n");
        System.out.println("--------------------------------------------------");
        System.out.println("TEST STARTED");
        System.out.println("Test Name   : " + result.getName());
        System.out.println("Description : " + description);
        System.out.println("--------------------------------------------------");
    }

    // Runs when test passes
    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTestManager.getTest().pass("Test Passed Successfully");

        System.out.println("[PASS] " + result.getName());
        System.out.println("--------------------------------------------------");

        ExtentTestManager.unload();
    }

    // Runs when test fails
    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTestManager.getTest().fail("Test Failed");

        if (result.getThrowable() != null) {

            ExtentTestManager.getTest()
                    .fail(result.getThrowable());
        }

        System.out.println("[FAIL] " + result.getName());

        if (result.getThrowable() != null) {
            System.out.println("       Failure Reason: "
                    + result.getThrowable().getMessage());
        }

        System.out.println("--------------------------------------------------");

        ExtentTestManager.unload();
    }

    // Runs when test is skipped
    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTestManager.getTest().skip("Test Skipped");

        if (result.getThrowable() != null) {

            ExtentTestManager.getTest()
                    .skip(result.getThrowable());
        }

        System.out.println("[SKIPPED] " + result.getName());

        if (result.getThrowable() != null) {
            System.out.println("          Reason: "
                    + result.getThrowable().getMessage());
        }

        System.out.println("--------------------------------------------------");

        ExtentTestManager.unload();
    }

    // Runs when TestNG context finishes
    @Override
    public void onFinish(ITestContext context) {

        System.out.println("\n");
        System.out.println("==================================================");
        System.out.println("TEST EXECUTION SUMMARY");
        System.out.println("Context Name : " + context.getName());
        System.out.println("Passed Tests : " + context.getPassedTests().size());
        System.out.println("Failed Tests : " + context.getFailedTests().size());
        System.out.println("Skipped Tests: " + context.getSkippedTests().size());
        System.out.println("==================================================");
        System.out.println("TEST EXECUTION FINISHED");
        System.out.println("==================================================\n");

        extent.flush();
    }

    // Reusable method to get test description
    private String getDescription(ITestResult result) {

        String description = result.getMethod().getDescription();

        if (description == null || description.trim().isEmpty()) {
            return "No description provided";
        }

        return description;
    }

    // Runs when test fails within success percentage
    @Override
    public void onTestFailedButWithinSuccessPercentage(
            ITestResult result) {

        ExtentTestManager.getTest().warning(
                "Partial test failure within success percentage");

        System.out.println("[PARTIAL FAILURE] " + result.getName());

        System.out.println("                    Description: "
                + getDescription(result));
    }
}
