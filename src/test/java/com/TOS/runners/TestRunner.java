package com.TOS.runners;

import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.qameta.allure.testng.AllureTestNg;

@CucumberOptions(features = "src/test/resources/features", glue = {
		"com.TOS.Stepdefinition" }, tags = "@component", plugin = { "pretty", "json:target/cucumber-reports/cucumber.json",
				"html:target/cucumber-reports/cucumberreport.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })

@Listeners(AllureTestNg.class)
public class TestRunner extends AbstractTestNGCucumberTests {

}
