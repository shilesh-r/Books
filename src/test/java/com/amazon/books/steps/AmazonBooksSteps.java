package com.amazon.books.steps;


import java.util.HashMap;
import java.util.List;

import org.jbehave.core.model.ExamplesTable;
import org.junit.Assert;

import com.amazon.books.pages.AmazonHomePage;
import com.amazon.books.pages.SearchResultsPage;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class AmazonBooksSteps extends ScenarioSteps{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	AmazonHomePage homePage;
	SearchResultsPage searchResultsPage;

	//Object to store book information from search results
	public HashMap<String, Object> bookDetailsFromResults = null;

	@Step
	public void navigateToAmazonLandingPage(){

		homePage.gotoAmazonLandingPage();

		System.out.println("Launched Amazon page");
	}

	@Step
	public void select_SearchCategory(ExamplesTable categoryData){

		String category = categoryData.getRow(0).get("Category")==null ? "":categoryData.getRow(0).get("Category");

		homePage.select_SearchCategory(category);
	}

	@Step
	public void enter_SearchText(ExamplesTable searchText){
		String searchTextValue = searchText.getRow(0).get("SearchText")== null ? "": searchText.getRow(0).get("SearchText");

		homePage.enter_SearchText(searchTextValue);
	}

	@Step
	public void click_SearchButton(){
		homePage.click_SearchButton();
	}

	@Step
	public void capture_BooksDetail_Results(ExamplesTable resultsRow){
		int row = resultsRow.getRow(0).get("ResultRow")==null? 1: Integer.valueOf(resultsRow.getRow(0).get("ResultRow"));

		//Verify at least one results displayed in results
		Assert.assertTrue("No Results displayed", searchResultsPage.get_SearchResultsCount()>0);

		bookDetailsFromResults = new HashMap<String, Object>();

		bookDetailsFromResults.put("BookImageAltText", searchResultsPage.get_ImageAltText(row));

		bookDetailsFromResults.put("BookTitle", searchResultsPage.get_BookTitle(row));		
		bookDetailsFromResults.put("PublishedDate", searchResultsPage.get_BookPublishedDate(row));
		bookDetailsFromResults.put("BookAuthors", searchResultsPage.get_BookAuthors(row));
		bookDetailsFromResults.put("IsEligibleForShippingToIndia", searchResultsPage.isEligibleForShippingtoIndia(row));
		bookDetailsFromResults.put("Editions", searchResultsPage.get_BookEditions_WithPrice_Stock(row));
		bookDetailsFromResults.put("MoreBuyingChoices", searchResultsPage.get_MoreBuyingChoices(row));
		bookDetailsFromResults.put("BookRatings", searchResultsPage.get_BookRatings(row));


	}

	@SuppressWarnings("unchecked")
	@Step
	public void verify_Captured_BookDetails(){

		Assert.assertNotNull("No Results captured", bookDetailsFromResults);

		Assert.assertTrue("Verify Book Title is not null", bookDetailsFromResults.get("BookTitle").toString().length()>0);
		Assert.assertEquals("Verify Image Alt text and Book title are same", bookDetailsFromResults.get("BookImageAltText").toString(), bookDetailsFromResults.get("BookTitle").toString());
		Assert.assertTrue("Verify Published date is not null", bookDetailsFromResults.get("PublishedDate").toString().length()>0);
		Assert.assertTrue("Verify atleast one author name displayed", ((List<String>)bookDetailsFromResults.get("BookAuthors")).size()>1);
		Assert.assertTrue("Verify atleast one edition with prize displayed", ((List<String>)bookDetailsFromResults.get("Editions")).size()>1);
	}

}
