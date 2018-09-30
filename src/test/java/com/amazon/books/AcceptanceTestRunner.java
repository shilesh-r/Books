package com.amazon.books;

import java.util.Arrays;
import java.util.List;

import net.serenitybdd.jbehave.SerenityStories;

public class AcceptanceTestRunner extends SerenityStories{

	//Initialize test runner
	public AcceptanceTestRunner(){
		
	}
	
	//Include story file path in the runner
	@Override
	public List<String> storyPaths(){
		return Arrays
				.asList(
						"stories/AmazonBooks_Scenarios.story"
						);
				
	}
	
	//invoke run method
	@Override
	public void run() throws Throwable{
		super.run();
	}
}
