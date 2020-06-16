package tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/*import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;*/
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class QCILPPayment extends QCStore
{

	public static void payment(String SSN,String AppURL)
	{
		int i;
		for(i=0;i<3;i++)
		{
		
	try{
			int lastrow=TestData.getLastRow("Payment");
			String sheetName="Payment";

			for(int row=2;row<=lastrow;row++)
			{		
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				if(SSN.equals(RegSSN))
				{
					String TxnType = TestData.getCellData(sheetName,"TxnType",row);
					String TenderType = TestData.getCellData(sheetName,"TenderType",row);
					String PaymentAmount = TestData.getCellData(sheetName,"PaymentAmount",row);
					String PIN = TestData.getCellData(sheetName,"Password",row);
					String TenderAmount = TestData.getCellData(sheetName,"TenderAmount",row);	
					String PaymentType = TestData.getCellData(sheetName,"PaymentType",row);
					String CCMONbr = TestData.getCellData(sheetName,"CCMONbr",row);
					String CardType = TestData.getCellData(sheetName,"Card Type ",row);
					String CardNbr = TestData.getCellData(sheetName,"Debit Card No",row);
					String ExpMonth = TestData.getCellData(sheetName,"Expiry Month",row);
					String ExpYear = TestData.getCellData(sheetName,"Expiry Year",row);
					String CVVNbr = TestData.getCellData(sheetName,"CVV",row);
					String CCName = TestData.getCellData(sheetName,"CardHolderName",row);
					String CheckNO = TestData.getCellData(sheetName,"ChkgAcctNbr_lastfour",row);

					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);

					Thread.sleep(3000);
					test.log(LogStatus.INFO,"PartialPayment started");
					driver.switchTo().frame("topFrame");
					driver.findElement(locator(prop.getProperty("transactions_tab"))).click();
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");
					
					

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");

					driver.findElement(By.cssSelector("li[id='911101']")).click();
					test.log(LogStatus.PASS, "Clicked on Transaction");		
					driver.switchTo().frame("main");	
					Thread.sleep(500);
					driver.findElement(By.name("ssn1")).sendKeys(SSN1);
					test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
					driver.findElement(By.name("ssn2")).sendKeys(SSN2);
					test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
					driver.findElement(By.name("ssn3")).sendKeys(SSN3);
					test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
					driver.findElement(locator(prop.getProperty("csr_new_loan_submit_button"))).click();
					test.log(LogStatus.PASS, "Clicked on submit Button");		
								
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");					    					   					     
					driver.findElement(locator(prop.getProperty("csr_new_loan_go_button"))).click();
					test.log(LogStatus.PASS, "Clicked on GO Button under search results");
					Thread.sleep(5000);					  
						
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					test.log(LogStatus.PASS, "Clicked on GO Button Under Product web table");
					Thread.sleep(5000);
					driver.findElement(By.name("transactionList")).sendKeys(TxnType);
					test.log(LogStatus.PASS, "Transaction Type is selected as :" +TxnType);
					driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Clicked on Go button ");
					Thread.sleep(500);	
					
					if(PaymentType.equalsIgnoreCase("Pay any other Amt")){
					
					driver.findElement(By.id("PD5")).click();
					test.log(LogStatus.PASS, "Clicked on pay other amount check box ");
					
					driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys(PaymentAmount);
					test.log(LogStatus.PASS, "PaymentAmount entered :"+PaymentAmount);
					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is :"+TenderType);
					
					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TenderAmount);
					test.log(LogStatus.PASS, "TenderAmount is :"+TenderAmount);	
					}
					else if(PaymentType.equalsIgnoreCase("Pay Off the balance")){
						driver.findElement(By.id("PD3")).click();
						test.log(LogStatus.PASS, "Clicked on pay other amount check box ");
						TenderAmount=driver.findElement(By.name("payOff")).getAttribute("value");
						if(TenderType.equalsIgnoreCase("Check"))
						{
					    driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
					    test.log(LogStatus.PASS, "Tender Type is :"+TenderType);
						driver.findElement(By.name("requestBean.siilBean.checkNbrFirst")).sendKeys(CheckNO);
						driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TenderAmount);
					    test.log(LogStatus.PASS, "TenderAmount is :"+TenderAmount);	
						}
						if(TenderType.equalsIgnoreCase("Cash"))
						{
						driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
						test.log(LogStatus.PASS, "Tender Type is :"+TenderType);
						driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TenderAmount.trim());
						test.log(LogStatus.PASS, "TenderAmount is :"+TenderAmount);	
						}
					}
					else if(PaymentType.equalsIgnoreCase("Pay Installment Amt")){
						driver.findElement(By.id("PD4")).click();
						test.log(LogStatus.PASS, "Clicked on Pay Installment Amt check box ");
						TenderAmount=driver.findElement(By.name("instAmt")).getAttribute("value");
						
						driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
						test.log(LogStatus.PASS, "Tender Type is :"+TenderType);
						driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TenderAmount.trim());
						test.log(LogStatus.PASS, "TenderAmount is :"+TenderAmount);	
					}
					if(TenderType.equalsIgnoreCase("Debit Card"))
					{
					 
					
						Thread.sleep(2000);
						/*driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(CardType);
						 test.log(LogStatus.PASS, " Tender amount " +CardNbr);*/
						driver.findElement(By.name("cardType")).sendKeys(CardType);
					 test.log(LogStatus.PASS, " Enter the Card Number " +CardNbr);
					 driver.findElement(By.name("ccnumber")).sendKeys(CardNbr);
					 test.log(LogStatus.PASS, " Enter the Card Number " +CardNbr);
					 driver.findElement(By.name("expmonth")).sendKeys(ExpMonth);
					 test.log(LogStatus.PASS, " Enter the Exp Month " +ExpMonth);
					 driver.findElement(By.name("expyear")).sendKeys(ExpYear);
					 test.log(LogStatus.PASS, " Enter the Exp Year " +ExpYear);
					 Thread.sleep(500);
					 driver.findElement(By.id("cvvnumber")).sendKeys(CVVNbr);
					 test.log(LogStatus.PASS, " Enter the CVV number " +CVVNbr);
					 driver.findElement(By.name("ccname")).sendKeys(CCName);
					 test.log(LogStatus.PASS, " Enter the card holder name " +CCName);
						//driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TenderAmount);
						//test.log(LogStatus.PASS, "TenderAmount is :"+TenderAmount);	
			
					}
					
					if(TenderType.equalsIgnoreCase("Cash"))
					{
					 
					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TenderAmount);
					test.log(LogStatus.PASS, "TenderAmount is :"+TenderAmount);	
					}
					if(TenderType.equalsIgnoreCase("Check"))
					{
					 
					driver.findElement(By.name("requestBean.siilBean.checkNbrFirst")).sendKeys(CheckNO);
					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TenderAmount);
				    test.log(LogStatus.PASS, "TenderAmount is :"+TenderAmount);	
					}
					driver.findElement(By.name("requestBean.password")).sendKeys(PIN);
					test.log(LogStatus.PASS, "Pin is entered as "+ PIN);
					driver.findElement(By.name("finish")).click();
					test.log(LogStatus.PASS, "Clicked on Finish Partial Payment");

					Thread.sleep(5000);
					try { 
						Alert alert = driver.switchTo().alert();

						alert.accept();
						test.log(LogStatus.PASS, "Alert Handled successfully");
																				
					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}
					Thread.sleep(4000);
					if(driver.findElement(By.name("checkno")).isDisplayed())
					{
					test.log(LogStatus.PASS, "Payment transaction is successfully");
					}
					test.log(LogStatus.PASS,"****************************************");
					break;
					
				}
					}
		
