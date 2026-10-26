package core;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        Log.info("Test dimulai: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("Test BERHASIL: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.error("Test GAGAL: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.warn("Test DILEWATI: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}
}