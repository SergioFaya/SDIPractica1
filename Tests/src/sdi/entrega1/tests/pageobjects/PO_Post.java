package sdi.entrega1.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Post  extends PO_View{
	
	static public void fillForm(WebDriver driver, String title, String message) {
		WebElement titl = driver.findElement(By.name("title"));
		titl.click();
		titl.clear();
		titl.sendKeys(title);
		WebElement mssg = driver.findElement(By.name("message"));
		mssg.click();
		mssg.clear();
		mssg.sendKeys(message);
		// Click en el boton
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	public static boolean isInListOfPosts(WebDriver driver, String id) {
		WebElement request = driver.findElement(By.id(id));
		return request !=null;
	}
	
	public static void fillForm(WebDriver driver, String title, String message,String photopath) throws InterruptedException {
		WebElement titl = driver.findElement(By.name("title"));
		titl.click();
		titl.clear();
		titl.sendKeys(title);
		WebElement mssg = driver.findElement(By.name("message"));
		mssg.click();
		mssg.clear();
		mssg.sendKeys(message);
		WebElement path = driver.findElement(By.name("photoPath"));
		path.click();
		path.sendKeys(photopath);
		// Click en el boton
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	
}