break;

}

				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//test.log(LogStatus.FAIL, MarkupHelper.createLabel("Getting Encryption from Admin is failed", ExtentColor.RED));
					test.log(LogStatus.INFO,"Exception occured "+e.toString().substring(0, 250));
					test.log(LogStatus.INFO, "Patial Payment process is initiated again due to Application sync issue");
					driver.get(prop.getProperty("login_page")); 
					continue;


				}

	}
		if(i==3)
		{
			test.log(LogStatus.FAIL, " Partail Payment setup is failed");
			Assert.assertTrue(false);
	
		}
	}
	public static void paymentTwice(String SSN,String AppURL) throws Exception
	{
		int i;
		for(i=0;i<3;i++)
		{
		
	try{
			int lastrow=TestData.getLastRow("PaymentTwice");
			String sheetName="PaymentTwice";

			for(int row=2;row<=lastrow;row++)
			{		
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				if(SSN.equals(RegSSN))
				{
					String TxnType = TestData.getCellData(sheetName,"TxnType",row);
					String TenderType = TestData.getCellData(sheetName,"TenderType",row);
					String PaymentAmount = TestData.getCellData(sheetName,"PaymentAmount",row);
					String PIN = TestData.getCellData(sheetName,"Password",row);
					String TenderAmount = TestData.getCellData(sheetName,"TenderAmount",row);	
					String PaymentType = TestData.getCellData(sheetName,"PaymentType",row);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);

					Thread.sleep(3000);
					test.log(LogStatus.INFO,"PartialPayment started");
					driver.switchTo().frame("topFrame");
					driver.findElement(locator(prop.getProperty("transactions_tab"))).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");

					driver.findElement(By.cssSelector("li[id='911101']")).click();
					test.log(LogStatus.PASS, "Clicked on Transaction");		
					driver.switchTo().frame("main");	
					Thread.sleep(500);
					driver.findElement(By.name("ssn1")).sendKeys(SSN1);
					test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
					driver.findElement(locator(prop.getProperty("CSR_SSN_second_field"))).sendKeys(SSN2);
					test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
					driver.findElement(locator(prop.getProperty("CSR_SSN_third_field"))).sendKeys(SSN3);
					test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
					driver.findElement(locator(prop.getProperty("csr_new_loan_submit_button"))).click();
					test.log(LogStatus.PASS, "Clicked on submit Button");		
								
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");					    					   					     
					driver.findElement(locator(prop.getProperty("csr_new_loan_go_button"))).click();
					test.log(LogStatus.PASS, "Clicked on GO Button under search results");
					Thread.sleep(5000);					  
						
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					test.log(LogStatus.PASS, "Clicked on GO Button Under Product web table");
					Thread.sleep(5000);
					driver.findElement(By.name("transactionList")).sendKeys(TxnType);
					test.log(LogStatus.PASS, "Transaction Type is selected as :" +TxnType);
					driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Clicked on Go button ");
					Thread.sleep(500);	
					
					if(PaymentType.equalsIgnoreCase("Pay any other Amt")){
					
					driver.findElement(By.id("PD5")).click();
					test.log(LogStatus.PASS, "Clicked on pay other amount check box ");
					
					driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys(PaymentAmount);
					test.log(LogStatus.PASS, "PaymentAmount entered :"+PaymentAmount);
					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is :"+TenderType);
					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TenderAmount);
					test.log(LogStatus.PASS, "TenderAmount is :"+TenderAmount);	
					}
					else if(PaymentType.equalsIgnoreCase("Pay Off the balance")){
						driver.findElement(By.id("PD3")).click();
						test.log(LogStatus.PASS, "Clicked on Pay Off the balance check box ");
						
						
						driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
						test.log(LogStatus.PASS, "Tender Type is :"+TenderType);
						String TAmoount=driver.findElement(By.name("payOff")).getAttribute("value");
						driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TAmoount.trim());
						test.log(LogStatus.PASS, "TenderAmount is :"+TAmoount);	
					}
					else if(PaymentType.equalsIgnoreCase("Pay Installment Amt")){
						driver.findElement(By.id("PD4")).click();
						test.log(LogStatus.PASS, "Clicked on Pay Installment Amt check box ");
						TenderAmount=driver.findElement(By.name("instAmt")).getAttribute("value");
						
						driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
						test.log(LogStatus.PASS, "Tender Type is :"+TenderType);
						driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TenderAmount.trim());
						test.log(LogStatus.PASS, "TenderAmount is :"+TenderAmount);	
					}
					driver.findElement(By.name("requestBean.password")).sendKeys(PIN);
					test.log(LogStatus.PASS, "Pin is entered as "+ PIN);
					driver.findElement(By.name("finish")).click();
					test.log(LogStatus.PASS, "Clicked on Finish Partial Payment");

					Thread.sleep(5000);
					try { 
						Alert alert = driver.switchTo().alert();

						alert.accept();
						test.log(LogStatus.PASS, "Alert Handled successfully");
																				
					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}
					Thread.sleep(4000);
					//if(driver.findElement(By.name("Ok")).isDisplayed())
					{
					test.log(LogStatus.PASS, "Payment transaction is successfully");
					}
					test.log(LogStatus.PASS,"****************************************");
					break;
					
				}
					}
		
