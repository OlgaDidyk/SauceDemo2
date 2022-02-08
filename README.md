## Saucedemo project. Page object pattern. Practice
 

[INFO] The following dependencies in Dependencies have newer versions  
[INFO]   org.seleniumhq.selenium:selenium-java ................. 4.1.0 -> 4.1.2  
[INFO]   org.testng:testng ....................................... 7.4.0 -> 7.5  

mvn clean test  
Tests run: 20, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 27.873 s - in TestSuite  

mvn test -Dtest=LoginTest  
mvn test -Dtest=LoginTest#validCredentialLogin  
mvn test -Dtest=LoginTest#validCredentialLogin+lockedUserTest  




Saucedemo is an online store created for practice 
automation testing. I used it for practice 
Page object pattern.  

### Checklist
|N|Page|Test name|Automated|
|---|----|----|------|
|1|Login page| Username field is required|+|
|2|Login page| Password field is required|+|
|3|Login page| Locked user don't have access to the app|+|
|4|Cart page| Product is added to the Cart|+|
|5|Cart page| Possible to make a purchase|+|
|6|Catalog page| Logout button moves a user to the Login page|+|
|7|Catalog page| Button title is changed after push|+|
|8|Catalog page| Empty cart has no counter|+|
|9|Catalog page| "Name (ZtoA)" sorting works correctly|+|
|10|Catalog page| "Name (AtoZ)" sorting works correctly|+|


