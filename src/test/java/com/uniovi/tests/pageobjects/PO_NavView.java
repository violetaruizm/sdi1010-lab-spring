package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.*;
import com.uniovi.tests.util.SeleniumUtils;

public class PO_NavView extends PO_View{
	
	public static void clickOption(WebDriver driver,String textOption,String criterio,
			String textoDestino) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href", textOption, getTimeout());
		
		assertTrue(elementos.size()==1);
		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, criterio, textoDestino, getTimeout());
	assertTrue(elementos.size()==1);
	}
	
	
	public static void changeIdiom(WebDriver driver, String textLanguage) {
		
		List<WebElement>  elementos=  SeleniumUtils.EsperaCargaPagina(driver,  "id",  "btnLanguage",  getTimeout());
		elementos.get(0).click();
		elementos=  SeleniumUtils.EsperaCargaPagina(driver,  "id",  "languageDropdownMenuButton",  getTimeout());
		elementos=  SeleniumUtils.EsperaCargaPagina(driver,  "id",  textLanguage,  getTimeout());
		elementos.get(0).click();
	
	}

}
