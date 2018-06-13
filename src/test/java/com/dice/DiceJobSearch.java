package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {

		// Set up chrome driver path
		WebDriverManager.chromedriver().setup();

		// invoke selenium webdriver
		WebDriver driver = new ChromeDriver();

		// full screen
		driver.manage().window().fullscreen();
		// set universal wait time in case web page
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		/*
		 * Step 1 Launch browser and navigate to http dice.com Expected: dice
		 * home page should be displayed
		 * 
		 */
		String keyword = "Java developer";
		String location = "22102";
		String url = "http://dice.com";
		driver.get(url);

		String actualTitle = driver.getTitle();
		String expetedTitle = "Job Search for Technology Professionals | Dice.com";

		if (actualTitle.equals(expetedTitle)) {
			System.out.println("Step PASS.Dice homepage successfully loaded ");

		} else {
			System.out.println("Step FAIL. Dice homepage did not open");
			throw new RuntimeException("Step FAILED. Dice homepage did not open");
		}

		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);

		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		driver.findElement(By.id("findTechJobs")).click();

		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);

		int countResult = Integer.parseInt(count.replace(",", ""));
		
		if (countResult > 0) {
			System.out.println("Step Pass: Keyword " + keyword + "search result" + countResult + "result in" + location);
		} else {
			System.out.println("Step FAIL: Keyword " + keyword + "search result" + countResult + "result in" + location);
		}

		driver.close();
		
		System.out.println("TEST COMPLETED"+LocalDateTime.now());
	}

}
