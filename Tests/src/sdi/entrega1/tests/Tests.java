package sdi.entrega1.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import sdi.entrega1.tests.pageobjects.PO_AdminLoginView;
import sdi.entrega1.tests.pageobjects.PO_Friends_List;
import sdi.entrega1.tests.pageobjects.PO_HomeView;
import sdi.entrega1.tests.pageobjects.PO_LoginView;
import sdi.entrega1.tests.pageobjects.PO_NavView;
import sdi.entrega1.tests.pageobjects.PO_Post;
import sdi.entrega1.tests.pageobjects.PO_RegisterView;
import sdi.entrega1.tests.pageobjects.PO_Requests_List;
import sdi.entrega1.tests.pageobjects.PO_Users_List;
import sdi.entrega1.tests.pageobjects.PO_View;
import sdi.entrega1.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {
	static String PathFirefox = ".\\Firefox46.win\\FirefoxPortable.exe";
	static String PathPhoto = ".\\img\\gato.png";
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
	public void PR12_RegInval() {
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
		PO_View.checkElement(driver, "text",
				"A continuación se muestran todos los usuarios registrados en la aplicación");
	}

	@Test
	public void PR32_ListUsrInval() {
		driver.navigate().to(URL + "/users/list");
		PO_View.checkElement(driver, "text", "Identifícate como usuario");
	}

	@Test
	public void PR41_BusUsrVal() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		PO_HomeView.clickOption(driver, "home", "class", "navbar navbar-inverse");
		PO_NavView.clickDropdown(driver, "users-menu");
		PO_HomeView.clickOption(driver, "users/list", "class", "navbar-form");
		PO_Users_List.searchUsers(driver, "mar");
		assertTrue(PO_Users_List.checkMartaYMaria(driver));
	}

	@Test
	public void PR51_InvVal() throws Exception {
		driver.navigate().to(URL + "/login");
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		PO_Users_List.clickAccionOBorrarSiAdmin(driver, "btn4");
		PO_HomeView.clickOption(driver, "friends/send/request/4", "class", "alert alert-success");
	}

	@Test
	public void PR52_InvInVal() throws Exception {
		driver.navigate().to(URL + "/login");
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		// Envia a marta
		PO_Users_List.clickAccionOBorrarSiAdmin(driver, "btn4");
		PO_HomeView.clickOption(driver, "friends/send/request/4", "class", "alert alert-danger");
	}

	@Test
	public void PR61_ListIvnVal() throws Exception {
		driver.navigate().to(URL + "/login");
		PO_LoginView.fillForm(driver, "marta@gmail.com", "123456");
		driver.navigate().to(URL + "/friends/requests/list");
		assertTrue(PO_Requests_List.isInRequest(driver, "id-pedro@gmail.com"));
	}

	@Test
	public void PR71_AcepIvnVal() throws Exception {
		driver.navigate().to(URL + "/login");
		PO_LoginView.fillForm(driver, "marta@gmail.com", "123456");
		driver.navigate().to(URL + "/friends/requests/list");
		PO_HomeView.clickOption(driver, "friends/accept/request/7", "id", "id-pedro@gmail.com");
		driver.navigate().to(URL + "/friends/requests/list");
	}

	@Test
	public void PR81_ListAmiVal() throws Exception {
		driver.navigate().to(URL + "/login");
		// lucas tiene amigos precargados
		PO_LoginView.fillForm(driver, "lucas@gmail.com", "123456");
		driver.navigate().to(URL + "/friends/list");
		assertTrue(PO_Friends_List.isInRequest(driver, "id-maria@gmail.com"));
	}

	@Test
	public void PR91_PubVal() throws Exception {
		driver.navigate().to(URL + "/login");
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		driver.navigate().to(URL + "/post/add");
		PO_Post.fillForm(driver, "TestPR91", "HELLO DUMMY!");
	}

	@Test
	public void Pr101_LisPubVal() throws Exception {
		PR91_PubVal();
		driver.navigate().to(URL + "/post/list");
		assertTrue(PO_Post.isInListOfPosts(driver, "TestPR91"));
	}

	@Test
	public void Pr111_LisPubAmiVal() throws Exception {
		driver.navigate().to(URL + "/login");
		PO_LoginView.fillForm(driver, "lucas@gmail.com", "123456");
		driver.navigate().to(URL + "/friends/list");
		PO_HomeView.clickOption(driver, "post/list/3", "id", "maria@gmail.com");
	}

	@Test
	public void Pr112_LisPubAmiVal() throws Exception {
		// usamos a pedro porque no tiene amigos
		driver.navigate().to(URL + "/login");
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		driver.navigate().to(URL + "/post/list/3");
		String URL = driver.getCurrentUrl();
		assertEquals(URL, "http://localhost:8090/error");
	}

	@Test
	public void PR121_PubFot1Val() throws Exception {
		driver.navigate().to(URL + "/login");
		PO_LoginView.fillForm(driver, "pedro@gmail.com", "123456");
		driver.navigate().to(URL + "/post/add");
		PO_Post.fillForm(driver, "TestPR121", "HELLO DUMMY!", "C:\\Users\\Manuel\\Documents\\GitHub\\SDI\\SDIPractica1\\Tests\\img\\gato.png");
	}

	@Test
	public void PR121_PubFot2Val() throws Exception {
		driver.navigate().to(URL + "/login");
		PO_LoginView.fillForm(driver, "maria@gmail.com", "123456");
		driver.navigate().to(URL + "/post/add");
		PO_Post.fillForm(driver, "TestPR122", "HELLO DUMMY!");
	}

	@Test
	public void PR131_AdInVal() {
		driver.navigate().to(URL + "/admin/adminLogin");
		PO_View.checkElement(driver, "text", "Identifícate como administrador");
		PO_AdminLoginView.fillFormAdmin(driver, "edward@gmail.com", "123456");
		PO_View.checkElement(driver, "text",
				"A continuación se muestran todos los usuarios registrados en la aplicación");
	}

	@Test
	public void PR132_AdInInVal() {
		driver.navigate().to(URL + "/admin/adminLogin");
		PO_View.checkElement(driver, "text", "Identifícate como administrador");
		PO_AdminLoginView.fillFormAdmin(driver, "pedro@gmail.com", "123456");
		PO_View.checkElement(driver, "text",
				"Las credenciales de inicio de sesión como administrador no son correctas.");
	}

	@Test
	public void PR141_AdLisUsrVal() {
		driver.navigate().to(URL + "/admin/adminLogin");
		PO_View.checkElement(driver, "text", "Identifícate como administrador");
		PO_AdminLoginView.fillFormAdmin(driver, "edward@gmail.com", "123456");
		PO_View.checkElement(driver, "text",
				"A continuación se muestran todos los usuarios registrados en la aplicación");
	}

	@Test
	public void PR151_AdBorUsrVal() {
		driver.navigate().to(URL + "/admin/adminLogin");
		PO_View.checkElement(driver, "text", "Identifícate como administrador");
		PO_AdminLoginView.fillFormAdmin(driver, "edward@gmail.com", "123456");
		PO_View.checkElement(driver, "text",
				"A continuación se muestran todos los usuarios registrados en la aplicación");
		SeleniumUtils.esperarSegundos(driver, 2);
		PO_Users_List.clickAccionOBorrarSiAdmin(driver, "btn5");
		// Nos aseguramos que se han borrado
		assertFalse(PO_Users_List.checkPelayoHaSidoBorrado(driver));
	}

	@Test
	public void PR152_AdBorUsrInVal() {
		// accedemos a la url para borrar al usuario pedro
		driver.navigate().to(URL + "/admin/list/eliminar/1");
		// Comprobamos que nos redirige a la failureURL de spring security (login)
		PO_View.checkElement(driver, "text", "Identifícate como usuario");
	}

}
