package com.uniovi.tests.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;
public class PO_PrivateView  extends PO_NavView{
	
	
static public void fillFormAddMark(WebDriver  driver,  int userOrder,  String  descriptionp,  String  scorep){
		//Esperamos  5  segundo  a  que  carge  el  DOM  porque  enalgunos  equipos  falla
		SeleniumUtils.esperarSegundos(driver,  5);
		//Seleccionamos el  alumnosuserOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		//Rellenemos el campo dedescripción
		WebElement  description=  driver.findElement(By.name("description"));
		description.clear();description.sendKeys(descriptionp);
		WebElement  score=  driver.findElement(By.name("score"));
		score.click();score.clear();score.sendKeys(scorep);
		By  boton=  By.className("btn");driver.findElement(boton).click();}
		}