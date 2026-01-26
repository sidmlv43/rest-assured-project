package com.comcast.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExtentReportManager implements ITestListener {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static ThreadLocal<ExtentTest> testNode = new ThreadLocal<>();
    private static Map<String, ExtentTest> testSuiteMap = new ConcurrentHashMap<>();
    private static String reportName;

    public synchronized static ExtentReports getExtentInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
            reportName = "Extent-Report-" + timestamp;
            String reportPath = System.getProperty("user.dir") + "/reports/" + reportName;

            sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Test Execution Summary");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Host Name", "LocalHost");
            extent.setSystemInfo("env", "QA");
            extent.setSystemInfo("User", System.getProperty("user.name"));
        }

        return extent;
    }

    @Override
    public void onStart(ITestContext context) {
        String suitName = context.getSuite().getName();
        ExtentTest suitNode = getExtentInstance().createTest(suitName);
        testSuiteMap.put(context.getName(), suitNode);
        suitNode.assignCategory("Suite: " + suitName);
        suitNode.info("Suite Started: " + suitName);

    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest suiteNode = testSuiteMap.get(result.getTestContext().getName());
        ExtentTest methodNode = suiteNode.createNode(result.getMethod().getMethodName());
        methodNode.assignCategory(result.getTestContext().getName());
        testNode.set(methodNode);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = testNode.get();
        test.log(Status.PASS, result.getMethod().getMethodName() + "Passed");
        test.info("Execution Time: " + getExecutionTime(result) + " ms");
    }

    @Override
    public void onTestFailure(ITestResult result) {
       ExtentTest test = testNode.get();
       test.log(Status.FAIL, result.getMethod().getMethodName());
       test.log(Status.FAIL, result.getThrowable());
        test.info("Execution Time: " + getExecutionTime(result) + " ms");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = testNode.get();
        test.log(Status.SKIP, result.getMethod().getMethodName());
        test.info("Reason: " + result.getThrowable());
        test.info("Execution Time: " + getExecutionTime(result) + " ms");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onFinish(ITestContext context) {
      ExtentTest suiteNode = testSuiteMap.get(context.getName());
      suiteNode.info("Suite Finished" + context.getSuite().getName());
      suiteNode.info("Passed: " + context.getPassedTests().size());
      suiteNode.info("Failed: " + context.getFailedTests().size());
      suiteNode.info("Skipped: " + context.getSkippedTests().size());

      getExtentInstance().flush();
      openReport();
    }

    private void openReport() {
        try {
            File reportFile = new File(System.getProperty("user.dir") + "/reports/" + reportName);
            Desktop.getDesktop().browse(reportFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long getExecutionTime(ITestResult result) {
        return result.getEndMillis() - result.getStartMillis();
    }
}