break;

}

				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//test.log(LogStatus.FAIL, MarkupHelper.createLabel("Getting Encryption from Admin is failed", ExtentColor.RED));
					test.log(LogStatus.INFO,"Exception occured "+e.toString().substring(0, 250));
					String screenshotPath = getScreenhot(driver, "Exception");
									test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
					test.log(LogStatus.INFO, "Patial Payment process is initiated again due to Application sync issue");
					driver.get(prop.getProperty("login_page")); 
					continue;


				}

	}
		if(i==3)
		{
			test.log(LogStatus.FAIL, " Partail Payment setup is failed");
			Assert.assertTrue(false);
	
		}
	}	
	public static void paymentThrice(String SSN,String AppURL) throws Exception
	{
		int i;
		for(i=0;i<3;i++)
		{
		
	try{
			int lastrow=TestData.getLastRow("PaymentTwice");
			String sheetName="PaymentTwice";

			for(int row=2;row<=lastrow;row++)
			{		
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				if(SSN.equals(RegSSN))
				{
					String TxnType = TestData.getCellData(sheetName,"TxnType",row);
					String TenderType = TestData.getCellData(sheetName,"TenderType",row);
					String PaymentAmount = TestData.getCellData(sheetName,"PaymentAmount",row);
					String PIN = TestData.getCellData(sheetName,"Password",row);
					String TenderAmount = TestData.getCellData(sheetName,"TenderAmount",row);	
					String PaymentType = TestData.getCellData(sheetName,"PaymentType",row);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);

					Thread.sleep(3000);
					test.log(LogStatus.INFO,"PartialPayment started");
					driver.switchTo().frame("topFrame");
					driver.findElement(locator(prop.getProperty("transactions_tab"))).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");

					driver.findElement(By.cssSelector("li[id='911101']")).click();
					test.log(LogStatus.PASS, "Clicked on Transaction");		
					driver.switchTo().frame("main");	
					Thread.sleep(500);
					driver.findElement(By.name("ssn1")).sendKeys(SSN1);
					test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
					driver.findElement(locator(prop.getProperty("CSR_SSN_second_field"))).sendKeys(SSN2);
					test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
					driver.findElement(locator(prop.getProperty("CSR_SSN_third_field"))).sendKeys(SSN3);
					test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
					driver.findElement(locator(prop.getProperty("csr_new_loan_submit_button"))).click();
					test.log(LogStatus.PASS, "Clicked on submit Button");		
								
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");					    					   					     
					driver.findElement(locator(prop.getProperty("csr_new_loan_go_button"))).click();
					test.log(LogStatus.PASS, "Clicked on GO Button under search results");
					Thread.sleep(5000);					  
						
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					test.log(LogStatus.PASS, "Clicked on GO Button Under Product web table");
					Thread.sleep(5000);
					driver.findElement(By.name("transactionList")).sendKeys(TxnType);
					test.log(LogStatus.PASS, "Transaction Type is selected as :" +TxnType);
					driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Clicked on Go button ");
					Thread.sleep(500);	
					
					if(PaymentType.equalsIgnoreCase("Pay any other Amt")){
					
					driver.findElement(By.id("PD5")).click();
					test.log(LogStatus.PASS, "Clicked on pay other amount check box ");
					
					driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys(PaymentAmount);
					test.log(LogStatus.PASS, "PaymentAmount entered :"+PaymentAmount);
					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is :"+TenderType);
					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TenderAmount);
					test.log(LogStatus.PASS, "TenderAmount is :"+TenderAmount);	
					}
					else if(PaymentType.equalsIgnoreCase("Pay Off the balance")){
						driver.findElement(By.id("PD3")).click();
						test.log(LogStatus.PASS, "Clicked on Pay Off the balance check box ");
						
						
						driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
						test.log(LogStatus.PASS, "Tender Type is :"+TenderType);
						String TAmoount=driver.findElement(By.name("payOff")).getAttribute("value");
						driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TAmoount.trim());
						test.log(LogStatus.PASS, "TenderAmount is :"+TAmoount);	
					}
					else if(PaymentType.equalsIgnoreCase("Pay Installment Amt")){
						driver.findElement(By.id("PD4")).click();
						test.log(LogStatus.PASS, "Clicked on Pay Installment Amt check box ");
						TenderAmount=driver.findElement(By.name("instAmt")).getAttribute("value");
						
						driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
						test.log(LogStatus.PASS, "Tender Type is :"+TenderType);
						driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TenderAmount.trim());
						test.log(LogStatus.PASS, "TenderAmount is :"+TenderAmount);	
					}
					driver.findElement(By.name("requestBean.password")).sendKeys(PIN);
					test.log(LogStatus.PASS, "Pin is entered as "+ PIN);
					driver.findElement(By.name("finish")).click();
					test.log(LogStatus.PASS, "Clicked on Finish Partial Payment");

					Thread.sleep(5000);
					try { 
						Alert alert = driver.switchTo().alert();

						alert.accept();
						test.log(LogStatus.PASS, "Alert Handled successfully");
																				
					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}
					Thread.sleep(4000);
					//if(driver.findElement(By.name("Ok")).isDisplayed())
					{
					test.log(LogStatus.PASS, "Payment transaction is successfully");
					}
					test.log(LogStatus.PASS,"****************************************");
					break;
					
				}
					}
		
