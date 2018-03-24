package sdi.entrega1.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Friends_List extends PO_View{

	public static boolean isInRequest(WebDriver driver, String id) {
		WebElement request = driver.findElement(By.id(id));
		return request !=null;
	}
	
	
	
}
