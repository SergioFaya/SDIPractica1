package sdi.entrega1.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Users_List extends PO_View {

	public static void searchUsers(WebDriver driver, String searchText) {
		WebElement searchField = driver.findElement(By.name("searchText"));
		searchField.click();
		searchField.sendKeys(searchText);
		WebElement buttonSearch = driver.findElement(By.id("btnSearch"));
		buttonSearch.click();
	}

	public static boolean checkMartaYMaria(WebDriver driver) {
		try {
			//WebElement maria = driver.findElement(By.id("row3"));
			//WebElement marta = driver.findElement(By.id("row4"));
			driver.findElement(By.id("row3"));
			driver.findElement(By.id("row4"));
		}catch(Exception e) {
			return false;
		}
		return true;
		//return maria != null && marta != null;
	}
	
	public static boolean checkPelayoHaSidoBorrado(WebDriver driver) {
		try {
			driver.findElement(By.id("row5"));
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Si el usuario es normal, se hace click al botón desplegable de acciones. Si
	 * el usuario es administrador, ese mismo botón se utilizará para borrar un
	 * usuario
	 * 
	 * @param driver
	 * @param btnId
	 */
	public static void clickAccionOBorrarSiAdmin(WebDriver driver, String btnId) {
		WebElement btnAccion = driver.findElement(By.id(btnId));
		btnAccion.click();
	}

}
