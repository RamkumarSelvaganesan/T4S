package com.TOS.Stepdefinition;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.TOS.utils.BasicFunctionsUtils;
import com.TOS.utils.DriverManager;
import com.TOS.utils.ExcelDataReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class Hooks {

	@Before
	public static void setUp(Scenario scenario) {
		DriverManager.getDriver();
		String featureUri = scenario.getUri().toString();
		String featureFileName = Paths.get(URI.create(featureUri)).getFileName().toString();
		System.out.println("Feature file name: " + featureFileName);
		ExcelDataReader.sheetName = featureFileName.substring(0, featureFileName.indexOf("."));
		System.out.println("sheetname" + ExcelDataReader.sheetName);
		System.out.println("scenario" + scenario.getName());
	}

	@After
	public static void tearDown(Scenario scenario) throws IOException, InterruptedException {
		Thread.sleep(3000);
		BasicFunctionsUtils.itemUniqueName = null;
		System.out.println("Enter into teardown method");
		if (scenario.isFailed()) {

			File screenshotAs = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			final byte[] screenshot = FileUtils.readFileToByteArray(screenshotAs);
			Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenshotAs));

//			byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());

			System.out.println("Enter into teardown method");
		}

		DriverManager.quitDriver();
	}
}