package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class loginTeste {
	
	private WebDriver browser;
	private static final String URL_LOGIN = "http://localhost:8080/login";
	
	@BeforeAll
	public static void beforeAll() {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
	}
	
	@BeforeEach
	public void beforeEach() {
		// TODO Auto-generated method stub
		this.browser = new ChromeDriver();
		browser.navigate().to(URL_LOGIN);
	}
	
	@AfterEach
	public void afterEach() {
		// TODO Auto-generated method stub
		//this.browser.quit();
	}
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		//recuperar pela tag form
		browser.findElement(By.id("login-form")).submit();
		
		Assert.assertFalse(browser.getCurrentUrl().equals("http://localhost:8080/login"));
		Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
		
	}
	
	
	@Test
	public void naoDeverialogarComdadosInvalidos() {
		// TODO Auto-generated method stub
		browser.findElement(By.id("username")).sendKeys("beltrano");
		browser.findElement(By.id("password")).sendKeys("1234");
		//recuperar pela tag form
		browser.findElement(By.id("login-form")).submit();
		
		Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
		Assert.assertTrue("fulano", browser.getPageSource().contains("Usuário e senha inválidos."));
		Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
		
	}

	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
	    this.browser.navigate().to("http://localhost:8080/leiloes/2");
	    Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login"));
	    Assert.assertFalse(browser.getPageSource().contains("Dados do Leilão"));
	}
}
