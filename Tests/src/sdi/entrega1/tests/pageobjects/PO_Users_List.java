package sdi.entrega1.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Users_List extends PO_View{
	
	public static void searchUsers(WebDriver driver, String searchText) {
		WebElement dropdownMenu = driver.findElement(By.name("searchText"));
		dropdownMenu.click();
	}
	
}
