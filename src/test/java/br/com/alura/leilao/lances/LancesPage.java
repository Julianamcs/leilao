package br.com.alura.leilao.lances;

import org.openqa.selenium.WebDriver;

import br.com.alura.leilao.PageObject;

public class LancesPage extends PageObject{

	private static final String URL_LANCES = "http://localhost:8080/leilao/2";
	
	
	public LancesPage() {
		// TODO Auto-generated constructor stub
		super(null);
	}

	public boolean isPaginaAtual() {
		return browser.getCurrentUrl().contains(URL_LANCES);
	}

	public boolean isTituloLeilaoVisivel() {
		return browser.getPageSource().contains("Dados do Leilão");
	}

	public void fechar() {
		this.browser.quit();
	}

}