break;

}

				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//test.log(LogStatus.FAIL, MarkupHelper.createLabel("Getting Encryption from Admin is failed", ExtentColor.RED));
					test.log(LogStatus.INFO,"Exception occured "+e.toString().substring(0, 250));
					String screenshotPath = getScreenhot(driver, "Exception");
									test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
					test.log(LogStatus.INFO, "Patial Payment process is initiated again due to Application sync issue");
					driver.get(prop.getProperty("login_page")); 
					continue;


				}

	}
		if(i==3)
		{
			test.log(LogStatus.FAIL, " Partail Payment setup is failed");
			Assert.assertTrue(false);
	
		}
	}
	public static void payment4(String SSN,String AppURL) throws Exception
	{
		int i;
		for(i=0;i<3;i++)
		{
		
	try{
			int lastrow=TestData.getLastRow("PaymentTwice");
			String sheetName="Payment4";

			for(int row=2;row<=lastrow;row++)
			{		
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				if(SSN.equals(RegSSN))
				{
					String TxnType = TestData.getCellData(sheetName,"TxnType",row);
					String TenderType = TestData.getCellData(sheetName,"TenderType",row);
					String PaymentAmount = TestData.getCellData(sheetName,"PaymentAmount",row);
					String PIN = TestData.getCellData(sheetName,"Password",row);
					String TenderAmount = TestData.getCellData(sheetName,"TenderAmount",row);	
					String PaymentType = TestData.getCellData(sheetName,"PaymentType",row);
					String SSN1 = SSN.substring(0, 3);
					String SSN2 = SSN.substring(3,5);
					String SSN3 = SSN.substring(5,9);

					Thread.sleep(3000);
					test.log(LogStatus.INFO,"PartialPayment started");
					driver.switchTo().frame("topFrame");
					driver.findElement(locator(prop.getProperty("transactions_tab"))).click();			
					test.log(LogStatus.PASS, "Clicked on Loan Transactions");

					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");

					driver.findElement(By.cssSelector("li[id='911101']")).click();
					test.log(LogStatus.PASS, "Clicked on Transaction");		
					driver.switchTo().frame("main");	
					Thread.sleep(500);
					driver.findElement(By.name("ssn1")).sendKeys(SSN1);
					test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
					driver.findElement(locator(prop.getProperty("CSR_SSN_second_field"))).sendKeys(SSN2);
					test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
					driver.findElement(locator(prop.getProperty("CSR_SSN_third_field"))).sendKeys(SSN3);
					test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
					driver.findElement(locator(prop.getProperty("csr_new_loan_submit_button"))).click();
					test.log(LogStatus.PASS, "Clicked on submit Button");		
								
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");					    					   					     
					driver.findElement(locator(prop.getProperty("csr_new_loan_go_button"))).click();
					test.log(LogStatus.PASS, "Clicked on GO Button under search results");
					Thread.sleep(5000);					  
						
					driver.switchTo().defaultContent();
					driver.switchTo().frame("mainFrame");
					driver.switchTo().frame("main");

					driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
					test.log(LogStatus.PASS, "Clicked on GO Button Under Product web table");
					Thread.sleep(5000);
					driver.findElement(By.name("transactionList")).sendKeys(TxnType);
					test.log(LogStatus.PASS, "Transaction Type is selected as :" +TxnType);
					driver.findElement(By.name("button")).click();
					test.log(LogStatus.PASS, "Clicked on Go button ");
					Thread.sleep(500);	
					
					if(PaymentType.equalsIgnoreCase("Pay any other Amt")){
					
					driver.findElement(By.id("PD5")).click();
					test.log(LogStatus.PASS, "Clicked on pay other amount check box ");
					
					driver.findElement(By.name("requestBean.siilBean.payAmt")).sendKeys(PaymentAmount);
					test.log(LogStatus.PASS, "PaymentAmount entered :"+PaymentAmount);
					driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
					test.log(LogStatus.PASS, "Tender Type is :"+TenderType);
					driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TenderAmount);
					test.log(LogStatus.PASS, "TenderAmount is :"+TenderAmount);	
					}
					else if(PaymentType.equalsIgnoreCase("Pay Off the balance")){
						driver.findElement(By.id("PD3")).click();
						test.log(LogStatus.PASS, "Clicked on Pay Off the balance check box ");
						
						
						driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
						test.log(LogStatus.PASS, "Tender Type is :"+TenderType);
						String TAmoount=driver.findElement(By.name("payOff")).getAttribute("value");
						driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TAmoount.trim());
						test.log(LogStatus.PASS, "TenderAmount is :"+TAmoount);	
					}
					else if(PaymentType.equalsIgnoreCase("Pay Installment Amt")){
						driver.findElement(By.id("PD4")).click();
						test.log(LogStatus.PASS, "Clicked on Pay Installment Amt check box ");
						TenderAmount=driver.findElement(By.name("instAmt")).getAttribute("value");
						
						driver.findElement(By.name("requestBean.siilBean.tenderTypeFirst")).sendKeys(TenderType);
						test.log(LogStatus.PASS, "Tender Type is :"+TenderType);
						driver.findElement(By.name("requestBean.siilBean.tenderAmtFirst")).sendKeys(TenderAmount.trim());
						test.log(LogStatus.PASS, "TenderAmount is :"+TenderAmount);	
					}
					driver.findElement(By.name("requestBean.password")).sendKeys(PIN);
					test.log(LogStatus.PASS, "Pin is entered as "+ PIN);
					driver.findElement(By.name("finish")).click();
					test.log(LogStatus.PASS, "Clicked on Finish Partial Payment");

					Thread.sleep(5000);
					try { 
						Alert alert = driver.switchTo().alert();

						alert.accept();
						test.log(LogStatus.PASS, "Alert Handled successfully");
																				
					}
					catch (NoAlertPresentException e) {
						//do what you normally would if you didn't have the alert.
					}
					Thread.sleep(4000);
					//if(driver.findElement(By.name("Ok")).isDisplayed())
					{
					test.log(LogStatus.PASS, "Payment transaction is successfully");
					}
					test.log(LogStatus.PASS,"****************************************");
					break;
					
				}
					}
		
break;

}

				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//test.log(LogStatus.FAIL, MarkupHelper.createLabel("Getting Encryption from Admin is failed", ExtentColor.RED));
					test.log(LogStatus.INFO,"Exception occured "+e.toString().substring(0, 250));
					String screenshotPath = getScreenhot(driver, "Exception");
									test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
					test.log(LogStatus.INFO, "Patial Payment process is initiated again due to Application sync issue");
					driver.get(prop.getProperty("login_page")); 
					continue;


				}

	}
		if(i==3)
		{
			test.log(LogStatus.FAIL, " Partail Payment setup is failed");
			Assert.assertTrue(false);
	
		}
	}
	
}
