package com.amazon.books.stepdefinitions;

import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import com.amazon.books.steps.AmazonBooksSteps;

import net.thucydides.core.annotations.Steps;

public class AmazonBooksScenarioSteps {

	@Steps
	AmazonBooksSteps amazonBooksSteps;

	//Set system property for Gecko Driver
	@BeforeStories
	public void initiateBrowser(){
		System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\browserdriver\\geckodriver.exe");
	}

	//Scenario Step definitions
	
	@Given("Navigate to Amazon landing page")
	public void givenNavigateToAmazonLandingPage(){
		amazonBooksSteps.navigateToAmazonLandingPage();
	}

	@Given("Select Category in Search Dropdown $category")
	public void select_Category_Search_Dropdown(ExamplesTable categoryData){
		amazonBooksSteps.select_SearchCategory(categoryData);
	}

	@Given("Enter Search text $searchValue")
	public void enter_SearchText(ExamplesTable searchValue){
		amazonBooksSteps.enter_SearchText(searchValue);
	}

	@Given("Click on search button")
	public void click_SearchButton(){
		amazonBooksSteps.click_SearchButton();
	}

	@When("Capture book details from results $resultRow")
	public void capture_BookDetails_From_Results(ExamplesTable resultRow){
		amazonBooksSteps.capture_BooksDetail_Results(resultRow);
	}

	@Then("Verify the captured book details")
	public void verify_Captured_Book_Details(){
		amazonBooksSteps.verify_Captured_BookDetails();
	}
}
