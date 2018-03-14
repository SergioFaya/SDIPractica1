package sdi.entrega1.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import sdi.entrega1.tests.util.SeleniumUtils;

public class PO_HomeView extends PO_NavView{
 
	static public void checkWelcome(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Espa�ol
		if(language==0) {
			SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", PO_Properties.SPANISH),
					getTimeout());
		}
		else {
			SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", PO_Properties.ENGLISH),
					getTimeout());
		}
		
	}

	static public void checkChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {
		// Esperamos a que se cargue el saludo de bienvenida en Espa�ol
		PO_HomeView.checkWelcome(driver, locale1);
		// Cambiamos a segundo idioma
		PO_HomeView.changeIdiom(driver, textIdiom2);
		// COmprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_HomeView.checkWelcome(driver, locale2);
		// Volvemos a Espa�ol.
		PO_HomeView.changeIdiom(driver, textIdiom1);
		// Esperamos a que se cargue el saludo de bienvenida en Espa�ol
		PO_HomeView.checkWelcome(driver, locale1);
	}

}
