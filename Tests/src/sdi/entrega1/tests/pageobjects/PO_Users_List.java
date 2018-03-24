package sdi.entrega1.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Users_List extends PO_View{
	
	public static void searchUsers(WebDriver driver, String searchText) {
		WebElement searchField = driver.findElement(By.name("searchText"));
		searchField.click();
		searchField.sendKeys(searchText);
		WebElement buttonSearch = driver.findElement(By.id("btnSearch"));
		buttonSearch.click();
	}
	
	public static boolean checkMartaYMaria(WebDriver driver) {
		WebElement maria = driver.findElement(By.id("row3"));
		WebElement marta = driver.findElement(By.id("row4"));
		return maria != null && marta != null;
	}
	
	public static void clickAccion(WebDriver driver) {
		WebElement btnAccion = driver.findElement(By.id("btn4"));
		btnAccion.click();
	}
	
}
