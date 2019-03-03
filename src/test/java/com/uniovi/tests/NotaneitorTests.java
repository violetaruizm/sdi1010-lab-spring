package com.uniovi.tests;

import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LogInView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorTests {

	// EnWindows (Debe ser la versión 65.0.1 y
	// desactivar las actualizacioens automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver022 = "C:\\Users\\Violeta\\Desktop\\SDI sesion 5 material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	// En MACOSX (Debe ser la versión 65.0.1y desactivar las actualizacioens
	// automáticas):
	// staticString
	// PathFirefox65="/Applications/Firefox.app/Contents/MacOS/firefox-­‐bin";
	// staticString Geckdriver024= "/Users/delacal/selenium/geckodriver024mac";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver022);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() throws Exception {

		driver.navigate().to(URL);
	}

	@After
	public void tearDown() throws Exception {

		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	public static void begin() {
	}

	@AfterClass
	public static void end() {
		driver.quit();
	}

	// PR01. Acceder a la página principal /
	@Test
	public void PR01() {
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
	}

	// PR02. OPción de navegación. Pinchar en el enlace Registro en la página home
	@Test
	public void PR02() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
	}

	// PR03. OPción de navegación. Pinchar en el enlace Identificate en la página
	// home
	@Test
	public void PR03() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	}

	// PR04. OPción de navegación. Cambio de idioma de Español a Ingles y vuelta a
	// Español
	@Test
	public void PR04() {
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());
		// SeleniumUtils.esperarSegundos(driver, 2);
	}
	
	//PR05.  Prueba del formulario de registro.  registro con datos correctos
	@Test
	public void PR05() {
		//Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "77777778A", "josefo", "perez", "77777777", "77777777");
		
		//Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}
	
	//PR06.  Pruebadelformularioderegistro.  DNI  repetidoenlaBD,  Nombrecorto,  ....  pagination  pagination-­‐centered,  Error.signup.dni.length
	@Test
	public void PR06()  {
		//Vamosalformularioderegistro
		PO_HomeView.clickOption(driver,  "signup",  "class",  "btn btn-primary");
		//Rellenamosel  formulario.
		PO_RegisterView.fillForm(driver,  "99999990A",  "Josefo",  "Perez",  "77777",  "77777");
		PO_View.getP();
		//COmprobamos  el  error  deDNI  repetido.
		PO_RegisterView.checkKey(driver,  "Error.signup.dni.duplicate",  PO_Properties.getSPANISH()  );
		//Rellenamosel  formulario.
		PO_RegisterView.fillForm(driver,  "99999990B",  "Jose",  "Perez",  "77777",  "77777");
		//COmprobamos  el  error  deNombrecorto.
		PO_RegisterView.checkKey(driver,  "Error.signup.name.length",  PO_Properties.getSPANISH()  );
		//Rellenamosel  formulario.
		PO_RegisterView.fillForm(driver,  "99999990B",  "Josefo",  "Per",  "77777",  "77777");}
	
	
	
	
	//PR07:Identificación válida con usuario de ROL usuario
	@Test 
	public void PR07() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		
		PO_LogInView.fillForm(driver, "99999990A", "123456");
		PO_View.checkElement(driver, "text", "Notas del usuario");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
	//PR08: Identificación válida con usuario de ROL profesor
	@Test
	public void PR08() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LogInView.fillForm(driver, "99999993D", "123456");
		PO_View.checkElement(driver, "text", "Notas del usuario");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		
	}
	
	//PR09: Identificación válida con usuario de ROL Administrado
	@Test
	public void PR09() {
		
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		
		PO_LogInView.fillForm(driver, "99999988F", "123456");
		PO_View.checkElement(driver, "text", "Notas del usuario");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		
	}
	
	//PR10: Identificación inválida con usuario de ROL alumno
	@Test 
	public void PR10() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		
		PO_LogInView.fillForm(driver, "99999990A", "1234");
		PO_View.checkElement(driver, "text", "Identifícate");
		
	}
	
	//PR11:Identificación   válida y   desconexión   con   usuario   de   ROL   usuario( 99999990A/123456).
	@Test 
	public void PR11() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		
		PO_LogInView.fillForm(driver, "99999990A", "123456");
		PO_View.checkElement(driver, "text", "Notas del usuario");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

}





