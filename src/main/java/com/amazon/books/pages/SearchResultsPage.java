package com.amazon.books.pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.thucydides.core.pages.PageObject;

public class SearchResultsPage extends PageObject{

	//xpath and css locator references for the results rows
	String resultsRecords = "ul[id='s-results-list-atf'] li";
	String resultsBaseElement = "ul[id='s-results-list-atf'] li[id='result_#row#'] div div div ";
	String resultsBaseElementxpath = "//li[@id='result_#row#']";
	String resultsImageAltText = "div:nth-child(1) div:nth-child(1) a img";
	String resultsBookTitle = "div:nth-child(2) div:nth-child(1) div:nth-child(1) a h2";
	String bookPublishedDate = "div:nth-child(2) div:nth-child(1) div:nth-child(1) span:nth-child(4)";
	String bookAuthor = "div:nth-child(2) div:nth-child(1) div:nth-child(2) span[class='a-size-small a-color-secondary']";
	String bookSpan = "div:nth-child(2) span";
	String bookEditions = "//a/h3";
	String editionPriceSpan = "//a/h3[contains(text(),'#Edition#')]/../../following-sibling::div[1]/a";
	String bookStockDelivery = "//a/h3[contains(text(),'#Edition#')]/../../following-sibling::div[2]/span";
	String moreBuyingChoices = "//span[contains(text(),'More Buying Choices')]/../following-sibling::div[1]/a";
	String bookRating = "//span/a/i[1]/span";

	String shippingText = "Eligible for Shipping to India";


	public int get_SearchResultsCount(){
		try{
			return getDriver().findElements(By.cssSelector(resultsRecords)).size();
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}

	//Methods to get row specific data

	public String get_ImageAltText(int row){
		String altText = "";
		try{
			altText = getDriver().findElement(By.cssSelector(resultsBaseElement.replace("#row#", String.valueOf(row-1)) + resultsImageAltText)).getAttribute("alt");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return altText;
	}

	public String get_BookTitle(int row){
		String bookTitle = "";
		try{
			bookTitle = getDriver().findElement(By.cssSelector(resultsBaseElement.replace("#row#", String.valueOf(row-1)) + resultsBookTitle)).getAttribute("innerText");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return bookTitle;
	}

	public String get_BookPublishedDate(int row){
		String publishDate = "";
		try{
			publishDate = getDriver().findElement(By.cssSelector(resultsBaseElement.replace("#row#", String.valueOf(row-1)) + bookPublishedDate)).getAttribute("innerText");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return publishDate;
	}

	public List<String> get_BookAuthors(int row){
		List<String> bookAuthors = new ArrayList<String>();
		List<WebElement> authors = getDriver().findElements(By.cssSelector(resultsBaseElement.replace("#row#", String.valueOf(row-1)) + bookAuthor));
		if(authors.size()>1){
			for(int index=1; index<authors.size(); index++){
				bookAuthors.add(authors.get(index).getAttribute("innerText").replace(" and", ""));
			}
		}
		else
			bookAuthors.add("Author information not available");		

		return bookAuthors;
	}

	public boolean isEligibleForShippingtoIndia(int row){

		boolean isEligible = false;
		try{

			List<WebElement> bookSpanElements = getDriver().findElements(By.cssSelector(resultsBaseElement.replace("#row#", String.valueOf(row-1)) + bookSpan)); 

			for(int index=0; index<bookSpanElements.size(); index++){
				if(bookSpanElements.get(index).getAttribute("innerText").contains(shippingText)){
					isEligible = true;
					break;
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return isEligible;
	}

	public List<LinkedHashMap<String, String>> get_BookEditions_WithPrice_Stock(int row){

		List<LinkedHashMap<String, String>> bookEditionsData = new ArrayList<LinkedHashMap<String, String>>();;

		LinkedHashMap<String, String> bookEditionData = null;

		List<WebElement> editions = getDriver().findElements(By.xpath(resultsBaseElementxpath.replace("#row#", String.valueOf(row-1)) + bookEditions));

		for(WebElement edition: editions){
			bookEditionData = new LinkedHashMap<String, String>();

			bookEditionData.put("Edition", edition.getAttribute("innerText"));

			try{
				bookEditionData.put("EditionPrice", getDriver().findElement(By.xpath(resultsBaseElementxpath.replace("#row#", String.valueOf(row-1)) + editionPriceSpan.replace("#Edition#", edition.getAttribute("innerText")) + "/span[2]/span/sup[1]")).getAttribute("innerText")
						+ getDriver().findElement(By.xpath(resultsBaseElementxpath.replace("#row#", String.valueOf(row-1)) + editionPriceSpan.replace("#Edition#", edition.getAttribute("innerText")) + "/span[2]/span/span")).getAttribute("innerText") + "."
						+ getDriver().findElement(By.xpath(resultsBaseElementxpath.replace("#row#", String.valueOf(row-1)) + editionPriceSpan.replace("#Edition#", edition.getAttribute("innerText")) + "/span[2]/span/sup[2]")).getAttribute("innerText"));
			}
			catch(Exception ex){
				ex.printStackTrace();
				bookEditionData.put("EditionPrice", getDriver().findElement(By.xpath(resultsBaseElementxpath.replace("#row#", String.valueOf(row-1)) + editionPriceSpan.replace("#Edition#", edition.getAttribute("innerText"))+ "/span")).getAttribute("innerText"));
			}

			bookEditionData.put("StockAndDelivery", getDriver().findElement(By.xpath(resultsBaseElementxpath.replace("#row#", String.valueOf(row-1)) + bookStockDelivery.replace("#Edition#", edition.getAttribute("innerText")))).getAttribute("innerText"));

			bookEditionsData.add(bookEditionData);
		}

		return bookEditionsData;
	}

	public String get_MoreBuyingChoices(int row){

		String buyingChoices = "";

		try{
			buyingChoices = getDriver().findElement(By.xpath(resultsBaseElementxpath.replace("#row#", String.valueOf(row-1)) + moreBuyingChoices)).getAttribute("innerText");
		}
		catch(Exception ex){
			ex.printStackTrace();
			buyingChoices = "buying choices not available";
		}

		return buyingChoices;
	}

	public String get_BookRatings(int row){

		String bookRatings = "";

		try{
			bookRatings = getDriver().findElement(By.xpath(resultsBaseElementxpath.replace("#row#", String.valueOf(row-1)) + bookRating)).getAttribute("innerText");
		}
		catch(Exception ex){
			ex.printStackTrace();
			bookRatings = "Book Ratings not available";
		}

		return bookRatings;
	}
}
