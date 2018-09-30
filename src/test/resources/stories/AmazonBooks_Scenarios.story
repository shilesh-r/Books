Narrative:
In order to verify books information in Amazon
As a Tester
I want to test different scenarios to capture and test the books information

Scenario: 01. Capture Data Catalog books information in Amazon
Given Navigate to Amazon landing page
And Select Category in Search Dropdown
|Category	|
|Books		|
And Enter Search text
|SearchText		|
|data catalog	|
And Click on search button
When Capture book details from results
|ResultRow	|
|1			|
Then Verify the captured book details
