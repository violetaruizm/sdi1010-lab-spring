package com.uniovi.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LogInView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

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

	// PR02. OPción de navegación. Pinchar en el enlace Registro en la página
	// home
	@Test
	public void PR02() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
	}

	// PR03. OPción de navegación. Pinchar en el enlace Identificate en la
	// página
	// home
	@Test
	public void PR03() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	}

	// PR04. OPción de navegación. Cambio de idioma de Español a Ingles y vuelta
	// a
	// Español
	@Test
	public void PR04() {
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish",
				PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		// SeleniumUtils.esperarSegundos(driver, 2);
	}

	// PR05. Prueba del formulario de registro. registro con datos correctos
	@Test
	public void PR05() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "77777778A", "josefo", "perez",
				"77777777", "77777777");

		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	// PR06. Pruebadelformularioderegistro. DNI repetidoenlaBD, Nombrecorto,
	// .... pagination pagination-­‐centered, Error.signup.dni.length
	@Test
	public void PR06() {
		// Vamosalformularioderegistro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamosel formulario.
		PO_RegisterView.fillForm(driver, "99999990A", "Josefo", "Perez",
				"77777", "77777");
		PO_View.getP();
		// COmprobamos el error deDNI repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.dni.duplicate",
				PO_Properties.getSPANISH());
		// Rellenamosel formulario.
		PO_RegisterView.fillForm(driver, "99999990B", "Jose", "Perez", "77777",
				"77777");
		// COmprobamos el error deNombrecorto.
		PO_RegisterView.checkKey(driver, "Error.signup.name.length",
				PO_Properties.getSPANISH());
		// Rellenamosel formulario.
		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Per", "77777",
				"77777");
	}

	// PR07:Identificación válida con usuario de ROL usuario
	@Test
	public void PR07() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		PO_LogInView.fillForm(driver, "99999990A", "123456");
		PO_View.checkElement(driver, "text", "Notas del usuario");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// PR08: Identificación válida con usuario de ROL profesor
	@Test
	public void PR08() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LogInView.fillForm(driver, "99999993D", "123456");
		PO_View.checkElement(driver, "text", "Notas del usuario");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// PR09: Identificación válida con usuario de ROL Administrado
	@Test
	public void PR09() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		PO_LogInView.fillForm(driver, "99999988F", "123456");
		PO_View.checkElement(driver, "text", "Notas del usuario");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// PR10: Identificación inválida con usuario de ROL alumno
	@Test
	public void PR10() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		PO_LogInView.fillForm(driver, "99999990A", "1234");
		PO_View.checkElement(driver, "text", "Identifícate");

	}

	// PR11:Identificación válida y desconexión con usuario de ROL usuario(
	// 99999990A/123456).
	@Test
	public void PR11() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		PO_LogInView.fillForm(driver, "99999990A", "123456");
		PO_View.checkElement(driver, "text", "Notas del usuario");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	// PR12. Loguearse, comprobar que se visualizan
	// 4 filas de notas y desconectarse usando el rol de estudiante.
	@Test
	public void PR12() {
		// Vamos al formulario delogueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamosel formulario
		PO_LogInView.fillForm(driver, "99999990A", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
		// Contamos el número de filas de notas
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 4);
		// Ahora nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

	// PR13. Loguearse como estudiante y ver los detalles de la nota co
	// nDescripcion= NotaA2.
	// P13. Ver la lista de Notas.
	@Test
	public void PR13() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LogInView.fillForm(driver, "99999990A", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
		SeleniumUtils.esperarSegundos(driver, 1);
		// Contamos las notas
		By enlace = By.xpath(
				"//td[contains(text(),  'Nota A2')]/following-sibling::*[2]");
		driver.findElement(enlace).click();
		SeleniumUtils.esperarSegundos(driver, 1);
		// Esperamos por la ventana de detalle
		PO_View.checkElement(driver, "text", "Detalles de la nota");
		SeleniumUtils.esperarSegundos(driver, 1);
		// Ahoranosdesconectamos
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

	// P14. Loguearse como profesor y Agregar Nota A2.
	// P14. Esta prueb apodría encapsularse mejor...
	@Test
	public void PR14() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LogInView.fillForm(driver, "99999993D", "123456");
		// COmprobamos que entramos en la pagina privada del Profesor
		PO_View.checkElement(driver, "text", "99999993D");
		// Pinchamos en la opción de menu de Notas:
		// li[contains(@id, 'marks-­‐menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,  'marks-menu')]/a");
		elementos.get(0).click();
		// Esperamosa aparezcalaopcióndeañadirnota:
		// a[contains(@href, 'mark/add')]
		elementos = PO_View.checkElement(driver, "free",
				"//a[contains(@href,  'mark/add')]");
		// PinchamosenagregarNota.
		elementos.get(0).click();
		// Ahoravamosa rellenarlanota.
		// option[contains(@value, '4')]
		PO_PrivateView.fillFormAddMark(driver, 3, "Nota Nueva 1", "8");
		// Esperamosa quesemuestrenlosenlacesdepaginaciónlalistadenotas
		elementos = PO_View.checkElement(driver, "free",
				"//a[contains(@class,  'page-link')]");
		// Nosvamosa laúltimapágina
		elementos.get(3).click();
		// Comprobamosqueaparecelanotaenlapagina
		elementos = PO_View.checkElement(driver, "text", "Nota Nueva 1");
		// Ahoranosdesconectamos
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

	// PRN. Loguearsecomoprofesor, vamosa laultimapáginay
	// EliminamoslaNotaNueva1.
	// PRN. VerlalistadeNotas.
	@Test
	public void PR15() {
		// Vamosalformulariodelogueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamosel formulario
		PO_LogInView.fillForm(driver, "99999993D", "123456");
		// COmprobamos queentramosenlapaginaprivadadelProfesor
		PO_View.checkElement(driver, "text", "99999993D");
		// Pinchamosenlaopcióndemenu deNotas:
		// li[contains(@id, 'marks-­‐menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,  'marks-menu')]/a");
		elementos.get(0).click();
		// Pinchamosenlaopcióndelistadenotas.
		elementos = PO_View.checkElement(driver, "free",
				"//a[contains(@href,'mark/list')]");
		elementos.get(0).click();
		// Esperamosa quesemuestrenlosenlacesdepaginacionlalistadenotas
		elementos = PO_View.checkElement(driver, "free",
				"//a[contains(@class,'page-link')]");
		// Nosvamosa laúltimapágina
		elementos.get(3).click();
		// Esperamosa queaparezcalaNuevanotaenlaultimapagina
		// Y Pinchamosenel enlacedeborradodelaNota"NotaNueva1"
		// td[contains(text(),
		// 'NotaNueva1')]/following-­‐sibling::*/a[contains(text(),
		// 'mark/delete')]"
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(),  'Nota Nueva 1')]/following-sibling::*/a[contains(@href,  'mark/delete')]");
		elementos.get(0).click();
		// Volvemosa laúltimapagina
		elementos = PO_View.checkElement(driver, "free",
				"//a[contains(@class,'page-link')]");
		elementos.get(3).click();
		// Y esperamosa queNO aparezcalaultima"NuevaNota1"
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Nota Nueva 1",
				PO_View.getTimeout());
		// Ahoranosdesconectamos
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}
}
