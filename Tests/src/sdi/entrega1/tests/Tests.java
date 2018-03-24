package sdi.entrega1.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import sdi.entrega1.tests.pageobjects.PO_HomeView;
import sdi.entrega1.tests.pageobjects.PO_LoginView;
import sdi.entrega1.tests.pageobjects.PO_NavView;
import sdi.entrega1.tests.pageobjects.PO_RegisterView;
import sdi.entrega1.tests.pageobjects.PO_View;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {
	static String PathFirefox = ".\\Firefox46.win\\FirefoxPortable.exe";
	static WebDriver driver = getDriver(PathFirefox);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox) {
		// Firefo x (Versión 46.0) sin geckodriver para Selenium 2.x.
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() throws Exception {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la ultima prueba
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	@Test
	public void PR11_RegVal() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "josefo@gmail.com", "Josefo", "Perez", "77777", "77777");
		PO_View.checkElement(driver, "text", "Esta es una zona privada de la web");
	}

	@Test
	public void PR11_RegInval() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "josefo@gmail.com", "Josefo", "Perez", "77777", "88888");
		PO_View.checkElement(driver, "text", "Regístrate");
	}

	@Test
	public void PR21_InVal() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		PO_View.checkElement(driver, "text",
				"A continuación se muestran todos los usuarios registrados en la aplicación");
	}

	@Test
	public void PR22_InInVal() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456789");
		PO_View.checkElement(driver, "text", "Las credenciales de inicio de sesión no son correctas.");
	}
	
	@Test
	public void PR31_LisUsrVal() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		PO_HomeView.clickOption(driver, "home", "class", "navbar navbar-inverse");
		PO_NavView.clickDropdown(driver, "users-menu");
		PO_HomeView.clickOption(driver, "users/list", "class", "navbar-form");
	}
	
	@Test
	public void PR32_ListUsrInval() {
		driver.navigate().to(URL+"/users/list");
		PO_View.checkElement(driver, "text", "Identifícate como usuario");
	}
	
	@Test
	public void PR41_BusUsrVal() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		PO_HomeView.clickOption(driver, "home", "class", "navbar navbar-inverse");
		PO_NavView.clickDropdown(driver, "users-menu");
		PO_HomeView.clickOption(driver, "users/list", "class", "navbar-form");
		
	}

}
