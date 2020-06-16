package tests;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.LogStatus;

public class VQCCSRLogin2997 extends QCStore
{
	public static void login(String SSN,String AppURL) throws InterruptedException, MalformedURLException
	{
		        int lastrow=TestData.getLastRow("Login");
				String sheetName="Login";

				for(int row=2;row<=lastrow;row++)
				{		
					String RegSSN = TestData.getCellData(sheetName,"SSN",row);
					//String csr_url = TestData.getCellData(sheetName,"csr_url",row);
					String csr_url="https://qclocalreg.qfund.net/cc/demoIndex.do"; 

					//String username = TestData.getCellData(sheetName,"UserName",row);
					String username = "admin";
					//String password = TestData.getCellData(sheetName,"Password",row);
					String password = "advance";
					//String store_id = TestData.getCellData(sheetName,"StoreID",row);
					String store_id = "2997";
					


					if(SSN.equals(RegSSN))
					{	
				
						Thread.sleep(4000);
						//test.log(LogStatus.INFO, "Opened the CSR URL " +AppURL);
						//test.log(LogStatus.INFO, MarkupHelper.createLabel("CSR login has started", ExtentColor.BLUE));

						//test.log(LogStatus.INFO, MarkupHelper.createLabel("Opened the CSR URL " +AppURL, ExtentColor.BLUE));
						test.log(LogStatus.INFO, "<FONT color=green style=Calibri > Opened the CSR URL " +csr_url);



						test.log(LogStatus.INFO, "CSR Application is launched " );
						//test.log(LogStatus.INFO, MarkupHelper.createLabel("CSR Application is launched", ExtentColor.BLUE));

						if(prop.getProperty("login_method").equalsIgnoreCase("local"))
						{
							driver = new InternetExplorerDriver();
						}
						else
						{
							String Node = prop.getProperty("login_node");  // Added in jan21.1
							 //String Node2 = "http://192.168.0.238:5566/wd/hub";
							 DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
							 driver = new RemoteWebDriver(new URL(Node), cap);	
						}
						 wait = new WebDriverWait(driver, 40000);

						driver.manage().window().maximize();
						driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					
						driver.get(csr_url);
						
						
					    driver.findElement(locator(prop.getProperty("csr_username"))).sendKeys(username);
				        test.log(LogStatus.PASS, "Username is entered: "+username);
				        
				        

					    driver.findElement(locator(prop.getProperty("csr_password"))).clear();
					    driver.findElement(locator(prop.getProperty("csr_password"))).sendKeys(password);
				        test.log(LogStatus.PASS, "Password is entered: "+password);
				        
				       
				        driver.findElement(locator(prop.getProperty("csr_storeid"))).sendKeys(store_id);
				        test.log(LogStatus.PASS, "Storenumber is entered: "+store_id);
				        //Click Login Button
				        driver.findElement(locator(prop.getProperty("csr_login_button"))).click();
				        test.log(LogStatus.PASS, "Clicked on login button");
				        
				       Thread.sleep(8000);
				       break;
}
					
	}
}
public static void logout(){
  try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");

			driver.findElement(By.xpath("//*[@id='icons']/li[7]/a")).click();
			test.log(LogStatus.PASS, "Clicked On logout Button");
			if(driver.findElement(By.xpath("//*[@id='signoutArea']/div[1]")).getText().contains("Sign out")){
				test.log(LogStatus.PASS, "<FONT color=green> Logout Successfully"); 
				test.log(LogStatus.INFO, "******************************************************** ");
				driver.close();
			}
			else{
				test.log(LogStatus.PASS, "<FONT color=Red> Logout was unsuccessfull"); 
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	public static void adminLogin(WebDriver driver,String SSN,String csr_url){
					
					
		try{
			int lastrow=TestData.getLastRow("Login");
			String sheetName="Login";

			for(int row=2;row<=lastrow;row++)
			{		
				String RegSSN = TestData.getCellData(sheetName,"SSN",row);
				if(SSN.equals(RegSSN))
				{
				String UserName = TestData.getCellData(sheetName,"AdminUserName",row);
				String Password = TestData.getCellData(sheetName,"AdminPassword",row);
				//String AdminURL = TestData.getCellData(sheetName,"AdminURL",row);
				String AdminURL = "https://qclocalauto.qfund.net/cc/adminIndex.do";
				String PIN = TestData.getCellData(sheetName,"PIN",row);
				String Tran_cd = TestData.getCellData(sheetName,"Tran_cd",row);
                

				//test.log(LogStatus.INFO, MarkupHelper.createLabel("Admin Application is launched", ExtentColor.BLUE));
				test.log(LogStatus.INFO, "********Admin Application is launched********");


				driver = new InternetExplorerDriver();
				wait = new WebDriverWait(driver, 40000);

				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
			    driver.get(AdminURL);
			}
		}
	}
	catch (Exception e) {
		
		test.log(LogStatus.FAIL,"Admin login is failed");
       e.printStackTrace();
	}
}	
public static void adminLogout(WebDriver driver,String SSN,String csr_url){
		try{
			driver.switchTo().defaultContent();
			 driver.switchTo().frame("topFrame");
			
	     driver.findElement(locator(prop.getProperty(("admin_logout_link")))).click();
	     test.log(LogStatus.PASS, "Clicked On logout Button");
	     if(driver.getTitle().contains("Login")){
	    	 test.log(LogStatus.PASS, "Logout is Successfully"); 
	    	 //test.log(LogStatus.INFO, MarkupHelper.createLabel("loggged out from the Admin Application", ExtentColor.BLUE));
	    	 test.log(LogStatus.INFO, "loggged out from the Admin Application");

	     driver.close();
	     }
	    else{
	    	 test.log(LogStatus.FAIL, "Logout was unsuccessfull"); 
	     }
	 }
catch(Exception e)
{
	//test.log(LogStatus.ERROR, MarkupHelper.createLabel("CSR logout is failed", ExtentColor.RED));
	test.log(LogStatus.ERROR,"Admin logout is failed");


	e.printStackTrace();
}
}
}