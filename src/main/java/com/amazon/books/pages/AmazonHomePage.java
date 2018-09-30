package com.amazon.books.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.thucydides.core.pages.PageObject;

public class AmazonHomePage extends PageObject{

	//Amazon.com url and search element references
	String amazonUrl = "http://www.amazon.com";
	String searchDropdown = "searchDropdownBox";
	String searchBox = "twotabsearchtextbox";
	String searchButton = "input[type='submit'][value='Go']";
	
	
	public AmazonHomePage(WebDriver driver){
		super(driver);
	}
	
	public void gotoAmazonLandingPage(){
		
		getDriver().manage().window().maximize();
		
		getDriver().get(amazonUrl);
		
		waitForPageLoad();
	}
	
	public void select_SearchCategory(String category){
		
		selectFromDropdown(getDriver().findElement(By.id(searchDropdown)), category);
		waitForPageLoad();
		
	}
	
	public void enter_SearchText(String value){
		typeInto(getDriver().findElement(By.id(searchBox)), value);
		waitForPageLoad();
	}
	
	public void click_SearchButton(){
		clickOn(getDriver().findElement(By.cssSelector(searchButton)));
		waitForPageLoad();
	}
	
	
	//method to wait till html document is fully loaded
	private void waitForPageLoad(){
		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(getDriver(),25);
		wait.until(pageLoadCondition);
	}
	
}
